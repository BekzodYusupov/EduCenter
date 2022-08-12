package uz.gita.eduroom.domain.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.eduroom.data.room.enitity.CourseEntity
import uz.gita.eduroom.domain.repository.CourseRepository
import uz.gita.eduroom.domain.repository.impl.CourseRepositoryImpl


class CourseViewModel : ViewModel() {

    private val _coursesListLiveData: MutableLiveData<List<CourseEntity>> = MutableLiveData()
    val coursesListLiveData: LiveData<List<CourseEntity>> = _coursesListLiveData

    private val _dialogLiveData: MediatorLiveData<Unit> = MediatorLiveData()
    val dialogLiveData: LiveData<Unit> = _dialogLiveData

    private val _openGroupsScreenLiveData: MutableLiveData<Long> = MutableLiveData()
    val openGroupsScreenLiveData: LiveData<Long> = _openGroupsScreenLiveData

    private val courseRepository: CourseRepository = CourseRepositoryImpl()

    init {
        _dialogLiveData.addSource(courseRepository.getAllCourses()) {
            _coursesListLiveData.value = it
        }
    }

    fun showGroupsCount(index: Long) {
        courseRepository.getCourseGroupsCount(index)
    }

    fun addContact(courseData: CourseEntity) {
            courseRepository.insertCourse(courseData)
    }

    fun showAddDialog() {
        _dialogLiveData.value = Unit
    }

    fun openGroupsScreen(courseId: Long) {
        _openGroupsScreenLiveData.value = courseId
    }
}