package com.reactions.fixt.data.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET

interface GoogleApi {

    @GET("https://storage.googleapis.com/cdn-og-test-api/test-task/fixtures.json")
    fun getFixtures(): Single<List<Dto.Fixture>>

    @GET("https://storage.googleapis.com/cdn-og-test-api/test-task/results.json")
    fun getResults(): Single<List<Dto.Results>>

    sealed class Dto {

        data class Fixture(
                @SerializedName("id") val id: Long,
                @SerializedName("type") val type: String,
                @SerializedName("homeTeam") val homeTeam: HomeTeam,
                @SerializedName("awayTeam") val awayTeam: AwayTeam,
                @SerializedName("date") val date: String,
                @SerializedName("competitionStage") val competitionStage: CompetitionStage,
                @SerializedName("venue") val venue: Venue?,
                @SerializedName("state") val state: String
        ) : Dto()

        data class Results(
                @SerializedName("id") val id: Long,
                @SerializedName("type") val type: String,
                @SerializedName("homeTeam") val homeTeam: HomeTeam,
                @SerializedName("awayTeam") val awayTeam: AwayTeam,
                @SerializedName("date") val date: String,
                @SerializedName("competitionStage") val competitionStage: CompetitionStage?,
                @SerializedName("venue") val venue: Venue?,
                @SerializedName("score") val score: Score?
        ) : Dto()

        data class HomeTeam(
                @SerializedName("id") val id: Long,
                @SerializedName("name") val name: String,
                @SerializedName("shortName") val shortName: String,
                @SerializedName("abbr") val abbr: String,
                @SerializedName("alias") val alias: String
        ) : Dto()

        data class AwayTeam(
                @SerializedName("id") val id: Long,
                @SerializedName("name") val name: String,
                @SerializedName("shortName") val shortName: String,
                @SerializedName("abbr") val abbr: String,
                @SerializedName("alias") val alias: String
        ) : Dto()

        data class CompetitionStage(
                @SerializedName("competition") val competition: Competition?,
                @SerializedName("stage") val stage: String?,
                @SerializedName("leg") val leg: String?
        ) : Dto()

        data class Competition(
                @SerializedName("id") val id: Long,
                @SerializedName("name") val name: String
        ) : Dto()

        data class Venue(
                @SerializedName("id") val id: Long,
                @SerializedName("name") val name: String
        ) : Dto()

        data class Score(
                @SerializedName("home") val home: Int,
                @SerializedName("away") val away: Int,
                @SerializedName("winner") val winner: String
        ) : Dto()
    }
}