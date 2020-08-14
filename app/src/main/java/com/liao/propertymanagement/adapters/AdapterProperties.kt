package com.liao.propertymanagement.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liao.propertymanagement.databinding.NewRowPropertiesBinding
import com.liao.propertymanagement.model.Properties


class AdapterProperties(private val context: Context) :
    RecyclerView.Adapter<AdapterProperties.MyViewHolder>() {


    private var listItem = ArrayList<Properties>()

    fun setData(list: List<Properties>) {
        listItem.clear()
        listItem.addAll(list)
        notifyDataSetChanged()

    }

    inner class MyViewHolder(private val binding: NewRowPropertiesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Properties) {
            binding.item = item
            binding.adapter = this@AdapterProperties
            binding.executePendingBindings()

            binding.foldingCell.setOnClickListener {
                binding.foldingCell.toggle(false)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = NewRowPropertiesBinding.inflate(LayoutInflater.from(parent.context))
        var layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        binding.root.layoutParams = layoutParams
        return MyViewHolder(binding)
    }

    override fun getItemCount() = listItem.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listItem[position])


    }



}