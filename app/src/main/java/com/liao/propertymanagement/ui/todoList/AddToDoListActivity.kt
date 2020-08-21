package com.liao.propertymanagement.ui.todoList

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.liao.propertymanagement.R
import com.liao.propertymanagement.adapters.AdapterTodoList
import com.liao.propertymanagement.databinding.ActivityAddToDoListBinding
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.model.TodoList
import com.liao.propertymanagement.view_model.todo_list.TodoListViewModel
import kotlinx.android.synthetic.main.activity_add_to_do_list.*

class AddToDoListActivity : AppCompatActivity(), AdapterTodoList.AdapterInteraction {
    private lateinit var viewModel: TodoListViewModel
    private val mAdapter = AdapterTodoList(this)
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

    private fun initList() {
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
        }

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

        viewModel.closeFragmentObserver().observe(this, Observer {
            if(it == true){
                supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.container)!!).commit()
            }
        })
    }


    private fun init() {
        toolbar("Add notes")



        button_add_note.setOnClickListener {
            var addTodoListFragment =
                AddTodoListFragment()

                supportFragmentManager.beginTransaction().replace(R.id.container, addTodoListFragment).addToBackStack("")
                    .commit()

        }
        mAdapter.setListener(this)
    }

    override fun clickEvent(item: TodoList) {
        viewModel.removeButtonClicked(item)

    }

    override fun clickEvent2(item: TodoList) {
        var todoListFragment = TodoListFragment.newInstance(item, "")

            supportFragmentManager.beginTransaction().replace(R.id.container, todoListFragment).addToBackStack("")
                .commit()
        viewModel.clearUpdateStatus()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar_add, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.add_menu_bar -> {
                var addTodoListFragment =
                    AddTodoListFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, addTodoListFragment).addToBackStack("")
                        .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}