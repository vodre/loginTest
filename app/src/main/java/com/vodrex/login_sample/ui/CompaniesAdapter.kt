package com.vodrex.login_sample.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ensar.tmdbkotlin.utils.DefaultDiffUtil
import com.vodrex.login_sample.db.entities.Company
import com.vodrex.mdb.ui.CompanyViewHolder

class CompaniesAdapter(private val listener: OnClickListener) :
    ListAdapter<Company, CompanyViewHolder>(DefaultDiffUtil<Company>()) {

    var companies: List<Company>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface OnClickListener {
        fun onItemClicked(view: View, item: Company)
    }
}

