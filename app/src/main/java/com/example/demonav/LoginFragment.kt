package com.example.demonav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.demonav.host.HostViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private val viewModel: HostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAuthenticationStatus()
        onLoginButtonClick()
        onQuitButtonClick()
    }

    private fun onQuitButtonClick() {
        btnQuit.setOnClickListener {
            findNavController().popBackStack(R.id.mainFragment, false)
        }
    }

    private fun observeAuthenticationStatus() {
        viewModel.status.observe(viewLifecycleOwner, Observer {
            processAuthenticationStatus(it)
        })
    }

    private fun processAuthenticationStatus(status: HostViewModel.Status) {
        when (status) {
            is HostViewModel.Status.Authenticated -> navigateToProfile()
            is HostViewModel.Status.InvalidAuthentication -> showLoginFail()
            else -> Log.d("zavanton", "zavanton - other")
        }
    }

    private fun navigateToProfile() {
        findNavController().popBackStack()
    }

    private fun showLoginFail() {
        Snackbar.make(vContainer, "Login details are incorrect!", Snackbar.LENGTH_SHORT).show()
    }

    private fun onLoginButtonClick() {
        btnLogin.setOnClickListener {
            viewModel.loginUser(etUsername.text.toString(), etPassword.text.toString())
        }
    }
}
