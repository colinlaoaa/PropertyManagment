package com.liao.propertymanagement.ui.actitvies.todoList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.liao.propertymanagement.R
import com.liao.propertymanagement.adapters.AdapterTodoList
import com.liao.propertymanagement.databinding.ActivityAddToDoListBinding
import com.liao.propertymanagement.model.TodoList
import com.liao.propertymanagement.viewModel.todoList.TodoListViewModel
import kotlinx.android.synthetic.main.activity_add_to_do_list.*

class AddToDoListActivity : AppCompatActivity() , AdapterTodoList.AdapterInteraction{
    private lateinit var viewModel: TodoListViewModel
    val mAdapter = AdapterTodoList(this)
    private lateinit var binding: ActivityAddToDoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_to_do_list)
        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)
        binding.viewModel = viewModel
        observerData()
        initList()

        init()
    }

    private fun initList(){
        binding.recyclerView.apply {
            adapter = mAdapter

        }


    }

    private fun observerData() {
        viewModel.todoRepositoryObserver().observe(this, Observer {
            if (it != null) {
                mAdapter.setData(it)
            }
        })
    }


    private fun init() {
        button_add_note.setOnClickListener {

        }
        mAdapter.setListener(this)
    }

    override fun clickEvent(item: TodoList) {
        viewModel.removeButtonClicked(item)

    }
}