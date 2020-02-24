package team.zucc.eecs.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.StudentDao;
import team.zucc.eecs.dao.TeacherDao;
import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.model.TeacherTrain;
@Component("TeacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherdao;

	@Override
	public List<Teacher> loadTeacherInfo() {
		return teacherdao.loadTeacherInfo();
	}
	
	@Override
	public void addTeacherInfo(int tch_id,String tch_name,String tch_title,String tch_fin_degree,String tch_qualification,String tch_prof,String tch_intro) throws Exception{
		teacherdao.addTeacherInfo(tch_id, tch_name, tch_title, tch_fin_degree, tch_qualification, tch_prof, tch_intro);
	}
	
	@Override
	public void deleteTeacherInfo(int tch_id)throws Exception{
		teacherdao.deleteTeacherInfo(tch_id);
	}
	
	@Override
	public void editTeacherInfo(int tch_id,String tch_name,String tch_title,String tch_fin_degree,String tch_qualification,String tch_prof,String tch_intro) throws Exception{
		teacherdao.editTeacherInfo(tch_id, tch_name, tch_title, tch_fin_degree, tch_qualification, tch_prof, tch_intro);
	}
	@Override
	public List<Teacher> loadSubTeacherInfo(String tch_id,String tch_name,String tch_title,String tch_fin_degree,String tch_qualification,String tch_prof,String tch_intro){
		return teacherdao.loadSubTeacherInfo(tch_id, tch_name, tch_title, tch_fin_degree, tch_qualification, tch_prof, tch_intro);
	}
	
	@Override
	public List<TeacherTrain> loadTeacherTrain(){
		return teacherdao.loadTeacherTrain();
	}
	
	@Override
	public void addTeacherTran(int tch_id,Timestamp tr_start_time,Timestamp tr_end_time,String tr_area,String tr_place,String tr_content,String tr_remark) throws Exception{
		teacherdao.addTeacherTran(tch_id, tr_start_time, tr_end_time, tr_area, tr_place, tr_content, tr_remark);
	}
	
	@Override
	public void deleteTeacherTran(int tr_id)throws Exception{
		teacherdao.deleteTeacherTran(tr_id);
	}
	@Override
	public void editTeacherTran(int tr_id,int tch_id,Timestamp tr_start_time,Timestamp tr_end_time,String tr_area,String tr_place,String tr_content,String tr_remark) throws Exception{
		teacherdao.editTeacherTran(tr_id, tch_id, tr_start_time, tr_end_time, tr_area, tr_place, tr_content, tr_remark);
	}
	
	@Override
	public List<TeacherTrain> loadSubTeacherTrain(String tr_id, String tch_id,String tr_area,String tr_place,String tr_content,String tr_remark){
		return teacherdao.loadSubTeacherTrain(tr_id, tch_id, tr_area, tr_place, tr_content, tr_remark);
	}
}
