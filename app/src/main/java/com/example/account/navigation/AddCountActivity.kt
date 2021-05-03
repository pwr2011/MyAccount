package com.example.account.navigation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.account.*
import kotlinx.android.synthetic.main.activity_add_count.*
import java.io.Serializable

class AddCountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_count)

        add_account_button.setOnClickListener {
            val intent = Intent()
            val new_company_name = input_account_name.text.toString()
            intent.putExtra("company", new_company_name)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}