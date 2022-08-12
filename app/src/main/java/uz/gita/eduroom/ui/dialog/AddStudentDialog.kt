package uz.gita.eduroom.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import uz.gita.eduroom.R
import uz.gita.eduroom.data.room.enitity.StudentEntity

class AddStudentDialog(context: Context, val groupId: Long) : Dialog(context) {

    private lateinit var inputStudentName: EditText
    private lateinit var btnConfirm: Button

    private var confirmAddStudentListener: ((StudentEntity) -> Unit)? = null

    fun setConfirmAddStudentListener(block: (StudentEntity) -> Unit) {
        confirmAddStudentListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_student)
        inputStudentName = findViewById(R.id.inputStudentName)
        btnConfirm = findViewById(R.id.btnConfirm)

        btnConfirm.setOnClickListener {
            val text = inputStudentName.text.toString()
            if (text.isNotEmpty()) {
                confirmAddStudentListener?.invoke(StudentEntity(0, text, groupId))
                dismiss()
            } else {
                Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show()
            }
        }

    }
}