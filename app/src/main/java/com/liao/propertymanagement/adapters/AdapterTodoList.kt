package com.liao.propertymanagement.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liao.propertymanagement.databinding.NewRowToDoListBinding
import com.liao.propertymanagement.model.TodoList


class AdapterTodoList(private val context: Context) :
    RecyclerView.Adapter<AdapterTodoList.MyViewHolder>() {

    private var mylistener:AdapterInteraction? = null

    private var listItem = ArrayList<TodoList>()

    fun setData(list: List<TodoList>) {
        listItem.clear()
        listItem.addAll(list)
        notifyDataSetChanged()

    }

    inner class MyViewHolder(private val binding: NewRowToDoListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoList) {
            binding.item = item
            binding.adapter = this@AdapterTodoList

            binding.executePendingBindings()
            binding.foldingCell.setOnClickListener {
                binding.foldingCell.toggle(false)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = NewRowToDoListBinding.inflate(LayoutInflater.from(parent.context))
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

    interface AdapterInteraction {
        fun clickEvent(item: TodoList)
        fun clickEvent2(item: TodoList)
    }
    fun setListener(adapterInteraction: AdapterInteraction){
        mylistener = adapterInteraction
    }

    fun onItemClicked(item: TodoList) {
        //Toast.makeText(context, item.num, Toast.LENGTH_SHORT).show()
        mylistener?.clickEvent(item)
    }
    fun onItemClicked2(item: TodoList) {
        mylistener?.clickEvent2(item)
    }

}