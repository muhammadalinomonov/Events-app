package uz.gita.eventsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.eventsapp.R
import uz.gita.eventsapp.data.model.EventData
import uz.gita.eventsapp.databinding.ItemOfScreenBinding


class EventsAdapter : ListAdapter<EventData, EventsAdapter.EventsViewHolder>(EventsDiffUtil) {


    private var onSwitchClickListener: ((id: Int, state: Boolean) -> Unit)? = null

    private object EventsDiffUtil : DiffUtil.ItemCallback<EventData>() {
        override fun areItemsTheSame(oldItem: EventData, newItem: EventData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: EventData, newItem: EventData): Boolean =
            oldItem == newItem
    }

    inner class EventsViewHolder(private val binding: ItemOfScreenBinding) :
        ViewHolder(binding.root) {

        fun bind() {
            val data = getItem(adapterPosition)
            binding.manageState.isChecked = data.eventState == 1
            binding.manageState.setOnCheckedChangeListener { _, isChecked ->

                onSwitchClickListener?.invoke(data.id, !isChecked)
            }
            binding.eventText.text =
                itemView.resources.getString(getItem(adapterPosition).eventName)
            binding.eventImage.setImageResource(getItem(adapterPosition).eventIcon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(
            ItemOfScreenBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_of_screen, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind()
    }

    fun setSwitchClickListener(block: (Int, Boolean) -> Unit) {
        onSwitchClickListener = block
    }
}