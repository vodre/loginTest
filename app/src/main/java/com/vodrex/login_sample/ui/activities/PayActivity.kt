package com.vodrex.login_sample.ui.activities


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.vodrex.login_sample.R
import com.vodrex.login_sample.databinding.ActivityPaymentsBinding
import com.vodrex.login_sample.viewmodel.PaymentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.R.string.no
import android.R.string.yes
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.ensar.tmdbkotlin.utils.setImageUrl
import com.ensar.tmdbkotlin.utils.toMoneyFormat
import com.squareup.picasso.Picasso
import com.vodrex.login_sample.generated.callback.OnClickListener
import com.vodrex.login_sample.utils.PreferencesHelper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_payments.*
import kotlinx.android.synthetic.main.dialog_confirmation.*
import java.text.SimpleDateFormat
import java.util.*


class PayActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel by viewModel<PaymentsViewModel>()
    val preferencesHelper by lazy {PreferencesHelper(this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityPaymentsBinding>(
            this@PayActivity, R.layout.activity_payments
        )

        viewModel.getCompany(intent.getStringExtra("company_name"))

        viewModel.company.observe(this, Observer {

        })

        binding.viewModel = viewModel
        binding.listener = this
    }

    override fun onClick(v: View?) {

        amount.error = null
        phone_number.error = null

        // Store values at the time of the login attempt.
        val phoneStr = phone_number.text.toString()
        val amountStr = amount.text.toString()


        if (TextUtils.isEmpty(phoneStr)) {
            phone_number.error = getString(R.string.error_field_required)
            return
        }

        if (TextUtils.isEmpty(amountStr)) {
            amount.error = getString(R.string.error_field_required)
            return
        }

        showConfirmationDialog()
    }

    private fun showConfirmationDialog() {
        val factory = LayoutInflater.from(this)
        val confirmationDialogView = factory.inflate(R.layout.dialog_confirmation, null)
        val confirmationDialog = AlertDialog.Builder(this).create()
        confirmationDialog.setView(confirmationDialogView)

        confirmationDialogView.findViewById<View>(R.id.confirmation).setOnClickListener {
            confirmationDialog.dismiss()
            showSuccessDialog()
        }

        confirmationDialogView.findViewById<View>(R.id.cancelation).setOnClickListener {
            confirmationDialog.dismiss()
        }

        confirmationDialogView.findViewById<TextView>(R.id.tv_id).text = preferencesHelper.userId
        confirmationDialogView.findViewById<TextView>(R.id.tv_time).text = getTime()
        confirmationDialogView.findViewById<TextView>(R.id.tv_date).text = getDate()
        confirmationDialogView.findViewById<TextView>(R.id.tv_phone_number).text = phone_number.text
        confirmationDialogView.findViewById<TextView>(R.id.tv_amount).text = amount.text.toString().toMoneyFormat()
        confirmationDialogView.findViewById<ImageView>(R.id.logo)?.let{
            Picasso.get().load(viewModel.company.value?.imagePath?: R.drawable.ic_logo).into(it)
        }

        confirmationDialog.show()
    }

    private fun showSuccessDialog() {
        val factory = LayoutInflater.from(this)
        val succcessDialogView = factory.inflate(R.layout.dialog_success, null)
        val successDialog = AlertDialog.Builder(this).create()
        successDialog.setView(succcessDialogView)

        succcessDialogView.findViewById<View>(R.id.confirmation).setOnClickListener {
            val intent = Intent(this, CompaniesActivity::class.java)
            startActivity(intent)
            finish()
        }

        successDialog.show()
    }

    private fun getDate(): String {
        val c = Calendar.getInstance()
        return "${c.get(Calendar.YEAR).toString().substring(1)}/${c.get(Calendar.MONTH).toString()}/${c.get(Calendar.DATE).toString()}"
    }

    private fun getTime(): String {
        val c = Calendar.getInstance()
       return "${c.get(Calendar.HOUR).toString()}:${c.get(Calendar.MINUTE).toString()}:${c.get(Calendar.SECOND).toString()}"

    }

}
