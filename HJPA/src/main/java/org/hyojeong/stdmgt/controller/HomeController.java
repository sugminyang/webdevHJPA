package org.hyojeong.stdmgt.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.model.AbsenceHistory;
import org.hyojeong.stdmgt.model.ActiveHistory;
import org.hyojeong.stdmgt.model.AwardsHistory;
import org.hyojeong.stdmgt.model.ConsultHistory;
import org.hyojeong.stdmgt.model.GradeHistory;
import org.hyojeong.stdmgt.model.GrantHistory;
import org.hyojeong.stdmgt.model.HolyHistory;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.StudentDomestic;
import org.hyojeong.stdmgt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/adminpage", method = RequestMethod.GET)
	public String adminpage(HttpSession session) {
		String auth = (String) session.getAttribute("auth");
		if(!auth.equalsIgnoreCase("2"))	{	//system admin
			return "redirect:/";
		}
		else	{
			return "admin_page";	
		}
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session) {
		String auth = (String) session.getAttribute("auth");
		
		if(auth.equalsIgnoreCase("0"))	{	//student has no authority to searh
			return "redirect:/";
		}
		else	{
			return "search";	
		}
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpSession session, Model model) {
		String auth = (String) session.getAttribute("auth");
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			return "redirect:/";
		}
		
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
	
	
	@RequestMapping(value = "/searchStudent", method = RequestMethod.POST,produces = "application/text; charset=UTF-8")
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String searchStudent(Model model, @RequestBody String filterJSON, HttpSession session,HttpServletResponse response) {
		String auth = (String) session.getAttribute("auth");
		
		if(auth.equalsIgnoreCase("0"))	{	//student has no authority to searh
			return "redirect:/";
		}
		
//		System.out.println("searchStudent...");
//		System.out.println(filterJSON);

		String[] items = filterJSON.split("!@#");
		String category = items[0];
		String value = items[1];
		
		List<Student> voList = null;
		if(value.trim().length() == 0)	{
			voList = userService.getStudentAll();
		}
		else	{
			voList = userService.searchStudents(category,value);
		}
		
//		System.out.println(voList);
		
		JSONArray jsonArray = null;
		
		if(voList != null)	{
			response.setContentType("json;charset=UTF-8"); 
			jsonArray = JSONArray.fromObject(voList);
			System.out.println(jsonArray);
		}

		return jsonArray.toString();
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
		System.out.println("Welcome studentInfo");
		
		//자신의 학생 정보를 DB에서 가져오기
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");	
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
		}
		
		System.out.println("[학생] pid=>"+ studentPid);				
		Student student = userService.getStudent(studentPid);
		
		ModelAndView mav = new ModelAndView("studentInfo");
		JSONArray jsonArray = null;
		
		if(student != null)	{
			System.out.println(student);
			
			mav.addObject("student", student);
			
//			학적 이력 load
			List<GradeHistory> gradeHistoryList = userService.getGradeHistory(student.getPid());
			int totalCredit = 0;
			if(gradeHistoryList != null)	{
				jsonArray = JSONArray.fromObject(gradeHistoryList);
//				System.out.println("학적 이력: "+jsonArray);
				
				model.addAttribute("semesterInfo", jsonArray);
				

				if(student.getTotalCredit() == 0)	{ // 이수학점을 처음으로 계산하는 학생
					//총 이수학점 정보
					for(GradeHistory vo : gradeHistoryList)	{
						totalCredit += vo.getCredit();
					}
					
					//총 이수학점 데이터 저장.	 
					student.setTotalCredit(totalCredit);
					userService.saveTotalCredit(student);
					
					mav.addObject("totalCredit",totalCredit);
				}
				else { // 이전에 기록이 있는 학생
					mav.addObject("totalCredit",student.getTotalCredit());
				}
				
				int totalGradeWarning = 0;
				if(student.getTotalGradeWarning() == 0)	{ // 성적 경고을 처음으로 계산하는 학생
					//총 성적 경고 정보
					for(GradeHistory vo : gradeHistoryList)	{
						totalGradeWarning += vo.getWarnings();
					}
					
					//총 성적 경고 데이터 저장.	 
					student.setTotalGradeWarning(totalGradeWarning);
					userService.saveTotalGradeWarning(student);
				}
			}
			else	{
				mav.addObject("totalCredit",totalCredit);
			}
			
//			신앙 이력 load
			List<HolyHistory> holyHistoryList = userService.getHolyHistory(student.getPid());
			if(holyHistoryList != null)	{
				jsonArray = JSONArray.fromObject(holyHistoryList);
//				System.out.println("신앙 이력: "+jsonArray);
				
				model.addAttribute("holyInfo", jsonArray);
				
				
				int totalHolyWarning = 0;
				if(student.getTotalHolyWarning() == 0)	{ // 신앙 경고를 처음으로 계산하는 학생
					//총 신앙 경고 정보
					for(HolyHistory vo : holyHistoryList)	{
						totalHolyWarning += vo.getWarnings();
					}
					
					//총 신앙 경고 데이터 저장.	 
					student.setTotalHolyWarning(totalHolyWarning);
					userService.saveTotalHolyWarning(student);
				}
			}
			
//			프로그램 활동 이력 load
			List<ActiveHistory> activeHistoryList = userService.getActiveHistory(student.getPid());
			if(activeHistoryList != null)	{
				jsonArray = JSONArray.fromObject(activeHistoryList);
//				System.out.println("프로그램 활동 이력: "+jsonArray);
				
				model.addAttribute("activeInfo", jsonArray);
			}
			
//			수상 이력 load
			List<AwardsHistory> awardsHistoryList = userService.getAwardsHistory(student.getPid());
			if(awardsHistoryList != null)	{
				jsonArray = JSONArray.fromObject(awardsHistoryList);
//				System.out.println("수상 이력: "+jsonArray);
				
				model.addAttribute("awardsInfo", jsonArray);
			}
			
//			상담 이력 load
			
			List<ConsultHistory> consultHistoryList = userService.getConsultHistory(student.getPid());
			if(consultHistoryList != null)	{
				jsonArray = JSONArray.fromObject(consultHistoryList);
//				System.out.println("상담 이력: "+jsonArray);
				
				model.addAttribute("consultInfo", jsonArray);
			}
			
//			휴학 이력 load
			List<AbsenceHistory> absenceHistoryList = userService.getAbsenceHistory(student.getPid());
			if(absenceHistoryList != null)	{
				jsonArray = JSONArray.fromObject(absenceHistoryList);
				
				model.addAttribute("absenceInfo", jsonArray);
			}	
			
//			장학 이력 load
			List<GrantHistory> grantHistoryList = userService.getGrantHistory(student.getPid());
			if(grantHistoryList != null)	{
				jsonArray = JSONArray.fromObject(grantHistoryList);
				
				model.addAttribute("grantInfo", jsonArray);
			}				
		}
		else	{
			mav = new ModelAndView("home");
		}
		 
