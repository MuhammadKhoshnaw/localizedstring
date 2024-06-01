package com.example.localizedstring.framework.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.example.localizedstring.R
import com.example.localizedstring.adapters.viewModel.string
import com.example.localizedstring.entity.localizedString
import com.example.localizedstring.entity.toLocalizedRowString
import com.example.localizedstring.entity.toLocalizedString


@Composable
fun MyComposable() {
    val intIdString = localizedString(R.string.app_name)
    val intIdStringWithArgs = localizedString(R.string.reset_existence_delay_button, 2)
    val stringIdString = localizedString("app_name")
    val stringIdStringWithArgs = localizedString("reset_existence_delay_button", 2)
    val rawString = localizedString("Hello World!")
    val emptyString = localizedString()

    Column {
        Text(text = intIdString.string())
        Text(text = intIdStringWithArgs.string())
        Text(text = stringIdString.string())
        Text(text = stringIdStringWithArgs.string())
        Text(text = rawString.string())
        Text(text = emptyString.string())
    }
}

class MyFragment : Fragment() {
    private val textView = TextView(context) // just for demonstration
    //create your view

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val intIdString = R.string.app_name.toLocalizedString()
        val intIdStringWithArgs = R.string.reset_existence_delay_button.toLocalizedString(2)
        val stringIdString = "app_name".toLocalizedString()
        val stringIdStringWithArgs = "reset_existence_delay_button".toLocalizedString(2)
        val rawString = "Hello World!".toLocalizedRowString()
        val emptyString = localizedString()

        textView.text = string(intIdString)
        textView.text = string(intIdStringWithArgs)
        textView.text = string(stringIdString)
        textView.text = string(stringIdStringWithArgs)
        textView.text = string(rawString)
        textView.text = string(emptyString)
    }
}

@Preview(showBackground = true)
@Composable
fun MyComposablePreview() {
    MyComposable()
}
