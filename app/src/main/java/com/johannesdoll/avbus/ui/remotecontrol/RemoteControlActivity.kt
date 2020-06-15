package com.johannesdoll.avbus.ui.remotecontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.johannesdoll.avbus.R
import com.johannesdoll.avbus.ui.remotecontrol.view.RemoteControlFragment
import kotlinx.android.synthetic.main.activity_remote_control.*

class RemoteControlActivity : AppCompatActivity(R.layout.activity_remote_control) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        val pageAdapter = RemoteControlDeviceAdapter()
        deviceViewPager.apply {
            adapter = pageAdapter
        }
        TabLayoutMediator(tabLayout, deviceViewPager) { tab, position ->
            tab.text = pageAdapter.getTitle(position)
        }.attach()
    }

    private inner class RemoteControlDeviceAdapter : FragmentStateAdapter(this) {
        private val pages = listOf(
            Page(getString(R.string.remote_control_device_amp)) { RemoteControlFragment.newInstance() },
            Page(getString(R.string.remote_control_device_amp)) { RemoteControlFragment.newInstance() }
        )

        fun getTitle(position: Int) = pages[position].title

        override fun getItemCount() = pages.size

        override fun createFragment(position: Int) = pages[position].factory.invoke()

    }

    data class Page(
        val title: String,
        val factory: (() -> Fragment)
    )

}
