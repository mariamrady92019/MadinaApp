package com.example.madinaapp.ui.needs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.madinaapp.R
import com.example.madinaapp.databinding.NeedsRecyclerItemBinding

class NeedsAdapter (val allNeeds:List<NeedsModel>): RecyclerView.Adapter<NeedsAdapter.NeedsViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeedsViewHolder {
        var item : NeedsRecyclerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.needs_recycler_item,parent, false
        )
      return NeedsViewHolder(item)

    }

    override fun onBindViewHolder(holder: NeedsViewHolder, position: Int) {
        val model= allNeeds[position]

           holder.bind(model)
    }

    override fun getItemCount(): Int {
      return allNeeds.size
    }

    class NeedsViewHolder(val item : NeedsRecyclerItemBinding):RecyclerView.
    ViewHolder(item.root){
        fun bind(needsmodel : NeedsModel){
            item.model=needsmodel
            item.executePendingBindings()
        }
    }
}
