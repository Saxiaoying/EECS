package team.zucc.eecs.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.model.TeacherTrain;

public interface TeacherDao {
	@Transactional(propagation = Propagation.REQUIRED)
	List<Teacher> loadTeacherInfo();
	@Transactional(propagation = Propagation.REQUIRED)
	void addTeacherInfo(int tch_id, String tch_name, String tch_title, String tch_fin_degree, String tch_qualification,
			String tch_prof, String tch_intro) throws Exception;
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteTeacherInfo(int tch_id) throws Exception;
	@Transactional(propagation = Propagation.REQUIRED)
	void editTeacherInfo(int tch_id, String tch_name, String tch_title, String tch_fin_degree, String tch_qualification,
			String tch_prof, String tch_intro) throws Exception;
	@Transactional(propagation = Propagation.REQUIRED)
	List<Teacher> loadSubTeacherInfo(String tch_id, String tch_name, String tch_title, String tch_fin_degree,
			String tch_qualification, String tch_prof, String tch_intro);
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<TeacherTrain> loadTeacherTrain();
	
	@Transactional(propagation = Propagation.REQUIRED)
	void addTeacherTran(int tch_id, Timestamp tr_start_time, Timestamp tr_end_time, String tr_area, String tr_place,
			String tr_content, String tr_remark) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteTeacherTran(int tr_id) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void editTeacherTran(int tr_id, int tch_id, Timestamp tr_start_time, Timestamp tr_end_time, String tr_area,
			String tr_place, String tr_content, String tr_remark) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<TeacherTrain> loadSubTeacherTrain(String tr_id, String tch_id, String tr_area, String tr_place,
			String tr_content, String tr_remark);

}
