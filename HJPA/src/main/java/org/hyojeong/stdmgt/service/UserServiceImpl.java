package org.hyojeong.stdmgt.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.dao.UserDao;
import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.StudentDomestic;
import org.hyojeong.stdmgt.model.UpdateHisory;
import org.hyojeong.stdmgt.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	public UserDao userDao;
	
	public int register(User user) {
		return userDao.register(user);  
	}

	@Override
	public Student getStudent(int pid) {
		return userDao.getStudent(pid);		
	}

	@Override
	public User validateUser(Login login, HttpSession session) {
		User user = userDao.validateUser(login);
		System.out.println(user);
		if(user != null) {
			session.setAttribute("id",user.getId());
			session.setAttribute("pid",user.getPid());
			session.setAttribute("auth",user.getAuthority());
			System.out.println("session: "+session.toString());
		}
		
		return user;
	}

	@Override
	public List<Student> getStudentAll() {
		System.out.println("enter getStudentAll()");
		ArrayList<Student> voList = new ArrayList<Student>();
		
		for(Student vo : userDao.getStudentAll()) {
			System.out.println(vo);
			voList.add(vo);
		}
		
		return voList; 
	}

	@Override
	public List<StudentDomestic> getStudentDomesticAll() {
		List<StudentDomestic> list = new ArrayList<StudentDomestic>();
		list.add(new StudentDomestic("18-0001","2018 원모장학생","대학활동","홍길동","22"));
		return list;
	}

	@Override
	public int idCheck(String id) {
		int isDuplicated = userDao.idCheck(id);
		return isDuplicated;
	}

	@Override
	public void addStudent(Student student) {
		userDao.addStudent(student);
	}

	
	/**
	 * 
	 * student가 수정할 수 있는 contents만 비교 
	 * */
	@Override
	public int updateStudentInfo(Student updateStudentInfo) {
//		System.out.println("enter.. updateStudentInfo(): student");
		
		//어떤 부분이 달라졌는지 check
		//기존 studentInfo를 찾아야 함..
		int pid = updateStudentInfo.getPid();
		Student originStudent = getStudent(pid);
		String updatedList = "";
		
//		대학교 학번이 변경된 경우
		if(!updateStudentInfo.getSno_univ().equalsIgnoreCase(originStudent.getSno_univ()))	{
			updatedList += "대학교 학번,";
			originStudent.setSno_univ(updateStudentInfo.getSno_univ());
		}
//		한교원 학번이 변경된 경우
		if(!updateStudentInfo.getSno_acad().equalsIgnoreCase(originStudent.getSno_acad()))	{
			updatedList += "한교원 학번,";
			originStudent.setSno_acad(updateStudentInfo.getSno_acad());
		}
//		학년 정보가 변경된 경우
		if(updateStudentInfo.getGrade() != originStudent.getGrade())	{
			updatedList += "학년,";
			originStudent.setGrade(updateStudentInfo.getGrade());
		}
//		학적 상태가 변경된 경우
		if(!updateStudentInfo.getStatus().equalsIgnoreCase(originStudent.getStatus()))	{
			updatedList += "학적 상태,";
			originStudent.setStatus(updateStudentInfo.getStatus());
		}
//		장학 상태가 변경된 경우		
		if(!updateStudentInfo.getAwardStatus().equalsIgnoreCase(originStudent.getAwardStatus()))	{
			updatedList += "장학 상태,";
			originStudent.setAwardStatus(updateStudentInfo.getAwardStatus());
		}		
//		이메일 정보가 변경된 경우 
		if(!updateStudentInfo.getEmail().equalsIgnoreCase(originStudent.getEmail()))	{
			updatedList += "이메일,";
			originStudent.setEmail(updateStudentInfo.getEmail());
		}	
//		휴대전화 정보가 변경된 경우
		if(!updateStudentInfo.getPhone().equalsIgnoreCase(originStudent.getPhone()))	{
			updatedList += "휴대폰 번호,";
			originStudent.setPhone(updateStudentInfo.getEmail());
		}
//		단과대 정보가 변경된 경우
		if(!updateStudentInfo.getCollege().equalsIgnoreCase(originStudent.getCollege()))	{
			updatedList += "단과대,";
			originStudent.setCollege(updateStudentInfo.getCollege());
		}			
//		학과 정보가 변경된 경우
		if(!updateStudentInfo.getDept().equalsIgnoreCase(originStudent.getDept()))	{
			updatedList += "학과,";
			originStudent.setDept(updateStudentInfo.getDept());
		}
//		sns type이 변경된 경우
		if(!updateStudentInfo.getSnsType().equalsIgnoreCase(originStudent.getSnsType()))	{
			updatedList += "sns 유형,";
			originStudent.setSnsType(updateStudentInfo.getSnsType());
		}
//		sns id가 변경된 경우
		if(!updateStudentInfo.getSns_id().equalsIgnoreCase(originStudent.getSns_id()))	{
			updatedList += "sns id,";
			originStudent.setSns_id(updateStudentInfo.getSns_id());
		}
//		주소가 변경된 경우
		if(!updateStudentInfo.getAddress().equalsIgnoreCase(originStudent.getAddress()))	{
			updatedList += "주소,";
			originStudent.setAddress(updateStudentInfo.getAddress());
		}
//		사진이 변경된 경우 
		if(!updateStudentInfo.getProfile().equalsIgnoreCase(originStudent.getProfile()))	{
			updatedList += "사진,";
			originStudent.setProfile(updateStudentInfo.getProfile());
		}
//		System.out.println("변경 사항: " + updatedList);
		
		//마지막 ','제거
		if(updatedList.length() != 0)	{
			updatedList = updatedList.substring(0,updatedList.length()-1);
		}
		else {
			return 0; //수정된 사항이 없음
		}
		
		//수정된 정보 내역 저장
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		UpdateHisory uhistory = new UpdateHisory(updateStudentInfo.getPid(),updatedList,nowDate);
		int result = userDao.insertUpdateStudentInfoHistory(uhistory);
//		System.out.println("result(insertUpdateStudentInfoHistory): "+result);
		
		
		//학생 정보 수정!! update
//		System.out.println("update student obj: " + originStudent);
		result = userDao.updateStudentInfo(originStudent);
//		System.out.println("result(updateStudentInfo): "+result);
		
		return result;
	}

	
	/**
	 * 
	 * admin or teacher는 모든 property를 수정할 수 있음 
	 * */	
	@Override
	public int updateAllItemsStudentInfo(Student updateStudentInfo) {
		System.out.println("enter.. updateAllItemsStudentInfo()");
		return 0;
	}
}
