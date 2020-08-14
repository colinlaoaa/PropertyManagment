package com.liao.propertymanagement.ui.actitvies.todoList

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.liao.propertymanagement.R
import com.liao.propertymanagement.adapters.AdapterTodoList
import com.liao.propertymanagement.databinding.ActivityAddToDoListBinding
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.model.TodoList
import com.liao.propertymanagement.ui.fragment.todoList.AddTodoListFragment
import com.liao.propertymanagement.ui.fragment.todoList.TodoListFragment
import com.liao.propertymanagement.viewModel.todoList.TodoListViewModel
import kotlinx.android.synthetic.main.activity_add_to_do_list.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_add_todo_list.view.*
import kotlinx.android.synthetic.main.fragment_todo_list.*
import kotlinx.android.synthetic.main.fragment_todo_list.view.*

class AddToDoListActivity : AppCompatActivity(), AdapterTodoList.AdapterInteraction {
    private lateinit var viewModel: TodoListViewModel
    private val mAdapter = AdapterTodoList(this)
    private lateinit var binding: ActivityAddToDoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_to_do_list)
        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)
        binding.viewModel= viewModel
        observerData()
        initList()

        init()
    }

    private fun initList() {
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
        toolbar("Add notes")
        button_add_note.setOnClickListener {
            var addTodoListFragment = AddTodoListFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, addTodoListFragment)
                .addToBackStack("").commit()
        }
        mAdapter.setListener(this)
    }

    override fun clickEvent(item: TodoList) {
        viewModel.removeButtonClicked(item)

    }

    override fun clickEvent2(item: TodoList) {
        var todoListFragment = TodoListFragment.newInstance(item,"")
        supportFragmentManager.beginTransaction().replace(R.id.container, todoListFragment)
            .addToBackStack("").commit()
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}