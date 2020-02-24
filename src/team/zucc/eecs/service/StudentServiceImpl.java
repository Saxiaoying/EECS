package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.StudentClassDao;
import team.zucc.eecs.dao.StudentDao;
import team.zucc.eecs.model.Student;
import team.zucc.eecs.model.StudentAdmission;
import team.zucc.eecs.model.StudentChangeSpec;
import team.zucc.eecs.model.StudentClass;
import team.zucc.eecs.model.StudentEmployOrient;


@Component("StudentServiceImpl")
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentDao studentdao;
	
	@Override
	public List<StudentAdmission> loadAd() {
		return studentdao.loadAd();
	}
	
	@Override
	public void addad(int spec_id, int ad_year, String ad_province, int ad_min_point, int ad_num) throws Exception{
		try {
			studentdao.addad(spec_id, ad_year, ad_province, ad_min_point, ad_num);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public void deletead(int ad_id)throws Exception{
		studentdao.deletead(ad_id);
		return ;
	}
	
	@Override
	public void editad(int ad_id,int spec_id,int ad_year,String ad_province,int ad_min_point,int ad_num) throws Exception{
		try {
			studentdao.editad(ad_id, spec_id, ad_year, ad_province, ad_min_point, ad_num);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<StudentAdmission> loadSubady(String spec_id,String ad_year,String ad_province,String ad_min_point,String ad_num){
		return studentdao.loadSubady(spec_id, ad_year, ad_province, ad_min_point, ad_num);
	}
	
	@Override
	public List<Student> loadstu() {
		return studentdao.loadstu();
	}
	
	@Override
	public void addstu(int stu_id, int class_id, String stu_name) throws Exception{
		studentdao.addstu(stu_id, class_id, stu_name);
	}
	
	@Override
	public void deletestu(int stu_id)throws Exception{
		studentdao.deletestu(stu_id);
	}
	
	@Override
	public void editstu(int stu_id,int class_id,String stu_name) throws Exception{
		studentdao.editstu(stu_id, class_id, stu_name);
	}
	
	@Override
	public List<Student> loadSubStu(String stu_id,String class_id,String stu_name){
		return studentdao.loadSubStu(stu_id, class_id, stu_name);
	}
	
	//chage_spec
	
	@Override
	public List<StudentChangeSpec> loadChangeSpec() {
		return studentdao.loadChangeSpec();
	}
	@Override
	public void addChangeSpec(int in_class_id, int out_class_id,int stu_id) throws Exception{
		studentdao.addChangeSpec(in_class_id, out_class_id, stu_id);
	}
	
	@Override
	public void deleteChangeSpec(int cs_id)throws Exception{
		studentdao.deleteChangeSpec(cs_id);
	}
	
	@Override
	public List<StudentChangeSpec> loadSubChangeSpec(String cs_id,String in_class_id,String out_class_id,String stu_id){
		return studentdao.loadSubChangeSpec(cs_id, in_class_id, out_class_id, stu_id);
	}
	

	@Override
	public void editChangeSpec(int cs_id,int in_class_id,int out_class_id) throws Exception{
		studentdao.editChangeSpec(cs_id, in_class_id, out_class_id);
	}
	
	//emplty orient 
	@Override
	public List<StudentEmployOrient> loadEmployOrient(){
		return studentdao.loadEmployOrient();
	}
	
	@Override
	public void addEmployOrient(int eoc_id,int stu_id,int eo_year,String eo_department,String eo_city,float eo_annual_salary) throws Exception{
		studentdao.addEmployOrient(eoc_id, stu_id, eo_year, eo_department, eo_city, eo_annual_salary);
	}
	
	@Override
	public void deleteEmployOrient(int eo_id)throws Exception{
		studentdao.deleteEmployOrient(eo_id);
	}
	
	@Override
	public void editEmployOrient(int eo_id,int eoc_id,int stu_id,int eo_year,String eo_department,String eo_city,float eo_annual_salary) throws Exception{
		studentdao.editEmployOrient(eo_id, eoc_id, stu_id, eo_year, eo_department, eo_city, eo_annual_salary);
	}
	@Override
	public List<StudentEmployOrient> loadSubEmployOrient(String eoc_id,String stu_id,String eo_department,String eo_city,String eo_annual_salary){
		return studentdao.loadSubEmployOrient(eoc_id, stu_id, eo_department, eo_city, eo_annual_salary);
	}
}
