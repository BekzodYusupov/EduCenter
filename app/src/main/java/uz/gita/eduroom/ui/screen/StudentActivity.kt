package uz.gita.eduroom.ui.screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.eduroom.R
import uz.gita.eduroom.data.room.enitity.StudentEntity
import uz.gita.eduroom.domain.presenter.StudentViewModel
import uz.gita.eduroom.ui.adapter.StudentAdapter
import uz.gita.eduroom.ui.dialog.AddStudentDialog

class StudentActivity : AppCompatActivity() {
    private lateinit var listStudents: RecyclerView
    private lateinit var btnAddStudent: FloatingActionButton

    private val adapter: StudentAdapter by lazy { StudentAdapter() }

    private val viewModel: StudentViewModel by lazy { ViewModelProvider(this)[StudentViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        listStudents = findViewById(R.id.listStudents)
        btnAddStudent = findViewById(R.id.btnAddStudent)

        val groupId = intent.getLongExtra("groupId", -1)
        viewModel.getStudents(groupId)

        listStudents.layoutManager = LinearLayoutManager(this)
        listStudents.adapter = adapter

        btnAddStudent.setOnClickListener {
            viewModel.showDialog()
        }


        viewModel.dialogLiveData.observe(this, dialogObserver)
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.groupsLiveData.observe(this, studentsObserver)


    }

    private val backObserver = Observer<Unit> {
        finish()
    }

    private val dialogObserver = Observer<Long> {
        val dialog = AddStudentDialog(this, it)
        dialog.setConfirmAddStudentListener {
            viewModel.addStudent(it)
        }
        dialog.show()
    }

    private val studentsObserver = Observer<List<StudentEntity>> {
        adapter.submitList(it)
    }
    private val openStudentsObserver = Observer<Long> {
        val intent = Intent(this, StudentActivity::class.java)
        intent.putExtra("groupId", it)
        startActivity(intent)
    }
}
