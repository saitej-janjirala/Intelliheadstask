package com.saitejajanjirala.intelliheadstask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saitejajanjirala.intelliheadstask.R
import com.saitejajanjirala.intelliheadstask.listeners.onSubCategoryListener
import com.saitejajanjirala.intelliheadstask.models.SubCategory

class SubCategoryAdapter (val list:ArrayList<SubCategory>,val parencheckbox:CheckBox):
    RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>(){
    var checksubitems:Boolean=false
    var count:Int=0
    class SubCategoryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val subitemname:TextView=itemView.findViewById(R.id.subcategoryitemname)
        val subitemcheckox:CheckBox=itemView.findViewById(R.id.subcategoryitemheckbox)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.subcategory_item,parent,
            false)
        return SubCategoryViewHolder(view)
    }

    override fun getItemCount()=list.size
    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        val obj=list[position]
        holder.subitemname.text=obj.name
        parencheckbox.setOnCheckedChangeListener { p0, p1 ->
            if(p1) {
                if (count !in 1 until list.size) {
                    checksubitems = p1
                    notifyDataSetChanged()
                }
            }
            else{
                checksubitems = p1
                notifyDataSetChanged()
            }
        }
        holder.subitemcheckox.isChecked = checksubitems
        holder.subitemcheckox.setOnCheckedChangeListener { p0, p1 ->
            if(p1){
                count+=1
                parencheckbox.isChecked=true
            } else{
                count-=1
                if(count==0){
                    parencheckbox.isChecked=false
                }
            }
        }
    }
}