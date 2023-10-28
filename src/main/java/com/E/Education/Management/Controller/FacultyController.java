package com.E.Education.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.E.Education.Management.Repo.FacultyRepo;
import com.E.Education.Management.dto.Faculty;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

	@Autowired
	private FacultyRepo facultyRepository;

	@GetMapping
	public String listFaculties(Model model) {
		List<Faculty> faculties = facultyRepository.findAll();
		model.addAttribute("faculties", faculties);
		return "faculty/list"; // Assuming you have a "faculty/list.html" template
	}

	@GetMapping("/create")
	public String createFacultyForm(Model model) {
		model.addAttribute("faculty", new Faculty());
		return "faculty/create"; // Assuming you have a "faculty/create.html" template
	}

	@PostMapping("/create")
	public String createFaculty(@ModelAttribute("faculty") Faculty faculty) {
		facultyRepository.save(faculty);
		return "redirect:/faculties";
	}

	@GetMapping("/edit/{id}")
	public String editFacultyForm(@PathVariable Long id, Model model) {
		Faculty faculty = facultyRepository.findById(id).orElse(null);
		if (faculty != null) {
			model.addAttribute("faculty", faculty);
		}
		return "faculty/edit"; // Assuming you have a "faculty/edit.html" template
	}

	@PostMapping("/edit/{id}")
	public String editFaculty(@PathVariable Long id, @ModelAttribute("faculty") Faculty updatedFaculty) {
		Faculty faculty = facultyRepository.findById(id).orElse(null);
		if (faculty != null) {
			faculty.setFirstName(updatedFaculty.getFirstName());
			faculty.setLastName(updatedFaculty.getLastName());
			faculty.setEmail(updatedFaculty.getEmail());
			facultyRepository.save(faculty);
		}
		return "redirect:/faculties";
	}

	@GetMapping("/delete/{id}")
	public String deleteFaculty(@PathVariable Long id) {
		facultyRepository.deleteById(id);
		return "redirect:/faculties";
	}
}
