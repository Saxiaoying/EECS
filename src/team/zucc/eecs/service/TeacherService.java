package team.zucc.eecs.service;

import java.sql.Timestamp;
import java.util.List;

import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.model.TeacherTrain;

public interface TeacherService {

	List<Teacher> loadTeacherInfo();

	void addTeacherInfo(int tch_id, String tch_name, String tch_title, String tch_fin_degree, String tch_qualification,
			String tch_prof, String tch_intro) throws Exception;

	void deleteTeacherInfo(int tch_id) throws Exception;

	void editTeacherInfo(int tch_id, String tch_name, String tch_title, String tch_fin_degree, String tch_qualification,
			String tch_prof, String tch_intro) throws Exception;

	List<Teacher> loadSubTeacherInfo(String tch_id, String tch_name, String tch_title, String tch_fin_degree,
			String tch_qualification, String tch_prof, String tch_intro);

	List<TeacherTrain> loadTeacherTrain();

	void addTeacherTran(int tch_id, Timestamp tr_start_time, Timestamp tr_end_time, String tr_area, String tr_place,
			String tr_content, String tr_remark) throws Exception;

	void deleteTeacherTran(int tr_id) throws Exception;

	void editTeacherTran(int tr_id, int tch_id, Timestamp tr_start_time, Timestamp tr_end_time, String tr_area,
			String tr_place, String tr_content, String tr_remark) throws Exception;

	List<TeacherTrain> loadSubTeacherTrain(String tr_id, String tch_id, String tr_area, String tr_place,
			String tr_content, String tr_remark);
	
}
