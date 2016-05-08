package com.example.katakonst.licenta.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText

import com.example.katakonst.licenta.R

class ChooseNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_name)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val setNameBut = findViewById(R.id.setNameButton) as Button
        setNameBut.setOnClickListener {

            var ChatActivityIntent= Intent(this, ChatActivity::class.java);
            val bundle=Bundle();
            val nameEditText=findViewById(R.id.chatText) as EditText
            bundle.putString("name",nameEditText.text.toString());
            ChatActivityIntent.putExtras(bundle)
            this.startActivity(ChatActivityIntent)


        }
    }

}
