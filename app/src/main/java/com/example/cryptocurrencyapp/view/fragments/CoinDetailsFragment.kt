package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.api.retrofit.RetrofitRequestHelper
import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem
import com.example.cryptocurrencyapp.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyapp.view.adapters.ProgressBarListener
import com.example.cryptocurrencyapp.view.adapters.TinyDB
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinDetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private val args: CoinDetailsFragmentArgs by navArgs()
    private val coinViewModel: AssetsListViewModel by activityViewModels {
        RetrofitRequestHelper.getListAssets()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
        val dataBase: TinyDB = TinyDB(requireContext())

        fun loadUrlFromGlide(assetItem: AssetsItem): String? {
            val assetUrlLink = coinViewModel.icon.value?.find {
                it.asset_id == assetItem.asset_id
            }
            return assetUrlLink?.url
        }

        context?.let {
            Glide.with(it)
                .load(R.drawable.ic_coin_base)
                .transform(CenterCrop())
                .into(binding.coinIconImageView)
        }
        val progressBar = binding.detailsProgressBar
        progressBar.visibility = View.VISIBLE
        Glide.with(binding.root)
            .load(loadUrlFromGlide(asset))
            .placeholder(R.drawable.ic_coin_base)
            .listener(ProgressBarListener(progressBar))
            .centerCrop()
            .into(binding.coinIconImageView)
        with(binding) {

            iconAssetIdTextView.text = asset.asset_id
            Log.d("TAG", asset.asset_id)
            Log.d("TAG", dataBase.hasItem(asset.asset_id).toString())
            when (dataBase.hasItem(asset.asset_id)) {
                true -> {
                    addButton.setText("Remover")
                    starIconImageView.visibility = View.VISIBLE
                    addButton.setOnClickListener {
                        dataBase.removeItem(asset.asset_id)
                        goToCoinList()
                    }
                }
                false -> {
                    addButton.setText("Adicionar")
                    starIconImageView.visibility = View.GONE
                    addButton.setOnClickListener {
                        dataBase.addItem(asset.asset_id)
                        goToCoinList()
                    }
                }
            }
            if (asset.price_usd != null) {
                priceUsdTextView.text = asset.price_usd.toString()
            } else {
                priceUsdTextView.text = "0.00 "
                priceUsdTextView.setEms(3)
            }



            lastHourValueTextView.text = asset.volume_1hrs_usd.toString()
            lastWeekValueTextView.text = asset.volume_1day_usd.toString()
            lastMounthValueTextView.text = asset.volume_1mth_usd.toString()
            backPressButton.setOnClickListener { goToCoinList() }

        }
    }

    fun goToCoinList() {
        fragmentManager?.popBackStack()
    }
}


