package com.example.katakonst.licenta.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.katakonst.licenta.Constants

import com.example.katakonst.licenta.R
import com.example.katakonst.licenta.chat.SocketTask
import java.net.Socket

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val args = this.intent.extras
        val name=args.getString("name")

           val tg=Thread(Runnable {
           val socket= Socket(Constants.matchIp, 8091)


           val  out = (socket.getOutputStream());
           val  input = (socket.getInputStream());

           val messageText=findViewById(R.id.messageText) as EditText

           val sendBut=findViewById(R.id.sendMessage) as Button

           sendBut.setOnClickListener({
               Log.d("ss",("sdadx"))
               out.write((name+":"+messageText.text.toString()).toByteArray());
               out.flush()

           })

           val txt=findViewById(R.id.chatView) as TextView
           val a= SocketTask(txt,this,input)
           a.execute()



       }).start()

    }

}
