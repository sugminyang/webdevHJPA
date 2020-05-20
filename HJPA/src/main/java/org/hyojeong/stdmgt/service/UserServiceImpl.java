package org.hyojeong.stdmgt.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.dao.UserDao;
import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.StudentDomestic;
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


}
