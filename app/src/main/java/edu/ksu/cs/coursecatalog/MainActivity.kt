package edu.ksu.cs.coursecatalog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cursor = contentResolver.query(
                CourseCatalog.Course.CONTENT_URI,
                arrayOf(CourseCatalog.Course.NUMBER, CourseCatalog.Course.PREFIX, CourseCatalog.Course.TITLE, CourseCatalogContract.CourseListing.COLUMN_NAME_DESCRIPTION),
                "prefix = ? AND number = ?",
                arrayOf("CIS", "490"),
                "",
                null
        )

        var stringBuilder = StringBuilder()
        while(cursor.moveToNext()){
            var msg = StringBuffer()
            for (i in 0..(cursor.count + 1)){
                msg.append(cursor.getString(i) + " ")
            }
            Log.i("Courses", msg.toString())
        }
    }
}
