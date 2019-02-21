package com.vodrex.login_sample.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Company(

        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: Int,

        @SerializedName("name")
        @Expose
        var name: String?,

        @SerializedName("sections")
        @Expose
        var secciones: String?,

        @SerializedName("image_path")
        @Expose
        var imagePath: Int
)