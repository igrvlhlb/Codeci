package com.example.codeci.ui.main

import android.media.MediaCodecInfo
import android.media.MediaCodecList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

typealias MediaCodecInfoPair = Pair<String,MediaCodecInfo>

class CodecsViewModel : ViewModel() {
    var teste: String = "Nao inicializada"
    lateinit var codecsSet: Set<MediaCodecInfoPair>

    val decoders: List<MediaCodecInfoPair>
        get() = codecsSet.filter { !it.second.isEncoder }
    val encoders: List<MediaCodecInfoPair>
        get() = codecsSet.filter { it.second.isEncoder }

    init {
        getCodecsInfos()
    }

    private fun getCodecByName(codec: String): MediaCodecInfo {
        return codecsSet.find { it.first == codec }!!.second
    }

    private fun getCodecsInfos() {
        val allCodecs = MediaCodecList(MediaCodecList.ALL_CODECS).codecInfos
        val decoders = allCodecs.filter { !it.isEncoder }
        val encoders = allCodecs.filter { it.isEncoder }
        codecsSet = allCodecs.map { it.name to it }.toSortedSet(CodecPairComparator.INSTANCE)
    }

    private class CodecPairComparator: Comparator<MediaCodecInfoPair> {
        override fun compare(
            o1: MediaCodecInfoPair,
            o2: MediaCodecInfoPair
        ): Int {
            val typeCmp = compareType(o1.second, o2.second)
            return if (typeCmp != 0) typeCmp else o1.first.compareTo(o2.first)
        }

        private fun compareType(t1: MediaCodecInfo, t2: MediaCodecInfo): Int =
            t1.supportedTypes[0].compareTo(t2.supportedTypes[0])

        companion object {
            val INSTANCE = CodecPairComparator()
        }
    }
}