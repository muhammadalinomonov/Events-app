package uz.gita.eventsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.eventsapp.data.model.EventData

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val eventIcon: Int,
    val events: String,
    val eventName: Int,
    val eventState: Int = 0
) {
    fun eventsEntityToEventsData(): EventData = EventData(
        id = id,
        eventIcon = eventIcon,
        events = events,
        eventName = eventName,
        eventState = eventState
    )
}