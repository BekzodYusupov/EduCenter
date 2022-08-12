package uz.gita.eduroom.domain.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.eduroom.data.room.AppDatabase
import uz.gita.eduroom.data.room.enitity.CourseEntity
import uz.gita.eduroom.domain.repository.CourseRepository

class CourseRepositoryImpl : CourseRepository {

    private val appDatabase: AppDatabase = AppDatabase.getInstance()
    private val courseDao = appDatabase.courseDao()

    override fun insertCourse(courseData: CourseEntity) =
        courseDao.insert(courseData)

    override fun deleteCourse(courseData: CourseEntity) =
        courseDao.delete(courseData)

    override fun updateCourse(courseData: CourseEntity) =
        courseDao.update(courseData)

    override fun getAllCourses(): LiveData<List<CourseEntity>> = courseDao.getAllCourses()

    override fun getCourseStudentsCount(courseId: Long): LiveData<Int> =
        courseDao.getStudentsCount(courseId)

    override fun getCourseGroupsCount(courseId: Long): LiveData<Int> =
        courseDao.getGroupsCount(courseId)
}