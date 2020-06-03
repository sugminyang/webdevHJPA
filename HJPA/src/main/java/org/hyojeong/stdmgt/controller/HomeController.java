package org.hyojeong.stdmgt.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.StudentDomestic;
import org.hyojeong.stdmgt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		
		if(student != null)	{
			System.out.println(student);
			
			mav.addObject("student", student);
		}
		else	{
			mav = new ModelAndView("home");
		}
		 
		String semesterJson = "[{\"학기\":'2019-1', \"이수 학점\":19, '성적': 4.1, '경고':0},"
				+ "{\"학기\":'2019-2', \"이수 학점\":21, '성적': 3.8, '경고':1}]";
		mav.addObject("semesterInfo", semesterJson);
		
		String holyJson = "[{\"학기\":'2019-1', \"훈독회\":90, '예배': 30, '경고':1},"
				+ "{\"학기\":'2019-2', \"훈독회\":20, '예배': 40, '경고':2},"
				+ "{\"학기\":'2020-1', \"훈독회\":80, '예배': 100, '경고':1}]";
		mav.addObject("holyInfo", holyJson);

		String activeJson = "[{\"연도\":'2019', \"프로그램 내용\":'선학 캠프', \"비고\": ''},"
				+ "{\"연도\":'2019', \"프로그램 내용\":\"교내 영어캠프\",  \"비고\":'장학금 지원받음'},"
				+ "{\"연도\":'2020', \"프로그램 내용\":\"취업 박람회\", \"비고\":'인턴쉽 확정'}]";
		mav.addObject("activeInfo", activeJson);
		
		String awardsJson = "[{\"연도\":'2019', \"내용\":'대학생 경진대회','주최 기관':'서울시', \"비고\": '우수상'},"
				+ "{\"연도\":'2020', \"내용\":\"교내 마라톤\", '주최 기관':'대학교', \"비고\": '3등'}]";
		mav.addObject("awardsInfo", awardsJson);
		
		String consultJson = "[{\"일자\":'20190701', \"상담자\":'김경진','내용':'교우관계 상담', \"비고\": '-'},"
				+ "{\"일자\":'20200503', \"상담자\":\"양교수\", '내용':'취업 상담', \"비고\": '인턴쉽 연계 회사에서 스카웃제의'}]";
		mav.addObject("consultInfo", consultJson);
		return mav;
	}
	
	@RequestMapping(value = "/getStudentInfo", method = RequestMethod.POST)
	public String getStudentInfo(HttpSession session, @RequestParam("pid") int pid) {
		//관심학생 등록
		session.setAttribute("sid",pid);
		return "";
	}	
	
	@RequestMapping(value = "/updateStudentInfo", method = RequestMethod.POST)
	@ResponseBody // 클라이언트에게 전송할 응답 데이터를 JSON 객체로 변환
	public String updateStudentInfo(@RequestBody String filterJSON, HttpSession session) {
		System.out.println("updateStudentInfo... 학생 정보 수정..request: ");
//		System.out.println(filterJSON);
		
		String[] items = filterJSON.split("!@#");
//		System.out.println("학생 정보 변경사항: " + items.length);
		Student updateStdInfo = new Student(items[0], items[1], Integer.parseInt(items[2]),items[3], 
				items[4], items[5], items[6], items[7], items[8], items[9], items[10],
				items[11], items[12]);
		
//		System.out.println(updateStdInfo);
		//학생의 pid 설정
		String auth = (String) session.getAttribute("auth");
		int studentPid = -1;
		int result = -1;
		
		if(auth.equalsIgnoreCase("0"))	{	//student
			studentPid = (Integer) session.getAttribute("pid");
			updateStdInfo.setPid(studentPid);
			result = userService.updateStudentInfo(updateStdInfo);
		}
		else	{
			studentPid = (Integer) session.getAttribute("sid");
			updateStdInfo.setPid(studentPid);
			result = userService.updateAllItemsStudentInfo(updateStdInfo);
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
	
	
//	@Resource(name="uploadPath")
//	String uploadPath;
//	private static final String UPLOAD_DIRECTORY ="/resources/img/";  
	
	@RequestMapping(value = "/uploadform", method = RequestMethod.POST)
	public ModelAndView uploadForm( HttpServletRequest request, MultipartFile file) throws IOException {
		System.out.println("Enter....uploadForm");
//		System.out.println("파일 이름: " + file.getOriginalFilename());
//		System.out.println("파일 크기: " + file.getSize());
//		System.out.println("컨텐츠 타입: " + file.getContentType());
//		
//		ServletContext context = request.getSession().getServletContext();  
//	    String uploadPath = context.getRealPath(UPLOAD_DIRECTORY);  
////		uploadPath = request.getSession().getServletContext().getRealPath("/resources/uploadImg/");
//		System.out.println("경로: " + uploadPath);
//		
//		String savedName = file.getOriginalFilename();
//		
//		File target = new File(uploadPath,savedName);
//		System.out.println("File...: "+target.getAbsolutePath());
//		
//		//임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사 
//		FileCopyUtils.copy(file.getBytes(),target);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
//		mav.addObject("fileName",target.getAbsolutePath());
		
		return mav;
	}
	
}