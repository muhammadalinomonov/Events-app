package uz.gita.eventsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.eventsapp.data.local.dao.EventsDao
import uz.gita.eventsapp.data.local.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
abstract class EventsDatabase : RoomDatabase() {

    abstract fun eventDao(): EventsDao
}