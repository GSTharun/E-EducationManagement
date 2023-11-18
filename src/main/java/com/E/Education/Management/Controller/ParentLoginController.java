package com.E.Education.Management.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.E.Education.Management.Repo.ParentRepo;
import com.E.Education.Management.dto.Parent;

import jakarta.servlet.http.HttpSession;

@Controller
public class ParentLoginController {
	
	@Autowired
	private ParentRepo parentRepository;
	
	@GetMapping("/plogin")
	public String login() {
		return "plogin"; // Assuming you have a "plogin" template for parent login
	}

	@PostMapping("/plogin")
	public String authenticates(@RequestParam String email, @RequestParam String password, Model model,HttpSession session) {
		// Retrieve the parent record from the database based on the provided email
		Optional<Parent> optionalParent = parentRepository.findByEmail(email);

		if (optionalParent.isPresent()) {
			Parent parent = optionalParent.get();
			
			// Compare the provided password with the password retrieved from the database
			if (parent.getPassword().equals(password) && parent.getEmail().equals(email)) {
				// Successful login
				session.setAttribute("parentFName",parent.getFirstName());
            	session.setAttribute("parentName",parent.getLastName());
            	session.setAttribute("relationToStudent",parent.getGender());
				return "redirect:/parentMenu"; // Redirect to the parent dashboard or another parent page
			}
		}

		// Authentication failed, add an error message to the model and return to the login page
		model.addAttribute("error", "Invalid email or password");
		return "plogin"; // Return to the parent login page
	}
}

