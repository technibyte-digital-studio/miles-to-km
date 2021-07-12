package com.technibyte.milestokm.ui.home

import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.technibyte.milestokm.R
import com.technibyte.milestokm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Disable the native keyboard
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
            binding.milesDisplay.showSoftInputOnFocus = false
            binding.kilometersDisplay.showSoftInputOnFocus = false
        }
        else { // API 11-20
            binding.milesDisplay.setTextIsSelectable(true)
            binding.kilometersDisplay.setTextIsSelectable(true)
        }

        // Dark Theme modifications
        val darkThemeIsActive = context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        when (darkThemeIsActive) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.clearAllNumbers.setTextColor(Color.parseColor("#59d3e5"))
                binding.clearAllNumbers.setStrokeColorResource(R.color.secondaryBlueLightVariant)
                binding.clearLastNumber.setStrokeColorResource(R.color.secondaryBlueLightVariant)
                binding.clearLastNumber.setIconTintResource(R.color.secondaryBlueLightVariant)
                binding.root.setBackgroundColor(Color.parseColor("#212121"))
                binding.textView5.alpha = 0.15F


            }
            Configuration.UI_MODE_NIGHT_NO -> {}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }

        // Observe and update the milesDisplay behavior
        homeViewModel.stringMilesDisplay.observe(
                viewLifecycleOwner,
                { stringCentimeterDisplay -> binding.milesDisplay.setText(stringCentimeterDisplay) }
        )

        // Observe and update the kilometersDisplay behavior
        homeViewModel.stringKilometersDisplay.observe(
                viewLifecycleOwner,
                { stringInchesDisplay -> binding.kilometersDisplay.setText(stringInchesDisplay) }
        )

        // Access the method to deal with click events
        val listener = View.OnClickListener { v ->
            homeViewModel.digitPressed(
                    (v as Button).text.toString(),
                    binding.milesDisplay.isFocused,
                    binding.kilometersDisplay.isFocused,
                    binding.decimalSign.isPressed,
                    binding.clearLastNumber.isPressed,
                    binding.clearAllNumbers.isPressed
            )
        }

        // Listen to click events
        binding.number0.setOnClickListener(listener)
        binding.number1.setOnClickListener(listener)
        binding.number2.setOnClickListener(listener)
        binding.number3.setOnClickListener(listener)
        binding.number4.setOnClickListener(listener)
        binding.number5.setOnClickListener(listener)
        binding.number6.setOnClickListener(listener)
        binding.number7.setOnClickListener(listener)
        binding.number8.setOnClickListener(listener)
        binding.number9.setOnClickListener(listener)
        binding.decimalSign.setOnClickListener(listener)
        binding.clearLastNumber.setOnClickListener(listener)
        binding.clearAllNumbers.setOnClickListener(listener)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}