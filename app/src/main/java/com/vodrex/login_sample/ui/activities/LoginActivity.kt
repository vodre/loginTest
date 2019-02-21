package com.vodrex.login_sample.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import com.vodrex.login_sample.db.entities.Data
import com.vodrex.login_sample.db.entities.LoginRequest
import com.vodrex.login_sample.viewmodel.LoginViewModel

import kotlinx.android.synthetic.main.activity_login.*
import androidx.lifecycle.Observer
import com.vodrex.login_sample.R
import com.vodrex.login_sample.utils.PreferencesHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(){

    private val viewModel by viewModel<LoginViewModel>()
    val preferencesHelper by lazy {PreferencesHelper(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        viewModel.loginLiveData.observe(this, Observer {response ->


            response.error?.let{
                showError(it.title, it.message)
            } ?: run {
                preferencesHelper.userToken = response.token
                preferencesHelper.userId = response.idUser.toString()
                val intent = Intent(this, CompaniesActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        })

        email_sign_in_button.setOnClickListener { attemptLogin() }
    }

    private fun showError(title: String?, message: String?) {
        AlertDialog.Builder(this@LoginActivity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { _, _ -> showProgress(false)}
            .setOnCancelListener { _ -> showProgress(false)}
            .create()
            .show()
    }


    private fun attemptLogin() {
        // Reset errors.
        agent.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val emailStr = agent.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            agent.error = getString(R.string.error_field_required)
            focusView = agent
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            agent.error = getString(R.string.error_invalid_email)
            focusView = agent
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        } else {
            val loginRequest = LoginRequest(
                Data(
                    pass= password.text.toString(),
                    user = agent.text.toString())
            )
            viewModel.loginCredentials(loginRequest)
            showProgress(true)
        }
    }

    private fun isEmailValid(agent: String): Boolean {
        return !agent.contains(" ")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_progress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
        } else {
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }
}
