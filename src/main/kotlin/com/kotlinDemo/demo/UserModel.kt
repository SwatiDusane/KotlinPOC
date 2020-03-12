package com.kotlinDemo.demo

import java.util.Date
import java.time.LocalDate

data class UserData (
	var id: Long = -1,
    var firstName: String = "",
    var lastName: String = "",
    var dob: LocalDate = LocalDate.of(1990,1,8)) {
}