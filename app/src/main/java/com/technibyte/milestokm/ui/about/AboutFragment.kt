package com.technibyte.milestokm.ui.about

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAdsInitProvider
import com.technibyte.milestokm.R
import com.technibyte.milestokm.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var slideshowViewModel: AboutViewModel
    private var _binding: FragmentAboutBinding? = null
    private lateinit var mAdView : AdView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProvider(this).get(AboutViewModel::class.java)

        _binding = FragmentAboutBinding.inflate(inflater, container, false)

        /*val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Code to configure text appearance on substrings.
        val spanAboutText1 = SpannableString(getString(R.string.about_text1))

        spanAboutText1.setSpan(
            StyleSpan(Typeface.BOLD),
            0, 12,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spanAboutText1.setSpan(
            StyleSpan(Typeface.BOLD),
            32, 49,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        val aboutText1 = binding.textView2
        aboutText1.text = spanAboutText1

        //Code to make about_url text clickable.
        val spanAboutUrl = SpannableString(getString(R.string.about_url))
        spanAboutUrl.setSpan(
            URLSpan("https://technibyte.com"),0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val aboutUrl = binding.textView7
        aboutUrl.text = spanAboutUrl


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