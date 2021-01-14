package com.technibyte.milestokm.ui.home

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAdsInitProvider
import com.technibyte.milestokm.R
import com.technibyte.milestokm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var mAdView : AdView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // AdMob initialization
        MobileAdsInitProvider()

        // AdMob request
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // Dark Theme modifications
        val darkThemeIsActive = context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        when (darkThemeIsActive) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.outlinedButton12.setTextColor(Color.parseColor("#59d3e5"))
                binding.outlinedButton12.setStrokeColorResource(R.color.secondaryBlueLightVariant)
                binding.outlinedButton13.setStrokeColorResource(R.color.secondaryBlueLightVariant)
                binding.outlinedButton13.setIconTintResource(R.color.secondaryBlueLightVariant)
                binding.root.setBackgroundColor(Color.parseColor("#212121"))
                binding.textView5.alpha = 0.15F


            }
            Configuration.UI_MODE_NIGHT_NO -> {}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}