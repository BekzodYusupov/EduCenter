package uz.gita.eduroom.domain.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.eduroom.data.room.AppDatabase
import uz.gita.eduroom.data.room.enitity.StudentEntity
import uz.gita.eduroom.domain.repository.StudentRepository

class StudentRepositoryImpl : StudentRepository {
    private val database = AppDatabase.getInstance()
    private val studentDao = database.studentDao()

    override fun getStudentsByGroup(groupId: Long): LiveData<List<StudentEntity>> =
        studentDao.getStudentsByGroupId(groupId)


    override fun insert(studentEntity: StudentEntity) = studentDao.insert(studentEntity)

    override fun update(studentEntity: StudentEntity) = studentDao.update(studentEntity)

    override fun delete(studentEntity: StudentEntity) = studentDao.delete(studentEntity)


}