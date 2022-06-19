package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.apilibrary.repository.database.TinyDB
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyapp.models.assets.Assets.AssetsItem
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
        val dataBase = coinViewModel.database
        settingImageIcon(asset)
        with(binding) {
            iconAssetIdTextView.text = asset.asset_id
            when (dataBase.hasItem(asset.asset_id)) {
                true -> {
                    setRemove(dataBase, asset)
                }
                false -> {
                    setAdd(dataBase, asset)
                }
            }

            settingPricesAndVolum(asset, asset.price_usd)
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
        dataBase: TinyDB,
        asset: AssetsItem,
    ) {
        addButton.setText("Adicionar")
        starIconImageView.visibility = View.GONE
        addButton.setOnClickListener {
            dataBase.addItem(asset.asset_id)
            goToCoinList()
        }
    }

    private fun DetailsFragmentBinding.setRemove(
        dataBase: TinyDB,
        asset: AssetsItem,
    ) {
        addButton.setText("Remover")
        starIconImageView.visibility = View.VISIBLE
        addButton.setOnClickListener {
            dataBase.removeItem(asset.asset_id)
            goToCoinList()
        }
    }

    private fun settingImageIcon(
        asset: AssetsItem,
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
