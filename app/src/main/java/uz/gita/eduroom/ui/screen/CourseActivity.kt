package uz.gita.eduroom.ui.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.eduroom.R
import uz.gita.eduroom.data.room.enitity.CourseEntity
import uz.gita.eduroom.domain.presenter.CourseViewModel
import uz.gita.eduroom.ui.adapter.CourseAdapter
import uz.gita.eduroom.ui.dialog.AddCourseDialog

class CourseActivity : AppCompatActivity() {

    private lateinit var btnAddCourse: FloatingActionButton
    private lateinit var listCourses: RecyclerView


    private val adapter: CourseAdapter by lazy { CourseAdapter() }

    private val viewModel: CourseViewModel by lazy { ViewModelProvider(this)[CourseViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        btnAddCourse = findViewById(R.id.btnAddCourse)
        listCourses = findViewById(R.id.listCourses)


        viewModel

        adapter.setItemClickListener {
            viewModel.openGroupsScreen(it.id)
        }

        listCourses.layoutManager = LinearLayoutManager(this)
        listCourses.adapter = adapter

        btnAddCourse.setOnClickListener {
            viewModel.showAddDialog()

        }

        viewModel.dialogLiveData.observe(this, dialogObserver)
        viewModel.coursesListLiveData.observe(this, courseListObserver)
        viewModel.openGroupsScreenLiveData.observe(this, openGroupsObserver)


    }

    private val courseListObserver = Observer<List<CourseEntity>> {
        adapter.submitList(it)
    }
    private val dialogObserver = Observer<Unit> {
        val dialog = AddCourseDialog(this)
        dialog.setConfirmAddCourseListener {
            viewModel.addContact(it)
        }
        dialog.show()
    }

    private val openGroupsObserver = Observer<Long> {
        val intent = Intent(this, GroupActivity::class.java)
        intent.putExtra("courseId", it)
        startActivity(intent)
    }




}