package com.kotlinDemo.demo.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid
import org.springframework.web.bind.annotation.RequestBody
import com.kotlinDemo.demo.UserData
import javax.annotation.PostConstruct
import java.time.LocalDate
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import java.util.Calendar

@RestController
@RequestMapping("/demo")
class UserController {

	val userData = mutableMapOf<Long, UserData>()

	@PostConstruct
	fun initial() {
		userData.put(1, UserData(1, "Jorge", "Wang", LocalDate.of(1996, 5, 25)))
		userData.put(2, UserData(2, "Sera", "Singh", LocalDate.of(1991, 1, 9)))
		userData.put(3, UserData(3, "Sera", "Singh", LocalDate.of(1984, 6, 18)))
	}

	@GetMapping("/getdata")
	fun getAll(): MutableMap<Long, UserData> {
		return userData
	}

	@PostMapping("/user")
	fun addUser(@Valid @RequestBody newUser: UserData): String {
		userData.put(newUser.id, newUser)
		return "New User Added $newUser"
	}

	@GetMapping("/{id}")
	fun calculateAge(@PathVariable id: Long): String {
		val userName = userData.getValue(id).firstName
		val idData = userData.getValue(id).dob
		val currentYear = Calendar.getInstance().get(Calendar.YEAR)
		val age = currentYear - idData.getYear()
		return "The user $userName is of age $age"
	}

	@GetMapping("/firstName/{firstName}/lastName/{lastName}")
	fun searchUser(@PathVariable firstName: String, @PathVariable lastName: String): List<UserData> {
		val fName = firstName
		val lName = lastName

		val usersList: List<UserData> = userData.values.filter { it.firstName == fName && it.lastName == lName }
		return usersList
	}

	@GetMapping("/firstName/{firstName}")
	fun searchByName(@PathVariable firstName: String): List<UserData> {
		val usersList = ArrayList<UserData>()
		userData.forEach { eachUser ->
			if (eachUser.value.firstName.equals(firstName)) {
				usersList.add(eachUser.value)
			}
		}
		return usersList
	}

}