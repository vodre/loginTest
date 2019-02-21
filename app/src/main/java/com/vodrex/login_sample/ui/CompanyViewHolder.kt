package com.vodrex.mdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vodrex.login_sample.R
import com.vodrex.login_sample.databinding.ItemCompanyBinding
import com.vodrex.login_sample.db.entities.Company
import com.vodrex.login_sample.ui.CompaniesAdapter
import com.vodrex.login_sample.utils.getValue
import com.vodrex.login_sample.utils.toCompanyType


class CompanyViewHolder(private val itemBinding: ItemCompanyBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    companion object {
        fun create(parent: ViewGroup): CompanyViewHolder {

            val itemBiding = DataBindingUtil.inflate<ItemCompanyBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_company,
                parent,
                false
            )

            return CompanyViewHolder(itemBiding)
        }
    }

    fun bind(item: Company, listener: CompaniesAdapter.OnClickListener) {
        val types = item.secciones?.split("-")
        itemBinding.item = item
        itemBinding.typeOne = types?.get(0)?.toCompanyType()?.getValue()?: ""
        itemBinding.typeTwo = types?.get(1)?.toCompanyType()?.getValue()?: ""
        itemBinding.typeThree = types?.get(2)?.toCompanyType()?.getValue()?: ""
        itemBinding.listener = listener
        itemBinding.executePendingBindings()
    }
}