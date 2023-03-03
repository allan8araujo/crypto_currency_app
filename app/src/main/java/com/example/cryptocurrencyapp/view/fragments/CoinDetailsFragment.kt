package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.abstraction.AssetsItem
import com.example.cryptocurrencyapp.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyapp.viewmodel.CoinDetailsViewModel
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel

class CoinDetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private val args: CoinDetailsFragmentArgs by navArgs()

    private val coinViewModel: CoinListViewModel by activityViewModels()
    private val coinFavoriteViewModel: CoinDetailsViewModel by activityViewModels()

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
//        coinViewModel.allFavoriteAssets.observe(viewLifecycleOwner) { listAssetsItems ->
//            setButtonAction(asset, listAssetsItems, binding)
//        }
    }

    fun setButtonAction(
        asset: AssetsItem,
        listAssetsItems: List<AssetsItem>,
        binding: DetailsFragmentBinding,
    ) {
        var findItemOnList: Boolean?
        coinFavoriteViewModel.settingImageIcon(asset, binding)
        with(binding) {
            iconAssetIdTextView.text = asset.asset_id
            findItemOnList = listAssetsItems.any {
                it.asset_id == asset.asset_id
            }
            if (findItemOnList == true) {
                setRemove(asset)
            } else {
                setAdd(asset)
            }
            coinFavoriteViewModel.settingPricesAndVolum(binding, asset, asset.price_usd)
        }
    }

    private fun DetailsFragmentBinding.setAdd(
        asset: AssetsItem,
    ) {
        addButton.text = "Adicionar"
        starIconImageView.visibility = View.GONE
        addButton.setOnClickListener {
//            coinViewModel.insertAsset(asset)
        }
    }

    private fun DetailsFragmentBinding.setRemove(
        asset: AssetsItem,
    ) {
        addButton.text = "Remover"
        starIconImageView.isVisible = true
        addButton.setOnClickListener {
            coinViewModel.deleteAsset(asset)
        }
    }
}
