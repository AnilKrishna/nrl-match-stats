package com.kayo.nrlmatchstats.matchstats.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kayo.nrlmatchstats.R
import com.kayo.nrlmatchstats.matchstats.model.Team
import kotlinx.android.synthetic.main.team_info_recycler.view.*

class TeamInfoAdapter(private val teamInfo: List<Team>) : RecyclerView.Adapter<TeamInfoAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context)
            .inflate(R.layout.team_info_recycler,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return teamInfo.size
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teamInfo[position]
        holder.textViewName.text = team.name

        val childLayoutManager = LinearLayoutManager(holder.teamRecyclerView.context, RecyclerView.VERTICAL, false)

        holder.teamRecyclerView.apply {
            layoutManager = childLayoutManager
            adapter = PlayerInfoAdapter(team.top_players) {(context as MatchStatsActivity).playerInfoDetails(it.id, team.id)}
            setRecycledViewPool(viewPool)
        }
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textViewName : TextView = itemView.teamNameTextView
        val teamRecyclerView : RecyclerView = itemView.rv_player_info_card as RecyclerView

    }
}