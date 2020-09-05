package com.padcmyanmar.padcx.podcastassignment.network.responses

import com.google.gson.annotations.SerializedName

data class PlayListNewResponse(
    @SerializedName("playlists") val playlists: List<PlaylistsVO>
)