package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abstraction.AssetsItem
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.ErrorScreenBinding
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.states.DataResult

class ErrorScreen : Fragment() {

    private lateinit var binding: ErrorScreenBinding
    private val coinViewModel: CoinListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ErrorScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        observeResults()
        binding.errorButton.setOnClickListener {
            coinViewModel.getAllAssets()
            observeResults()
        }
        return view
    }

    private fun observeResults() {
        coinViewModel.assetsFromResultApi.observe(viewLifecycleOwner) { dataResults ->
            when (dataResults) {
                is DataResult.Loading -> {
                    binding.tryAgainProgressBar.isVisible = true
                }
                is DataResult.Success -> {
                    binding.tryAgainProgressBar.isVisible = false
                    findNavController().navigate(R.id.action_errorScreen_to_coinList)
                }
                is DataResult.Error -> {
                    bindDataError(dataResults)
                }
            }
            binding.tryAgainProgressBar.isVisible = false
        }
    }

    private fun bindDataError(dataResults: DataResult.Error<List<AssetsItem>>) {
        when (dataResults.throwable.code()) {
            400 -> binding.errorTextView.text = "Problema ao carregar a lista de moedas."
            401 -> binding.errorTextView.text =
                "Erro ao carregar lista de moedas, tente novamente mais tarde."
            403 -> binding.errorTextView.text = "Erro ao carregar lista de moedas."
            429 -> binding.errorTextView.text =
                "Erro ao carregar lista de moedas, tente novamente mais tarde."
            550 -> binding.errorTextView.text =
                "Erro ao carregar lista de moedas. Você excedeu os limite de chamadas, tente novamente mais tarde."
        }
    }
}
