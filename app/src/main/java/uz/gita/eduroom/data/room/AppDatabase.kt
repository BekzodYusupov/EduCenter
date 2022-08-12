package uz.gita.eduroom.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.eduroom.data.room.dao.CourseDao
import uz.gita.eduroom.data.room.dao.GroupDao
import uz.gita.eduroom.data.room.dao.StudentDao
import uz.gita.eduroom.data.room.enitity.CourseEntity
import uz.gita.eduroom.data.room.enitity.GroupEntity
import uz.gita.eduroom.data.room.enitity.StudentEntity

@Database(
    entities = [CourseEntity::class, GroupEntity::class, StudentEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun groupDao(): GroupDao
    abstract fun studentDao(): StudentDao

    companion object {
        private var appDatabase: AppDatabase? = null

        fun init(context: Context) {
            appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "lc_db")
                .allowMainThreadQueries()
                .build()
        }
        fun getInstance() = appDatabase!!
    }

}