package com.example.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.account.Stock
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    lateinit var User_Info_Frag: User_Info_page
    val manager = supportFragmentManager
    var Stocks_List = ArrayList<Stock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewer : TextView = findViewById(R.id.show_stock)
        Stocks_List.add(Stock("삼성전자", 2,83000.0))

        viewer.setText(Stocks_List[0].toString())


        var btn_add = findViewById<Button>(R.id.add_button)
        btn_add.setOnClickListener(){
            var name_btn = findViewById<EditText>(R.id.input_name)
            var count_btn = findViewById<EditText>(R.id.input_count)
            var price_btn = findViewById<EditText>(R.id.input_price)

            Stocks_List.add(Stock(name_btn.text.toString(),
                    count_btn.text.toString().toInt(),
                    price_btn.text.toString().toDouble()))

            name_btn.text.clear()
            count_btn.text.clear()
            price_btn.text.clear()
            Toast.makeText(this@MainActivity,"추가되었어요!",Toast.LENGTH_SHORT).show()
        }
    }

    fun ShowTabOne(){
        val transaction = manager.beginTransaction()
        val fragment = User_Info_page()
        transaction.addToBackStack(null)
        transaction.commit()
    }


}