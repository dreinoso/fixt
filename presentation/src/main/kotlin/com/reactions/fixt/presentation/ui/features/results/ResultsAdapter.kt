package com.reactions.fixt.presentation.ui.features.results

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.presentation.R

class ResultsAdapter internal constructor(private val context: Context?) : RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {

    private var resultsList : MutableList<Entity.Results> = mutableListOf()

    fun setResults(resultsList: Collection<Entity.Results>) {
        this.resultsList.clear()
        this.resultsList.addAll(resultsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ResultsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ResultsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(resultsList[position])
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }

    fun filter(ResultsList: Collection<Entity.Results>, date: String, league: String) {
        var filteredCollection : MutableList<Entity.Results> = mutableListOf()
        ResultsList.forEach() {
            if ((date.isEmpty() || it.date?.startsWith(date)!!) &&
                    (league.isEmpty() || it.competitionStage?.competition?.name?.equals(league)!!)) {
                filteredCollection.add(it)
            }
        }
        setResults(filteredCollection)
    }

    inner class ResultsViewHolder internal constructor(val view: View) : RecyclerView.ViewHolder(view) {
        private var tvLeague: TextView = view.findViewById(R.id.tv_league)
        private val tvPlaceAndDate: TextView = view.findViewById(R.id.tv_place_and_date)
        private val tvHomeTeam: TextView = this.view.findViewById(R.id.tv_home_team)
        private val tvAwayTeam: TextView = view.findViewById(R.id.tv_away_team)
        private val tvHomeTeamScore: TextView = view.findViewById(R.id.tv_home_team_score)
        private val tvAwayTeamScore: TextView = view.findViewById(R.id.tv_away_team_score)

        fun bind(result: Entity.Results) {
            tvLeague.text = result.competitionStage?.competition?.name.toString()
            val placeAmdDate = result.venue?.name.toString() + " | " + result.date.toString()
            tvPlaceAndDate.text = placeAmdDate
            tvHomeTeam.text = result.homeTeam?.name
            tvAwayTeam.text = result.awayTeam?.name
            tvHomeTeamScore.text = result.score?.home.toString()
            tvAwayTeamScore.text = result.score?.away.toString()
            if (!result.score?.winner.isNullOrEmpty()) {
                if(result.score?.winner.equals("home")) {
                    tvHomeTeam.setTextColor(context?.resources?.getColor(R.color.winner)!!)
                    tvHomeTeamScore.setTextColor(context.resources.getColor(R.color.winner))
                    tvAwayTeam.setTextColor(context.resources.getColor(R.color.looser))
                    tvAwayTeamScore.setTextColor(context.resources.getColor(R.color.looser))
                } else {
                    tvHomeTeam.setTextColor(context?.resources?.getColor(R.color.looser)!!)
                    tvHomeTeamScore.setTextColor(context.resources.getColor(R.color.looser))
                    tvAwayTeam.setTextColor(context.resources.getColor(R.color.winner))
                    tvAwayTeamScore.setTextColor(context.resources.getColor(R.color.winner))
                }
            }
        }
    }
}