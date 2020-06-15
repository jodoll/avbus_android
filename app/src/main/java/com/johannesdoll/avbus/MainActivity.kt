package com.johannesdoll.avbus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.johannesdoll.avbus.ui.remotecontrol.view.RemoteControlFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RemoteControlFragment.newInstance())
                .commitNow()
        }
    }

}
