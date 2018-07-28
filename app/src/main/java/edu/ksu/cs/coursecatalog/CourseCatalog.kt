package edu.ksu.cs.coursecatalog

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import org.xmlpull.v1.XmlPullParser

class CourseCatalog : ContentProvider() {

    lateinit var nCourses: Array<CourseListing>

    companion object {
        val AUTHORITY = "edu.ksu.cs.coursecatalog.provider"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/courses")
    }

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, "courses", 1)
        uriMatcher.addURI(AUTHORITY, "courses/#", 2)
    }

    override fun onCreate(): Boolean {
        var xml = context.resources.getXml(R.xml.courses)

        var courses = mutableListOf<CourseListing>()

        var eventType = xml.next()

        val stringBuffer = StringBuffer()

        fun parseCourse(){
            var id: Int = -1
            var ugrad: Boolean = false
            var grad: Boolean = false
            var prefix: String = ""
            var number: String = ""
            var title: String = ""
            var description: String = ""
            var minHours: Int = -1
            var maxHours: Int = -1
            var variableHours: Boolean = false
            var requisites: String = ""
            var semesters: String = ""
            var uge: Boolean = false
            var kstate8: String = ""
            eventType = xml.next()
            var map = mutableMapOf<String, String>()

            var name: String
            while(xml.name != "htmlparse.Course"){
                if(eventType == XmlPullParser.START_TAG){
                    when(xml.name){
                        "id" -> id = xml.nextText().toInt()
                        "ugrad" -> ugrad = xml.nextText().toBoolean()
                        "grad" -> grad = xml.nextText().toBoolean()
                        "prefix" -> prefix = xml.nextText()
                        "number" -> number = xml.nextText()
                        "title" -> title = xml.nextText()
                        "description" -> description = xml.nextText()
                        "minHours" -> minHours = xml.nextText().toInt()
                        "maxHours" -> maxHours = xml.nextText().toInt()
                        "variableHours" -> variableHours = xml.nextText().toBoolean()
                        "requisites" -> requisites = xml.nextText()
                        "semesters" -> semesters = xml.nextText()
                        "uge" -> uge = xml.nextText().toBoolean()
                        "kstate8" -> kstate8 = xml.nextText()
                    }
                }
                eventType = xml.next()
            }
            courses.add(CourseListing(id, ugrad, grad, prefix, number, title, description, minHours, maxHours, variableHours, requisites, semesters, uge, kstate8))
        }

        while(eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xml.name == "htmlparse.Course") {
                parseCourse()
            }
            eventType = xml.next()
        }

        nCourses = courses.toTypedArray()

        Log.i("xmlcontent", stringBuffer.toString())

        return true
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO("Implement this to handle requests for the MIME type of the data" +
                "at the given URI")
    }

    override fun insert(uri: Uri, values: ContentValues): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        when (uriMatcher.match(uri)) {
            1 -> { // All Courses
                var cursor = MatrixCursor(projection, nCourses.size)
                var course: CourseListing
                var list = mutableListOf<Any>()
                for (i in 0..(nCourses.size - 1)) {
                    course = nCourses[i]
                    for (str in projection!!.iterator()) {
                        list.add(course.content.get(str) ?: "")
                    }
                    cursor.addRow(list)
                    list.clear()
                }
                return cursor
            }
            2 -> { // Specific course
            }
        }
        return null
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}
