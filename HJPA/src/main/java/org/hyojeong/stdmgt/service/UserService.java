package org.hyojeong.stdmgt.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.model.ActiveHistory;
import org.hyojeong.stdmgt.model.AwardsHistory;
import org.hyojeong.stdmgt.model.ConsultHistory;
import org.hyojeong.stdmgt.model.GradeHistory;
import org.hyojeong.stdmgt.model.HolyHistory;
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

	public void addStudent(Student student);

	public int updateStudentInfo(Student updateStudentInfo);

	public int updateAllItemsStudentInfo(Student updateStudentInfo);

	public List<GradeHistory> getGradeHistory(int pid);

	public List<HolyHistory> getHolyHistory(int pid);

	public List<ActiveHistory> getActiveHistory(int pid);

	public List<AwardsHistory> getAwardsHistory(int pid);

	public List<ConsultHistory> getConsultHistory(int pid);

	public int updateGradeHistory(GradeHistory gHis);
}
