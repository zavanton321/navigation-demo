package com.example.demonav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvDetail.text = args.lastName

        // navigate using generated class (by safe args gradle plugin)
        btnPlayGame.setOnClickListener {
            Navigation.findNavController(tvDetail).navigate(
                DetailFragmentDirections
                    .actionDetailFragmentToGameGraph()
            )
        }

//        // convenience method for a button
//        btnPlayGame.setOnClickListener(
//            Navigation
//                .createNavigateOnClickListener(R.id.action_detailFragment_to_gameGraph)
//        )

//        // navigate using id
//        btnPlayGame.setOnClickListener {
//            Navigation.findNavController(tvDetail)
//                .navigate(R.id.action_detailFragment_to_gameGraph)
//        }
    }
}
