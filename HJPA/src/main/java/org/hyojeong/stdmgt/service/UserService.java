package org.hyojeong.stdmgt.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hyojeong.stdmgt.model.AbsenceHistory;
import org.hyojeong.stdmgt.model.ActiveHistory;
import org.hyojeong.stdmgt.model.AwardsHistory;
import org.hyojeong.stdmgt.model.ConsultHistory;
import org.hyojeong.stdmgt.model.GradeHistory;
import org.hyojeong.stdmgt.model.GrantHistory;
import org.hyojeong.stdmgt.model.HolyHistory;
import org.hyojeong.stdmgt.model.Login;
import org.hyojeong.stdmgt.model.Notice;
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

	public int updateAllItemsStudentInfo(Student updateStudentInfo, int modifiedUser);

	public List<GradeHistory> getGradeHistory(int pid);

	public List<HolyHistory> getHolyHistory(int pid);

	public List<ActiveHistory> getActiveHistory(int pid);

	public List<AwardsHistory> getAwardsHistory(int pid);

	public List<ConsultHistory> getConsultHistory(int pid);

	public int updateGradeHistory(GradeHistory gHis);

	public List<AbsenceHistory> getAbsenceHistory(int pid);

	public List<GrantHistory> getGrantHistory(int pid);

	public int updateProfileImg(int studentPid, String absolutePath);

	public int updateActiveHistory(ActiveHistory aHis);

	public int updateAwardsHistory(AwardsHistory awHis);

	public int removeAwardsHistory(AwardsHistory awHis);

	public int removeActiveHistory(ActiveHistory awHis);

	public int removeGradeHistory(GradeHistory gHis);

	public int removeHolyHistory(HolyHistory hHis);

	public int updateHolyHistory(HolyHistory hHis);

	public int removeConsultHistory(ConsultHistory cHis);

	public int updateConsultHistory(ConsultHistory cHis);

	public int removeAbsenceHistory(AbsenceHistory aHis);

	public int updateAbsenceHistory(AbsenceHistory aHis);

	public int removeGrantHistory(GrantHistory gHis);

	public int updateGrantHistory(GrantHistory gHis);

	public List<Student> searchStudents(String category, String value);

	public void saveTotalCredit(Student student);

	public void saveTotalGradeWarning(Student student);

	public void saveTotalHolyWarning(Student student);

	public List<User> getAdminUserAll();

	public int adminUserRemove(User user);

	public int updateAdminUser(User user);

	public int insertAdminUser(User user);

	public void bulkInsertStudentHistory(String absolutePath);

	public int changepassword(Login vo);

	public User getUserId(int studentPid);

	public List<Notice> getNoticeListAll();

	public List<String> getAutoNationalityComplete(String query);

	public Notice getNoticeList(String notice_id);

}
