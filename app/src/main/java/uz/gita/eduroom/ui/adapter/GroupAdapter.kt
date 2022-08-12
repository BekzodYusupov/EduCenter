package uz.gita.eduroom.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.eduroom.R
import uz.gita.eduroom.data.room.enitity.GroupEntity

class GroupAdapter : ListAdapter<GroupEntity, GroupAdapter.GroupItemViewHolder>(
    GroupDiffUtilCallBack
) {
    private var itemClickListener: ((GroupEntity) -> Unit)? = null

    fun setItemClickListener(block: (GroupEntity) -> Unit) {
        itemClickListener = block
    }

    inner class GroupItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtGroupName: TextView = view.findViewById(R.id.txtGroupName)

        fun bind() {
            txtGroupName.text = getItem(adapterPosition).name
        }
        init {
            view.setOnClickListener {
                itemClickListener?.invoke(getItem(adapterPosition))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GroupItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
    )

    override fun onBindViewHolder(holder: GroupItemViewHolder, position: Int) = holder.bind()

}

val GroupDiffUtilCallBack = object : DiffUtil.ItemCallback<GroupEntity>() {

    override fun areItemsTheSame(oldItem: GroupEntity, newItem: GroupEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GroupEntity, newItem: GroupEntity): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.courseId==newItem.courseId
    }

}