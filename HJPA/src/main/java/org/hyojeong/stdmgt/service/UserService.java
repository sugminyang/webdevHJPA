package org.hyojeong.stdmgt.service;

import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.User;


public interface UserService {

	public int register(Student student);

	public User validateUser(Login login, HttpSession session);

	public Student getStudent(int pid);
}