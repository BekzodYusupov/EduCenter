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
import uz.gita.eduroom.data.room.enitity.GroupEntity
import uz.gita.eduroom.domain.presenter.GroupViewModel
import uz.gita.eduroom.ui.adapter.GroupAdapter
import uz.gita.eduroom.ui.dialog.AddGroupDialog

class GroupActivity : AppCompatActivity() {

    private lateinit var listGroups: RecyclerView
    private lateinit var btnAddGroup: FloatingActionButton

    private val adapter: GroupAdapter by lazy { GroupAdapter() }

    private val viewModel: GroupViewModel by lazy { ViewModelProvider(this)[GroupViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        listGroups = findViewById(R.id.listGroups)
        btnAddGroup = findViewById(R.id.btnAddGroup)

        val courseId = intent.getLongExtra("courseId", -1)
        viewModel.getGroups(courseId)

        listGroups.layoutManager = LinearLayoutManager(this)
        listGroups.adapter = adapter

        adapter.setItemClickListener {
            viewModel.openStudentsScreen(it.id)
        }

        btnAddGroup.setOnClickListener {
            viewModel.showDialog()
        }


        viewModel.dialogLiveData.observe(this, dialogObserver)
        viewModel.groupsLiveData.observe(this, groupsObserver)
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.openStudentsScreenLiveData.observe(this, openStudentsObserver)
    }

    private val backObserver = Observer<Unit> {
        finish()
    }

    private val dialogObserver = Observer<Long> {
        val dialog = AddGroupDialog(this, it)
        dialog.setConfirmAddGroupListener {
            viewModel.addGroup(it)
        }
        dialog.show()
    }

    private val groupsObserver = Observer<List<GroupEntity>> {
        adapter.submitList(it)
    }
    private val openStudentsObserver = Observer<Long> {
        val intent = Intent(this, StudentActivity::class.java)
        startActivity(intent)
    }
}