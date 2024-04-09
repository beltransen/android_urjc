package urjc.telematica.ciclovida

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button

const val TAG: String = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var btnAcerca : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Create event")
        setContentView(R.layout.activity_main)

        btnAcerca = findViewById(R.id.btnAbout)
        btnAcerca.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java).apply {
                putExtra("nombre", "Jorge")
                putExtra("rol", "Profesor")
            }
            startActivity(intent)

//            val tomarFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            try {
//                startActivityForResult(tomarFoto, 1)
//            }catch (e: ActivityNotFoundException){
//                Log.e(TAG, e.message!!)
//            }
        }
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