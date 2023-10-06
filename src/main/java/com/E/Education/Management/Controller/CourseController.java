package com.E.Education.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.E.Education.Management.Repo.CourseRepo;
import com.E.Education.Management.dto.Course;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepo courseRepository;

	@GetMapping
	public String listCourses(Model model) {
		List<Course> courses = courseRepository.findAll();
		model.addAttribute("courses", courses);
		return "course/list";
	}

	@GetMapping("/create")
	public String createCourseForm(Model model) {
		model.addAttribute("course", new Course());
		return "course/create";
	}

	@PostMapping("/create")
	public String createCourse(@ModelAttribute("course") Course course) {
		courseRepository.save(course);
		return "redirect:/courses";
	}

	@GetMapping("/edit/{id}")
	public String editCourseForm(@PathVariable Long id, Model model) {
	    Course course = courseRepository.findById(id).orElse(null);
	    if (course != null) {
	        model.addAttribute("course", course); // Add the course object to the model
	    }
	    return "course/edit";
	}

	

	@PostMapping("/edit/{id}")
	public String editCourse(@PathVariable Long id, @ModelAttribute("course") Course updatedCourse) {
		Course course = courseRepository.findById(id).orElse(null);
		if (course != null) {
			course.setName(updatedCourse.getName());
			course.setDescription(updatedCourse.getDescription());
			courseRepository.save(course);
		}
		return "redirect:/courses";
	}

	@GetMapping("/delete/{id}")
	public String deleteCourse(@PathVariable Long id) {
		courseRepository.deleteById(id);
		return "redirect:/courses";
	}
}
