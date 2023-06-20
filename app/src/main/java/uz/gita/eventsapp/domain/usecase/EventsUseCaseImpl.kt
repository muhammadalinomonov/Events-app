package uz.gita.eventsapp.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.eventsapp.domain.repository.AppRepository
import javax.inject.Inject

class EventsUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : EventsUseCase {
    override fun getAllDisableEvents() = flow {
        val result = repository.getAllDisableEvents().map {
            it.eventsEntityToEventsData()
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getAllEnableEvents() = flow {
        val result = repository.getAllEnableEvents().map {
            it.eventsEntityToEventsData()
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getAllEvents() = flow {
        val result = repository.getAllEvents().map {
            it.eventsEntityToEventsData()
        }
        emit(result)
    }.flowOn(Dispatchers.IO)


    override fun updateEventStateToDisable(eventId: Int) = flow {
        repository.updateEventStateToDisable(eventId)
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override fun updateEventStateToEnable(eventId: Int) = flow {
        repository.updateEventStateToEnable(eventId)
        emit(Unit)
    }.flowOn(Dispatchers.IO)


}