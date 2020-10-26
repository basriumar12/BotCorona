package com.basri.coroinfo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basri.coroinfo.R
import com.basri.coroinfo.entity.DataAdapter
import kotlinx.android.synthetic.main.item_adapter.view.*


class Adapter(val data: ArrayList<DataAdapter>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {

        if (data.get(position).status.equals(false)){
            holder.itemView.itemSender.visibility = View.VISIBLE
            holder.itemView.itemReceiver.visibility = View.GONE
            holder.itemView.tvSenderMessage.text = data.get(position).data
        } else{
            holder.itemView.itemReceiver.visibility = View.VISIBLE
            holder.itemView.itemSender.visibility = View.GONE
            holder.itemView.tvReceiverMessage.text = data.get(position).data
        }

    }


}