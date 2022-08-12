package uz.gita.eduroom.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uz.gita.eduroom.data.room.enitity.StudentEntity

@Dao
interface StudentDao : BaseDao<StudentEntity> {

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<StudentEntity>

    @Query(
        "SELECT s.id, s.name, s.group_id " +
                "FROM course c, `group` g, student s " +
                "WHERE  g.course_id= :courseId AND s.group_id=g.id"
    )
    fun getStudentsByCourseId(courseId: Long): LiveData<List<StudentEntity>>

    @Query("SELECT * FROM student WHERE group_id = :groupId")
    fun getStudentsByGroupId(groupId: Long): LiveData<List<StudentEntity>>

    @Query("SELECT * FROM student WHERE id = :id LIMIT 1")
    fun getStudentById(id: Long): StudentEntity?
}