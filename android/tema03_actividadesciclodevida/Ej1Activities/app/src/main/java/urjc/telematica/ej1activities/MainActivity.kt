package urjc.telematica.ej1activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

const val TAG: String = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Create event")
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "Start event")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Resume event")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "Pause event")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "Stop event")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Destroy event")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "Restart event")
    }
}