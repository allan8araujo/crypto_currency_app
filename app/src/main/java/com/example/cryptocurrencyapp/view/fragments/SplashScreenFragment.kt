package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.SplashScreenFragmentBinding

class SplashScreenFragment : Fragment() {

    private lateinit var binding: SplashScreenFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashScreenFragmentBinding.inflate(inflater, container, false)
        setupSplashScreen()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupSplashScreen(){
        Handler().postDelayed({
            findNavController().navigate(R.id.action_to_coinList)
        }, 2500)
    }
}