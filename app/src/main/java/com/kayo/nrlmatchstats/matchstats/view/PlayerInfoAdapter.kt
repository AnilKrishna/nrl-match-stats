package com.kayo.nrlmatchstats.matchstats.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kayo.nrlmatchstats.R
import com.kayo.nrlmatchstats.matchstats.model.TopPlayer
import kotlinx.android.synthetic.main.player_info_card.view.*

class PlayerInfoAdapter(private val playerInfo: List<TopPlayer>, private val clickListener: (TopPlayer) -> Unit) : RecyclerView.Adapter<PlayerInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context)
            .inflate(R.layout.player_info_card,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return playerInfo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = playerInfo[position]
        Glide.with(holder.itemView.context)
            .load("http://media.foxsports.com.au/match-centre/includes/images/headshots/nrl/${player.id}.jpg")
            .placeholder(R.drawable.player_headshot_blank_large)
            .into(holder.imageView)
        //holder.imageView.setImageResource(R.mipmap.ic_launcher)
        holder.textViewName.text = player.short_name
        holder.textViewJumper.text = player.jumper_number.toString()
        holder.textViewPosition.text  = player.position
        holder.textViewStat.text = player.stat_value.toString()

        holder.itemView.setOnClickListener {  clickListener(player) }

    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.profileImageView
        val textViewName : TextView = itemView.playerNameTextView
        val textViewJumper : TextView = itemView.jumperTextView
        val textViewPosition : TextView = itemView.positionTextView
        val textViewStat : TextView = itemView.statValueTextView
    }
}