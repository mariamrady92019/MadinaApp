package com.example.madinaapp.ui.weekend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.madinaapp.R
import com.example.madinaapp.databinding.FragmentWeekendBinding
import com.example.madinaapp.ui.AddBottomSheet_Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeekendFragment : Fragment() {

    private var _binding: FragmentWeekendBinding? = null
    private val binding get() = _binding!!

    var addButton: FloatingActionButton?=null



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(WeekendViewModel::class.java)

        _binding = FragmentWeekendBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addButton = _binding?.addBtn
        setAddBtnClickListener()


    }
    fun setAddBtnClickListener() {

        addButton?.setOnClickListener {

            showBottomSheet()
        }
    }


    fun showBottomSheet() {
        val bottomSheet = AddBottomSheet_Fragment().show(this.parentFragmentManager,"")




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}