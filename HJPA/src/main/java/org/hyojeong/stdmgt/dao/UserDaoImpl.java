package org.hyojeong.stdmgt.dao;

import java.util.HashMap;
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
	
	public int register(Student student) {
		return 1;
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

}