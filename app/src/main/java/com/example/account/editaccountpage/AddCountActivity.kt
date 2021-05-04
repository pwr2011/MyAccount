package com.example.account.editaccountpage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.account.R
import kotlinx.android.synthetic.main.activity_add_account.*

class AddCountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        add_account_button.setOnClickListener {
            val intent = Intent()
            val new_company_name = input_account_name.text.toString()
            intent.putExtra("company", new_company_name)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}