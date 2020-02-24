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

import team.zucc.eecs.model.Student;
import team.zucc.eecs.model.StudentAdmission;
import team.zucc.eecs.model.StudentChangeSpec;
import team.zucc.eecs.model.StudentClass;
import team.zucc.eecs.model.StudentEmployOrient;

@Component("StudentDaoImpl")
public class StudentDaoImpl implements StudentDao{
	@Autowired
	JdbcTemplate template;
	
	
	@Override
	public List<StudentAdmission> loadAd() {
		String sql = "select * from tb_ad";
		List<StudentAdmission> ads = new ArrayList<StudentAdmission>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StudentAdmission ad= new StudentAdmission();
				ad.setAd_id(rs.getInt("ad_id"));
				ad.setSpec_id(rs.getInt("spec_id"));
				ad.setAd_year(rs.getInt("ad_year"));
				ad.setAd_province(rs.getString("ad_province"));
				ad.setAd_min_point(rs.getInt("ad_min_point"));
				ad.setAd_num(rs.getInt("ad_num"));
				ads.add(ad);
			}
		});
		return ads;
	}
	
	
	@Override
	public void addad(int spec_id, int ad_year,String ad_province,int ad_min_point,int ad_num) throws Exception{
		String sql = "select count(*) from tb_ad where spec_id = ? and ad_year = ? and ad_province = ? and ad_min_point = ? and ad_num = ?";
		int count = template.queryForObject(sql, new Object[] {spec_id,ad_year,ad_province,ad_min_point,ad_num},Integer.class);
		if(count!=0)
			throw new Exception("该入学信息已经存在");
		
		sql = "select count(*) from tb_spec where spec_id = ?";
		count = template.queryForObject(sql, new Object[] {spec_id},Integer.class);
		if(count==0)
			throw new Exception("输入的专业不存在");
		
		
		sql = "insert into tb_ad(spec_id,ad_year,ad_province,ad_min_point,ad_num) values(?,?,?,?,?)";
		template.update(sql,new Object[] {spec_id,ad_year,ad_province,ad_min_point,ad_num});
		
		return  ;
	}
	
	@Override
	public void deletead(int ad_id)throws Exception{ 
		String sql = "delete from tb_ad where ad_id = ?";
		template.update(sql,new Object[] {ad_id});
	}
	
	
	@Override
	public void editad(int ad_id,int spec_id,int ad_year,String ad_province,int ad_min_point,int ad_num) throws Exception{
	
		String sql = "select count(*) from tb_ad where spec_id = ? and ad_year = ? and ad_province = ? and ad_min_point = ? and ad_num = ?";
		int count = template.queryForObject(sql, new Object[] {spec_id,ad_year,ad_province,ad_min_point,ad_num},Integer.class);
		if(count!=0)
			throw new Exception("该入学信息已经存在");
		
		sql = "select count(*) from tb_spec where spec_id = ?";
		count = template.queryForObject(sql, new Object[] {spec_id},Integer.class);
		if(count==0)
			throw new Exception("所填专业不存在");
		
		
		
		sql = "update tb_ad set spec_id = ? , ad_year = ? , ad_province = ? , ad_min_point = ? , ad_num = ? where ad_id = ?";
		template.update(sql,new Object[] {spec_id,ad_year,ad_province,ad_min_point,ad_num,ad_id});
		
		return  ;
	}
	
	@Override
	public List<StudentAdmission> loadSubady(String spec_id,String ad_year,String ad_province,String ad_min_point,String ad_num){
		String sql = "select * from tb_ad where spec_id like ? and ad_year like ? and ad_province like ? and ad_min_point like ? and ad_num like ?";
		List<StudentAdmission> ads = new ArrayList<StudentAdmission>();
		template.query(sql, new Object[] {"%"+spec_id+"%","%"+ad_year+"%","%"+ad_province+"%","%"+ad_min_point+"%","%"+ad_num+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StudentAdmission ad= new StudentAdmission();
				ad.setAd_id(rs.getInt("ad_id"));
				ad.setSpec_id(rs.getInt("spec_id"));
				ad.setAd_year(rs.getInt("ad_year"));
				ad.setAd_province(rs.getString("ad_province"));
				ad.setAd_min_point(rs.getInt("ad_min_point"));
				ad.setAd_num(rs.getInt("ad_num"));
				ads.add(ad);
			}
		});
		return ads;
	}
	
	@Override
	public List<Student> loadstu() {
		String sql = "select * from tb_stu";
		List<Student> stus = new ArrayList<Student>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Student stu= new Student();
				stu.setStu_id(rs.getInt("stu_id"));
				stu.setClass_id(rs.getInt("class_id"));
				stu.setStu_name(rs.getString("stu_name"));
				stus.add(stu);
			}
		});
		return stus;
	}
	
	@Override
	public void addstu(int stu_id, int class_id,String stu_name) throws Exception{
		String sql = "select count(*) from tb_stu where stu_id = ? and class_id = ? and stu_name = ?";
		int count = template.queryForObject(sql, new Object[] {stu_id,class_id,stu_name},Integer.class);
		if(count!=0)
			throw new Exception("该学生信息已经存在");
		
		sql = "select count(*) from tb_class where class_id = ?";
		count = template.queryForObject(sql, new Object[] {class_id},Integer.class);
		if(count==0)
			throw new Exception("输入的班级不存在");
		
		
		sql = "insert into tb_stu(stu_id,class_id,stu_name) values(?,?,?)";
		template.update(sql,new Object[] {stu_id,class_id,stu_name});
		
		return  ;
	}
	
	@Override
	public void deletestu(int stu_id)throws Exception{ 
		String sql = "select count(*) from tb_change_spec where stu_id = ?";
		int count = template.queryForObject(sql, new Object[] {stu_id},Integer.class);
		if(count!=0)
			throw new Exception("该学生信息已经存在在转专业表中,无法删除");
		
		sql = "select count(*) from tb_employ_orient where stu_id = ?";
		count = template.queryForObject(sql, new Object[] {stu_id},Integer.class);
			if(count!=0)
				throw new Exception("该学生信息已经存在在毕业生去向中,无法删除");
			
		sql = "delete from tb_stu where stu_id = ?";
		template.update(sql,new Object[] {stu_id});
	}
	
	@Override
	public void editstu(int stu_id,int class_id,String stu_name) throws Exception{
	
		String sql = "select count(*) from tb_class where class_id = ?";
		int count = template.queryForObject(sql, new Object[] {class_id},Integer.class);
		if(count==0)
			throw new Exception("该班级不存在");
		
		
		
		
		sql = "update tb_stu set class_id = ? , stu_name = ? where stu_id = ?";
		template.update(sql,new Object[] {class_id,stu_name,stu_id});
		
		return  ;
	}
	
	@Override
	public List<Student> loadSubStu(String stu_id,String class_id,String stu_name){
		String sql = "select * from tb_stu where stu_id like ? and class_id like ? and stu_name like ?";
		List<Student> stus = new ArrayList<Student>();
		template.query(sql, new Object[] {"%"+stu_id+"%","%"+class_id+"%","%"+stu_name+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Student stu= new Student();
				stu.setStu_id(rs.getInt("stu_id"));
				stu.setClass_id(rs.getInt("class_id"));
				stu.setStu_name(rs.getString("stu_name"));
				stus.add(stu);
			}
		});
		return stus;
	}
	
	
	//change_spec
	
	@Override
	public List<StudentChangeSpec> loadChangeSpec() {
		String sql = "select * from tb_change_spec";
		List<StudentChangeSpec> scss = new ArrayList<StudentChangeSpec>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StudentChangeSpec scs= new StudentChangeSpec();
				scs.setCs_id(rs.getInt("cs_id"));
				scs.setCs_time(rs.getTimestamp("cs_time"));
				scs.setIn_class_id(rs.getInt("in_class_id"));
				scs.setOut_class_id(rs.getInt("out_class_id"));
				scs.setStu_id(rs.getInt("stu_id"));
				scss.add(scs);
			}
		});
		return scss;
	}
	
	@Override
	public void addChangeSpec(int in_class_id, int out_class_id,int stu_id) throws Exception{
		
		String sql = "select count(*) from tb_class where class_id = ?";
		int count = template.queryForObject(sql, new Object[] {in_class_id},Integer.class);
		if(count==0)
			throw new Exception("输入的转入班级不存在");
		
		sql = "select count(*) from tb_class where class_id = ?";
		count = template.queryForObject(sql, new Object[] {out_class_id},Integer.class);
		if(count==0)
			throw new Exception("输入的转出班级不存在");
		
		sql = "select count(*) from tb_stu where stu_id = ?";
		count = template.queryForObject(sql, new Object[] {stu_id},Integer.class);
		if(count==0)
			throw new Exception("输入的学生不存在");
		
		sql = "insert into tb_change_spec(in_class_id,out_class_id,stu_id,cs_time) values(?,?,?,?)";
//		Date date = new Date();       
//		Timestamp nousedate = new Timestamp(date.getTime());

		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		template.update(sql,new Object[] {in_class_id,out_class_id,stu_id,d});
		
		sql = "update tb_stu set class_id = ? where stu_id = ?";
		template.update(sql,new Object[] {in_class_id,stu_id});
		return  ;
	}
	
	
	@Override
	public void deleteChangeSpec(int cs_id)throws Exception{ 
		String sql = "delete from tb_change_spec where cs_id = ?";
		template.update(sql,new Object[] {cs_id});
	}
	
	@Override
	public List<StudentChangeSpec> loadSubChangeSpec(String cs_id,String in_class_id,String out_class_id,String stu_id){
		String sql = "select * from tb_change_spec where cs_id like ? and in_class_id like ? and out_class_id like ? and stu_id like ?";
		List<StudentChangeSpec> scss = new ArrayList<StudentChangeSpec>();
		template.query(sql, new Object[] {"%"+cs_id+"%","%"+in_class_id+"%","%"+out_class_id+"%","%"+stu_id+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StudentChangeSpec scs= new StudentChangeSpec();
				scs.setCs_id(rs.getInt("cs_id"));
				scs.setCs_time(rs.getTimestamp("cs_time"));
				scs.setIn_class_id(rs.getInt("in_class_id"));
				scs.setOut_class_id(rs.getInt("out_class_id"));
				scs.setStu_id(rs.getInt("stu_id"));
				scss.add(scs);
			}
		});
		return scss;
	}
	
	@Override
	public void editChangeSpec(int cs_id,int in_class_id,int out_class_id) throws Exception{
	
		String sql = "select count(*) from tb_class where class_id = ?";
		int count = template.queryForObject(sql, new Object[] {in_class_id},Integer.class);
		if(count==0)
			throw new Exception("该转入班级不存在");
		
		sql = "select count(*) from tb_class where class_id = ?";
		count = template.queryForObject(sql, new Object[] {out_class_id},Integer.class);
		if(count==0)
			throw new Exception("该转出班级不存在");
		
		sql = "update tb_change_spec set in_class_id = ? , out_class_id = ? where cs_id = ?";
		template.update(sql,new Object[] {in_class_id,out_class_id,cs_id});
		
		return  ;
	}
	
	//employ orient
	@Override
	public List<StudentEmployOrient> loadEmployOrient() {
		String sql = "select * from tb_employ_orient";
		List<StudentEmployOrient> seos = new ArrayList<StudentEmployOrient>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StudentEmployOrient seo= new StudentEmployOrient();
				seo.setEo_annual_salary(rs.getFloat("eo_annual_salary"));
				seo.setEo_city(rs.getString("eo_city"));
				seo.setEo_department(rs.getString("eo_department"));
				seo.setEo_id(rs.getInt("eo_id"));
				seo.setEo_year(rs.getInt("eo_year"));
				seo.setEoc_id(rs.getInt("eoc_id"));
				seo.setStu_id(rs.getInt("stu_id"));
				seos.add(seo);
			}
		});
		return seos;
	}
	
	@Override
	public void addEmployOrient(int eoc_id,int stu_id,int eo_year,String eo_department,String eo_city,float eo_annual_salary) throws Exception{
		
		String sql = "select count(*) from tb_employ_orient_code where eoc_id = ?";
		int count = template.queryForObject(sql, new Object[] {eoc_id},Integer.class);
		if(count==0)
			throw new Exception("输入的毕业去向编码不存在");
		
		sql = "select count(*) from tb_stu where stu_id = ?";
		count = template.queryForObject(sql, new Object[] {stu_id},Integer.class);
		if(count==0)
			throw new Exception("输入的学生不存在");
		
		
		sql = "insert into tb_employ_orient(eoc_id,stu_id,eo_year,eo_department,eo_city,eo_annual_salary) values(?,?,?,?,?,?)";


		template.update(sql,new Object[] {eoc_id,stu_id,eo_year,eo_department,eo_city,eo_annual_salary});
		
		return  ;
	}
	
	@Override
	public void deleteEmployOrient(int eo_id)throws Exception{ 
		String sql = "delete from tb_employ_orient where eo_id = ?";
		template.update(sql,new Object[] {eo_id});
	}
	
	@Override
	public void editEmployOrient(int eo_id,int eoc_id,int stu_id,int eo_year,String eo_department,String eo_city,float eo_annual_salary) throws Exception{
	
		String sql = "select count(*) from tb_employ_orient_code where eoc_id = ?";
		int count = template.queryForObject(sql, new Object[] {eoc_id},Integer.class);
		if(count==0)
			throw new Exception("输入的毕业去向编码不存在");
		
		sql = "select count(*) from tb_stu where stu_id = ?";
		count = template.queryForObject(sql, new Object[] {stu_id},Integer.class);
		if(count==0)
			throw new Exception("输入的学生不存在");
		
		sql = "update tb_employ_orient set eoc_id = ? , stu_id = ? , eo_year = ? , eo_department = ? , eo_city = ? , eo_annual_salary = ? where eo_id = ?";
		template.update(sql,new Object[] {eoc_id,stu_id,eo_year,eo_department,eo_city,eo_annual_salary,eo_id});
		
		return  ;
	}
	
	@Override
	public List<StudentEmployOrient> loadSubEmployOrient(String eoc_id,String stu_id,String eo_department,String eo_city,String eo_annual_salary){
		String sql = "select * from tb_employ_orient where eoc_id like ? and stu_id like ? and eo_department like ? and eo_city like ? and eo_annual_salary like ?";
		List<StudentEmployOrient> seos = new ArrayList<StudentEmployOrient>();
		template.query(sql, new Object[] {"%"+eoc_id+"%","%"+stu_id+"%","%"+eo_department+"%","%"+eo_city+"%","%"+eo_annual_salary+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StudentEmployOrient seo= new StudentEmployOrient();
				seo.setEo_annual_salary(rs.getFloat("eo_annual_salary"));
				seo.setEo_city(rs.getString("eo_city"));
				seo.setEo_department(rs.getString("eo_department"));
				seo.setEo_id(rs.getInt("eo_id"));
				seo.setEo_year(rs.getInt("eo_year"));
				seo.setEoc_id(rs.getInt("eoc_id"));
				seo.setStu_id(rs.getInt("stu_id"));
				seos.add(seo);
			}
		});
		return seos;
	}
}
