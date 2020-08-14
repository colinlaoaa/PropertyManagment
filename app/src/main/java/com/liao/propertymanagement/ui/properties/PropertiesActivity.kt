package com.liao .propertymanagement.ui.properties

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.liao.propertymanagement.R
import com.liao.propertymanagement.adapters.AdapterProperties
import com.liao.propertymanagement.databinding.ActivityPropertiesBinding
import com.liao.propertymanagement.helper.toolbar
import com.liao.propertymanagement.view_model.properties.GetPropertiesViewModel

class PropertiesActivity : AppCompatActivity() {
    private lateinit var viewModel: GetPropertiesViewModel
    lateinit var binding: ActivityPropertiesBinding
    private val mAdapter = AdapterProperties(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_properties)
        toolbar("Properties")
        viewModel = ViewModelProvider(this).get(GetPropertiesViewModel::class.java)
        binding.viewModel = viewModel
        observerData()
        initList()


        init()
    }

    private fun initList() {
        binding.recyclerViewProperties.apply {
            adapter = mAdapter

        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.onRefreshButtonClicked()
        }
    }

    private fun observerData() {
       viewModel.getPropertiesObservation().observe(this, Observer {
           binding.refreshLayout.isRefreshing = false
           if (it != null) {
               mAdapter.setData(it)
           }

       })
    }

    private fun init() {
        viewModel.makeCallGetPropertiesInfo()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.add_menu_bar -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,PropertiesFragment(viewModel)).addToBackStack("").commit()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}