package com.technibyte.milestokm.ui.math_behind

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
import com.technibyte.milestokm.databinding.FragmentMathBehindBinding

class MathBehindFragment : Fragment() {

    private lateinit var mathBehindViewModel: MathBehindViewModel
    private var _binding: FragmentMathBehindBinding? = null
    private lateinit var mAdView : AdView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mathBehindViewModel =
                ViewModelProvider(this).get(MathBehindViewModel::class.java)

        _binding = FragmentMathBehindBinding.inflate(inflater, container, false)

        return binding.root
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
                binding.imageView5.setImageResource(R.drawable.math_behind_header_dark_mode)
                binding.imageView6.setImageResource(R.drawable.math_behind_km_mi_dark_mode)
                binding.imageView7.setImageResource(R.drawable.math_behind_mi_km_dark_mode)
                binding.root.setBackgroundColor(Color.parseColor("#212121"))
                binding.textView11.alpha = 0.15F

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