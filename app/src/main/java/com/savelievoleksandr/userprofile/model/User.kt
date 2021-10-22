package com.savelievoleksandr.userprofile.model

data class User(
    val name: String,
    val photo: String,
    val lastSeen: String,
    val posts: String,
    var followers: String,
    val following: String,
    val bio: String,
    val phone: String,
    val email: String
)