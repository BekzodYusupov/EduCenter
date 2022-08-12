package uz.gita.eduroom.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.eduroom.R
import uz.gita.eduroom.data.room.enitity.CourseEntity

class CourseAdapter : ListAdapter<CourseEntity, CourseAdapter.CourseItemViewHolder>(
    CourseDiffUtilCallBack
) {

    private var itemClickListener: ((CourseEntity) -> Unit)? = null

    fun setItemClickListener(block: (CourseEntity) -> Unit) {
        itemClickListener = block
    }

    inner class CourseItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtCourseName: TextView = view.findViewById(R.id.txtCourseName)

        fun bind() {
            txtCourseName.text = getItem(adapterPosition).name
        }

        init {
            view.setOnClickListener {
                itemClickListener?.invoke(getItem(adapterPosition))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CourseItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
    )

    override fun onBindViewHolder(holder: CourseItemViewHolder, position: Int) = holder.bind()


}

val CourseDiffUtilCallBack = object : DiffUtil.ItemCallback<CourseEntity>() {

    override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }

}