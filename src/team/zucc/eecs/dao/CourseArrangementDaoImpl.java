//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.CourseArrangement;

@Component("CourseArrangementDaoImpl")
public class CourseArrangementDaoImpl implements CourseArrangementDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<CourseArrangement> getCourseArrangementList() {
		List<CourseArrangement> courseList = new ArrayList<>();
		String sql = "select * from tb_coz_arg";
		courseList = this.template.query(sql, new RowMapper<CourseArrangement>() {
			public CourseArrangement mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseArrangement cag = new CourseArrangement();
				cag.setCag_id(rs.getInt("cag_id"));
				cag.setCs_id(rs.getInt("cs_id"));
				cag.setTch_id(rs.getInt("tch_id"));
				cag.setCag_num(rs.getInt("cag_num"));
				cag.setCag_name(rs.getString("cag_name"));
				return cag;
			}
		});
		return courseList;
	}

	@Override
	public CourseArrangement getCourseArrangementByCag_id(int cag_id) {
		return template.query("select * from tb_coz_arg where cag_id ='" +cag_id + "'", new ResultSetExtractor<CourseArrangement>() {
			@Override
			public CourseArrangement extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseArrangement cag = new CourseArrangement();
					cag.setCag_id(rs.getInt("cag_id"));
					cag.setCs_id(rs.getInt("cs_id"));
					cag.setTch_id(rs.getInt("tch_id"));
					cag.setCag_num(rs.getInt("cag_num"));
					cag.setCag_name(rs.getString("cag_name"));
					return cag;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<CourseArrangement> getCourseArrangementByCs_id(int cs_id) {
		List<CourseArrangement> courseList = new ArrayList<>();
		String sql = "select * from tb_coz_arg where cs_id = " + cs_id;
		courseList = this.template.query(sql, new RowMapper<CourseArrangement>() {
			public CourseArrangement mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseArrangement cag = new CourseArrangement();
				cag.setCag_id(rs.getInt("cag_id"));
				cag.setCs_id(rs.getInt("cs_id"));
				cag.setTch_id(rs.getInt("tch_id"));
				cag.setCag_num(rs.getInt("cag_num"));
				cag.setCag_name(rs.getString("cag_name"));
				return cag;
			}
		});
		return courseList;
	}

	@Override
	public List<CourseArrangement> getCourseArrangementByTch_id(int tch_id) {
		List<CourseArrangement> courseList = new ArrayList<>();
		String sql = "select * from tb_coz_arg where tch_id = " + tch_id;
		courseList = this.template.query(sql, new RowMapper<CourseArrangement>() {
			public CourseArrangement mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseArrangement cag = new CourseArrangement();
				cag.setCag_id(rs.getInt("cag_id"));
				cag.setCs_id(rs.getInt("cs_id"));
				cag.setTch_id(rs.getInt("tch_id"));
				cag.setCag_num(rs.getInt("cag_num"));
				cag.setCag_name(rs.getString("cag_name"));
				return cag;
			}
		});
		return courseList;
	}

	@Override
	public List<CourseArrangement> getCourseArrangementByCs_idAndTch_id(int cs_id, int tch_id) {
		List<CourseArrangement> courseList = new ArrayList<>();
		String sql = "select * from tb_coz_arg where tch_id = " + tch_id + " and cs_id = " + cs_id;
		courseList = this.template.query(sql, new RowMapper<CourseArrangement>() {
			public CourseArrangement mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseArrangement cag = new CourseArrangement();
				cag.setCag_id(rs.getInt("cag_id"));
				cag.setCs_id(rs.getInt("cs_id"));
				cag.setTch_id(rs.getInt("tch_id"));
				cag.setCag_num(rs.getInt("cag_num"));
				cag.setCag_name(rs.getString("cag_name"));
				return cag;
			}
		});
		return courseList;
	}

	@Override
	public void addCourseArrangement(int cag_id, int cs_id, int tch_id, int cag_num, String cag_name) {
		template.update("insert into tb_coz_arg (cag_id, cs_id, tch_id, cag_num, cag_name)  "
				+ "values (?, ?, ?, ?, ?)", cag_id, cs_id, tch_id, cag_num, cag_name);
	}

	@Override
	public void deleteCourseArrangementByCag_id(int cag_id) {
		template.update("delete from tb_coz_arg where cag_id = " + cag_id);
	}

	@Override
	public void updateCourseArrangement(int cag_id, int cs_id, int tch_id, int cag_num, String cag_name) {
		template.update("update tb_coz_arg set cs_id = ?, tch_id = ?, cag_num = ?  where cag_id = ?", 
				cs_id, tch_id, cag_num, cag_name, cag_id);
	}

}
