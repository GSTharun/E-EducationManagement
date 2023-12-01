package com.E.Education.Management.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String welcomePage() {
		return "welcome"; // This corresponds to the Thymeleaf template name (welcome.html).
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "admin"; // Create an "admin.html" Thymeleaf template similarly.
	}

	@GetMapping("/studentLogin")
	public String studentPage() {
		return "studentLogin"; // Create a "student.html" Thymeleaf template similarly.
	}

	@GetMapping("/faculty")
	public String facultyPage() {
		return "faculty"; // Create a "faculty.html" Thymeleaf template similarly.
	}

	@GetMapping("/parent")
	public String parentPage() {
		return "parent"; // Create a "parent.html" Thymeleaf template similarly.
	}

	@GetMapping("/menu")
	public String menuPage() {
		return "menu"; // Create a "menu.html" Thymeleaf template similarly.
	}
	
	@GetMapping("/studentMenu")
	public String studentmenu() {
		return "studentMenu"; // Create a "menu.html" Thymeleaf template similarly.
	}
		
	@GetMapping("/FacultyLogin")
	public String facultyPages() {
		return "FacultyLogin"; // Create a "student.html" Thymeleaf template similarly.
	}

	@GetMapping("/FacultyMenu")
	public String FacultyMenu() {
		return "FacultyMenu"; // Create a "menu.html" Thymeleaf template similarly.
	}
	
	@GetMapping("/ParentLogin")
	public String parentPages() {
		return "ParentLogin"; // Create a "student.html" Thymeleaf template similarly.
	}

	@GetMapping("/ParentMenu")
	public String ParentMenu() {
		return "ParentMenu"; // Create a "menu.html" Thymeleaf template similarly.
	}
	
	@GetMapping("/profile")
	public String StudentProfile() {
		return "profile"; // Create a "menu.html" Thymeleaf template similarly.
	}
	
	@GetMapping("/profilef")
	public String FacultyProfile() {
		return "profilef"; // Create a "menu.html" Thymeleaf template similarly.
	}
	
	@GetMapping("/profilep")
	public String ParentProfile() {
		return "profilep"; // Create a "menu.html" Thymeleaf template similarly.
	}
	
}
