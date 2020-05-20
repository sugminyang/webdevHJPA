package org.hyojeong.stdmgt.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

	@Inject
	public UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(HttpServletRequest request, HttpServletResponse response) {

		return "register";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("student") Student student, @ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		
		if(userService.idCheck(user.getId()) == 0)	{	//신규 
			userService.register(user);
			
			if(student != null)	{
				mav = new ModelAndView("studentInfo");
				mav.addObject("student", student);
				mav.addObject("authority",0);
			}
			else	{
				mav.addObject("student", new Student());
			}
		}
		else	{
			mav = new ModelAndView("register");
		}
		
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/idchk", method = RequestMethod.POST)
	public void idchk(@ModelAttribute("id") String id, HttpServletResponse response) {
		int data = userService.idCheck(id);
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("data",data);
//		data == 1 //중복
//		data == 0 //사용가능
		System.out.println((data == 1)? "중복된 아이디":"사용가능한 아이디");

	    try {
	        response.getWriter().print(data);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }   
	}
	
}
