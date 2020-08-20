package com.liao.propertymanagement.ui.properties

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
class PropertiesFragment(private var mViewModel: GetPropertiesViewModel) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var mfragmentListener: FragmentListener? = null

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

        init(binding)


        return binding.root
    }

    interface FragmentListener {
        fun closeFragment()
    }
    fun setFragmentListener(fragmentListener: FragmentListener){
        mfragmentListener=fragmentListener
    }


    private fun init(binding: FragmentPropertiesBinding) {
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

        binding.root.photo_button.setOnClickListener {
            showBottomSheetDialog()
        }


    }

    private fun observerData() {
        viewModel.postPropertiesListRepositoryObserver().observe(requireActivity(), Observer {
            if (it!=null) {
                mViewModel.makeCallGetPropertiesInfo()
                viewModel.clearProperties()
                mfragmentListener?.closeFragment()

            }
        })

        viewModel.bitmapPhotoObserver().observe(requireActivity(), Observer {
            if (it !=null){
                var path = it.path
                binding.bitmapImageView.visibility = View.VISIBLE
                binding.btnOk.visibility = View.VISIBLE
                binding.btnOk.setOnClickListener {
                    binding.btnDelete.visibility = View.GONE
                    binding.btnOk.visibility = View.GONE
                    viewModel.uploadImageConfirm(path)
                    binding.btnOk.visibility = View.GONE
                }
                binding.bitmapImageView.setImageBitmap(it.photo)
                binding.bitmapImageView.setOnClickListener {
                    if(binding.btnDelete.visibility == View.GONE){
                        binding.btnDelete.visibility = View.VISIBLE
                    }else{
                        binding.btnDelete.visibility = View.GONE
                    }

                    binding.btnDelete.setOnClickListener {
                        binding.bitmapImageView.setImageBitmap(null)
                        binding.bitmapImageView.visibility = View.GONE
                        binding.btnDelete.visibility = View.GONE
                        binding.btnOk.visibility = View.GONE
                        viewModel.getImageLink("https://insidelatinamerica.net/wp-content/uploads/2018/01/noImg_2.jpg")
                    }

                }
            }
        })

        viewModel.imageLinkObserver().observe(requireActivity(), Observer {
            if (it!=null){
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                viewModel.getImageLink(it)
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

    private fun showBottomSheetDialog() {
        var bottomSheetFragment = PropertiesAddPicBottomSheetFragment(viewModel)
        bottomSheetFragment.show(activity?.supportFragmentManager!!, bottomSheetFragment.tag);
    }

}