package io.software.servicecoordinator.domain.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonProfile
{
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = false

    @SerializedName("also_known_as")
    @Expose
    var also_known_as: MutableList<String>? = null

    @SerializedName("biography")
    @Expose
    var biography: String? = null

    @SerializedName("birthday")
    @Expose
    var birthday: String? = null

    @SerializedName("deathday")
    @Expose
    var deathday: String? = null

    @SerializedName("gender")
    @Expose
    var gender: Int? = 0

    @SerializedName("homepage")
    @Expose
    var homepage: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = 0

    @SerializedName("imdb_id")
    @Expose
    var imdb_id: String? = null

    @SerializedName("known_for_department")
    @Expose
    var known_for_department: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("place_of_birth")
    @Expose
    var place_of_birth: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity: String? = null

    @SerializedName("profile_path")
    @Expose
    var profile_path: String? = null
}