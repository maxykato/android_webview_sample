package com.astool.android.android_webview_sample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_google_sign_in.*

/**
 *
 * Created by hiasa on 2019/01/19.
 */

class GoogleSignInActivity: AppCompatActivity() {

    companion object {
        const val requestCode = 9001
    }

    private val googleSignInOptions: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(this.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
    }

    // - Life Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLayout()
        setupBindings()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == Companion.requestCode) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val googleAccount = task.getResult(ApiException::class.java)
            if (googleAccount == null) {
                Toast.makeText(this , "失敗しました。", Toast.LENGTH_SHORT).show()
                return
            }

            Toast.makeText(this , "成功しました。", Toast.LENGTH_SHORT).show();
        }
    }

    // - Private
    private fun setupLayout() {
        setContentView(R.layout.activity_google_sign_in)
        originalSignInButton.setSize(SignInButton.SIZE_STANDARD);
    }

    private fun setupBindings() {
        customSignInButton.setOnClickListener {
            signInGoogle()
        }

        originalSignInButton.setOnClickListener {
            signInGoogle()
        }
    }

    @Throws(Exception::class)
    private fun signInGoogle() {
        val intent = getGoogleSignInIntent(this)
                ?: throw IllegalStateException("Failure create google login client. So please check the activation.")
        startActivityForResult(intent, requestCode)
    }

    private fun getGoogleSignInIntent(context: Context): Intent? {
        val googleSignInClient = getGoogleSignInClient(context)
        return googleSignInClient.signInIntent
    }

    private fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        return GoogleSignIn.getClient(context, googleSignInOptions)
    }

}