package uz.gita.eduroom.domain.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.eduroom.data.room.enitity.StudentEntity
import uz.gita.eduroom.domain.repository.StudentRepository
import uz.gita.eduroom.domain.repository.impl.StudentRepositoryImpl

class StudentViewModel : ViewModel() {
    private val studentRepository: StudentRepository = StudentRepositoryImpl()

    private val _backLiveData: MediatorLiveData<Unit> = MediatorLiveData()
    val backLiveData: LiveData<Unit> = _backLiveData

    private val _studentsLiveData: MutableLiveData<List<StudentEntity>> = MutableLiveData()
    val groupsLiveData: LiveData<List<StudentEntity>> = _studentsLiveData

    private val _dialogLiveData: MutableLiveData<Long> = MutableLiveData()
    val dialogLiveData: LiveData<Long> = _dialogLiveData

    private var studentId: Long = -1

    fun getStudents(studentId: Long) {
        this.studentId = studentId
        _backLiveData.addSource(studentRepository.getStudentsByGroup(studentId)) {
            _studentsLiveData.value = it
        }
    }

    fun showDialog() {
        _dialogLiveData.value = studentId
    }

    fun back() {
        _backLiveData.value = Unit
    }

    fun addStudent(studentEntity: StudentEntity) {
        studentRepository.insert(studentEntity)
    }

}