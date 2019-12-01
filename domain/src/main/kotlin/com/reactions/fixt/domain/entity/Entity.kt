package com.reactions.fixt.domain.entity

/**
 * Album entity
 */
sealed class Entity {

    data class Album(
            val id: Long,
            val title: String,
            val userId: Long
    ) : Entity()

    data class Fixture(
            val id: Long,
            val type: String?,
            val homeTeam: HomeTeam?,
            val awayTeam: AwayTeam?,
            val date: String?,
            val competitionStage: CompetitionStage?,
            val venue: Venue?,
            val state: String?
    ) : Entity()

    data class Results(
            val id: Long,
            val type: String,
            val homeTeam: HomeTeam,
            val awayTeam: AwayTeam,
            val date: String,
            val competitionStage: CompetitionStage?,
            val venue: Venue,
            val score: Score
    ) : Entity()

    data class HomeTeam(
            val id: Long,
            val name: String,
            val shortName: String,
            val abbr: String,
            val alias: String
    ) : Entity()

    data class AwayTeam(
            val id: Long,
            val name: String,
            val shortName: String,
            val abbr: String,
            val alias: String
    ) : Entity()

    data class CompetitionStage(
            val competition: Competition?,
            val stage: String,
            val leg: String
    ) : Entity()

    data class Competition(
            val id: Long,
            val name: String
    ) : Entity()

    data class Venue(
            val id: Long,
            val name: String
    ) : Entity()

    data class Score(
            val home: Int,
            val away: Int,
            val winner: String
    ) : Entity()
}