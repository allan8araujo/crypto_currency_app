package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.abstraction.AssetsItem
import com.example.cryptocurrencyapp.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinDetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private val args: CoinDetailsFragmentArgs by navArgs()

    private val coinViewModel: AssetsListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        binding.backPressButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingView(args)
    }

    private fun bindingView(args: CoinDetailsFragmentArgs) {
        val asset: AssetsItem = args.asset
        coinViewModel.allFavoriteAssets.observe(viewLifecycleOwner) { listAssetsItems ->
            coinViewModel.setButtonAction(asset, listAssetsItems, binding)
        }
    }
}
