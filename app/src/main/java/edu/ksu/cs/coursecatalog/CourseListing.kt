package edu.ksu.cs.coursecatalog

data class CourseListing(
        val id: Int,
        val ugrad: Boolean,
        val grad: Boolean,
        val prefix: String,
        val number: String,
        val title: String,
        val description: String,
        val minHours: Int,
        val maxHours: Int,
        val variableHours: Boolean,
        val requisites: String,
        val semesters: String,
        val uge: Boolean,
        val kstate8: String
) {
    var content = mutableMapOf(
            Pair("id", id),
            Pair("ugrad", ugrad),
            Pair("grad", grad),
            Pair("prefix", prefix),
            Pair("number", number),
            Pair("title", title),
            Pair("description", description),
            Pair("minHours", minHours),
            Pair("maxHours", maxHours),
            Pair("variableHours", variableHours),
            Pair("requisites", requisites),
            Pair("semesters", semesters),
            Pair("uge", uge),
            Pair("kstate8", kstate8)
            )
}