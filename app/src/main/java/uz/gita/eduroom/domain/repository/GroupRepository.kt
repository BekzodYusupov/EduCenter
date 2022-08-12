package uz.gita.eduroom.domain.repository

import androidx.lifecycle.LiveData
import uz.gita.eduroom.data.room.enitity.GroupEntity

interface GroupRepository {

    fun getGroupsByCourse(courseId: Long): LiveData<List<GroupEntity>>

    fun insert(groupEntity: GroupEntity)

    fun update(groupEntity: GroupEntity)

    fun delete(groupEntity: GroupEntity)

}