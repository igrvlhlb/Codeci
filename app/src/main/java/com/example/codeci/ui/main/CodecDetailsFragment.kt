package com.example.codeci.ui.main

import android.app.ActionBar
import android.media.MediaCodecInfo
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.codeci.MainActivity
import com.example.codeci.databinding.FragmentCodecDetailsBinding
import com.example.codeci.utils.isSoftwareCodec

private const val TAG = "CodecDetailsFragment"
class CodecDetailsFragment: Fragment() {
    private var _binding: FragmentCodecDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var codecInfo: MediaCodecInfo
    private val codecsViewModel: CodecsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* codec SHOULD NOT be null */
        val codec = arguments?.getString(CODEC)!!
        setTitle(codec)
        codecInfo = codecsViewModel.getCodecByName(codec)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCodecDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateDetails()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun populateDetails() {
        binding.apply {
            canonicalNameValue.text = ifApiLevel(Build.VERSION_CODES.Q,
                { codecInfo.canonicalName },
                { "-" }
            )
            isAliasValue.text = ifApiLevel(Build.VERSION_CODES.Q,
                { codecInfo.isAlias.toString() },
                { "-" }
            )
            isEncoderValue.text = codecInfo.isEncoder.toString()
            isHardwareAcceleratedValue.text = ifApiLevel(Build.VERSION_CODES.Q,
                { codecInfo.isHardwareAccelerated.toString() },
                { (!codecInfo.isSoftwareCodec()).toString() })
            isSoftwareOnlyValue.text = ifApiLevel(Build.VERSION_CODES.Q,
                { codecInfo.isSoftwareOnly.toString() },
                { codecInfo.isSoftwareCodec().toString() })
            isVendorValue.text = ifApiLevel(Build.VERSION_CODES.Q,
                { codecInfo.isVendor.toString() },
                { "-" })
            supportedTypesLayout.run {
                codecInfo.supportedTypes.forEach { codec ->
                    val textView = TextView(this.context).apply {
                        text = codec
                    }
                    addView(textView)
                }
            }
        }
    }

    private fun setTitle(title: String) {
        ((activity as MainActivity).supportActionBar)?.apply {
            this.title = title
            setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        val CODEC = "codec"
    }
}

fun ifApiLevel(apiLevel: Int, f: () -> String, felse: () -> String): String{
    return if (Build.VERSION.SDK_INT >= apiLevel) {
        f()
    } else {
        felse()
    }
}