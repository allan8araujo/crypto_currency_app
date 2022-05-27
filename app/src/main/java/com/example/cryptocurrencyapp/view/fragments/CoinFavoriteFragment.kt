package com.example.cryptocurrencyapp.view.fragments

import androidx.fragment.app.Fragment

class CoinFavoriteFragment : Fragment() {
//    private lateinit var listAdapter: CoinFavoritAdapter
//    private lateinit var binding: FavoriteFragmentBinding
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    private val coinViewModel: AssetsListViewModel by viewModels {
//        RetrofitRequestHelper.getListAssets()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupRecycler()
//        collectAssetsObserver()
//}
//    private fun setupRecycler() {
//        listAdapter = CoinFavoritAdapter(requireContext()) { asset -> goToCoinDetails(asset as AssetsItem) }
//        linearLayoutManager = LinearLayoutManager(
//            activity,
//            LinearLayoutManager.VERTICAL,
//            false
//        )
////        binding.coinListRecyclerView.layoutManager = linearLayoutManager
////        binding.coinListRecyclerView.adapter = listAdapter
//
//
//    }
//
//
//        private fun collectAssetsObserver() {
////            coinViewModel.getAllAssets()
////            coinViewModel.assets.observe(viewLifecycleOwner) { assetsResults ->
////                setListAdapter(assetsResults)
////            }
//        setListAdapter(funMockLives())
//    }
//
//    private fun setListAdapter(list: List<AssetsItem>) {
//        listAdapter.submitList(list)
//
//    }
//
//    private fun goToCoinDetails(asset: AssetsItem) {
//        val bundle = bundleOf("asset" to asset)
//        findNavController().navigate(R.id.action_to_detailIcon, bundle)
//    }
}