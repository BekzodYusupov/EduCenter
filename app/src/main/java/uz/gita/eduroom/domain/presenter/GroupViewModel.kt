package uz.gita.eduroom.domain.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.eduroom.data.room.enitity.GroupEntity
import uz.gita.eduroom.domain.repository.GroupRepository
import uz.gita.eduroom.domain.repository.impl.GroupRepositoryImpl

class GroupViewModel : ViewModel() {

    private val groupRepository: GroupRepository = GroupRepositoryImpl()

    private val _backLiveData: MediatorLiveData<Unit> = MediatorLiveData()
    val backLiveData: LiveData<Unit> = _backLiveData

    private val _groupsLiveData: MutableLiveData<List<GroupEntity>> = MutableLiveData()
    val groupsLiveData: LiveData<List<GroupEntity>> = _groupsLiveData

    private val _openStudentsScreenLiveData: MutableLiveData<Long> = MutableLiveData()
    val openStudentsScreenLiveData: LiveData<Long> = _openStudentsScreenLiveData

    private val _dialogLiveData: MutableLiveData<Long> = MutableLiveData()
    val dialogLiveData: LiveData<Long> = _dialogLiveData

    private var courseId: Long = -1

    fun getGroups(courseId: Long) {
        this.courseId = courseId
        _backLiveData.addSource(groupRepository.getGroupsByCourse(courseId)) {
            _groupsLiveData.value = it
        }
    }

    fun showDialog() {
        _dialogLiveData.value = courseId
    }

    fun back() {
        _backLiveData.value = Unit
    }

    fun addGroup(groupEntity: GroupEntity) {
        groupRepository.insert(groupEntity)
    }
    fun openStudentsScreen(groupId: Long) {
            _openStudentsScreenLiveData.value = groupId
}       }