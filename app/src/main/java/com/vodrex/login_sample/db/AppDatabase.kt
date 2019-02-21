package com.vodrex.login_sample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vodrex.login_sample.db.dao.CompanyDao
import com.vodrex.login_sample.db.entities.Company
import com.vodrex.login_sample.db.entities.LoginResponse

@Database(entities = [Company::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
}