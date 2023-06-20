package uz.gita.eventsapp.data.model

data class EventData(
    val id: Int,
    val eventIcon: Int,
    val events: String,
    val eventName: Int,
    var eventState: Int = 0
)