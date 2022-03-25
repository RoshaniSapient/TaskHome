package com.example.hometask.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.hometask.R
import com.example.hometask.model.stockList

class stockAdaptor(var Stockdata : List<stockList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var listDataSearch : List<stockList> = ArrayList()
    init {
       listDataSearch = Stockdata
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.stoc_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listDataSearch.size
    }


    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(stockItem : stockList) {
            view.Text = stockItem.name
            view.Description = stockItem.Description
        }
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    listDataSearch = Stockdata as ArrayList<stockList>
                } else {
                    val resultList = ArrayList<stockList>()
                    for (row in Stockdata) {
                        if (row.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    listDataSearch = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listDataSearch
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listDataSearch = results?.values as ArrayList<stockList>
                notifyDataSetChanged()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(listDataSearch[position]);
    }


}




