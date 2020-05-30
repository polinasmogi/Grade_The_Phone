package com.polinasmogi.gradethephone.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.polinasmogi.gradethephone.R
import com.polinasmogi.gradethephone.db.AppDatabase
import com.polinasmogi.gradethephone.db.PhoneEntity
import com.polinasmogi.gradethephone.ui.adapters.PhonesAdapter
import com.polinasmogi.gradethephone.ui.interfaces.ItemClickExecutor
import com.polinasmogi.gradethephone.ui.interfaces.Constants
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var adapter: PhonesAdapter
    private var phones: List<PhoneEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onResume() {
        super.onResume()
        updatePhones()
    }

    private fun init() {
        adapter = PhonesAdapter(Executor())
        phone_list.adapter = adapter

        updatePhones()
    }

    private fun updatePhones() {
        doAsync {

            phones = AppDatabase.getInstance(this@MainActivity).phoneDao().getPhones()

            adapter.submitList(phones)

            no_phones_icon.isVisible = phones.isNullOrEmpty()
            no_phones_text.isVisible = phones.isNullOrEmpty()

        }
    }


    inner class Executor : ItemClickExecutor {
        override fun execute(position: Int) {

            val selectedPhone = adapter.getPhone(position)

            val intent = Intent(this@MainActivity, PhoneInfoActivity::class.java)
            intent.putExtra(Constants.PHONE_ID, selectedPhone.id)
            startActivity(intent)

        }

    }
}
