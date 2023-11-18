package com.E.Education.Management.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.E.Education.Management.Repo.StudentRepo;
import com.E.Education.Management.dto.Parent;
import com.E.Education.Management.dto.Student;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentLoginController {
	
	@Autowired
	private StudentRepo studentRepository;
	
	   @GetMapping("/slogin")
	    public String login() {
	        return "/slogin";
	    }
	    
	    @PostMapping("/slogin")
	    public String authenticates(@RequestParam String email, @RequestParam String password, Model model,HttpSession session,Parent parent) {
	        // Retrieve the student record from the database based on the provided email
	        Optional<Student> optionalStudent = studentRepository.findByEmail(email);

	        if (optionalStudent.isPresent()) {
	            Student student = optionalStudent.get();
	            
	            // Compare the provided password with the password retrieved from the database
	            if (student.getPassword().equals(password) && student.getEmail().equals(email)) {
	                // Successful login
	            	session.setAttribute("studentID", student.getId());
	            	session.setAttribute("studentFName", student.getFirstName());
	            	session.setAttribute("studentName", student.getLastName());
	            	session.setAttribute("studentEmail", student.getEmail());
	     
	                return "redirect:/studentMenu"; // Redirect to the student dashboard or another student page
	            }
	        }

	        // Authentication failed, add an error message to the model and return to the login page
	        model.addAttribute("error", "Invalid email or password");
	        return "studentLogin";
	    }

}
