package uz.gita.eduroom.data.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(
        onConflict = OnConflictStrategy.ABORT
    )
    fun insert(vararg data: T)

    @Insert(
        onConflict = OnConflictStrategy.IGNORE
    )

    fun insert(data: List<T>)

    @Update()
    fun update(vararg data: T)

    @Update
    fun update(data: List<T>)

    @Delete
    fun delete(vararg data: T)

    @Delete
    fun delete(data: List<T>)
}
