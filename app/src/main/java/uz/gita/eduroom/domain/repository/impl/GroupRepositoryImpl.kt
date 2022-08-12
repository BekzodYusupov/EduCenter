package uz.gita.eduroom.domain.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.eduroom.data.room.AppDatabase
import uz.gita.eduroom.data.room.enitity.GroupEntity
import uz.gita.eduroom.domain.repository.GroupRepository

class GroupRepositoryImpl : GroupRepository {
    private val database=AppDatabase.getInstance()
    private val groupDao=database.groupDao()


    override fun getGroupsByCourse(courseId: Long): LiveData<List<GroupEntity>> =
        groupDao.getGroupsByCourseId(courseId)

    override fun insert(groupEntity: GroupEntity)=groupDao.insert(groupEntity)

    override fun update(groupEntity: GroupEntity)=groupDao.update(groupEntity)

    override fun delete(groupEntity: GroupEntity) =groupDao.delete(groupEntity)
}