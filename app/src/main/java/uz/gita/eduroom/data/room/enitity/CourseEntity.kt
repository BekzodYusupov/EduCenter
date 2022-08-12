package uz.gita.eduroom.data.room.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)
