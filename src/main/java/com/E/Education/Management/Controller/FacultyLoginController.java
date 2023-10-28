package com.E.Education.Management.Controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.E.Education.Management.Repo.FacultyRepo;
import com.E.Education.Management.dto.Faculty;

import jakarta.servlet.http.HttpSession;

@Controller
public class FacultyLoginController {

    @Autowired
    private FacultyRepo facultyRepository;

    @GetMapping("/flogin")
    public String login() {
        return "flogin"; // Update this to match your faculty login page template name
    }

    @PostMapping("/flogin")
    public String authenticate(@RequestParam String email, @RequestParam String password, Model model,HttpSession session) {
        // Retrieve the faculty record from the database based on the provided email
        Optional<Faculty> optionalFaculty = facultyRepository.findByEmail(email);

        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();

            // Compare the provided password with the password retrieved from the database
            if (faculty.getPassword().equals(password) && faculty.getEmail().equals(email)) {
                // Successful login
            	session.setAttribute("facultyFName", faculty.getFirstName());
            	session.setAttribute("facultyName", faculty.getLastName());
            	session.setAttribute("facultyEmail", faculty.getEmail());
            	return "redirect:/FacultyMenu"; // Redirect to the faculty dashboard or another faculty page
            }
        }

        // Authentication failed, add an error message to the model and return to the login page
        model.addAttribute("error", "Invalid email or password");
        return "FacultyLogin"; // Update this to match your faculty login page template name
    }
}

