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
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectController()
        observeModel()
    }

    private fun connectController() {
        // TODO
    }

    private fun observeModel() {
        model.ioError().observe(viewLifecycleOwner) { showIoError(it) }
    }

    private fun showIoError(it: String) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }
}
