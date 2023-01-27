package com.example.pengenalan

import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight:EditText
    private lateinit var edtLength:EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)
    }

    override fun onClick(p0: View?){
        if (p0?.id == R.id.btn_calculate) {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false
            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true
                edtLength.setError("Field ini tidak boleh kosong")
            }
            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true
                edtWidth.setError("Field ini tidak boleh kosong")
            }
            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true
                edtHeight.setError("Field ini tidak boleh kosong")
            }
            val panjang = convertToDouble(inputLength);
            val lebar = convertToDouble(inputWidth);
            val tinggi = convertToDouble(inputHeight);

            if (panjang == null){
                isInvalidDouble = true
                edtLength.error = "nilai tidak valid"
            }
            if (lebar == null){
                isInvalidDouble=true
                edtWidth.error = "nilai tidak valid"
            }
            if(tinggi == null){
                isInvalidDouble=true
                edtHeight.error = "nilai tidak valid"
            }

            if(!isEmptyFields&& !isInvalidDouble) {
                val volume = panjang!!.toDouble() * tinggi!!.toDouble() * lebar!!.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(STATE_RESULT,tvResult.text.toString())
    }
    private fun convertToDouble(str:String):Double?{
        return try {
            str.toDouble()
        }catch(e:NumberFormatException){
            null
        }
    }

    companion object{
        private const val STATE_RESULT="state_result"
    }
}

private fun convertToDouble(str: String):Double?{
    return try{
        str.toDouble()
    }catch (e:java.lang.NumberFormatException){
        null
    }
}