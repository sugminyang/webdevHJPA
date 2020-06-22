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
	public Student getStudent(int pid) {
		return sqlSession.selectOne(Namespace+".getStudent",pid);
	}

	@Override
	public List<Student> getStudentAll() {
		System.out.println("DAO - Enter a getStudentAll()");
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

}