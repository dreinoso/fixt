package com.reactions.fixt.presentation.ui.features.fixtures

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.presentation.R

class FixturesAdapter internal constructor(private val context: Context?) : RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder>() {

    private var fixturesList : MutableList<Entity.Fixture> = mutableListOf()

    fun setFixtures(fixturesList: Collection<Entity.Fixture>) {
        this.fixturesList.clear()
        this.fixturesList.addAll(fixturesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FixturesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_fixture, parent, false)
        return FixturesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FixturesViewHolder, position: Int) {
        holder.bind(fixturesList[position])
    }

    override fun getItemCount(): Int {
        return fixturesList.size
    }

    fun filter(fixturesList: Collection<Entity.Fixture>, date: String, league: String) {
        var filteredCollection : MutableList<Entity.Fixture> = mutableListOf()
        fixturesList.forEach() {
            if ((date.isEmpty() || it.date?.startsWith(date)!!) &&
                    (league.isEmpty() || it.competitionStage?.competition?.name?.equals(league)!!)) {
                filteredCollection.add(it)
            }
        }
        setFixtures(filteredCollection)
    }

    inner class FixturesViewHolder internal constructor(val view: View) : RecyclerView.ViewHolder(view) {
        private var tvLeague: TextView = view.findViewById(R.id.tv_league)
        private val tvPlaceAndDate: TextView = view.findViewById(R.id.tv_place_and_date)
        private val tvHomeTeam: TextView = this.view.findViewById(R.id.tv_home_team)
        private val tvAwayTeam: TextView = view.findViewById(R.id.tv_away_team)
        private val tvPostPoned: TextView = view.findViewById(R.id.tv_postponed)

        fun bind(fixture: Entity.Fixture) {
            tvLeague.text = fixture.competitionStage?.competition?.name.toString()
            val placeAmdDate = fixture.venue?.name.toString() + " | " + fixture.date.toString()
            tvPlaceAndDate.text = placeAmdDate
            tvHomeTeam.text = fixture.homeTeam?.name
            tvAwayTeam.text = fixture.awayTeam?.name
            if (fixture.state?.toLowerCase().equals("postponed")) {
                tvPostPoned.visibility = View.VISIBLE
            }
        }
    }
}