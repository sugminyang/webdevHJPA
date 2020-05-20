package org.hyojeong.stdmgt.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.User;
import org.hyojeong.stdmgt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Inject
	private UserService userService;
 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());

		return mav;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpSession session, @ModelAttribute("login") Login vo) {
		ModelAndView mav = null;
		System.out.println("enter.. loginProcess");
		User user = userService.validateUser(vo,session);
		
		System.out.println(user);
		if (null != user) {
			mav = new ModelAndView("home");
			mav.addObject("authority",user.getAuthority());
			
			Student student = userService.getStudent(user.getPid());
			if(student != null)	{
				mav.addObject("student", student);
			}
			else	{
				mav.addObject("student", new Student());
			}
			
		} else {
			mav = new ModelAndView("login");
			mav.addObject("msg", "fail");
		}

		return mav;
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView showLogout(HttpSession session) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("isLogined",false);
		mav.addObject("msg","logout");
		
		//session정보 초기화
		session.invalidate();
		
		return mav;
	}
}
