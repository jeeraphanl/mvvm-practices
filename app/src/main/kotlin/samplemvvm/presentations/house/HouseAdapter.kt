package samplemvvm.presentations.house

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.house_item.view.*
import samplemvvm.R
import samplemvvm.models.entities.HouseResponse


class HouseAdapter : RecyclerView.Adapter<ViewHolder>() {

    var houseList = mutableListOf<HouseResponse.House>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return HouseHolder(LayoutInflater.from(parent?.context).inflate(R.layout.house_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(houseList, position)
            }
        }
    }

    override fun getItemCount() = houseList.let { houseList.size }

    abstract inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(listSearchResult: List<HouseResponse.House>, position: Int)
    }

    inner class HouseHolder(view: View) : ItemViewHolder(view) {

        override fun bind(listSearchResult: List<HouseResponse.House>, position: Int) {

            val house = listSearchResult[position]

            itemView.apply {
                name_textview.text = house.castleName
            }
        }
    }
}