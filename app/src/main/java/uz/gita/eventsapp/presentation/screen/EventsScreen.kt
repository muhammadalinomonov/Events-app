package uz.gita.eventsapp.presentation.screen

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.eventsapp.R
import uz.gita.eventsapp.data.model.EventData
import uz.gita.eventsapp.databinding.ScreenEventsBinding
import uz.gita.eventsapp.presentation.adapter.EventsAdapter
import uz.gita.eventsapp.presentation.dialog.MyBottomSheetDialog
import uz.gita.eventsapp.presentation.screen.viewmodel.EventsViewModel
import uz.gita.eventsapp.presentation.screen.viewmodel.EventsViewModelImpl
import uz.gita.eventsapp.service.EventService

@AndroidEntryPoint
class EventsScreen : Fragment(R.layout.screen_events) {
    private val binding by viewBinding(ScreenEventsBinding::bind)
    private val viewModel: EventsViewModel by viewModels<EventsViewModelImpl>()

    private val adapter = EventsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        viewModel.allEventsLiveData.observe(viewLifecycleOwner, getAllEventsObserver)
        viewModel.onClickMoreLiveData.observe(viewLifecycleOwner, onClickMoreObserver)
        viewModel.onClickShareLiveData.observe(viewLifecycleOwner, onClickShareObserver)
        viewModel.onClickRateLiveData.observe(viewLifecycleOwner, onClickRateObserver)
        viewModel.onClickFeedbackLiveData.observe(viewLifecycleOwner, onCLickFeedbackObserver)


        binding.recyclerScreen.adapter = adapter
        binding.recyclerScreen.layoutManager = LinearLayoutManager(requireContext())
        adapter.setSwitchClickListener { id, state ->
            if (state) {
                viewModel.updateEventStateToDisable(id)
            } else {
                viewModel.updateEventStateToEnabled(id)
            }
        }
    }

    private val getAllEventsObserver = Observer<List<EventData>> { list ->
        val arrayList = ArrayList<String>()

        list.forEach {
            if (it.eventState == 1) {
                arrayList.add(it.events)
            }
        }
        val intent = Intent(requireContext(), EventService::class.java)
        intent.putStringArrayListExtra("enabledActions", arrayList)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().startForegroundService(intent)
        } else {
            requireActivity().startService(intent)
        }
        adapter.submitList(list)
    }

    private val onClickMoreObserver = Observer<Unit> {
        val dialog = MyBottomSheetDialog()

        dialog.setOnClickShareListener {
            viewModel.onClickShare()
        }
        dialog.setOnClickRateListener {
            viewModel.onClickRate()
        }
        dialog.setOnClickFeedbackListener {
            viewModel.onCLickFeedback()
        }

        dialog.show(childFragmentManager, "Bottom Sheet Dialog")

    }
    private val onClickShareObserver = Observer<Unit> {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        val body =
            "https://play.google.com/store/apps/details?id=uz.gita.events_app_"
        intent.putExtra(Intent.EXTRA_TEXT, body)
        startActivity(Intent.createChooser(intent, "share using"))
    }

    private val onClickRateObserver = Observer<Unit> {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=${requireActivity().packageName}")
            )
        )
    }

    private val onCLickFeedbackObserver = Observer<Unit> {
        val emailIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "muhammadalinomonov837@gmail.com"))
        startActivity(emailIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.moreMenu -> {
                viewModel.onClickMore()
                true
            }

            else -> false
        }
    }
}