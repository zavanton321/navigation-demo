package com.example.demonav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendLastName()

        navigateToNested()
    }

    private fun navigateToNested() {
        btnNestedGraph.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections
                    .actionMainFragmentToNestedGraph()
            )
        }
    }

    private fun sendLastName() {
        btnSend.setOnClickListener {
            val directions = MainFragmentDirections
                .actionMainFragmentToDetailFragment(etLastName.text.toString())

            Navigation.findNavController(it).navigate(directions)
        }
    }
}
