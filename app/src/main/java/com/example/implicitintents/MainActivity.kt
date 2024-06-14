package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var subject : EditText
    private lateinit var emailbody : EditText
    private lateinit var sendmail : Button
    private lateinit var number : EditText
    private lateinit var smsbody : EditText
    private lateinit var sendsms : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        email = findViewById(R.id.email)
        subject = findViewById(R.id.subject)
        emailbody = findViewById(R.id.body)
        sendmail = findViewById(R.id.sendmail)
        number = findViewById(R.id.number)
        smsbody = findViewById(R.id.smsbody)
        sendsms = findViewById(R.id.sendsms)

        sendmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${email.text}")
                putExtra(Intent.EXTRA_SUBJECT, subject.text.toString())
                putExtra(Intent.EXTRA_TEXT, emailbody.text.toString())
            }
            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(emailIntent)
            }
        }

        sendsms.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:${number.text}")
                putExtra("sms_body", smsbody.text.toString())
            }
            if (smsIntent.resolveActivity(packageManager) != null) {
                startActivity(smsIntent)
            }
        }
    }
}
