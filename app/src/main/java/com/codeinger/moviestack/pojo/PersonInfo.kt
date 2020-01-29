package com.codeinger.moviestack.pojo


import com.google.gson.annotations.SerializedName

data class PersonInfo(
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("also_known_as")
    var alsoKnownAs: List<String> = listOf(),
    @SerializedName("biography")
    var biography: String = "",
    @SerializedName("birthday")
    var birthday: String = "",
    @SerializedName("deathday")
    var deathday: Any = Any(),
    @SerializedName("gender")
    var gender: Int = 0,
    @SerializedName("homepage")
    var homepage: Any = Any(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("imdb_id")
    var imdbId: String = "",
    @SerializedName("known_for_department")
    var knownForDepartment: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("place_of_birth")
    var placeOfBirth: String = "",
    @SerializedName("popularity")
    var popularity: Double = 0.0,
    @SerializedName("profile_path")
    var profilePath: String = ""
) {
    fun getNickName(): String {
        return if (alsoKnownAs.size > 0)
            alsoKnownAs.get(0)
        else ""
    }
}