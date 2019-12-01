package com.reactions.fixt.data.mapper

import com.com.reactions.fixt.data.api.AlbumApi
import com.reactions.fixt.data.api.GoogleApi
import com.reactions.fixt.domain.entity.Entity

/**
 * Extension class to map album dto to album entity
 */
fun AlbumApi.Dto.Album.map() = Entity.Album(
        id = id,
        userId = userId,
        title = title
)

fun GoogleApi.Dto.Fixture?.map() = Entity.Fixture(
        id = this!!.id,
        type = type,
        homeTeam = homeTeam.map(),
        awayTeam = awayTeam.map(),
        date = date,
        competitionStage = competitionStage.map(),
        venue = venue.map(),
        state = state
)

fun GoogleApi.Dto.Results.map() = Entity.Results(
        id = id,
        type = type,
        homeTeam = homeTeam.map(),
        awayTeam = awayTeam.map(),
        date = date,
        competitionStage = competitionStage?.map(),
        venue = venue.map(),
        score = score.map()
)

fun GoogleApi.Dto.HomeTeam.map() = Entity.HomeTeam(
        id = id,
        name = name,
        shortName = shortName,
        abbr = abbr,
        alias = alias
)

fun GoogleApi.Dto.AwayTeam.map() = Entity.AwayTeam(
        id = id,
        name = name,
        shortName = shortName,
        abbr = abbr,
        alias = alias
)

fun GoogleApi.Dto.CompetitionStage?.map() = Entity.CompetitionStage(
        competition = this!!.competition.map(),
        stage = stage.toString(),
        leg = leg.toString()
)

fun GoogleApi.Dto.Competition?.map() = Entity.Competition(
        id = this!!.id,
        name = name
)

fun GoogleApi.Dto.Venue?.map() = Entity.Venue(
        id = this!!.id,
        name = name
)

fun GoogleApi.Dto.Score?.map() = Entity.Score(
        home = this!!.home,
        away = away,
        winner = winner
)