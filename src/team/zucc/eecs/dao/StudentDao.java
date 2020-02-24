package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.Student;
import team.zucc.eecs.model.StudentAdmission;
import team.zucc.eecs.model.StudentChangeSpec;
import team.zucc.eecs.model.StudentClass;
import team.zucc.eecs.model.StudentEmployOrient;

public interface StudentDao {
	List<StudentAdmission> loadAd();
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	void addad(int spec_id, int ad_year, String ad_province, int ad_min_point, int ad_num) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void deletead(int ad_id) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void editad(int ad_id, int spec_id, int ad_year, String ad_province, int ad_min_point, int ad_num) throws Exception;


	List<StudentAdmission> loadSubady(String spec_id,String ad_year, String ad_province, String ad_min_point, String ad_num);

	@Transactional(propagation = Propagation.REQUIRED)
	List<Student> loadstu();

	@Transactional(propagation = Propagation.REQUIRED)
	void addstu(int stu_id, int class_id, String stu_name) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void deletestu(int stu_id) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void editstu(int stu_id, int class_id, String stu_name) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	List<Student> loadSubStu(String stu_id, String class_id, String stu_name);

	@Transactional(propagation = Propagation.REQUIRED)
	List<StudentChangeSpec> loadChangeSpec();
	@Transactional(propagation = Propagation.REQUIRED)
	void addChangeSpec(int in_class_id, int out_class_id, int stu_id) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void deleteChangeSpec(int cs_id) throws Exception;


	@Transactional(propagation = Propagation.REQUIRED)
	List<StudentChangeSpec> loadSubChangeSpec(String cs_id, String in_class_id, String out_class_id, String stu_id);

	@Transactional(propagation = Propagation.REQUIRED)
	void editChangeSpec(int cs_id, int in_class_id, int out_class_id) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	List<StudentEmployOrient> loadEmployOrient();

	@Transactional(propagation = Propagation.REQUIRED)
	void addEmployOrient(int eoc_id, int stu_id, int eo_year, String eo_department, String eo_city,
			float eo_annual_salary) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void deleteEmployOrient(int eo_id) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void editEmployOrient(int eo_id, int eoc_id, int stu_id, int eo_year, String eo_department, String eo_city,
			float eo_annual_salary) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	List<StudentEmployOrient> loadSubEmployOrient(String eoc_id, String stu_id, String eo_department, String eo_city,
			String eo_annual_salary);
}
