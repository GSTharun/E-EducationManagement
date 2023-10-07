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

import com.E.Education.Management.Repo.CourseRepo;
import com.E.Education.Management.Repo.StudentRepo;
import com.E.Education.Management.dto.Course;
import com.E.Education.Management.dto.Student;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepo studentRepository;

	@Autowired
	private CourseRepo courseRepo;

	@GetMapping
	public String listStudents(Model model) {
		List<Student> students = studentRepository.findAll();
		model.addAttribute("students", students);
		return "student/list";
	}

	@GetMapping("/create")
	public String createStudentForm(Model model) {
		model.addAttribute("student", new Student());

		// Fetch the list of courses
		List<Course> courses = courseRepo.findAll();
		model.addAttribute("courses", courses);

		return "student/create";
	}

	@PostMapping("/create")
	public String createStudent(@ModelAttribute("student") Student student, BindingResult bindingResult,
			@RequestParam("courseId") Long courseId) {
		if (bindingResult.hasErrors()) {
			return "student/create";
		}

		// Retrieve the course by its ID
		Course course = courseRepo.findById(courseId).orElse(null);

		if (course != null) {
			// Associate the course with the student
			student.setCourse(course);

			// Save the student
			studentRepository.save(student);
		}

		return "redirect:/students";
	}

	@GetMapping("/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null) {
			model.addAttribute("student", student);
		}
		return "student/edit";
	}

	@PostMapping("/edit/{id}")
	public String editStudent(@PathVariable Long id, @ModelAttribute("student") Student updatedStudent,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "student/edit";
		}
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null) {
			student.setFirstName(updatedStudent.getFirstName());
			student.setLastName(updatedStudent.getLastName());
			student.setEmail(updatedStudent.getEmail());
			student.setPassword(updatedStudent.getPassword());
			studentRepository.save(student);
		}
		return "redirect:/students";
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentRepository.deleteById(id);
		return "redirect:/students";
	}

	@GetMapping("/details/{id}")
	public String studentDetails(@PathVariable Long id, Model model) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null) {
			model.addAttribute("student", student);
			return "student/details";
		}
		return "redirect:/students";
	}

}
