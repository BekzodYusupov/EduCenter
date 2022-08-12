package uz.gita.eduroom.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import uz.gita.eduroom.R
import uz.gita.eduroom.data.room.enitity.CourseEntity

class AddCourseDialog(context: Context) : Dialog(context) {

    private lateinit var inputCourseName: EditText
    private lateinit var btnConfirm: Button

    private var confirmAddCourseListener: ((CourseEntity) -> Unit)? = null

    fun setConfirmAddCourseListener(block: (CourseEntity) -> Unit) {
        confirmAddCourseListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_course)

        inputCourseName = findViewById(R.id.inputCourseName)
        btnConfirm = findViewById(R.id.btnConfirm)

        btnConfirm.setOnClickListener {
            val text = inputCourseName.text.toString()
            if (text.isNotEmpty()) {
                confirmAddCourseListener?.invoke(CourseEntity(0, text))
                dismiss()
            } else {
                Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}