package com.example.account

import android.app.Activity
import android.content.Intent
import android.graphics.ColorSpace.get
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.account.navigation.SummaryFragment
import com.example.account.navigation.MyAccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileWriter
import java.io.IOException

var my_account_array = ArrayList<Company>()

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    val manager = supportFragmentManager
    lateinit var summary_tab: SummaryFragment
    lateinit var my_tab: MyAccountFragment

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_my_account -> {
                my_tab = MyAccountFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, my_tab).commit()
                return true
            }
            R.id.action_summary -> {
                summary_tab = SummaryFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, summary_tab).commit()
                return true
            }
//            R.id.action_extra->{
//                var my_account_fragment = MyAccountFragment()
//                supportFragmentManager.beginTransaction().replace(R.id.main_content,my_account_fragment).commit()
//                return true
//            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Load_file()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        show_summary_tab()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1->{
                    if(data!!.hasExtra("company")){
                        var company_name = data!!.getStringExtra("company").toString()
                        my_account_array.add(Company(company_name, ArrayList<Stock>()))
                        Write_Company_Append("\n"+company_name)
                    }
                    else{
                        Toast.makeText(this,"전달된 이름에 오류가 있다.",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun show_summary_tab() {
        //https://yuuj.tistory.com/entry/AndroidKotlin-Fragment%EB%A1%9C-%ED%83%AD-%EA%B5%AC%EC%A1%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0-%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8-%EA%B5%90%EC%B2%B4
        summary_tab = SummaryFragment()
        replaceFragment(summary_tab)
    }

    fun show_my_tab() {
        my_tab = MyAccountFragment()
        replaceFragment(my_tab)
    }

    //출처: https://mc10sw.tistory.com/16 [Make it possible]
    fun replaceFragment(tab: Fragment) {
        // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, tab)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun Write_Company_Append(text : String){
        try {
            val fw = FileWriter(filesDir.toString()+"/UserCompanyData.txt", true)
            fw.write(text)
            fw.close()

        } catch (e: IOException) {
            println("IO error happen")
            Toast.makeText(this@MainActivity, "파일 쓰기 오류!", Toast.LENGTH_SHORT).show()
        }
    }

    //수정중
    fun Load_file() {
        // 계좌 정보 Load
        try {
            val file = File(filesDir, "UserCompanyData.txt")
            if (file.exists()) {
                println("Load Company file")
                println(file.path)
                file.bufferedReader().useLines { lines ->
                    lines.forEach {
                            line->
                        println(line)
                        val company_name = line.toString()
                        if(company_name.length>0) {
                            my_account_array.add(Company(company_name,ArrayList<Stock>()))
                        }
                    }
                }
                println(my_account_array)
            }
            else {
                println("else로 들어감!")
                file.createNewFile()
            }
        } catch (e: IOException) {
            println("IO error happen")
            Toast.makeText(this@MainActivity, "계좌 정보를 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
        }

        //Stock 정보 Load
        try {
            val file = File(filesDir, "UserStockData.txt")
            if (file.exists()) {
                println("Load User Stock Data file")
                println(file.path)
                file.bufferedReader().useLines { lines ->
                    lines.forEach {
                        line->
                        println(line)
                        val data = line.split(" ")
                        var company = my_account_array.find({it.name==data[0]})
                        if(company != null) {
                            company!!.own.add(Stock(data[1], data[2].toInt(), data[3].toDouble()))
                        }
                    }
                }

                println(my_account_array)
            }
            else {
                println("else로 들어감!")
                file.createNewFile()
            }
        } catch (e: IOException) {
            println("IO error happen")
            Toast.makeText(this@MainActivity, "유저 정보를 읽을 수 없습니다!", Toast.LENGTH_SHORT).show()
        }
    }
}