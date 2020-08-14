package com.liao.propertymanagement.ui.properties

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.liao.propertymanagement.R
import com.liao.propertymanagement.databinding.FragmentPropertiesBinding
import com.liao.propertymanagement.view_model.properties.GetPropertiesViewModel
import com.liao.propertymanagement.view_model.properties.PostPropertiesViewModel
import kotlinx.android.synthetic.main.fragment_properties.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: PostPropertiesViewModel
private lateinit var binding: FragmentPropertiesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PropertiesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PropertiesFragment(private var mViewModel:GetPropertiesViewModel) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_properties, container, false)

        binding.root.check_box_propertyStatus_t.setOnClickListener {
            binding.root.check_box_propertyStatus_f.isChecked =
                !binding.root.check_box_propertyStatus_t.isChecked
        }

        binding.root.check_box_propertyStatus_f.setOnClickListener {
            if (binding.root.check_box_propertyStatus_f.isChecked) {
                viewModel.setPropertyStatusCheckboxFalse()
            } else {
                viewModel.setPropertyStatusCheckboxTrue()
            }
        }


        binding.root.check_box_mortageInfo_t.setOnClickListener {
            binding.root.check_box_mortageInfo_f.isChecked =
                !binding.root.check_box_mortageInfo_t.isChecked
        }

        binding.root.check_box_mortageInfo_f.setOnClickListener {
            if (binding.root.check_box_mortageInfo_f.isChecked) {
                viewModel.setMortageInfoCheckboxFalse()
            } else {
                viewModel.setMortageInfoCheckboxTrue()
            }
        }




        return binding.root
    }

    private fun observerData() {
        viewModel.postPropertiesListRepositoryObserver().observe(requireActivity(), Observer {
            if (!it.error){
                activity?.onBackPressed()
                mViewModel.makeCallGetPropertiesInfo()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(
            PostPropertiesViewModel::class.java
        )
        binding.viewModel = viewModel
        observerData()


    }

}