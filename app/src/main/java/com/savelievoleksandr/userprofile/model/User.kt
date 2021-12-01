package com.savelievoleksandr.userprofile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey()
    var id: Int,
    @ColumnInfo(name = "user_name")
    var name: String,
    @ColumnInfo(name = "user_photo")
    var photo: String,
    @ColumnInfo(name = "user_last_Seen")
    var lastSeen: String,
    @ColumnInfo(name = "user_posts")
    var posts: String,
    @ColumnInfo(name = "user_followers")
    var followers: String,
    @ColumnInfo(name = "user_following")
    var following: String,
    @ColumnInfo(name = "user_bio")
    var bio: String,
    @ColumnInfo(name = "user_phone")
    var phone: String,
    @ColumnInfo(name = "user_email")
    var email: String
)