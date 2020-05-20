package org.hyojeong.stdmgt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.StudentDomestic;
import org.hyojeong.stdmgt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private UserService userService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		model.addAttribute("isLogined", false );
		
		return "home";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpSession session, Model model) {
		logger.info("Welcome search");
		
		List<Student> voList = userService.getStudentAll();
//		List<Student> voList = new ArrayList<Student>();
//		voList.add(userService.getStudent(1));
		System.out.println(voList);
		
		JSONArray jsonArray = null;
		
		if(voList != null)	{
			jsonArray = JSONArray.fromObject(voList);
			System.out.println(jsonArray);
			
			model.addAttribute("data", jsonArray);
			
		}
		  
		return "search";
	}
	
	@RequestMapping(value = "/search_domestic", method = RequestMethod.GET)
	public String search_domestic(HttpSession session, Model model) {
		logger.info("Welcome search_domestic");
		
		List<StudentDomestic> voList = userService.getStudentDomesticAll();
//		List<Student> voList = new ArrayList<Student>();
//		voList.add(userService.getStudent(1));
		JSONArray jsonArray = null;
		
		if(voList != null)	{
			jsonArray = JSONArray.fromObject(voList);
			System.out.println(jsonArray);
			
			model.addAttribute("data", jsonArray);
		}
		  
		return "search_domestic";
	}
	
	
	@RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
	public ModelAndView studentInfo(HttpSession session, Model model) {
		logger.info("Welcome studentInfo");
		
		//자신의 학생 정보를 DB에서 가져오기
		int studentPid= (Integer) session.getAttribute("pid");
		System.out.println("[개인 정보 변경] pid=>"+ studentPid);
		Student student = userService.getStudent(studentPid);
		
		ModelAndView mav = new ModelAndView("studentInfo");
		
		if(student != null)	{
			System.out.println(student);
			
			mav.addObject("student", student);
		}
		else	{
			mav = new ModelAndView("home");
		}
		  
		return mav;
	}
}