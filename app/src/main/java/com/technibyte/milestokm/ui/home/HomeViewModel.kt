package com.technibyte.milestokm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val milesDisplay = MutableLiveData<String>()
    val stringMilesDisplay: LiveData<String>
        get() = milesDisplay

    private val kilometersDisplay = MutableLiveData<String>()
    val stringKilometersDisplay: LiveData<String>
        get() = kilometersDisplay

    private var i = 0 //For clearLastNUmberPressed case inside of cmDisplay.

    private var j = 0 //For clearLastNumbersPressed case inside of inDisplay.

    //It gets the result of the miles to kilometers conversion.
    private fun milesResult(): String {
        return if (this.kilometersDisplay.value == "") {
            this.kilometersDisplay.value!!
        } else {
            "%.5f".format(this.kilometersDisplay.value!!.toDoubleOrNull()?.times(0.62137))
        }

    }


    // It gets the result of the kilometers to miles conversion.
    private fun kilometersResult(): String {
        return if (this.milesDisplay.value == "") {
            this.milesDisplay.value!!
        } else {
            "%.5f".format(this.milesDisplay.value!!.toDoubleOrNull()?.div(0.62137))
        }
    }


    fun digitPressed(
            caption: String,
            milesDisplayFocused: Boolean,
            kilometersDisplayFocused: Boolean,
            decimalSignPressed: Boolean,
            clearLastNumberPressed: Boolean,
            clearAllNumbersPressed: Boolean
    ) {

        // Code to program the milesDisplay behavior.
        if (milesDisplayFocused) {

            if (decimalSignPressed) ++i

            if (this.milesDisplay.value?.contains('.') == true) ++i

            if (this.milesDisplay.value != null) {

                if (i <= 1 || !decimalSignPressed) {
                    if (clearLastNumberPressed && this.milesDisplay.value!!.isNotEmpty()) {

                        if (this.milesDisplay.value!!.last() == '.') i = 0

                        this.milesDisplay.value = this.milesDisplay.value!!.dropLast(1)

                    } else if (clearAllNumbersPressed && this.milesDisplay.value!!.isNotEmpty()) {
                        if (this.milesDisplay.value!!.contains('.')) i = 0

                        this.milesDisplay.value = ""

                    } else {

                        if (clearLastNumberPressed || clearAllNumbersPressed) {
                            this.milesDisplay.value = ""
                        } else {

                            if (this.milesDisplay.value!!.length < 9) {
                                if (this.milesDisplay.value!!.isEmpty() && caption == ".") {
                                    this.milesDisplay.value =
                                            this.milesDisplay.value + "0" + caption
                                } else {
                                    this.milesDisplay.value = this.milesDisplay.value + caption
                                }
                            } else {
                                if (caption.contains('.')) i = 0
                            }

                        }

                    }

                    this.kilometersDisplay.value = kilometersResult().replace(',', '.')


                }

            } else {

                if (clearLastNumberPressed || clearAllNumbersPressed) {
                    this.milesDisplay.value = ""
                } else {

                    if (caption == ".") {
                        this.milesDisplay.value = "0$caption"
                        ++j
                    } else {
                        this.milesDisplay.value = caption
                    }

                    this.kilometersDisplay.value = kilometersResult().replace(',', '.')
                }

            }

            // Code to program the kilometersDisplay behavior.
        } else if (kilometersDisplayFocused) {
            when (decimalSignPressed) {
                true -> ++j
            }

            if (this.kilometersDisplay.value?.contains('.') == true) ++j

            if (this.kilometersDisplay.value != null) {
                if (j <= 1 || !decimalSignPressed) {
                    if (clearLastNumberPressed && this.kilometersDisplay.value!!.isNotEmpty()) {
                        if (this.kilometersDisplay.value!!.last() == '.') {
                            j = 0
                        }

                        this.kilometersDisplay.value = this.kilometersDisplay.value!!.dropLast(1)

                    } else if (clearAllNumbersPressed && this.kilometersDisplay.value!!.isNotEmpty()) {
                        if (this.kilometersDisplay.value!!.contains('.')) j = 0

                        this.kilometersDisplay.value = ""

                    } else {

                        if (clearLastNumberPressed || clearAllNumbersPressed) {
                            this.kilometersDisplay.value = ""
                        } else {

                            if (this.kilometersDisplay.value!!.length < 9) {

                                if (this.kilometersDisplay.value!!.isEmpty() && caption == ".") {
                                    this.kilometersDisplay.value = this.kilometersDisplay.value + "0" + caption
                                } else {
                                    this.kilometersDisplay.value = this.kilometersDisplay.value + caption
                                }

                            } else {
                                if (caption.contains('.')) j = 0
                            }
                        }

                    }

                    this.milesDisplay.value = milesResult().replace(',', '.')
                }

            } else {

                if (clearLastNumberPressed || clearAllNumbersPressed) {
                    this.kilometersDisplay.value = ""
                } else {

                    if (caption == ".") {
                        this.kilometersDisplay.value = "0$caption"
                        ++i
                    } else {
                        this.kilometersDisplay.value = caption
                    }
                    this.milesDisplay.value = milesResult().replace(',', '.')

                }
            }

        }

    }

}