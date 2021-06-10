package com.example.travel_cost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.travel_cost.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_calculate) {
            calculate()
        }
    }

    private fun calculate() {
        if (validation()) {
            val distance = binding.inputDistance.text.toString().toInt()
            val priceForLitre = binding.inputPrice.text.toString().toFloat()
            val autonomy = binding.inputAutonomy.text.toString().toFloat()

            val totalCost = (distance * priceForLitre) / autonomy
            binding.totalCost.text = "R$ ${"%.2f".format(totalCost)}"
        } else {
            Toast.makeText(this, getString(R.string.fill_every_inputs), Toast.LENGTH_LONG).show()
        }
    }

    private fun validation(): Boolean {
        when {
            binding.inputDistance.text.toString() == "" -> return false
            binding.inputPrice.text.toString() == "" -> return false
            binding.inputAutonomy.text.toString() == "" -> return false
            binding.inputAutonomy.text.toString() == "0" -> {
                Toast.makeText(this, getString(R.string.error_value), Toast.LENGTH_LONG).show()
                return false
            }
        }
        return true
    }
}