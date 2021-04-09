package com.example.mobilenetwork1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private var phoneNumber: String = ""
    private var isPhoneNumberEmpty: Boolean = true
    private var tiPhoneNumber: TextInputEditText? = null
    private var phoneNumberMask: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        phoneNumberMask = resources.getString(R.string.phoneNumberMask)
        tiPhoneNumber = findViewById(R.id.tiPhoneNumber)

        tiPhoneNumber?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                handleTextChange(s.toString())
            }
        })
    }

    private fun handleTextChange(text: String) {
        if (phoneNumber == text) {
            return
        }

        phoneNumber = text

        if (phoneNumber == phoneNumberMask) {
            tiPhoneNumber?.setSelection(0)
            return
        }

        if (isPhoneNumberEmpty) {
            val charInserted = text[text.length - 1]
            if (charInserted == 'x' || charInserted == '-') {
                tiPhoneNumber?.setText(phoneNumberMask)
                tiPhoneNumber?.setSelection(phoneNumber.length)
                return
            } else {
                phoneNumber = charInserted.toString()
            }
        }
        if (text.isEmpty()) {
            isPhoneNumberEmpty = true
            phoneNumber = phoneNumberMask!!
        } else {
            isPhoneNumberEmpty = false
        }

        tiPhoneNumber?.setText(phoneNumber)
        tiPhoneNumber?.setSelection(phoneNumber.length)
    }

//    private fun addNumber(string: String) {
//        val replaceIndex = tiPhoneNumber?.text?.toString()?.indexOf('x') ?: return
//
//        if (replaceIndex == -1) {
//            formattedPhoneNumber?.let {
//                tiPhoneNumber?.setText(it)
//                tiPhoneNumber?.setSelection(it.length)
//            }
//            return
//        }
//
//        formattedPhoneNumber = StringBuilder(string).apply {
//            deleteCharAt(replaceIndex)
//            insert(replaceIndex, string.last())
//            deleteCharAt(this.length - 1)
//        }.toString()
//
//        tiPhoneNumber?.setText(formattedPhoneNumber)
//        tiPhoneNumber?.setSelection(formattedPhoneNumber?.length!! - 1)
//    }
}