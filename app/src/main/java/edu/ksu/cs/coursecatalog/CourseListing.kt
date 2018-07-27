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
) {}