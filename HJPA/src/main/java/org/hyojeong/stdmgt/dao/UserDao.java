package org.hyojeong.stdmgt.dao;

import java.util.List;

import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.User;

public interface UserDao {

	public int register(User user);

	public User validateUser(Login login);

	public Student getStudent(int pid);

	public List<Student> getStudentAll();

	public int idCheck(String id);
}
