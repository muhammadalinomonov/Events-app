package uz.gita.eventsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.eventsapp.data.local.entity.EventEntity

@Dao
interface EventsDao {
    @Query("Select exists (select * from events)")
    fun isInitialized(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInitializedEvents(events: List<EventEntity>)

    @Query("Select * From events Where events.eventState = 0")
    fun getAllDisableEvents(): List<EventEntity>

    @Query("Select * From events Where events.eventState = 1")
    fun getAllEnableEvents(): List<EventEntity>

    @Query("Select * From events")
    fun getAllEvents(): List<EventEntity>


    @Query("Update events set eventState=0 where id=:eventId")
    fun updateEventStateToDisable(eventId: Int)

    @Query("Update events set eventState = 1 Where id=:eventId")
    fun updateEventStateToEnable(eventId: Int)



}