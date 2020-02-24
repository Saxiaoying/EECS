package team.zucc.eecs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.Specialty;
import team.zucc.eecs.model.StudentClass;
@Component("StudentClassDaoImpl")
public class StudentClassDaoImpl implements StudentClassDao{
	@Autowired
	JdbcTemplate template;
	
	
	@Override
	public List<StudentClass> loadClass() {
		String sql = "select * from tb_class";
		List<StudentClass> classes = new ArrayList<StudentClass>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StudentClass cas= new StudentClass();
				cas.setSpec_id(rs.getInt("spec_id"));
				cas.setClass_id(rs.getInt("class_id"));
				cas.setClass_name(rs.getString("class_name"));
				classes.add(cas);
			}
		});
		return classes;
	}
	
	@Override
	public void addclass(String class_name,int spec_id) throws Exception{
		String sql = "select count(*) from tb_class where class_name = ? and spec_id = ?";
		int count = template.queryForObject(sql, new Object[] {class_name,spec_id},Integer.class);
		if(count!=0)
			throw new Exception("该班级已经存在");
		
		sql = "select count(*) from tb_spec where spec_id = ?";
		count = template.queryForObject(sql, new Object[] {spec_id},Integer.class);
		if(count==0)
			throw new Exception("该班级所属的专业不存在");
		
		
		sql = "insert into tb_class(class_name,spec_id) values(?,?)";
		template.update(sql,new Object[] {class_name,spec_id});
		
		return  ;
	}
	
	@Override
	public void deleteclass(int class_id)throws Exception{
		 String sql = "select count(*) from tb_stu where class_id = ?";
		 int count = template.queryForObject(sql, new Object[] {class_id},Integer.class);
		 
		 if(count!=0)
			 throw new Exception("有学生存在该班级内无法删除");
		 
		 sql = "select count(*) from tb_change_spec where in_class_id = ?";
		 count = template.queryForObject(sql, new Object[] {class_id},Integer.class);
		 
		 sql = "select count(*) from tb_change_spec where out_class_id = ?";
		 int count1 = template.queryForObject(sql, new Object[] {class_id},Integer.class);
		 if(count!=0||count1!=0)
			 throw new Exception("转专业表中存在该班级无法删除");
		 
		 
		sql = "delete from tb_class where class_id = ?";
		template.update(sql,new Object[] {class_id});
	}
	
	@Override
	public void editclass(int class_id,String class_name,int spec_id) throws Exception{
	
		String sql = "select count(*) from tb_class where class_name = ? and spec_id = ?";
		int count = template.queryForObject(sql, new Object[] {class_name,spec_id},Integer.class);
		if(count!=0)
			throw new Exception("该班级已经存在");
		
		sql = "select count(*) from tb_spec where spec_id = ?";
		count = template.queryForObject(sql, new Object[] {spec_id},Integer.class);
		if(count==0)
			throw new Exception("该班级所属的专业不存在");
		
		
		
		sql = "update tb_class set class_name = ? , spec_id =? where class_id = ?";
		template.update(sql,new Object[] {class_name,spec_id,class_id});
		
		return  ;
	}
	
	@Override
	public List<StudentClass> loadSubClass(String class_name,String spec_id){
		String sql = "select * from tb_class where class_name like ? and spec_id like ?";
		List<StudentClass> classes = new ArrayList<StudentClass>();
		template.query(sql, new Object[] {"%"+class_name+"%","%"+spec_id+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StudentClass cas= new StudentClass();
				cas.setSpec_id(rs.getInt("spec_id"));
				cas.setClass_id(rs.getInt("class_id"));
				cas.setClass_name(rs.getString("class_name"));
				classes.add(cas);
			}
		});
		return classes;
	}
	
}
