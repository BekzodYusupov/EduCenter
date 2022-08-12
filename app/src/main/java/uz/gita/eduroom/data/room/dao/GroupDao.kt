package uz.gita.eduroom.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uz.gita.eduroom.data.room.enitity.GroupEntity

@Dao
interface GroupDao : BaseDao<GroupEntity> {

    @Query("SELECT * FROM `group`")
    fun getAllGroups(): LiveData<List<GroupEntity>>

    @Query("SELECT * FROM `group` WHERE course_id = :courseId")
    fun getGroupsByCourseId(courseId: Long): LiveData<List<GroupEntity>>

    @Query("SELECT * FROM `group` WHERE id = :id LIMIT 1")
    fun getGroupById(id: Long): GroupEntity?

}