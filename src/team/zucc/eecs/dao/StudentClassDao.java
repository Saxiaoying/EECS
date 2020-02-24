package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.Specialty;
import team.zucc.eecs.model.StudentClass;

public interface StudentClassDao {
	List<StudentClass> loadClass();
	
	@Transactional(propagation = Propagation.REQUIRED)
	void addclass(String class_name,int spec_id) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteclass(int class_id) throws Exception;

	void editclass(int class_id, String class_name, int spec_id) throws Exception;

	List<StudentClass> loadSubClass(String class_name, String spec_id);
}
