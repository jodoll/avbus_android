package com.johannesdoll.avbus.ui.remotecontrol.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.johannesdoll.avbus.R
import com.johannesdoll.avbus.core.entity.Command
import com.johannesdoll.avbus.core.entity.Command.AmpCommand
import kotlinx.android.synthetic.main.fragment_remote_control.*


class RemoteControlFragment : Fragment() {

    companion object {
        fun newInstance() = RemoteControlFragment()
    }

    private val controller: RemoteControlContract.Controller by viewModels<RemoteControlViewModel>()
    private val model: RemoteControlContract.Model by viewModels<RemoteControlViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_remote_control, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectController()
        observeModel()
    }

    private fun connectController() {
        tunerButton.setOnClickListener { AmpCommand.INPUT_TAPE_MON.send() }
        phonoButton.setOnClickListener { AmpCommand.INPUT_PHONO.send() }
        cdButton.setOnClickListener { AmpCommand.INPUT_CD.send() }
        tapeButton.setOnClickListener { AmpCommand.INPUT_TAPE_MON.send() }
        tvButton.setOnClickListener { AmpCommand.INPUT_TV.send() }
        videoButton.setOnClickListener { AmpCommand.INPUT_VIDEO.send() }
        vcrButton.setOnClickListener { AmpCommand.INPUT_VCR.send() }
    }

    private fun Command.send() {
        controller.sendCommand(this)
    }

    private fun observeModel() {
        model.ioError().observe(viewLifecycleOwner) { showIoError(it) }
    }

    private fun showIoError(it: String) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }
}
