package com.example.demonav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_final.*

class FinalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_final, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnReturnToExisting.setOnClickListener(
            Navigation
                .createNavigateOnClickListener(R.id.action_return_to_existing_mainFragment)
        )

        btnReturnToNew.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_return_and_create_new_mainFragment)
        )
    }
}
