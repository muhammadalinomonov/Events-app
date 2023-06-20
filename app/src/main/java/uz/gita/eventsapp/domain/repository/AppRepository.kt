package uz.gita.eventsapp.domain.repository

import uz.gita.eventsapp.data.local.entity.EventEntity

interface AppRepository {
    suspend fun getAllDisableEvents(): List<EventEntity>

    suspend fun getAllEnableEvents(): List<EventEntity>

    suspend fun getAllEvents(): List<EventEntity>

    suspend fun updateEventStateToDisable(eventId: Int)

    suspend fun updateEventStateToEnable(eventId: Int)
}