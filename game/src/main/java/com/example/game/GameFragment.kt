package com.example.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToWin()
        navigateToLose()
    }

    private fun navigateToWin() {
        btnWin.setOnClickListener {
            findNavController().navigate(
                GameFragmentDirections
                    .actionGameFragmentToWinFragment()
            )
        }
    }

    private fun navigateToLose() {
        btnLose.setOnClickListener {
            findNavController().navigate(
                GameFragmentDirections
                    .actionGameFragmentToLoseFragment()
            )
        }
    }
}