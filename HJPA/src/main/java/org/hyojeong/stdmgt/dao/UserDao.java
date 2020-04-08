package org.hyojeong.stdmgt.dao;

import java.util.List;

import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.User;

public interface UserDao {

	public int register(Student student);

	public User validateUser(Login login);

	public Student getStudent(int pid);

	public List<Student> getStudentAll();
}
