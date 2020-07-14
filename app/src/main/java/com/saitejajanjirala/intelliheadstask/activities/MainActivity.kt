package com.saitejajanjirala.intelliheadstask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.saitejajanjirala.intelliheadstask.R
import com.saitejajanjirala.intelliheadstask.adapters.CategoryAdapter
import com.saitejajanjirala.intelliheadstask.models.Category
import com.saitejajanjirala.intelliheadstask.models.SubCategory
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity(){
    private lateinit var categorylist:ArrayList<Category>
    private lateinit var adapter:CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        categorylist= ArrayList()
        getdata()
    }
    private fun getdata(){
        progresslayout.visibility=View.VISIBLE
        val queue=Volley.newRequestQueue(this@MainActivity)
        val url="https://run.mocky.io/v3/f79cbce1-a70e-4313-8d76-00d19ee3b4c1"
        val jsonArrayRequest=object:JsonArrayRequest(Request.Method.GET,url,null,
            Response.Listener<JSONArray>{
                categorylist.clear()
            for(i in 0 until it.length()){
                val jsonobj=it.getJSONObject(i)
                val obj=Category(
                    jsonobj.getInt("id"),jsonobj.getString("name"),
                    jsonobj.getInt("parent_id"))
                val sublist=ArrayList<SubCategory>()
                if(jsonobj.has("subCategory")) {
                    val array=jsonobj.getJSONArray("subCategory")
                        for (j in 0 until array.length()) {
                            val subjsonobj = array.getJSONObject(j)
                            val subobj = SubCategory(
                                subjsonobj.getInt("id"),
                                subjsonobj.getString("name"),
                                subjsonobj.getInt("parent_id")
                            )
                            sublist.add(subobj)
                        }
                }
                obj.subCategories=sublist
                categorylist.add(obj)
            }
                adapter= CategoryAdapter(categorylist)
                val dividerItemDecoration=DividerItemDecoration(this@MainActivity,
                LinearLayoutManager.VERTICAL)
                categoryrecyclerview.addItemDecoration(dividerItemDecoration)
                categoryrecyclerview.adapter=adapter
                Log.i("list of categories","$categorylist")
                progresslayout.visibility=View.GONE
        },
        Response.ErrorListener {
            progresslayout.visibility=View.GONE
            Toast.makeText(this@MainActivity, it.message.toString(),
                Toast.LENGTH_SHORT).show()
        }){}
        queue.add(jsonArrayRequest)
    }
}