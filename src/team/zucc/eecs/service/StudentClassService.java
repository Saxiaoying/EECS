package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.StudentClass;

public interface StudentClassService {
	List<StudentClass> loadClass();
	
	void addclass(String class_name,int spec_id) throws Exception;
	void deleteclass(int class_id) throws Exception;

	void editclass(int class_id, String class_name, int spec_id) throws Exception;

	List<StudentClass> loadSubClass(String class_name, String spec_id);
}
