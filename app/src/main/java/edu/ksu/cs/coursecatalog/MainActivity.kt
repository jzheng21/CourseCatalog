package edu.ksu.cs.coursecatalog

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cursor = contentResolver.query(
                Uri.parse("content://edu.ksu.cs.coursecatalog.provider/courses"),
                arrayOf("id", "title"),
                "",
                emptyArray(),
                "",
                null
        )
    }
}
