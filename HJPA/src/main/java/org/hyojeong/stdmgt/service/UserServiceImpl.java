package org.hyojeong.stdmgt.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.dao.UserDao;
import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	public UserDao userDao;

	public int register(Student student) {
		return userDao.register(student);
	}

	@Override
	public Student getStudent(int pid) {
		return userDao.getStudent(pid);		
	}

	@Override
	public User validateUser(Login login, HttpSession session) {
		User user = userDao.validateUser(login);
		if(user != null) {
			session.setAttribute("username",user.getUsername());
			session.setAttribute("pid",user.getPid());
			session.setAttribute("auth",user.getAuthority());
			System.out.println(session.toString());
		}
		
		return user;
	}


}
