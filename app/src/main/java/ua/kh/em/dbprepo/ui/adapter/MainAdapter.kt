package ua.kh.em.dbprepo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ua.kh.em.dbprepo.R
import ua.kh.em.dbprepo.data.model.Fruit


class MainAdapter (
    private var itemList: List<Fruit>,
    private var clickListener: View.OnClickListener
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemFruit.text = item.fruitName
        holder.itemView.tag = item
        holder.itemView.setOnClickListener(clickListener)
    }

    inner class MainViewHolder(itemView: View): ViewHolder(itemView) {
        var itemFruit: TextView = itemView.findViewById(R.id.name_fruit)
    }

    fun showListItems(listItems: List<Fruit>) {
        itemList = listItems
        notifyDataSetChanged()
    }

}


