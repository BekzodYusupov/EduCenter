package uz.gita.eduroom.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.eduroom.R
import uz.gita.eduroom.data.room.enitity.StudentEntity

class StudentAdapter : ListAdapter<StudentEntity, StudentAdapter.StudentItemViewHolder>(
    StudentDiffUtilCallBack
) {
    inner class StudentItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var txtStudentName: TextView = view.findViewById(R.id.txtStudentName)
        fun bind() {
            txtStudentName.text = getItem(adapterPosition).name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StudentItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
    )

    override fun onBindViewHolder(holder: StudentItemViewHolder, position: Int) = holder.bind()
}

val StudentDiffUtilCallBack = object : DiffUtil.ItemCallback<StudentEntity>() {
    override fun areItemsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.groupId == newItem.groupId
    }


}
