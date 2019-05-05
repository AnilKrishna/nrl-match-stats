package com.kayo.nrlmatchstats.matchstats.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kayo.nrlmatchstats.R
import com.kayo.nrlmatchstats.matchstats.model.StatsInfo
import com.kayo.nrlmatchstats.matchstats.model.Team
import kotlinx.android.synthetic.main.stats_info_recycler.view.*

class StatsInfoAdapter(private val statsInfo: List<StatsInfo>)  : RecyclerView.Adapter<StatsInfoAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context)
            .inflate(R.layout.stats_info_recycler,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return statsInfo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stats = statsInfo[position]
        val teamAList = ArrayList<Team>()
        val teamBList = ArrayList<Team>()
        statsInfo.forEach {
            teamAList.add(it.team_A)
            teamBList.add(it.team_B)
        }

        holder.textViewStatsName.text = stats.stat_type

        val teamOneChildLayoutManager = LinearLayoutManager(holder.teamOneRecyclerView.context, RecyclerView.VERTICAL, false)
        val teamTwoChildLayoutManager = LinearLayoutManager(holder.teamTwoRecyclerView.context, RecyclerView.VERTICAL, false)

        holder.teamOneRecyclerView.apply {
            layoutManager = teamOneChildLayoutManager
            adapter = TeamInfoAdapter(teamAList)
            setRecycledViewPool(viewPool)
        }

        holder.teamTwoRecyclerView.apply {
            layoutManager = teamTwoChildLayoutManager
            adapter = TeamInfoAdapter(teamBList)
            setRecycledViewPool(viewPool)
        }

    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewStatsName : TextView = itemView.txtStatsType
        val teamOneRecyclerView : RecyclerView = itemView.rv_team_one_info_card as RecyclerView
        val teamTwoRecyclerView : RecyclerView = itemView.rv_team_two_info_card as RecyclerView
    }

}