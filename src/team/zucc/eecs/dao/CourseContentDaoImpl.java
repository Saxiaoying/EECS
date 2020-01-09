package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.CourseContent;

public class CourseContentDaoImpl implements CourseContentDao {

	@Override
	public List<CourseContent> getCourseContentList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseContent getCourseContentByCont_id(int cont_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseContent getCourseContentByCs_id(int cs_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCourseContent(int cont_id, int cs_id, int cont_typ, String cont_name, int cont_num, String cont_cont,
			int cont_method, String cont_key, String cont_diff, double cont_hrs_tch, double cont_hrs_pr,
			int cont_cla_exe, String cont_hw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCourseContentByCoz_id(int cont_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCourseContent(int cont_id, int cs_id, int cont_typ, String cont_name, int cont_num,
			String cont_cont, int cont_method, String cont_key, String cont_diff, double cont_hrs_tch,
			double cont_hrs_pr, int cont_cla_exe, String cont_hw) {
		// TODO Auto-generated method stub

	}

}
