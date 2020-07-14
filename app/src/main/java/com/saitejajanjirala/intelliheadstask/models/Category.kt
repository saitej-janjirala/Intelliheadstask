package com.saitejajanjirala.intelliheadstask.models

data class Category(var id:Int=0,
                    var name:String="",
                    var parent_id:Int=0,
                    var subCategories: ArrayList<SubCategory> =ArrayList<SubCategory>()
                )        {
}