package uz.gita.eventsapp.presentation.screen.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.eventsapp.data.model.EventData

interface EventsViewModel {
    val allEventsLiveData: LiveData<List<EventData>>
    val onClickMoreLiveData: LiveData<Unit>
    val onClickShareLiveData: LiveData<Unit>
    val onClickRateLiveData: LiveData<Unit>
    val onClickFeedbackLiveData: LiveData<Unit>

    fun updateEventStateToDisable(eventId: Int)
    fun updateEventStateToEnabled(evenId: Int)
    fun onClickMore()
    fun onClickShare()
    fun onClickRate()
    fun onCLickFeedback()
}