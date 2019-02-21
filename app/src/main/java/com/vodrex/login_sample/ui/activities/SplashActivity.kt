package com.vodrex.login_sample.ui.activities



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import com.vodrex.login_sample.R
import com.vodrex.login_sample.ui.fragments.CompaniesFragment
import com.vodrex.login_sample.utils.PreferencesHelper

class SplashActivity : AppCompatActivity(){

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 5000 //5 seconds
    val preferencesHelper by lazy { PreferencesHelper(this) }

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(500)

            var intent = Intent()
            if (preferencesHelper.userId.isNullOrEmpty()){
                intent = Intent(applicationContext, LoginActivity::class.java)
            }else{
                intent = Intent(applicationContext, CompaniesActivity::class.java)
            }


            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
