package uz.gita.eduroom.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import uz.gita.eduroom.R
import uz.gita.eduroom.data.room.enitity.GroupEntity

class AddGroupDialog(context: Context, val courseId: Long) : Dialog(context) {

    private lateinit var inputGroupName: EditText
    private lateinit var btnConfirm: Button

    private var confirmAddGroupListener: ((GroupEntity) -> Unit)? = null

    fun setConfirmAddGroupListener(block: (GroupEntity) -> Unit) {
        confirmAddGroupListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_group)

        inputGroupName = findViewById(R.id.inputGroupName)
        btnConfirm = findViewById(R.id.btnConfirm)

        btnConfirm.setOnClickListener {
            val text = inputGroupName.text.toString()
            if (text.isNotEmpty()) {
                confirmAddGroupListener?.invoke(GroupEntity(0, text, courseId))
                dismiss()
            } else {
                Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show()
            }
        }

    }
}