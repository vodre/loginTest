package com.vodrex.login_sample.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vodrex.login_sample.db.entities.Company
import io.reactivex.Single

@Dao
interface CompanyDao {

    @Query("SELECT * FROM Company")
    fun getCompanies(): Single<List<Company>>

    @Query("SELECT * FROM Company WHERE name = :name")
    fun getCompany(name: String): Single<Company>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompanies(example: List<Company>)
}