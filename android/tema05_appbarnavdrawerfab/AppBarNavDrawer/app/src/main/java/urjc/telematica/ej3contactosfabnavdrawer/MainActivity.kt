package urjc.telematica.appbarnavdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drawer)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_compass)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Elementos de navegación"

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerLayout.close()
            when(it.itemId){
                R.id.nav_gallery -> Log.d("Drawer","Acción de la galería")
                R.id.nav_slideshow ->  Log.d("Drawer","Acción del carrousel")
                R.id.nav_settings ->  Log.d("Drawer","Acción de las preferencias")
            }
            true
        }

        fab = findViewById(R.id.fab)
        fab.setOnClickListener(){
            Toast.makeText(this, "FAB", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> drawerLayout.open()
            R.id.action_gallery -> Log.d("Toolbar","Acción de la galería")
            R.id.action_slideshow -> Log.d("Toolbar","Acción del carrousel")
            R.id.action_settings ->  Log.d("Toolbar","Acción de las preferencias")
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}