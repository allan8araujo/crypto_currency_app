package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.abstraction.AssetsItem
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyapp.utils.ProgressBarListener
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingView(args)
    }

    private fun bindingView(args: CoinDetailsFragmentArgs) {
        val asset: AssetsItem = args.asset
        coinViewModel.allFavoriteAssets.observe(viewLifecycleOwner){ listAssetsItems ->
            settingImageIcon(asset)
            with(binding) {
                iconAssetIdTextView.text = asset.asset_id
                val findItemOnList = listAssetsItems?.any{
                    it.asset_id == asset.asset_id
                }
                if (findItemOnList == true) {
                    setRemove(listAssetsItems, asset)
                } else {
                    setAdd(listAssetsItems, asset)
                }
                settingPricesAndVolum(asset, asset.price_usd)
            }
        }
    }

    private fun DetailsFragmentBinding.settingPricesAndVolum(
        asset: AssetsItem,
        price_usd: Double?,
    ) {
        if (asset.price_usd != null) {
            priceUsdTextView.text = price_usd.toString()
        } else {
            priceUsdTextView.setEms(5)
            priceUsdTextView.setCompoundDrawablesRelative(null, null, null, null)
            priceUsdTextView.text = "Indisponivel"
        }
        lastHourValueTextView.text = asset.volume_1hrs_usd.toString()
        lastWeekValueTextView.text = asset.volume_1day_usd.toString()
        lastMounthValueTextView.text = asset.volume_1mth_usd.toString()
        backPressButton.setOnClickListener { goToCoinList() }
    }

    private fun DetailsFragmentBinding.setAdd(
        dataBase: List<AssetsItem>?,
        asset: AssetsItem,
    ) {
        addButton.setText("Adicionar")
        starIconImageView.visibility = View.GONE
        addButton.setOnClickListener {
            coinViewModel.insertAsset(asset)
            goToCoinList()
        }
    }

    private fun DetailsFragmentBinding.setRemove(
        dataBase: List<AssetsItem>?,
        asset: AssetsItem,
    ) {
        addButton.setText("Remover")
        starIconImageView.visibility = View.VISIBLE
        addButton.setOnClickListener {
            coinViewModel.deleteAsset(asset)
            goToCoinList()
        }
    }

    private fun settingImageIcon(
        asset: com.example.abstraction.AssetsItem,
    ) {
        val progressBar = binding.detailsProgressBar
        progressBar.visibility = View.VISIBLE
        Glide.with(binding.root)
            .load(coinViewModel.loadUrlFromGlide(asset))
            .placeholder(R.drawable.ic_coin_base)
            .listener(ProgressBarListener(progressBar))
            .centerCrop()
            .into(binding.coinIconImageView)
    }

    private fun goToCoinList() {
        findNavController().popBackStack()
    }
}
