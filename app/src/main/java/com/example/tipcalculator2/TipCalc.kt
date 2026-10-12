package com.example.tipcalculator2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun TipCalc() {
    // rememberSaveable (not remember) so values survive rotation
    var bill by rememberSaveable { mutableStateOf("") }
    var pct by rememberSaveable { mutableStateOf(15f) }

    val billAmount = bill.toDoubleOrNull()   // null if empty or not a number
    val tip = (billAmount ?: 0.0) * pct / 100
    val total = (billAmount ?: 0.0) + tip
    val hasError = bill.isNotEmpty() && billAmount == null

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = bill,
            onValueChange = { bill = it },
            label = { Text("Bill amount") },
            singleLine = true,
            isError = hasError,
            supportingText = { if (hasError) Text("Enter numbers only, e.g. 1250.50") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Tip percentage: ${pct.roundToInt()}%")
        Slider(
            value = pct,
            onValueChange = { pct = it },
            valueRange = 0f..30f
        )

        Text("Tip: %.2f".format(tip), style = MaterialTheme.typography.titleMedium)
        Text("Total: %.2f".format(total), style = MaterialTheme.typography.titleLarge)
    }
}