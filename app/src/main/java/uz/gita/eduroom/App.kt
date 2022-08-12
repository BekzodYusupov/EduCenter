package uz.gita.eduroom

import android.app.Application
import uz.gita.eduroom.data.room.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)

    }
}