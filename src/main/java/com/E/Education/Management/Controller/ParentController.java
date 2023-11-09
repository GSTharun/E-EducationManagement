package com.E.Education.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.E.Education.Management.Repo.ParentRepo;
import com.E.Education.Management.Repo.StudentRepo;
import com.E.Education.Management.dto.Parent;
import com.E.Education.Management.dto.Student;

@Controller
	@RequestMapping("/parents")
	public class ParentController {
	
	@Autowired
	private ParentRepo parentRepository;

	@Autowired
	private StudentRepo studentRepo;
	
	@GetMapping
	public String listParents(Model model) {
	    List<Parent> parents = parentRepository.findAll();
	    model.addAttribute("parents", parents);
	    return "parent/list";
	}// Create the "parent/list.html" template for displaying parents.
	    
	    @GetMapping("/create")
	    public String createParentForm(Model model) {
	        model.addAttribute("parent", new Parent());

	        // Fetch the list of student names
	        List<Student> students = studentRepo.findAll();
	        model.addAttribute("students", students);

	        return "parent/create"; // Create the "parent/create.html" template for creating parents.
	    }

	    @PostMapping("/create")
	    public String createParent(@ModelAttribute("parent") Parent parent, BindingResult bindingResult,
	            @RequestParam("studentId") Long studentId) {
	        if (bindingResult.hasErrors()) {
	            return "parent/create"; // Create the "parent/create.html" template for creating parents.
	        }

	        // Retrieve the student by their ID
	        Student student = studentRepo.findById(studentId).orElse(null);

	        if (student != null) {
	            // Add the student to the list of students associated with the parent
	            parent.getStudents().add(student);

	            // Save the parent
	            parentRepository.save(parent);
	        }

	        return "redirect:/parents";
	    }

	    
	    
	    @GetMapping("/edit/{id}")
	    public String editParentForm(@PathVariable Long id, Model model) {
	        Parent parent = parentRepository.findById(id).orElse(null);
	        if (parent != null) {
	            model.addAttribute("parent", parent);
	        }
	        return "parent/edit"; // Create the "parent/edit.html" template for editing parents.
	    }

	    @PostMapping("/edit/{id}")
	    public String editParent(@PathVariable Long id, @ModelAttribute("parent") Parent updatedParent,
	            BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return "parent/edit"; // Create the "parent/edit.html" template for editing parents.
	        }
	        Parent parent = parentRepository.findById(id).orElse(null);
	        if (parent != null) {
	            parent.setFirstName(updatedParent.getFirstName());
	            parent.setLastName(updatedParent.getLastName());
	            parent.setEmail(updatedParent.getEmail());
	            parent.setPassword(updatedParent.getPassword());
	            parentRepository.save(parent);
	        }
	        return "redirect:/parents";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteParent(@PathVariable Long id) {
	        parentRepository.deleteById(id);
	        return "redirect:/parents";
	    }

	    @GetMapping("/details/{id}")
	    public String parentDetails(@PathVariable Long id, Model model) {
	        Parent parent = parentRepository.findById(id).orElse(null);
	        if (parent != null) {
	            model.addAttribute("parent", parent);
	            return "parent/details"; // Create the "parent/details.html" template for displaying parent details.
	        }
	        return "redirect:/parents";
	    }

	    
	}




