package com.selcukileri.widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.selcukileri.widgets.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var kontrol = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOku.setOnClickListener {
            val alinanVeri = binding.editTextGirdi.text.toString()

            binding.textViewSonuc.text = alinanVeri
        }
        binding.buttonResim1.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.resim1)
        }

        binding.buttonResim2.setOnClickListener {
            binding.imageView.setImageResource(resources.getIdentifier("resim2","drawable",packageName))
        }
        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.e("Widgetsss", "Switch : ON")
            } else {
                Log.e("Widgetsss", "Switch : OFF")
            }
        }
        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            kontrol = isChecked
            val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
            if (kontrol) {
                val buttonYazi = secilenButton.text.toString()
                Log.e("Widgetsss", buttonYazi)
            } else {

            }

        }
        val ulkeler = ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("Italya")
        ulkeler.add("Japonya")
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,ulkeler)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }
        binding.buttonDurdur.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }
        binding.slider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textViewSlider.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        binding.buttonSaat.setOnClickListener {
            val dp = MaterialTimePicker.Builder()
                .setTitleText("Saat Seç")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            dp.show(supportFragmentManager,"Saat")
            dp.addOnPositiveButtonClickListener {
                binding.editTextSaat.setText("${dp.hour} : ${dp.minute}")
            }
        }

        binding.buttonTarih.setOnClickListener {
            val tp = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seç")
                .build()

            tp.show(supportFragmentManager,"Saat")
            tp.addOnPositiveButtonClickListener {
                val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val tarih = df.format(it)
                binding.editTextTarih.setText(tarih)
            }
        }


        binding.textViewSlider.text = binding.slider.progress.toString()

        binding.buttonGoster.setOnClickListener {
            Log.e("Widgetsss", "Switch Durum : ${binding.switch1.isChecked}")
            if (kontrol) {
                val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val buttonYazi = secilenButton.text.toString()
                Log.e("Widgetsss", "Toggle Durum: $buttonYazi")
            }
            val ulke = binding.autoCompleteTextView.text.toString()
            Log.e("Widgetsss", "Ülke : ${ulke}")
            binding.slider.progress.toString()
            Log.e("Widgetsss", "Slider Durum : ${binding.slider.progress}")
        }


    }
}