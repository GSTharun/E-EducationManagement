package com.E.Education.Management.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

 @GetMapping("/login") 
 public String login() {
     return "/login";
 }
 
 @PostMapping("/login")
 public String authenticate(@RequestParam String email, @RequestParam String password, Model model) {
     // Replace this logic with your actual admin authentication mechanism
     if ("admin@gmail.com".equals(email) && "password123".equals(password)) {
         // Successful login
         return "redirect:/menu"; // Redirect to the admin dashboard or another admin page
     } else {
         // Authentication failed, add an error message to the model and return to the login page
         model.addAttribute("error", "Invalid email or password");
         return "admin";
     }
 }
}

