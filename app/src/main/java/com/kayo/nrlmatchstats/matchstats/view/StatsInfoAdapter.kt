package com.kayo.nrlmatchstats.matchstats.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kayo.nrlmatchstats.R
import com.kayo.nrlmatchstats.matchstats.model.StatsInfo
import com.kayo.nrlmatchstats.matchstats.model.TopPlayer
import kotlinx.android.synthetic.main.stats_info_recycler.view.imgArrow
import kotlinx.android.synthetic.main.stats_info_recycler.view.txtStatsType
import kotlinx.android.synthetic.main.stats_info_recycler.view.*

class StatsInfoAdapter(private val statsInfo: List<StatsInfo>) :
    RecyclerView.Adapter<StatsInfoAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.stats_info_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return statsInfo.size
    }

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stats = statsInfo[position]

        // Assuming the sizes of the top players lists from both the teams are always equal
        val mergedTopPlayerArraySize = stats.team_A.top_players.size * 2
        val topPlayerArray = Array(mergedTopPlayerArraySize)
            { TopPlayer(0, "", 0, 0, "", "", 0) }
        var mergedTopPlayerArrayIndex = 0

        for (i in 0 until stats.team_A.top_players.size) {
            // Team A for Left Grid
            topPlayerArray[mergedTopPlayerArrayIndex] = stats.team_A.top_players[i]
            topPlayerArray[mergedTopPlayerArrayIndex].teamId = stats.team_A.id
            mergedTopPlayerArrayIndex++

            // Team B for Right Grid
            topPlayerArray[mergedTopPlayerArrayIndex] = stats.team_B.top_players[i]
            topPlayerArray[mergedTopPlayerArrayIndex].teamId = stats.team_B.id
            mergedTopPlayerArrayIndex++
        }

        val combinedTopPlayersList = topPlayerArray.toList()

        val playerInfoLayoutManager =
            GridLayoutManager(holder.playerInfoRecyclerView.context, 2, RecyclerView.VERTICAL, false)

        holder.textViewStatsName.text = stats.stat_type.capitalize()
        holder.teamOneName.text = stats.team_A.name
        holder.teamTwoName.text = stats.team_B.name
        holder.playerInfoRecyclerView.apply {
            layoutManager = playerInfoLayoutManager
            adapter = PlayerInfoAdapter(combinedTopPlayersList) {
                (context as MatchStatsActivity).playerInfoDetails(
                    it.id,
                    it.teamId
                )
            }
            setRecycledViewPool(viewPool)
        }

        holder.toggleExpand()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewStatsName: TextView = itemView.txtStatsType
        private val imgStatsMore: ImageView = itemView.imgArrow
        val playerInfoRecyclerView: RecyclerView = itemView.rv_player_info_details
        val teamOneName: TextView = itemView.tv_team_one_title
        val teamTwoName: TextView = itemView.tv_team_two_title

        override fun onClick(view: View?) {
            if (view?.id == R.id.imgArrow) {
                if (playerInfoRecyclerView.visibility == View.VISIBLE) {
                    playerInfoRecyclerView.visibility = View.GONE
                    teamOneName.visibility = View.GONE
                    teamTwoName.visibility = View.GONE
                    imgStatsMore.setImageResource(R.drawable.ic_down_arrow)
                } else {
                    playerInfoRecyclerView.visibility = View.VISIBLE
                    teamOneName.visibility = View.VISIBLE
                    teamTwoName.visibility = View.VISIBLE
                    imgStatsMore.setImageResource(R.drawable.ic_up_arrow)
                }
            }
        }

        fun toggleExpand() {
            imgStatsMore.setOnClickListener(this)
            playerInfoRecyclerView.visibility = View.GONE
            teamOneName.visibility = View.GONE
            teamTwoName.visibility = View.GONE

            val intMaxNoOfChild = statsInfo.size
            val noOfChildViews = playerInfoRecyclerView.childCount

            if (intMaxNoOfChild < noOfChildViews) {
                for (index in intMaxNoOfChild until noOfChildViews) {
                    val playerInfoCard = playerInfoRecyclerView.getChildAt(index)
                    playerInfoCard.visibility = View.GONE
                }
            }
        }
    }

}