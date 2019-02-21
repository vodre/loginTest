package com.vodrex.login_sample.ui.activities



import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.vodrex.login_sample.R
import com.vodrex.login_sample.ui.fragments.CompaniesFragment
import kotlinx.android.synthetic.main.activity_companies.*

class CompaniesActivity : AppCompatActivity(){

    private var fragment : CompaniesFragment = CompaniesFragment()
    private var isFiltering = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_companies)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()

        filter.setOnClickListener { filterClick() }
    }

    private fun showCompanyList() {
        filter.text = getString(R.string.action_todos)
        val builder = AlertDialog.Builder(this)
        builder.setItems(fragment.getCompanies()?.toTypedArray()) { _, which ->
            fragment.filterByCompanyName(which)
        }

        builder.show()

    }

    private fun filterClick() {
        if (isFiltering){
            filter.text = getString(R.string.filter)
            fragment.getAllCompanies()
            isFiltering = false
        }else{
            filter.text = getString(R.string.action_todos)
            isFiltering = true
            showCompanyList()
        }
    }
}
