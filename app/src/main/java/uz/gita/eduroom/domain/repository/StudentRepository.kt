package uz.gita.eduroom.domain.repository

import androidx.lifecycle.LiveData
import uz.gita.eduroom.data.room.enitity.StudentEntity

interface StudentRepository {
    fun getStudentsByGroup(groupId: Long): LiveData<List<StudentEntity>>

    fun insert(studentEntity: StudentEntity)

    fun update(studentEntity: StudentEntity)

    fun delete(studentEntity: StudentEntity)
}