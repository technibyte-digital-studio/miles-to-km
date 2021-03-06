package com.technibyte.milestokm.ui.about

import android.content.res.Configuration
import android.graphics.Color
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
import com.technibyte.milestokm.R
import com.technibyte.milestokm.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var slideshowViewModel: AboutViewModel
    private var _binding: FragmentAboutBinding? = null

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

        // Dark theme modifications
        val darkThemeIsActive = context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        when (darkThemeIsActive) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.imageView3.setImageResource(R.drawable.about_technibyte_logo_dark_mode)
                binding.ivLogo.setImageResource(R.drawable.about_smartconv_logo_dark_mode)
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