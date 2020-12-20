package com.technibyte.milestokm.ui.math_behind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAdsInitProvider
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
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        mathBehindViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}