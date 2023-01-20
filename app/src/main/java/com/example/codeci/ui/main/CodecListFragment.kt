package com.example.codeci.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.codeci.R
import com.example.codeci.databinding.FragmentCodecListBinding

private const val TAG = "CodecListFragment"
class CodecListFragment : Fragment() {

    private val codecsViewModel: CodecsViewModel by activityViewModels()
    private var _binding: FragmentCodecListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, codecsViewModel.teste)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodecListBinding.inflate(inflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_codec_list, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val decoders = binding.decodersRecyclerView
        val encoders = binding.encodersRecyclerView
        val lambda = {codec: String -> Log.d(TAG, "$codec selected!!!")}
        decoders.adapter = CodecCardRecyclerView(codecsViewModel.decoders) { lambda(it) }
        encoders.adapter = CodecCardRecyclerView(codecsViewModel.encoders) { lambda(it) }
    }
}