//		AbsenceHistory test = new AbsenceHistory();
//		jsonArray = JSONArray.fromObject(test);
//		model.addAttribute("absenceInfo", jsonArray);

//		GrantHistory temp = new GrantHistory();
//		jsonArray = JSONArray.fromObject(temp);
//		model.addAttribute("grantInfo", jsonArray);		
		
		
		
		return mav;
	}
	
	@RequestMapping(value = "/getStudentInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String getStudentInfo(HttpSession session, @RequestParam("pid") int pid) {
		//관심학생 등록
		session.setAttribute("sid",pid);
		return "";
	}	
	
	@RequestMapping(value = "/updateStudentInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String updateStudentInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("updateStudentInfo... 학생 정보 수정..request: ");
		System.out.println(filterJSON);
		
		
		String[] items = filterJSON.split("!@#");
//		System.out.println("학생 정보 변경사항: " + items.length);
		Student updateStdInfo = new Student(items[0], items[1], Integer.parseInt(items[2]),items[3], 
				items[4], items[5], items[6], items[7], items[8], items[9], items[10],
				items[11], items[12],items[13], items[14],items[15], items[16],items[17],items[18],items[19]);
		
//		System.out.println(updateStdInfo);
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			updateStdInfo.setPid(studentPid);
			System.out.println(updateStdInfo);
			result = userService.updateStudentInfo(updateStdInfo);
		}
		else	{
			int modifiedUser = (Integer) session.getAttribute("pid");
			studentPid = (Integer) session.getAttribute("sid");
			updateStdInfo.setPid(studentPid);
			System.out.println(updateStdInfo);
			result = userService.updateAllItemsStudentInfo(updateStdInfo,modifiedUser);
		}
		
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return test;
	}
	
	@RequestMapping(value = "/gradeHistoryInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String gradeHistoryInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("gradeHistoryInfo... 학적 정보 수정: ");
//		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
//		System.out.println("학생 정보 변경사항: " + items.length);

		GradeHistory gHis = new GradeHistory(Integer.parseInt(items[0]),Integer.parseInt(items[5]), items[1], Integer.parseInt(items[2]), items[3], Integer.parseInt(items[4]));
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			//TODO: table에 데이터 수정!!!
//			result = userService.updateStudentInfo(updateStdInfo);
			gHis.setModifiedBy(studentPid);
			result = userService.updateGradeHistory(gHis);
			

		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			//TODO: table에 데이터 수정!!!
//			result = userService.updateAllItemsStudentInfo(updateStdInfo);
			gHis.setModifiedBy((Integer) session.getAttribute("pid"));
			result = userService.updateGradeHistory(gHis);
		}
		
		Student student = userService.getStudent(studentPid);
		int credit = Integer.parseInt(items[2]);
		student.setTotalCredit(student.getTotalCredit() + credit);
		userService.saveTotalCredit(student);
		
		int warning = Integer.parseInt(items[4]);
		student.setTotalGradeWarning(student.getTotalGradeWarning() + warning);
		userService.saveTotalGradeWarning(student);
		
		
		System.out.println(gHis);
		System.out.println("update result: "+result);
		
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
		}
		
		return gHis.getTid()+"";
	}
	
	@RequestMapping(value = "/activeHistoryInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String activeHistoryInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("activeHistoryInfo... 학적 정보 수정: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		ActiveHistory aHis = new ActiveHistory(Integer.parseInt(items[4]),Integer.parseInt(items[0]),
				Integer.parseInt(items[1]), items[2], items[3]);
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			//TODO: table에 데이터 수정!!!
			aHis.setModifiedBy(studentPid);
			result = userService.updateActiveHistory(aHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			//TODO: table에 데이터 수정!!!
			aHis.setModifiedBy((Integer) session.getAttribute("pid"));
			result = userService.updateActiveHistory(aHis);
		}
		
		System.out.println(aHis);
		System.out.println("update result: "+result);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
		return aHis.getTid()+"";
	}
	
	@RequestMapping(value = "/awardsHistoryInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String awardsHistoryInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("awardsHistoryInfo... 학적 정보 수정: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		AwardsHistory awHis = new AwardsHistory(Integer.parseInt(items[5]), Integer.parseInt(items[0]), Integer.parseInt(items[1]),
				items[2],items[3],items[4]);
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			//TODO: table에 데이터 수정!!!
			awHis.setModifiedBy(studentPid);
			result = userService.updateAwardsHistory(awHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			//TODO: table에 데이터 수정!!!
			awHis.setModifiedBy((Integer) session.getAttribute("pid"));
			result = userService.updateAwardsHistory(awHis);
		}

		System.out.println(awHis);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return awHis.getTid()+"";
	}	

	
	@RequestMapping(value = "/activeHistoryRemoveInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String activeHistoryRemoveInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("activeHistoryRemoveInfo... 학적 정보 삭제: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		ActiveHistory aHis = new ActiveHistory(Integer.parseInt(items[1]), Integer.parseInt(items[0]));
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			result = userService.removeActiveHistory(aHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			result = userService.removeActiveHistory(aHis);
		}
		
		System.out.println(aHis);
		System.out.println("update result: "+result);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return test;
	}	
	
	
	
	@RequestMapping(value = "/awardsHistoryRemoveInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String awardsHistoryRemoveInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("awardsHistoryRemoveInfo... 학적 정보 삭제: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		AwardsHistory awHis = new AwardsHistory(Integer.parseInt(items[1]), Integer.parseInt(items[0]));
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			result = userService.removeAwardsHistory(awHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			result = userService.removeAwardsHistory(awHis);
		}
		
		System.out.println(awHis);
		System.out.println("update result: "+result);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return test;
	}	
	
	@RequestMapping(value = "/gradeHistoryRemoveInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String gradeHistoryRemoveInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("gradeHistoryRemoveInfo... 학적 정보 삭제: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		GradeHistory gHis = new GradeHistory(Integer.parseInt(items[1]), Integer.parseInt(items[0]));
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			result = userService.removeGradeHistory(gHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			result = userService.removeGradeHistory(gHis);
		}
		
		int totalCredit = 0;
		Student student = userService.getStudent(studentPid);
		
		List<GradeHistory> gradeHistoryList = userService.getGradeHistory(student.getPid());
		//총 이수학점 정보
		for(GradeHistory vo : gradeHistoryList)	{
			totalCredit += vo.getCredit();
		}
		
		//총 이수학점 데이터 저장.	 
		student.setTotalCredit(totalCredit);
		userService.saveTotalCredit(student);
		
		System.out.println(gHis);
		System.out.println("update result: "+result);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return test;
	}	
	
	
	@RequestMapping(value = "/holyHistoryRemoveInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String holyHistoryRemoveInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("holyHistoryRemoveInfo... 학적 정보 삭제: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		HolyHistory hHis = new HolyHistory(Integer.parseInt(items[1]), Integer.parseInt(items[0]));
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			result = userService.removeHolyHistory(hHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			result = userService.removeHolyHistory(hHis);
		}
		
		int totalHolyWarning = 0;
		Student student = userService.getStudent(studentPid);
		
		List<HolyHistory> holyHistoryList = userService.getHolyHistory(student.getPid());
		
		//총 신앙 정보
		for(HolyHistory vo : holyHistoryList)	{
			totalHolyWarning += vo.getWarnings();
		}
		
		//총 이수학점 데이터 저장.	 
		student.setTotalHolyWarning(totalHolyWarning);
		userService.saveTotalHolyWarning(student);
		
		System.out.println(hHis);
		System.out.println("update result: "+result);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return test;
	}		
	
	
	@RequestMapping(value = "/holyHistoryInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String holyHistoryInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("holyHistoryInfo... 학적 정보 수정: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		HolyHistory hHis = new HolyHistory(Integer.parseInt(items[5]), items[1], items[2],
				items[3],Integer.parseInt(items[4]),Integer.parseInt(items[0]));

		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			hHis.setModifiedBy(studentPid);
			result = userService.updateHolyHistory(hHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			hHis.setModifiedBy((Integer) session.getAttribute("pid"));
			result = userService.updateHolyHistory(hHis);
		}
		
		Student student = userService.getStudent(studentPid);
		int warning = Integer.parseInt(items[4]);
		student.setTotalHolyWarning(student.getTotalHolyWarning() + warning);
		userService.saveTotalHolyWarning(student);
		
		System.out.println(hHis);
		
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
		}
		
	    return hHis.getTid()+"";
	}	
	
	
	
	@RequestMapping(value = "/consultHistoryRemoveInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String consultHistoryRemoveInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("consultHistoryRemoveInfo... 학적 정보 삭제: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		ConsultHistory cHis = new ConsultHistory(Integer.parseInt(items[1]), Integer.parseInt(items[0]));
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			result = userService.removeConsultHistory(cHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			result = userService.removeConsultHistory(cHis);
		}
		
		System.out.println(cHis);
		System.out.println("update result: "+result);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return test;
	}	
	
	
	@RequestMapping(value = "/consultHistoryInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String consultHistoryInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("consultHistoryInfo... 학적 정보 수정: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		
		ConsultHistory cHis = new ConsultHistory(Integer.parseInt(items[5]), items[1], items[2],
				items[3],items[4],Integer.parseInt(items[0]));

		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			cHis.setModifiedBy(studentPid);
			result = userService.updateConsultHistory(cHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			cHis.setModifiedBy((Integer) session.getAttribute("pid"));
			result = userService.updateConsultHistory(cHis);
		}

		System.out.println(cHis);
		
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
		}
		
	    return cHis.getTid()+"";
	}	
	
	
	
	@RequestMapping(value = "/absenceHistoryRemoveInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String absenceHistoryRemoveInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("absenceHistoryRemoveInfo... 학적 정보 삭제: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		
		AbsenceHistory aHis = new AbsenceHistory(Integer.parseInt(items[1]), Integer.parseInt(items[0]));
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			result = userService.removeAbsenceHistory(aHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			result = userService.removeAbsenceHistory(aHis);
		}
		
		System.out.println(aHis);
		System.out.println("update result: "+result);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return test;
	}	
	
	
	
	@RequestMapping(value = "/absenceHistoryInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String absenceHistoryInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("absenceHistoryInfo... 학적 정보 수정: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		
		AbsenceHistory aHis = new AbsenceHistory(Integer.parseInt(items[5]), items[1], items[2],
				items[3],items[4],Integer.parseInt(items[0]));

		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			aHis.setModifiedBy(studentPid);
			result = userService.updateAbsenceHistory(aHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			aHis.setModifiedBy((Integer) session.getAttribute("pid"));
			result = userService.updateAbsenceHistory(aHis);
		}

		System.out.println(aHis);
		
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
		}
		
	    return aHis.getTid()+"";
	}	
	
	
	
	
	@RequestMapping(value = "/grantHistoryRemoveInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String grantHistoryRemoveInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("grantHistoryRemoveInfo... 학적 정보 삭제: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		
		GrantHistory gHis = new GrantHistory(Integer.parseInt(items[1]), Integer.parseInt(items[0]));
		
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			result = userService.removeGrantHistory(gHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			result = userService.removeGrantHistory(gHis);
		}
		
		System.out.println(gHis);
		System.out.println("update result: "+result);
		
		String test = "";
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
			test = "1";
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
			test = "0";
		}
		
	    return test;
	}	
	
	
	
	@RequestMapping(value = "/grantHistoryInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String grantHistoryInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("grantHistoryInfo... 학적 정보 수정: ");
		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
		System.out.println("학생 정보 변경사항: " + items.length);
		
		GrantHistory gHis = new GrantHistory(Integer.parseInt(items[6]), items[1], items[2],
				items[3],items[4],items[5],Integer.parseInt(items[0]));

		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			
			gHis.setModifiedBy(studentPid);
			result = userService.updateGrantHistory(gHis);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			
			gHis.setModifiedBy((Integer) session.getAttribute("pid"));
			result = userService.updateGrantHistory(gHis);
		}

		System.out.println(gHis);
		
		if(result == 1)	{
			System.out.println("수정 사항이 올바르게 변경되었습니다.");
		}
		else	{
			System.out.println("변경 사항이 올바르지 않습니다.");
		}
		
	    return gHis.getTid()+"";
	}	
	
	
	@Resource(name="uploadPath")
	String uploadPath;
	private static final String UPLOAD_DIRECTORY ="/resources/img/";  
	
	@RequestMapping(value = "/uploadform", method = RequestMethod.POST)
	public String uploadForm(HttpSession session, HttpServletRequest request, MultipartFile file) throws IOException {
		System.out.println("Enter....uploadForm");
		System.out.println("파일 이름: " + file.getOriginalFilename());
		System.out.println("파일 크기: " + file.getSize());
		System.out.println("컨텐츠 타입: " + file.getContentType());
		
		String uploadPath = "/Users/dean/Documents/etc/project/HJPA/develop/uploadImg/";
		System.out.println("경로: " + uploadPath);
		
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
		}
		
		UUID uuid = UUID.randomUUID();
		String savedName = "pid"+studentPid + "_"+uuid+"_"+file.getOriginalFilename();
		
		File target = new File(uploadPath,savedName);
		System.out.println("File...: "+target.getAbsolutePath());
		
		//임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사 
//		FileCopyUtils.copy(file.getBytes(),target);
		file.transferTo(target);
		
		//change profile imge
		
		
		userService.updateProfileImg(studentPid,savedName);
		
		
		return "redirect:/studentInfo";
	}
	

}