package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.SpecialtyDao;
import team.zucc.eecs.dao.StudentClassDao;
import team.zucc.eecs.model.StudentClass;

@Component("StudentClassServiceImpl")
public class StudentClassServiceImpl implements StudentClassService{
	@Autowired
	private StudentClassDao studentclassdao;
	
	@Override
	public List<StudentClass> loadClass(){
		return studentclassdao.loadClass();
	}
	
	@Override
	public void addclass(String class_name,int spec_id) throws Exception{
		try {
			studentclassdao.addclass(class_name, spec_id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public void deleteclass(int class_id) throws Exception{
		try {
			studentclassdao.deleteclass(class_id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	@Override
	public void editclass(int class_id,String class_name,int spec_id) throws Exception{
		try {
			studentclassdao.editclass(class_id, class_name, spec_id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public List<StudentClass> loadSubClass(String class_name,String spec_id){
		return studentclassdao.loadSubClass(class_name, spec_id);
	}
}
