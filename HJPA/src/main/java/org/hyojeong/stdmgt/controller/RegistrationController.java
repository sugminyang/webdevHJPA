package org.hyojeong.stdmgt.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.User;
import org.hyojeong.stdmgt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

	@Inject
	public UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
//		mav.addObject("user", new User());

		return mav;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("student") Student student) {

		userService.register(student);
		ModelAndView mav = new ModelAndView("welcome");
		
		if(student != null)	{
			mav.addObject("student", student);
		}
		else	{
			mav.addObject("student", new Student());
		}
		
		return mav;
	}
}
