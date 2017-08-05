package br.com.up.hello

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    private var loginButton: Button? = null
    private var progressBar: ProgressBar? = null
    private var count = 0

    private val context: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate")
        if (savedInstanceState != null)
            count = savedInstanceState.getInt("count")

        loginButton = findViewById<Button>(R.id.btnLogin)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        loginButton!!.setOnClickListener {
            val img = findViewById<ImageView>(R.id.imgLogin)

            if (mudou) {
                img.setImageResource(R.drawable.login)
                mudou = false
            } else {
                img.setImageResource(R.drawable.images)
                mudou = true
            }

            count++
            loginButton!!.text = "Count: $count"

            loginButton!!.visibility = View.INVISIBLE
            progressBar!!.visibility = View.VISIBLE

            callOtherScreen()
        }
    }

    private fun callOtherScreen() {
        object : Thread() {
            override fun run() {
                super.run()

                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                val intent = Intent(context, ListaActivity::class.java)
                intent.putExtra("nome", "Franklyn Vieira")
                startActivity(intent)
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        loginButton!!.visibility = View.VISIBLE
        progressBar!!.visibility = View.INVISIBLE
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("count", count)
    }

    companion object {
        private var mudou = false
    }
}
