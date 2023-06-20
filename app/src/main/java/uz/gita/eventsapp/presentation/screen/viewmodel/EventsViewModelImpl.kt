package uz.gita.eventsapp.presentation.screen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.eventsapp.data.model.EventData
import uz.gita.eventsapp.domain.usecase.EventsUseCase
import javax.inject.Inject

@HiltViewModel
class EventsViewModelImpl @Inject constructor(
    private val useCase: EventsUseCase
) : ViewModel(), EventsViewModel {
    override val allEventsLiveData = MutableLiveData<List<EventData>>()
    override val onClickMoreLiveData = MutableLiveData<Unit>()
    override val onClickShareLiveData = MutableLiveData<Unit>()
    override val onClickRateLiveData = MutableLiveData<Unit>()
    override val onClickFeedbackLiveData = MutableLiveData<Unit>()

    init {
        loadEvents()
    }

    private fun loadEvents() {
        useCase.getAllEvents().onEach {
            allEventsLiveData.value = it
        }.launchIn(viewModelScope)
    }

    override fun updateEventStateToDisable(eventId: Int) {
        useCase.updateEventStateToDisable(eventId).onEach {
//            loadEvents()
        }.launchIn(viewModelScope)
    }

    override fun updateEventStateToEnabled(evenId: Int) {
        useCase.updateEventStateToEnable(evenId)
            .onEach {/* loadEvents()*/ }.launchIn(viewModelScope)
    }

    override fun onClickMore() {
        onClickMoreLiveData.value = Unit
    }

    override fun onClickShare() {
        onClickShareLiveData.value = Unit
    }

    override fun onClickRate() {
        onClickRateLiveData.value = Unit
    }

    override fun onCLickFeedback() {
        onClickFeedbackLiveData.value = Unit
    }
}