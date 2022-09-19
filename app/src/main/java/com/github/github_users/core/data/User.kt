package com.github.github_users.core.data

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("gists_url")
    val gistsUrl: String? = null,

    @field:SerializedName("repos_url")
    var reposUrl: String? = null,

    @field:SerializedName("following_url")
    var followingUrl: String? = null,

    @field:SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @field:SerializedName("bio")
    var bio: Any? = null,

    @field:SerializedName("created_at")
    var createdAt: String? = null,

    @field:SerializedName("login")
    var login: String? = null,

    @field:SerializedName("type")
    var type: String? = null,

    @field:SerializedName("blog")
    var blog: String? = null,

    @field:SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("site_admin")
    val siteAdmin: Boolean? = null,

    @field:SerializedName("company")
    val company: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("public_repos")
    val publicRepos: Int? = null,

    @field:SerializedName("gravatar_id")
    val gravatarId: String? = null,

    @field:SerializedName("email")
    val email: Any? = null,

    @field:SerializedName("organizations_url")
    val organizationsUrl: String? = null,

    @field:SerializedName("hireable")
    val hireable: Any? = null,

    @field:SerializedName("starred_url")
    val starredUrl: String? = null,

    @field:SerializedName("followers_url")
    val followersUrl: String? = null,

    @field:SerializedName("public_gists")
    val publicGists: Int? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,

    @field:SerializedName("followers")
    val followers: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("events_url")
    val eventsUrl: String? = null,

    @field:SerializedName("html_url")
    val htmlUrl: String? = null,

    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("node_id")
    val nodeId: String? = null,

    @field:SerializedName("score")
    val score: Double? = null,


    )
