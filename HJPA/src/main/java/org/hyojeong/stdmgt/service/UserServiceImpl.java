package org.hyojeong.stdmgt.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.dao.UserDao;
import org.hyojeong.stdmgt.model.AbsenceHistory;
import org.hyojeong.stdmgt.model.ActiveHistory;
import org.hyojeong.stdmgt.model.AwardsHistory;
import org.hyojeong.stdmgt.model.ConsultHistory;
import org.hyojeong.stdmgt.model.GradeHistory;
import org.hyojeong.stdmgt.model.GrantHistory;
import org.hyojeong.stdmgt.model.HolyHistory;
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
		
		updatedList = compareInfo(updateStudentInfo, originStudent);
		if(updatedList == null) return 0;
		
		//수정된 정보 내역 저장
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		UpdateHisory uhistory = new UpdateHisory(updateStudentInfo.getPid(),updatedList,nowDate);
		int result = userDao.insertUpdateStudentInfoHistory(uhistory);
//		System.out.println("result(insertUpdateStudentInfoHistory): "+result);
		
		//학생 정보 수정!! update
		result = userDao.updateStudentInfo(originStudent);
		
		return result;
	}

	private String compareInfo(Student updateStudentInfo, Student originStudent) {
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
////		사진이 변경된 경우 
//		if(!updateStudentInfo.getProfile().equalsIgnoreCase(originStudent.getProfile()))	{
//			updatedList += "사진,";
//			originStudent.setProfile(updateStudentInfo.getProfile());
//		}
//		장학 선발 연도가 변경된 경우 
		if(!updateStudentInfo.getYearOfscholarship().equalsIgnoreCase(originStudent.getYearOfscholarship()))	{
			updatedList += "장학선발 연도,";
			originStudent.setYearOfscholarship(updateStudentInfo.getYearOfscholarship());
		}		
		
//		생일
		if(!updateStudentInfo.getBirth().equalsIgnoreCase(originStudent.getBirth()))	{
			updatedList += "생일,";
			originStudent.setBirth(updateStudentInfo.getBirth());
		}	
//		국적
		if(!updateStudentInfo.getNationality().equalsIgnoreCase(originStudent.getNationality()))	{
			updatedList += "국적,";
			originStudent.setNationality(updateStudentInfo.getNationality());
		}
//		한글 영어 이름 변경
		if(!updateStudentInfo.getName_eng().equalsIgnoreCase(originStudent.getName_eng()))	{
			updatedList += "영어 이름,";
			originStudent.setName_eng(updateStudentInfo.getName_eng());
		}
		if(!updateStudentInfo.getName_kor().equalsIgnoreCase(originStudent.getName_kor()))	{
			updatedList += "한글 이름,";
			originStudent.setName_kor(updateStudentInfo.getName_kor());
		}
//		장학 상태 
		if(!updateStudentInfo.getAwardStatus().equalsIgnoreCase(originStudent.getAwardStatus()))	{
			updatedList += "장학 상태,";
			originStudent.setAwardStatus(updateStudentInfo.getAwardStatus());
		}
		
//		성별
		if(!updateStudentInfo.getSex().equalsIgnoreCase(originStudent.getSex()))	{
			updatedList += "성별,";
			originStudent.setSex(updateStudentInfo.getSex());
		}
		
//		대륙
		if(!updateStudentInfo.getContinent().equalsIgnoreCase(originStudent.getContinent()))	{
			updatedList += "대륙,";
			originStudent.setContinent(updateStudentInfo.getContinent());
		}
		
		//마지막 ','제거
		if(updatedList.length() != 0)	{
			updatedList = updatedList.substring(0,updatedList.length()-1);
		}
		else {
			return null; //수정된 사항이 없음
		}

		System.out.println("수정사항: "+ updatedList);

		return updatedList;
	}

	/**
	 * 
	 * admin or teacher는 모든 property를 수정할 수 있음 
	 * */	
	@Override
	public int updateAllItemsStudentInfo(Student updateStudentInfo, int modifiedUser) {
		//어떤 부분이 달라졌는지 check
		//기존 studentInfo를 찾아야 함..
		int pid = updateStudentInfo.getPid();
		Student originStudent = getStudent(pid);
		String updatedList = "";

		updatedList = compareInfo(updateStudentInfo, originStudent);
		if(updatedList == null) return 0;

		//수정된 정보 내역 저장
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		UpdateHisory uhistory = new UpdateHisory(updateStudentInfo.getPid(),updatedList,nowDate);
		int result = userDao.insertUpdateStudentInfoHistory(uhistory);
		//				System.out.println("result(insertUpdateStudentInfoHistory): "+result);

		//학생 정보 수정!! update
		result = userDao.updateAllItemsStudentInfo(originStudent);

		return result;
	}

	@Override
	public List<GradeHistory> getGradeHistory(int pid) {
		return userDao.getGradeHistory(pid);
	}

	@Override
	public List<HolyHistory> getHolyHistory(int pid) {
		return userDao.getHolyHistory(pid);
	}

	@Override
	public List<ActiveHistory> getActiveHistory(int pid) {
		return userDao.getActiveHistory(pid);
	}

	@Override
	public List<AwardsHistory> getAwardsHistory(int pid) {
		return userDao.getAwardsHistory(pid);
	}

	@Override
	public List<ConsultHistory> getConsultHistory(int pid) {
		return userDao.getConsultHistory(pid);
	}

	@Override
	public int updateGradeHistory(GradeHistory gHis) {
		if(gHis.getTid() == 0)	{	//new history
			System.out.println("insert new grade history");
			return userDao.insertNewGradeHistory(gHis);
		}
		else	{
			System.out.println("update grade history");
			return userDao.updateGradeHistory(gHis);
		}
	}

	@Override
	public List<AbsenceHistory> getAbsenceHistory(int pid) {
		return userDao.getAbsenceHistory(pid);
	}

	@Override
	public List<GrantHistory> getGrantHistory(int pid) {
		return userDao.getGrantHistory(pid);
	}

	@Override
	public int updateProfileImg(int studentPid, String profilePath) {
		return userDao.updateProfileImg(studentPid,profilePath);
	}

	@Override
	public int updateActiveHistory(ActiveHistory aHis) {
		if(aHis.getTid() == 0)	{	//new history
			System.out.println("insert new active history");
			return userDao.insertNewActiveHistory(aHis);
		}
		else	{
			System.out.println("update active history");
			return userDao.updateActiveHistory(aHis);
		}
	}

	@Override
	public int updateAwardsHistory(AwardsHistory awHis) {
		if(awHis.getTid() == 0)	{	//new history
			System.out.println("insert new Awards history");
			return userDao.insertNewAwardsHistory(awHis);
		}
		else	{
			System.out.println("update Awards history");
			return userDao.updateAwardsHistory(awHis);
		}
	}

	@Override
	public int removeAwardsHistory(AwardsHistory awHis) {
		return userDao.removeAwardsHistory(awHis);
	}

	@Override
	public int removeActiveHistory(ActiveHistory aHis) {
		return userDao.removeActiveHistory(aHis);
	}

	@Override
	public int removeGradeHistory(GradeHistory gHis) {
		return userDao.removeGradeHistory(gHis);
	}

	@Override
	public int removeHolyHistory(HolyHistory hHis) {
		return userDao.removeHolyHistory(hHis);
	}

	@Override
	public int updateHolyHistory(HolyHistory hHis) {
		if(hHis.getTid() == 0)	{	//new history
			System.out.println("insert new Awards history");
			return userDao.insertHolyHistory(hHis);
		}
		else	{
			System.out.println("update Awards history");
			return userDao.updateHolyHistory(hHis);
		}
	}

	@Override
	public int removeConsultHistory(ConsultHistory cHis) {
		return userDao.removeConsultHistory(cHis);
	}

	@Override
	public int updateConsultHistory(ConsultHistory cHis) {
		if(cHis.getTid() == 0)	{	//new history
			System.out.println("insert new Consult history");
			return userDao.insertConsultHistory(cHis);
		}
		else	{
			System.out.println("update Consult history");
			return userDao.updateConsultHistory(cHis);
		}
	}

	@Override
	public int removeAbsenceHistory(AbsenceHistory aHis) {
		return userDao.removeAbsenceHistory(aHis);
	}

	@Override
	public int updateAbsenceHistory(AbsenceHistory aHis) {
		if(aHis.getTid() == 0)	{	//new history
			System.out.println("insert new Absence history");
			return userDao.insertAbsenceHistory(aHis);
		}
		else	{
			System.out.println("update Absence history");
			return userDao.updateAbsenceHistory(aHis);
		}
	}

	@Override
	public int removeGrantHistory(GrantHistory gHis) {
		return userDao.removeGrantHistory(gHis);
	}

	@Override
	public int updateGrantHistory(GrantHistory gHis) {
		if(gHis.getTid() == 0)	{	//new history
			System.out.println("insert new Grant history");
			return userDao.insertGrantHistory(gHis);
		}
		else	{
			System.out.println("update Grant history");
			return userDao.updateGrantHistory(gHis);
		}
	}

	@Override
	public List<Student> searchStudents(String category, String value) {
		
		return userDao.searchStudents(category,value);
	}

	@Override
	public void saveTotalCredit(Student student) {
		userDao.saveTotalCredit(student);
	}

	@Override
	public void saveTotalGradeWarning(Student student) {
		userDao.saveTotalGradeWarning(student);
	}

	@Override
	public void saveTotalHolyWarning(Student student) {
		userDao.saveTotalHolyWarning(student);		
	}

}
