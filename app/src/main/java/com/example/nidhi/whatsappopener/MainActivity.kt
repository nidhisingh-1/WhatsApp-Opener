package com.example.nidhi.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number = String()

        if(intent.action == Intent.ACTION_PROCESS_TEXT) {

            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }

        if (number.isDigitsOnly()){
            startWhatsapp(number)
        }
        else {
            Toast.makeText(this, "Check Your Number", Toast.LENGTH_LONG).show()
        }
        }

    private fun startWhatsapp(number: String) {
      val i = Intent()
        i.action = Intent.ACTION_VIEW
        i.setPackage("com.whatsapp")
        val data : String = if (number[0] == '+'){
            number.substring(startIndex = 1)
        }
        else if (number.length == 10){
            "91" + number
        }
        else{
            number
        }

        i.data = Uri.parse("https://wa.me/$data")
        if (packageManager.resolveActivity(i,0) != null){
            startActivity(i)
        }

        else {
            Toast.makeText(this, "Whatsapp on this number not found", Toast.LENGTH_LONG).show()
        }
    }
}