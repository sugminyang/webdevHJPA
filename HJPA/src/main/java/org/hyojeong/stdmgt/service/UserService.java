package org.hyojeong.stdmgt.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.StudentDomestic;
import org.hyojeong.stdmgt.model.User;


public interface UserService {

	public int register(User user);

	public User validateUser(Login login, HttpSession session);

	public Student getStudent(int pid);

	public List<Student> getStudentAll();

	public List<StudentDomestic> getStudentDomesticAll();

	public int idCheck(String id);
}
