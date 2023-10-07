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


	
}
