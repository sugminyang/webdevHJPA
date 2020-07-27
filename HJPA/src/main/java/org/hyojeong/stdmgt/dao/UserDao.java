package org.hyojeong.stdmgt.dao;

import java.util.List;

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

	public int updateGradeHistory(GradeHistory gHis);

	public int insertNewGradeHistory(GradeHistory gHis);

	public List<AbsenceHistory> getAbsenceHistory(int pid);

	public List<GrantHistory> getGrantHistory(int pid);

	public int updateProfileImg(int studentPid, String profilePath);

	public int insertNewActiveHistory(ActiveHistory aHis);

	public int updateActiveHistory(ActiveHistory aHis);

	public int insertNewAwardsHistory(AwardsHistory awHis);

	public int updateAwardsHistory(AwardsHistory awHis);

	public int removeAwardsHistory(AwardsHistory awHis);

	public int removeActiveHistory(ActiveHistory aHis);

	public int removeGradeHistory(GradeHistory gHis);

	public int removeHolyHistory(HolyHistory hHis);

	public int insertHolyHistory(HolyHistory hHis);

	public int updateHolyHistory(HolyHistory hHis);

	public int removeConsultHistory(ConsultHistory cHis);

	public int insertConsultHistory(ConsultHistory cHis);

	public int updateConsultHistory(ConsultHistory cHis);

	public int removeAbsenceHistory(AbsenceHistory aHis);

	public int insertAbsenceHistory(AbsenceHistory aHis);

	public int updateAbsenceHistory(AbsenceHistory aHis);

	public int removeGrantHistory(GrantHistory gHis);

	public int insertGrantHistory(GrantHistory gHis);

	public int updateGrantHistory(GrantHistory gHis);

	public List<Student> searchStudents(String category, String value);

	public void saveTotalCredit(Student student);

	public void saveTotalGradeWarning(Student student);

	public void saveTotalHolyWarning(Student student);

	public int updateAllItemsStudentInfo(Student originStudent);

	public List<User> getAdminUserAll();

	public int adminUserRemove(User user);

	public int updateAdminUser(User user);

	public int insertAdminUser(User user);

	public List<Student> getPidWithSnoUniv(String sno_univ);

	public int changepassword(Login vo);

	public User getUserId(int studentPid);

	public List<Notice> getNoticeListAll();

	public List<String> getAutoNationalityComplete(String query);

	public Notice getNoticeList(String notice_id);

	public int noticeInsert(Notice notice);

	public int noticeDelete(Notice notice);
}
