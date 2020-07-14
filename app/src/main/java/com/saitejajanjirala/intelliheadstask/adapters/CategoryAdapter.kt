package com.saitejajanjirala.intelliheadstask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saitejajanjirala.intelliheadstask.R
import com.saitejajanjirala.intelliheadstask.listeners.onCategoryListener
import com.saitejajanjirala.intelliheadstask.listeners.onSubCategoryListener
import com.saitejajanjirala.intelliheadstask.models.Category

class CategoryAdapter(val list: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categorycheckbox:CheckBox=itemView.findViewById(R.id.categoryitemheckbox)
        val categoryname:TextView=itemView.findViewById(R.id.categoryitemname)
        val categoryrecyclerview:RecyclerView=itemView.findViewById(R.id.subcategoryrecyclerview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item,
                parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val obj=list[position]
        holder.categoryname.text=obj.name
        val madapter=SubCategoryAdapter(obj.subCategories,holder.categorycheckbox)
        holder.categoryrecyclerview.adapter=madapter
       // holder.categoryrecyclerview.
    }

}