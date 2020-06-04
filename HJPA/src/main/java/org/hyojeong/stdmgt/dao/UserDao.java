package org.hyojeong.stdmgt.dao;

import java.util.List;

import org.hyojeong.stdmgt.model.ActiveHistory;
import org.hyojeong.stdmgt.model.AwardsHistory;
import org.hyojeong.stdmgt.model.ConsultHistory;
import org.hyojeong.stdmgt.model.GradeHistory;
import org.hyojeong.stdmgt.model.HolyHistory;
import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Student;
import org.hyojeong.stdmgt.model.UpdateHisory;
import org.hyojeong.stdmgt.model.User;

public interface UserDao {

	public int register(User user);

	public User validateUser(Login login);

	public Student getStudent(int pid);

	public List<Student> getStudentAll();

	public int idCheck(String id);

	public int addStudent(Student student);

	public int insertUpdateStudentInfoHistory(UpdateHisory uhistory);

	public int updateStudentInfo(Student originStudent);

	public List<GradeHistory> getGradeHistory(int pid);

	public List<HolyHistory> getHolyHistory(int pid);

	public List<ActiveHistory> getActiveHistory(int pid);

	public List<AwardsHistory> getAwardsHistory(int pid);

	public List<ConsultHistory> getConsultHistory(int pid);
}
