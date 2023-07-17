package io.software.servicecoordinator.domain.response

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie
{
    @SerializedName("page")
    @Expose
    var page: Int? = 0

    @SerializedName("results")
    @Expose
    var results: MutableList<Results>? = null

    @SerializedName("total_results")
    @Expose
    var total_results: Int? = 0

    @SerializedName("total_pages")
    @Expose
    var total_pages: Int? = 0

    class Results : Serializable
    {
        @SerializedName("adult")
        @Expose
        var adult: Boolean? = false

        @SerializedName("backdrop_path")
        @Expose
        var backdrop_path: String? = null

        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("original_language")
        @Expose
        var original_language: String? = null

        @SerializedName("original_title")
        @Expose
        var original_title: String? = null

        @SerializedName("overview")
        @Expose
        var overview: String? = null

        @SerializedName("popularity")
        @Expose
        var popularity: Double? = 0.0

        @SerializedName("poster_path")
        @Expose
        var poster_path: String? = null

        @SerializedName("release_date")
        @Expose
        var release_date: String? = null

        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("video")
        @Expose
        var video: Boolean? = false

        @SerializedName("vote_average")
        @Expose
        var vote_average: Double? = 0.0

        @SerializedName("vote_count")
        @Expose
        var vote_count: Int? = 0

    }

    override fun toString(): String {
        return GsonBuilder().disableHtmlEscaping().create().toJson(this)
    }
}