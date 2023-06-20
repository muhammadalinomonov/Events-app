package uz.gita.eventsapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.eventsapp.data.model.EventData

interface EventsUseCase {

    fun getAllDisableEvents(): Flow<List<EventData>>

    fun getAllEnableEvents(): Flow<List<EventData>>

    fun getAllEvents(): Flow<List<EventData>>

    fun updateEventStateToDisable(eventId: Int): Flow<Unit>

    fun updateEventStateToEnable(eventId: Int): Flow<Unit>
}