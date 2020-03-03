package com.johannesdoll.avbus.ui.remotecontrol.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.johannesdoll.avbus.R

class RemoteControlFragment : Fragment() {

    companion object {
        fun newInstance() = RemoteControlFragment()
    }

    private val viewModel: RemoteControlViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

}
