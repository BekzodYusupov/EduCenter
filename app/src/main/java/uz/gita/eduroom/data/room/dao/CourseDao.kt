package uz.gita.eduroom.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.eduroom.data.room.enitity.CourseEntity

@Dao
interface CourseDao : BaseDao<CourseEntity> {

    @Query("SELECT * FROM course")
    fun getAllCourses(): LiveData<List<CourseEntity>>

    @Query("SELECT * FROM course WHERE id = :id LIMIT 1")
    fun getCourseById(id: Long): CourseEntity?

    @Query("SELECT COUNT(id) FROM `group` WHERE course_id = :courseId")
    fun getGroupsCount(courseId: Long): LiveData<Int>

    @Query("SELECT COUNT(s.id) FROM `group` g, student s WHERE g.course_id = :courseId AND g.id = s.group_id")
    fun getStudentsCount(courseId: Long): LiveData<Int>

}