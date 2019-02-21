package com.vodrex.login_sample.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vodrex.login_sample.R
import com.vodrex.login_sample.db.entities.Company
import com.vodrex.login_sample.ui.CompaniesAdapter
import com.vodrex.login_sample.ui.activities.PayActivity
import com.vodrex.login_sample.viewmodel.CompaniesViewModel
import kotlinx.android.synthetic.main.fragment_companies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompaniesFragment : Fragment(), CompaniesAdapter.OnClickListener {

    private val viewModel by viewModel<CompaniesViewModel>()

    private val adapter = CompaniesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.companiesLiveData.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

    }

    fun getCompanies() : List<String?>? {
        return viewModel.companiesLiveData.value?.map{it.name}
    }

    fun getAllCompanies() {
        return viewModel.getCompanyList()
    }

    fun filterByCompanyName(position: Int){
        viewModel.companiesLiveData.value = viewModel.companiesLiveData.value?.filter { company ->
            company.name!!.contains(viewModel.companiesLiveData.value?.get(position)?.name?: "")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_companies, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        companies_recycler_view.adapter = adapter
        companies_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClicked(view: View, item: Company) {
        val intent = Intent(context, PayActivity::class.java)
        intent.putExtra("company_name", item.name)
        activity?.startActivity(intent)

    }

}
