package team.zucc.eecs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.StudentAdmission;
import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.model.TeacherTrain;

@Component("TeacherDaoImpl")
public class TeacherDaoImpl implements TeacherDao{
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<Teacher> loadTeacherInfo() {
		String sql = "select * from tb_tch";
		List<Teacher> teas = new ArrayList<Teacher>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Teacher tea= new Teacher();
				tea.setTch_fin_degree(rs.getString("tch_fin_degree"));
				tea.setTch_id(rs.getInt("tch_id"));
				tea.setTch_intro(rs.getString("tch_intro"));
				tea.setTch_name(rs.getString("tch_name"));
				tea.setTch_prof(rs.getString("tch_prof"));
				tea.setTch_qualification(rs.getString("tch_qualification"));
				tea.setTch_title(rs.getString("tch_title"));
				teas.add(tea);
			}
		});
		return teas;
	}
	
	@Override
	public void addTeacherInfo(int tch_id,String tch_name,String tch_title,String tch_fin_degree,String tch_qualification,String tch_prof,String tch_intro) throws Exception{
		String sql = "select count(*) from tb_tch where tch_id = ? ";
		int count = template.queryForObject(sql, new Object[] {tch_id},Integer.class);
		if(count!=0)
			throw new Exception("该教师已经存在");
		
		sql = "insert into tb_tch(tch_id,tch_name,tch_title,tch_fin_degree,tch_qualification,tch_prof,tch_intro) values(?,?,?,?,?,?,?)";
		template.update(sql,new Object[] {tch_id,tch_name,tch_title,tch_fin_degree,tch_qualification,tch_prof,tch_intro});
		
		return  ;
	}
	@Override
	public void deleteTeacherInfo(int tch_id)throws Exception{ 
		String sql = "select count(*) from tb_tch_train where tch_id = ? ";
		int count = template.queryForObject(sql, new Object[] {tch_id},Integer.class);
		if(count!=0)
			throw new Exception("该教师已经存在在教师训练中，无法删除");
		
		sql = "delete from tb_tch where tch_id = ?";
		template.update(sql,new Object[] {tch_id});
	}
	
	
	@Override
	public void editTeacherInfo(int tch_id,String tch_name,String tch_title,String tch_fin_degree,String tch_qualification,String tch_prof,String tch_intro) throws Exception{
	
		
		String sql = "update tb_tch set tch_prof = ? , tch_name = ? , tch_title = ? , tch_fin_degree = ? , tch_qualification = ? , tch_intro = ? where tch_id = ?";
		template.update(sql,new Object[] {tch_prof,tch_name,tch_title,tch_fin_degree,tch_qualification,tch_intro,tch_id});
		
		return  ;
	}
	
	@Override
	public List<Teacher> loadSubTeacherInfo(String tch_id,String tch_name,String tch_title,String tch_fin_degree,String tch_qualification,String tch_prof,String tch_intro){
		String sql = "select * from tb_tch where tch_id like ? and tch_name like ? and tch_title like ? and tch_fin_degree like ? and tch_qualification like ? and tch_prof like ? and tch_intro like ?";
		List<Teacher> teas = new ArrayList<Teacher>();
		template.query(sql, new Object[] {"%"+tch_id+"%","%"+tch_name+"%","%"+tch_title+"%","%"+tch_fin_degree+"%","%"+tch_qualification+"%","%"+tch_prof+"%","%"+tch_intro+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Teacher tea= new Teacher();
				tea.setTch_fin_degree(rs.getString("tch_fin_degree"));
				tea.setTch_id(rs.getInt("tch_id"));
				tea.setTch_intro(rs.getString("tch_intro"));
				tea.setTch_name(rs.getString("tch_name"));
				tea.setTch_prof(rs.getString("tch_prof"));
				tea.setTch_qualification(rs.getString("tch_qualification"));
				tea.setTch_title(rs.getString("tch_title"));
				teas.add(tea);
			}
		});
		return teas;
	}
	
	//teacher train
	
	@Override
	public List<TeacherTrain> loadTeacherTrain() {
		String sql = "select * from tb_tch_train";
		List<TeacherTrain> teas = new ArrayList<TeacherTrain>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TeacherTrain tea= new TeacherTrain();
				tea.setTch_id(rs.getInt("tch_id"));
				tea.setTr_area(rs.getString("tr_area"));
				tea.setTr_content(rs.getString("tr_content"));
				tea.setTr_end_time(rs.getTimestamp("tr_end_time"));
				tea.setTr_id(rs.getInt("tr_id"));
				tea.setTr_place(rs.getString("tr_place"));
				tea.setTr_remark(rs.getString("tr_remark"));
				tea.setTr_start_time(rs.getTimestamp("tr_start_time"));
				teas.add(tea);
			}
		});
		return teas;
	}
	
	@Override
	public void addTeacherTran(int tch_id,Timestamp tr_start_time,Timestamp tr_end_time,String tr_area,String tr_place,String tr_content,String tr_remark) throws Exception{
		String sql = "select count(*) from tb_tch where tch_id = ? ";
		int count = template.queryForObject(sql, new Object[] {tch_id},Integer.class);
		if(count==0)
			throw new Exception("该教师不存在");
		
		sql = "insert into tb_tch_train(tch_id,tr_start_time,tr_end_time,tr_area,tr_place,tr_content,tr_remark) values(?,?,?,?,?,?,?)";
		template.update(sql,new Object[] {tch_id,tr_start_time,tr_end_time,tr_area,tr_place,tr_content,tr_remark});
		
		return  ;
	}
	
	@Override
	public void deleteTeacherTran(int tr_id)throws Exception{ 
		
		String sql = "delete from tb_tch_train where tr_id = ?";
		template.update(sql,new Object[] {tr_id});
	}
	
	@Override
	public void editTeacherTran(int tr_id,int tch_id,Timestamp tr_start_time,Timestamp tr_end_time,String tr_area,String tr_place,String tr_content,String tr_remark) throws Exception{
	
		
		String sql = "update tb_tch_train set tch_id = ? , tr_start_time = ? , tr_end_time = ? , tr_area = ? , tr_place = ? , tr_content = ? , tr_remark = ? where tr_id = ?";
		template.update(sql,new Object[] {tch_id,tr_start_time,tr_end_time,tr_area,tr_place,tr_content,tr_remark,tr_id});
		
		return  ;
	}
	
	@Override
	public List<TeacherTrain> loadSubTeacherTrain(String tr_id, String tch_id,String tr_area,String tr_place,String tr_content,String tr_remark){
		String sql = "select * from tb_tch_train where tr_id like ? and tch_id like ? and tr_area like ? and tr_place like ? and tr_content like ? and tr_remark like ?";
		List<TeacherTrain> teas = new ArrayList<TeacherTrain>();
		template.query(sql, new Object[] {"%"+tr_id+"%","%"+tch_id+"%","%"+tr_area+"%","%"+tr_place+"%","%"+tr_content+"%","%"+tr_remark+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TeacherTrain tea= new TeacherTrain();
				tea.setTch_id(rs.getInt("tch_id"));
				tea.setTr_area(rs.getString("tr_area"));
				tea.setTr_content(rs.getString("tr_content"));
				tea.setTr_end_time(rs.getTimestamp("tr_end_time"));
				tea.setTr_id(rs.getInt("tr_id"));
				tea.setTr_place(rs.getString("tr_place"));
				tea.setTr_remark(rs.getString("tr_remark"));
				tea.setTr_start_time(rs.getTimestamp("tr_start_time"));
				teas.add(tea);
			}
		});
		return teas;
	}
}
