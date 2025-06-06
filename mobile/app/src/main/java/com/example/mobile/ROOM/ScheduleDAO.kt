package com.example.mobile.ROOM

import androidx.room.*

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule WHERE groupId = :groupId ORDER BY dayOfWeek, time")
    suspend fun getScheduleByGroup(groupId: Int): List<ScheduleEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(schedule: List<ScheduleEntity>)


    @Query("DELETE FROM schedule WHERE groupId = :groupId")
    suspend fun deleteByGroup(groupId: Int)


    @Query("DELETE FROM schedule")
    suspend fun clearAll()
}
