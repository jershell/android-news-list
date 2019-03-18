package com.github.jershell.newsaplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.view.Window
import androidx.navigation.NavController
import androidx.navigation.Navigation
import android.net.ConnectivityManager
import android.widget.Button
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private fun onPressNavBarMenuItem(menuItem: MenuItem): Boolean {
        Log.i("MainActivity", "onPressNavBarMenuItem ${menuItem.title ?: ""}")
        return when (menuItem.itemId) {
            R.id.menu_item_id_overview -> {
                navController.navigate(R.id.overviewFragment)
                true
            }
            R.id.menu_item_id_home -> {
                navController.popBackStack()
                true
            }
            R.id.menu_item_id_notifications -> {
                navController.navigate(R.id.notificationsFragment)
                true
            }
            R.id.menu_item_id_search -> {
                navController.navigate(R.id.searchFragment)
                true
            }
            R.id.menu_item_id_profile -> {
                navController.navigate(R.id.profileFragment)
                true
            }
            else -> false
        }
    }

    fun navigateToDetail(itemPosition: Int) {
        val bundle = Bundle()
        bundle.putInt("itemPosition", itemPosition)
        navController.navigate(R.id.newsDetailFragment, bundle)
    }

    fun dialog(action: () -> Unit) {
        val dialog = Dialog(this, R.style.AppTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_connection_error)
        dialog
            .findViewById<Button>(R.id.dialog_connection_error_button)
            .setOnClickListener {
                if(isOnline()) {
                    action()
                    dialog.hide()
                }
            }
        dialog.show()
    }

    fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    fun ifOnline(action: () -> Unit) {
        if (isOnline()) {
            action()
        } else {
            dialog(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        toolbar.setNavigationIcon(R.drawable.ic_back)


        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            onPressNavBarMenuItem(menuItem)
        }
    }
}
