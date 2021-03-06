package com.liao.propertymanagement.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liao.propertymanagement.R
import com.liao.propertymanagement.ui.properties.PropertiesActivity
import com.liao.propertymanagement.ui.todoList.AddToDoListActivity
import kotlinx.android.synthetic.main.fragment_landlord_home.view.*
import kotlinx.android.synthetic.main.fragment_tanents_home.view.*
import kotlinx.android.synthetic.main.fragment_tanents_home.view.animatedIconView
import tarek360.animated.icons.IconFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TenantsHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TenantsHomeFragment : Fragment() {
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
        var view = inflater.inflate(R.layout.fragment_tanents_home, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        view.animatedIconView.setAnimatedIcon(IconFactory.iconNotificationAlert())
        view.animatedIconView.setOnClickListener { view.animatedIconView.startAnimation() }
        view.image_view_todo_list_t.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    AddToDoListActivity::class.java
                )
            )
        }
        view.image_view_properties_t.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    PropertiesActivity::class.java
                )
            )
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TanentsHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TenantsHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}