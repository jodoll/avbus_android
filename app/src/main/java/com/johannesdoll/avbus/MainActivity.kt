package com.johannesdoll.avbus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.johannesdoll.avbus.ui.remotecontrol.view.RemoteControlFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        device_view_pager.apply {
            adapter = RemoteControlDeviceAdapter()
        }
    }

    private inner class RemoteControlDeviceAdapter : FragmentStateAdapter(this) {
        private val pages = listOf<(() -> Fragment)>(
            { RemoteControlFragment.newInstance() },
            { RemoteControlFragment.newInstance() }
        )

        override fun getItemCount() = pages.size

        override fun createFragment(position: Int) = pages[position].invoke()
    }

}
