package com.example.photoapi.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.photoapi.databinding.FragmentWelcomeBinding
import com.example.photoapi.model.network.ApiManager
import com.example.photoapi.model.repository.PhotoRepository
import com.example.photoapi.viewmodel.PhotoViewModel

class WelcomeFragment: Fragment(){

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding get() = _binding!!
    private val viewModel: PhotoViewModel by activityViewModels {
        PhotoViewModel.Factory(PhotoRepository(ApiManager()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setText("Welcome to our exclusive photo collection...")

        with(binding){
            welcomeFragmentTv.text = viewModel.text.value
            nextBtn.setOnClickListener {
                val directions =
                    WelcomeFragmentDirections.actionWelcomeFragmentToPhotoFragment()
                findNavController().navigate(directions)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}