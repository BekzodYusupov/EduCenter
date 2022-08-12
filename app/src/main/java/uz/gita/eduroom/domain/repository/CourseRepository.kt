package uz.gita.eduroom.domain.repository

import androidx.lifecycle.LiveData
import uz.gita.eduroom.data.room.enitity.CourseEntity

interface CourseRepository {

    fun insertCourse(courseData: CourseEntity)

    fun deleteCourse(courseData: CourseEntity)

    fun updateCourse(courseData: CourseEntity)

    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getCourseStudentsCount(courseId: Long): LiveData<Int>

    fun getCourseGroupsCount(courseId: Long): LiveData<Int>

}