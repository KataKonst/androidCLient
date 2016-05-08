package com.example.katakonst.licenta.activity

import android.content.Intent
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.katakonst.licenta.AsyncTask.tracks.GetTrackByMd5
import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.ItemDetailActivity
import com.example.katakonst.licenta.JsonModels.Tracks

import com.example.katakonst.licenta.R
import java.io.IOException
import java.net.*
import java.nio.ByteBuffer
import java.nio.ByteOrder

class MatchActivity : AppCompatActivity() {

    private var startButton: Button? = null
    private var stopButton: Button? = null
    private var result: Tracks? = null



    private val port = 50005

    internal  lateinit var recorder: AudioRecord;

    private val sampleRate = 44100 // 44100 for music
    private val channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT
    internal var minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)
    private var status = true
    fun findAudioRecord(): AudioRecord {
        for (rate in mSampleRates) {
            for (audioFormat in shortArrayOf(AudioFormat.ENCODING_PCM_8BIT.toShort(), AudioFormat.ENCODING_PCM_16BIT.toShort())) {
                for (channelConfig in shortArrayOf(AudioFormat.CHANNEL_IN_MONO.toShort(), AudioFormat.CHANNEL_IN_STEREO.toShort())) {
                    try {
                        Log.d("sss", "Attempting rate " + rate + "Hz, bits: " + audioFormat + ", channel: "
                                + channelConfig)
                        val bufferSize = AudioRecord.getMinBufferSize(rate, channelConfig.toInt(), audioFormat.toInt())

                        if (bufferSize != AudioRecord.ERROR_BAD_VALUE) {
                            // check if we can instantiate and have a success
                            val recorder = AudioRecord(MediaRecorder.AudioSource.DEFAULT, rate, channelConfig.toInt(), audioFormat.toInt(), bufferSize)

                            if (recorder.state == AudioRecord.STATE_INITIALIZED)
                                return recorder
                        }
                    } catch (e: Exception) {
                        Log.e("SS",  "Exception, keep trying.", e)
                    }

                }
            }
        }
        return   AudioRecord(MediaRecorder.AudioSource.DEFAULT, 0, 0, 0, 0)
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        startButton = findViewById(R.id.start_button) as Button
        stopButton = findViewById(R.id.stop_button) as Button

        startButton!!.setOnClickListener(startListener)
        stopButton!!.setOnClickListener(stopListener)

    }

    private val stopListener = View.OnClickListener {
        status = false
        recorder.release()
        Log.d("VS", "Recorder released")
    }

    private val startListener = View.OnClickListener {
        status = true
        startStreaming()
    }


    fun startStreaming() {

        socket=DatagramSocket();


        val streamThread = Thread(Runnable {
            try {

                Log.d("VS", "Socket Created")
                minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)

                val buffer = ByteArray(minBufSize)
                val data = ShortArray(minBufSize / 2)


                Log.d("VS", "Buffer created of size " + minBufSize)
                var packet: DatagramPacket

                val destination = InetAddress.getByName(Constants.matchIp)
                Log.d("VS", "Address retrieved")

                val socket=Socket(destination,port)
                val  out = (socket.getOutputStream());
                val  input = (socket.getInputStream());
                recorder = findAudioRecord()
                Log.d("VS", "Recorder initialized")

                recorder.startRecording()

                val matchResult = findViewById(R.id.match_result) as TextView
                val sss = Thread(Runnable {
                    val res=ByteArray(200);
                    val g=input.read(res)

                    runOnUiThread(Runnable {
                        matchResult.text=String(res,0,g).split('|')[0]
                        val savefun:(List<Tracks>)->Tracks={tracks->result=tracks.get(0);tracks.get(0)}
                        val geTrackByMd5Task=GetTrackByMd5(String(res,0,g).split('|')[1]+".mp3",savefun)
                        geTrackByMd5Task.execute();
                        matchResult.setOnClickListener({
                            var intent= Intent(this, ItemDetailActivity::class.java);

                            var bundle=Bundle()
                            bundle.putString(Tracks.Id,result!!.id)
                            bundle.putString(Tracks.Name,result!!.name)
                            bundle.putString(Tracks.Link,result!!.link)
                            bundle.putString(Tracks.PhotoLink,result!!.photoLink)

                            intent.putExtras(bundle)
                            this.startActivity(intent)



                        })

                    });


                    Log.d("VS",String(res,0,g));
                    status=false;
                    status = false
                    recorder.release()


                })

                sss.start()


                while (status == true) {


                    minBufSize = recorder.read(data, 0, buffer.size / 2)

                    val buffr = ByteBuffer.allocate(data.size * 2)
                    buffr.order(ByteOrder.BIG_ENDIAN)
                    buffr.asShortBuffer().put(data)
                    val bytes = buffr.array()


                    out.write(bytes)
                    println("MinBufferSize: " + minBufSize)


                }
                out.flush();
                sss.interrupt();


            } catch (e: UnknownHostException) {
                Log.e("VS", "UnknownHostException")
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("VS", "IOException")
            }
        })
        streamThread.start()

    }

    companion object {
        lateinit  var socket: DatagramSocket


        private val mSampleRates = intArrayOf(44100, 11025, 22050, 8000)
    }

}
