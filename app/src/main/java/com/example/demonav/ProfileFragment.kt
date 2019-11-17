package com.example.demonav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.demonav.host.HostViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private val viewModel: HostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAuthenticationStatus()
    }

    private fun observeAuthenticationStatus() {
        viewModel.status.observe(viewLifecycleOwner,
            Observer {
                processAuthenticationStatus(it)
            })
    }

    private fun processAuthenticationStatus(authenticationStatus: HostViewModel.Status) {
        when (authenticationStatus) {
            is HostViewModel.Status.Unauthenticated -> navigateToLogin()
            is HostViewModel.Status.Authenticated -> showGreeting(authenticationStatus.username)
            else -> navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(
            ProfileFragmentDirections
                .actionProfileFragmentToLoginFragment()
        )
    }

    private fun showGreeting(username: String) {
        tvWelcome.text = "Welcome, $username"
    }
}
