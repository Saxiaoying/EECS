package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.Student;
import team.zucc.eecs.model.StudentAdmission;
import team.zucc.eecs.model.StudentChangeSpec;
import team.zucc.eecs.model.StudentEmployOrient;

public interface StudentService {

	List<StudentAdmission> loadAd();
	void addad(int spec_id, int ad_year, String ad_province, int ad_min_point, int ad_num) throws Exception;
	void deletead(int ad_id) throws Exception;
	void editad(int ad_id, int spec_id, int ad_year, String ad_province, int ad_min_point, int ad_num) throws Exception;
	List<StudentAdmission> loadSubady(String spec_id, String ad_year, String ad_province, String ad_min_point, String ad_num);
	List<Student> loadstu();
	void addstu(int stu_id, int class_id, String stu_name) throws Exception;
	void deletestu(int stu_id) throws Exception;
	void editstu(int stu_id, int class_id, String stu_name) throws Exception;
	List<Student> loadSubStu(String stu_id, String class_id, String stu_name);
	List<StudentChangeSpec> loadChangeSpec();
	void addChangeSpec(int in_class_id, int out_class_id, int stu_id) throws Exception;
	void deleteChangeSpec(int cs_id) throws Exception;
	List<StudentChangeSpec> loadSubChangeSpec(String cs_id, String in_class_id, String out_class_id, String stu_id);
	void editChangeSpec(int cs_id, int in_class_id, int out_class_id) throws Exception;
	List<StudentEmployOrient> loadEmployOrient();
	void addEmployOrient(int eoc_id, int stu_id, int eo_year, String eo_department, String eo_city,
			float eo_annual_salary) throws Exception;
	void deleteEmployOrient(int eo_id) throws Exception;
	void editEmployOrient(int eo_id, int eoc_id, int stu_id, int eo_year, String eo_department, String eo_city,
			float eo_annual_salary) throws Exception;
	List<StudentEmployOrient> loadSubEmployOrient(String eoc_id, String stu_id, String eo_department, String eo_city,
			String eo_annual_salary);
	
}
