package edu.ksu.cs.coursecatalog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cursor = contentResolver.query(
                CourseCatalog.Course.CONTENT_URI,
                arrayOf(CourseCatalog.Course.NUMBER, CourseCatalog.Course.PREFIX, CourseCatalog.Course.TITLE),
                CourseCatalog.Course.PREFIX + " = ?",
                arrayOf("CIS"),
                "",
                null
        )
    }
}
