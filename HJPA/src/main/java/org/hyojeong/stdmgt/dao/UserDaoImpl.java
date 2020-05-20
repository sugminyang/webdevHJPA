package org.hyojeong.stdmgt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
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

}