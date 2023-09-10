package com.example.mykotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mykotlincalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //Numbers
        binding.num0.setOnClickListener   {appendVal("0", false)}
        binding.num1.setOnClickListener   {appendVal("1", false)}
        binding.num2.setOnClickListener   {appendVal("2", false)}
        binding.num3.setOnClickListener   {appendVal("3", false)}
        binding. num4.setOnClickListener  {appendVal("4",false)}
        binding. num5.setOnClickListener  {appendVal("5",false)}
        binding.num6.setOnClickListener   {appendVal("6", false)}
        binding. num7.setOnClickListener  {appendVal("7",false)}
        binding.num8.setOnClickListener   {appendVal("8", false)}
        binding.num9.setOnClickListener   {appendVal("9", false)}
        binding.numDot.setOnClickListener {appendVal(".", false)}

        //Arithmetic Operations
        binding.clear.setOnClickListener { appendVal("", true) }
        binding.startBracket.setOnClickListener { appendVal(" ( ", false) }

        binding.closeBracket.setOnClickListener { appendVal(" ) ", false) }

        binding.actionDivide.setOnClickListener { appendVal(" / ", false) }

        binding.actionMultiply.setOnClickListener { appendVal(" * ", false) }

        binding.actionMinus.setOnClickListener { appendVal(" - ", false) }

        binding. actionAdd.setOnClickListener { appendVal(" + ", false) }


        //notice the inbuilt functions used in this application -substring, -ExpressionBuilder(), -append().

        binding. actionBack.setOnClickListener {
            val expression = binding.placeholder.text.toString()

            if (expression.isNotEmpty()) {
                binding.placeholder.text = expression.substring(0, expression.length - 1)
            }
        }

        binding.actionEquals.setOnClickListener {

            try {
                val expression = ExpressionBuilder(binding.placeholder.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble()) {

                    Toast.makeText(this, "Double", Toast.LENGTH_SHORT).show()
                    binding.answer.text = longResult.toString()

                } else

                    binding.answer.text = result.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION", "Message: ${e.message}")
            }
        }

    }


    private fun appendVal(string: String, isClear: Boolean) {

        if (isClear) {
            binding.placeholder.text = ""
            binding.answer.text = ""
           //placeholder.append(string)

        } else {
            binding.placeholder.append(string)
        }
    }
}
        //You can as well study the commented codes
/*
// Set a common click listener for numeric buttons (0-9) and dot
val buttonIds = listOf(
    R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4,
    R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9, R.id.numDot
)

for (buttonId in buttonIds) {
    val button = findViewById<Button>(buttonId)
    button.setOnClickListener { appendVal(button.text.toString(), false) }
}

// Set a common click listener for arithmetic operation buttons
val operatorIds = listOf(
    R.id.clear, R.id.startBracket, R.id.closeBracket,
    R.id.actionDivide, R.id.actionMultiply, R.id.actionMinus, R.id.actionAdd
)

for (operatorId in operatorIds) {
    val operator = findViewById<Button>(operatorId)
    operator.setOnClickListener { appendVal(operator.text.toString(), operatorId == R.id.clear) }
}

// Set click listener for the backspace button
binding.actionBack.setOnClickListener {
    val expression = binding.placeholder.text.toString()

    if (expression.isNotEmpty()) {
        binding.placeholder.text = expression.substring(0, expression.length - 1)
    }
}

// Set click listener for the equals button
binding.actionEquals.setOnClickListener {
    try {
        val expression = ExpressionBuilder(binding.placeholder.text.toString()).build()
        val result = expression.evaluate()
        val longResult = result.toLong()

        if (result == longResult.toDouble()) {
            Toast.makeText(this, "Double", Toast.LENGTH_SHORT).show()
            binding.answer.text = longResult.toString()
        } else {
            binding.answer.text = result.toString()
        }
    } catch (e: Exception) {
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        Log.d("EXCEPTION", "Message: ${e.message}")
    }
}

private fun appendVal(string: String, isClear: Boolean) {
    if (isClear) {
        binding.placeholder.text = ""
        binding.answer.text = ""
    } else {
        binding.placeholder.append(string)
    }
}
*/
