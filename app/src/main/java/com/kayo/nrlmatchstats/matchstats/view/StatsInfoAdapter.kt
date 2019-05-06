package com.kayo.nrlmatchstats.matchstats.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stats = statsInfo[position]
        val teamAList = ArrayList<Team>()
        val teamBList = ArrayList<Team>()

        teamAList.add(stats.team_A)
        teamBList.add(stats.team_B)

        holder.textViewStatsName.text = stats.stat_type.capitalize()

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

        holder.toggleExpand()

    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewStatsName : TextView = itemView.txtStatsType
        val imgStatsMore : ImageView = itemView.imgArrow
        val teamOneRecyclerView : RecyclerView = itemView.rv_team_one_info_card as RecyclerView
        val teamTwoRecyclerView : RecyclerView = itemView.rv_team_two_info_card as RecyclerView

        override fun onClick(view: View?) {
            if (view?.id == R.id.imgArrow) {
                if (teamOneRecyclerView.visibility == View.VISIBLE
                    && teamTwoRecyclerView.visibility == View.VISIBLE) {
                    teamOneRecyclerView.visibility = View.GONE
                    teamTwoRecyclerView.visibility = View.GONE
                    imgStatsMore.setImageResource(R.drawable.ic_down_arrow)
                } else {
                    teamOneRecyclerView.visibility = View.VISIBLE
                    teamTwoRecyclerView.visibility = View.VISIBLE
                    imgStatsMore.setImageResource(R.drawable.ic_up_arrow)
                }
            }
        }

        fun toggleExpand() {
            teamOneRecyclerView.visibility = View.GONE
            teamTwoRecyclerView.visibility = View.GONE

            val intMaxNoOfChild = statsInfo.size

            imgStatsMore.setOnClickListener(this)

            val noOfChildViews = teamOneRecyclerView.childCount

            if (intMaxNoOfChild < noOfChildViews) {
                for (index in intMaxNoOfChild until noOfChildViews) {
                    val currentView1 = teamOneRecyclerView.getChildAt(index)
                    currentView1.visibility = View.GONE

                    val currentView2 = teamTwoRecyclerView.getChildAt(index)
                    currentView2.visibility = View.GONE
                }
            }
        }
    }

}