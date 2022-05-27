package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem
import com.example.cryptocurrencyapp.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyapp.view.adapters.TinyDB

class CoinDetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private val args: CoinDetailsFragmentArgs by navArgs()

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
        val clickEvent: Boolean

        context?.let {
            Glide.with(it)
                .load(R.drawable.ic_coin_base)
                .transform(CenterCrop())
                .into(binding.coinIconImageView)
        }

        with(binding) {
            if ( TinyDB(requireContext()).ler().contains(asset.asset_id)) {
                starIconImageView.visibility = View.VISIBLE
            }
            iconAssetIdTextView.text = asset.asset_id
            priceUsdTextView.text = asset.price_usd.toString()
            lastHourTextView.text = asset.volume_1hrs_usd.toString()
            lastMonthTextView.text = asset.volume_1day_usd.toString()
            lastYearTextView.text = asset.volume_1mth_usd.toString()
            addButton.setOnClickListener {
                starIconImageView.visibility = View.VISIBLE
                addButton.text = "Remover"
                TinyDB(requireContext()).escrever(asset.asset_id)
            }

        }

    }

    private fun buttonClick(click: Boolean) {

    }

}


