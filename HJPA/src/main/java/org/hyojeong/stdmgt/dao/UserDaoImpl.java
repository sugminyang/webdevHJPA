package org.hyojeong.stdmgt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hyojeong.stdmgt.model.AbsenceHistory;
import org.hyojeong.stdmgt.model.ActiveHistory;
import org.hyojeong.stdmgt.model.AwardsHistory;
import org.hyojeong.stdmgt.model.ConsultHistory;
import org.hyojeong.stdmgt.model.GradeHistory;
import org.hyojeong.stdmgt.model.GrantHistory;
import org.hyojeong.stdmgt.model.HolyHistory;
import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.UpdateHisory;
import org.hyojeong.stdmgt.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	@Inject
	private SqlSession sqlSession;

	private static final String Namespace = "org.hyojeong.stdmgt.mappers.resultMapper";
	public static final String STD_AUTHORITY = "0";
	
	public int register(User user) {
		user.setAuthority(STD_AUTHORITY);
		return sqlSession.insert(Namespace+".register",user);
	}

	public User validateUser(Login login) {
		Map<String,Login> map = new HashMap<String,Login>();
		map.put("login",login);
		System.out.println(login);
		return sqlSession.selectOne(Namespace+".validateUser",login);
	}
	
	@Override
	public User getUserId(int studentPid) {
		return sqlSession.selectOne(Namespace+".getUserId",studentPid);
	}

	
	
	@Override
	public Student getStudent(int pid) {
		return sqlSession.selectOne(Namespace+".getStudent",pid);
	}

	@Override
	public List<Student> getStudentAll() {
//		System.out.println("DAO - Enter a getStudentAll()");
		return sqlSession.selectList(Namespace+".getStudentAll");
	}

	@Override
	public int idCheck(String id) {
		User user = new User();
		user.setId(id);
		return sqlSession.selectOne(Namespace+".idCheck",user);
	}

	@Override
	public int addStudent(Student student) {
		
		return sqlSession.insert(Namespace+".addStudent",student);
	}

	@Override
	public int insertUpdateStudentInfoHistory(UpdateHisory uhistory) {
		return sqlSession.insert(Namespace+".insertUpdateStudentInfoHistory",uhistory);
	}

	@Override
	public int updateStudentInfo(Student originStudent) {
		return sqlSession.insert(Namespace+".updateStudentInfo",originStudent);
	}

	@Override
	public List<GradeHistory> getGradeHistory(int pid) {
		return sqlSession.selectList(Namespace+".getGradeHistory",pid);
	}

	@Override
	public List<HolyHistory> getHolyHistory(int pid) {
		return sqlSession.selectList(Namespace+".getHolyHistory",pid);
	}

	@Override
	public List<ActiveHistory> getActiveHistory(int pid) {
		return sqlSession.selectList(Namespace+".getActiveHistory",pid);
	}

	@Override
	public List<AwardsHistory> getAwardsHistory(int pid) {
		return sqlSession.selectList(Namespace+".getAwardsHistory",pid);
	}

	@Override
	public List<ConsultHistory> getConsultHistory(int pid) {
		return sqlSession.selectList(Namespace+".getConsultHistory",pid);
	}

	@Override
	public int updateGradeHistory(GradeHistory gHis) {
		return sqlSession.update(Namespace+".updateGradeHistory",gHis);
	}

	@Override
	public int insertNewGradeHistory(GradeHistory gHis) {
		return sqlSession.update(Namespace+".insertNewGradeHistory",gHis);
	}

	@Override
	public List<AbsenceHistory> getAbsenceHistory(int pid) {
		return sqlSession.selectList(Namespace+".getAbsenceHistory",pid);
	}

	@Override
	public List<GrantHistory> getGrantHistory(int pid) {
		return sqlSession.selectList(Namespace+".getGrantHistory",pid);
	}

	@Override
	public int updateProfileImg(int studentPid, String profilePath) {
		Student temp = new Student();
		temp.setProfile(profilePath);
		temp.setPid(studentPid);
		
		return sqlSession.update(Namespace+".updateProfileImg",temp);
	}

	@Override
	public int insertNewActiveHistory(ActiveHistory aHis) {
		return sqlSession.update(Namespace+".insertNewActiveHistory",aHis);
	}

	@Override
	public int updateActiveHistory(ActiveHistory aHis) {
		return sqlSession.update(Namespace+".updateActiveHistory",aHis);
	}

	@Override
	public int insertNewAwardsHistory(AwardsHistory awHis) {
		return sqlSession.update(Namespace+".insertNewAwardsHistory",awHis);
	}

	@Override
	public int updateAwardsHistory(AwardsHistory awHis) {
		return sqlSession.update(Namespace+".updateAwardsHistory",awHis);
	}

	@Override
	public int removeAwardsHistory(AwardsHistory awHis) {
		return sqlSession.delete(Namespace+".removeAwardsHistory",awHis);
	}

	@Override
	public int removeActiveHistory(ActiveHistory aHis) {
		return sqlSession.delete(Namespace+".removeActiveHistory",aHis);
	}

	@Override
	public int removeGradeHistory(GradeHistory gHis) {
		return sqlSession.delete(Namespace+".removeGradeHistory",gHis);
	}

	@Override
	public int removeHolyHistory(HolyHistory hHis) {
		return sqlSession.delete(Namespace+".removeHolyHistory",hHis);
	}

	@Override
	public int insertHolyHistory(HolyHistory hHis) {
		return sqlSession.update(Namespace+".insertHolyHistory",hHis);
	}

	@Override
	public int updateHolyHistory(HolyHistory hHis) {
		return sqlSession.update(Namespace+".updateHolyHistory",hHis);
	}

	@Override
	public int removeConsultHistory(ConsultHistory cHis) {
		return sqlSession.delete(Namespace+".removeConsultHistory",cHis);
	}

	@Override
	public int insertConsultHistory(ConsultHistory cHis) {
		return sqlSession.update(Namespace+".insertConsultHistory",cHis);
	}

	@Override
	public int updateConsultHistory(ConsultHistory cHis) {
		return sqlSession.update(Namespace+".updateConsultHistory",cHis);
	}

	@Override
	public int removeAbsenceHistory(AbsenceHistory aHis) {
		return sqlSession.delete(Namespace+".removeAbsenceHistory",aHis);
	}

	@Override
	public int insertAbsenceHistory(AbsenceHistory aHis) {
		return sqlSession.update(Namespace+".insertAbsenceHistory",aHis);
	}

	@Override
	public int updateAbsenceHistory(AbsenceHistory aHis) {
		return sqlSession.update(Namespace+".updateAbsenceHistory",aHis);
	}

	@Override
	public int removeGrantHistory(GrantHistory gHis) {
		return sqlSession.delete(Namespace+".removeGrantHistory",gHis);
	}

	@Override
	public int insertGrantHistory(GrantHistory gHis) {
		return sqlSession.update(Namespace+".insertGrantHistory",gHis);
	}

	@Override
	public int updateGrantHistory(GrantHistory gHis) {
		return sqlSession.update(Namespace+".updateGrantHistory",gHis);
	}

	@Override
	public List<Student> searchStudents(String category, String value) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("category",category);
		map.put("value",value);
		System.out.println(map);
		return sqlSession.selectList(Namespace+".searchStudents",map);
	}

	@Override
	public void saveTotalCredit(Student student) {
		sqlSession.update(Namespace+".saveTotalCredit",student);
	}

	@Override
	public void saveTotalGradeWarning(Student student) {
		sqlSession.update(Namespace+".saveTotalGradeWarning",student);		
	}

	@Override
	public void saveTotalHolyWarning(Student student) {
		sqlSession.update(Namespace+".saveTotalHolyWarning",student);
	}

	@Override
	public int updateAllItemsStudentInfo(Student originStudent) {
		return sqlSession.update(Namespace+".updateAllItemsStudentInfo",originStudent);
	}

	@Override
	public List<User> getAdminUserAll() {
		return sqlSession.selectList(Namespace+".getAdminUserAll");
	}

	@Override
	public int adminUserRemove(User user) {
		return sqlSession.delete(Namespace+".adminUserRemove",user);
	}

	@Override
	public int updateAdminUser(User user) {
		return sqlSession.delete(Namespace+".updateAdminUser",user);
	}

	@Override
	public int insertAdminUser(User user) {
		return sqlSession.insert(Namespace+".insertAdminUser",user);
	}

	@Override
	public List<Student> getPidWithSnoUniv(String sno_univ) {
		return sqlSession.selectList(Namespace+".getPidWithSnoUniv",sno_univ);
	}

	@Override
	public int changepassword(Login vo) {
		return sqlSession.update(Namespace+".changepassword",vo);
	}

	



}