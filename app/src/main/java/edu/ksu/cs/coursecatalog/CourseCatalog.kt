package edu.ksu.cs.coursecatalog

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import org.xmlpull.v1.XmlPullParser

class CourseCatalog : ContentProvider() {

    lateinit var nCourses: Array<CourseListing>

    override fun onCreate(): Boolean {
        var xml = context.resources.getXml(R.xml.courses)

        var courses = mutableListOf<CourseListing>()

        var eventType = xml.next()

        val stringBuffer = StringBuffer()

        fun parseCourse(){
            eventType = xml.next()
            var map = mutableMapOf<String, String>()
            while(xml.name != "htmlparse.Course"){
                if(eventType == XmlPullParser.START_TAG){
                    map[xml.name] = xml.nextText()
                }
                eventType = xml.next()
            }
            var courseListing: CourseListing = CourseListing(
                    map.getValue("id").toInt(),
                    map.getValue("ugrad").toBoolean(),
                    map.getValue("grad").toBoolean(),
                    map.getValue("prefix"),
                    map.getValue("number"),
                    map.getValue("title"),
                    map.getValue("description"),
                    map.getValue("minHours").toInt(),
                    map.getValue("maxHours").toInt(),
                    map.getValue("variableHours").toBoolean(),
                    map.getValue("requisites"),
                    map.getValue("semesters"),
                    map.getValue("uge").toBoolean(),
                    map.getValue("kstate8")
            )
            courses.add(courseListing)
        }

        while(eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xml.name == "htmlparse.Course") parseCourse()
            eventType = xml.next()
        }

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
        TODO("Implement this to handle query requests from clients.")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}
