package com.example.photoapi.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photoapi.adapter.PhotoAdapter
import com.example.photoapi.databinding.FragmentPhotoBinding
import com.example.photoapi.model.network.ApiManager
import com.example.photoapi.model.repository.PhotoRepository
import com.example.photoapi.viewmodel.PhotoViewModel

class PhotoFragment: Fragment(){
    private var _binding: FragmentPhotoBinding? = null
    private val binding: FragmentPhotoBinding get() = _binding!!
    private val viewModel: PhotoViewModel by activityViewModels {
        PhotoViewModel.Factory(PhotoRepository(ApiManager()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setText("Our Photos")

        with(binding){

            photoFragmentTv.text = viewModel.text.value

            viewModel.photos.observe(viewLifecycleOwner){photo ->
                photoRv.apply {
                    adapter = photo?.let { PhotoAdapter(it) }
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }

            backBtn.setOnClickListener {
                val directions =
                    PhotoFragmentDirections.actionPhotoFragmentToWelcomeFragment()
                findNavController().navigate(directions)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}