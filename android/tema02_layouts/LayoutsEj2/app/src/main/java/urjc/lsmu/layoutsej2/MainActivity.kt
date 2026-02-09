package urjc.lsmu.layoutsej2

import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Button
import java.util.Locale
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var importe: EditText
    lateinit var fromCurrency: RadioGroup
    lateinit var toCurrency: RadioGroup
    lateinit var resultado: TextView

    private fun convertCurrency(amount: Double, from: String, to: String): Double {
        // Crear mapa de conversion de divisas EUR -> Any
        val rates = mapOf(
            "EUR" to 1.0,
            "USD" to 1.1,
            "GBP" to 0.9
        )
        val fromRate = rates[from] ?: return 0.0
        val toRate = rates[to] ?: return 0.0
        return amount * toRate / fromRate
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        importe = findViewById(R.id.importe)
        fromCurrency = findViewById(R.id.currencyFrom)
        toCurrency = findViewById(R.id.currencyTo)
        resultado  = findViewById(R.id.resultado)
        findViewById<Button>(R.id.convertButton).setOnClickListener {
            val amount = importe.text.toString().toDoubleOrNull()
            if (amount != null) {
                val from = when (fromCurrency.checkedRadioButtonId) {
                    R.id.fromEUR -> "EUR"
                    R.id.fromUSD -> "USD"
                    R.id.fromGBP -> "GBP"
                    else -> null
                }
                val to = when (toCurrency.checkedRadioButtonId) {
                    R.id.toEUR -> "EUR"
                    R.id.toUSD -> "USD"
                    R.id.toGBP -> "GBP"
                    else -> null
                }
                if (from != null && to != null) {
                    val converted = convertCurrency(amount, from, to)
                    resultado.text = String.format(Locale.getDefault(), "%.2f %s", converted, to)
                } else {
                    resultado.text = getString(R.string.select_both)
                }
            }
        }
    }
}