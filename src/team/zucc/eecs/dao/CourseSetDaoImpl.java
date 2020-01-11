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

import team.zucc.eecs.model.CourseSet;

@Component("CourseSetDaoImpl")
public class CourseSetDaoImpl implements CourseSetDao {
	@Autowired
	JdbcTemplate template;

	@Override
	public List<CourseSet> getCourseSetList() {
		List<CourseSet> courseSetList = new ArrayList<>();
		String sql = "select * from tb_coz_set";
		courseSetList = this.template.query(sql, new RowMapper<CourseSet>() {
			public CourseSet mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseSet cs = new CourseSet();
				cs.setCs_id(rs.getInt("cs_id"));
				cs.setCoz_id(rs.getString("coz_id"));
				cs.setCs_acad_yr(rs.getInt("cs_acad_yr"));
				cs.setCs_sem(rs.getInt("cs_sem"));
				return cs;
			}
		});
		return courseSetList;
	}

	@Override
	public CourseSet getCourseSetByCs_id(int cs_id) {
		return template.query("select * from tb_coz_set where cs_id ='" +cs_id + "'", new ResultSetExtractor<CourseSet>() {
			@Override
			public CourseSet extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseSet cs = new CourseSet();
					cs.setCs_id(rs.getInt("cs_id"));
					cs.setCoz_id(rs.getString("coz_id"));
					cs.setCs_acad_yr(rs.getInt("cs_acad_yr"));
					cs.setCs_sem(rs.getInt("cs_sem"));
					return cs;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<CourseSet> getCourseSetListByCoz_id(String coz_id) {
		List<CourseSet> courseSetList = new ArrayList<>();
		String sql = "select * from tb_coz_set where coz_id like '%" + coz_id + "%'";
		courseSetList = this.template.query(sql, new RowMapper<CourseSet>() {
			public CourseSet mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseSet cs = new CourseSet();
				cs.setCs_id(rs.getInt("cs_id"));
				cs.setCoz_id(rs.getString("coz_id"));
				cs.setCs_acad_yr(rs.getInt("cs_acad_yr"));
				cs.setCs_sem(rs.getInt("cs_sem"));
				return cs;
			}
		});
		return courseSetList;
	}

	@Override
	public List<CourseSet> getCourseSetListByCs_acad_yr(int cs_acad_yr) {
		List<CourseSet> courseSetList = new ArrayList<>();
		String sql = "select * from tb_coz_set where cs_acad_yr =" + cs_acad_yr;
		courseSetList = this.template.query(sql, new RowMapper<CourseSet>() {
			public CourseSet mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseSet cs = new CourseSet();
				cs.setCs_id(rs.getInt("cs_id"));
				cs.setCoz_id(rs.getString("coz_id"));
				cs.setCs_acad_yr(rs.getInt("cs_acad_yr"));
				cs.setCs_sem(rs.getInt("cs_sem"));
				return cs;
			}
		});
		return courseSetList;
	}

	@Override
	public List<CourseSet> getCourseSetListByCs_sem(int cs_sem) {
		List<CourseSet> courseSetList = new ArrayList<>();
		String sql = "select * from tb_coz_set where cs_sem =" + cs_sem;
		courseSetList = this.template.query(sql, new RowMapper<CourseSet>() {
			public CourseSet mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseSet cs = new CourseSet();
				cs.setCs_id(rs.getInt("cs_id"));
				cs.setCoz_id(rs.getString("coz_id"));
				cs.setCs_acad_yr(rs.getInt("cs_acad_yr"));
				cs.setCs_sem(rs.getInt("cs_sem"));
				return cs;
			}
		});
		return courseSetList;
	}

	@Override
	public List<CourseSet> getCourseSetListByInf(String coz_id, int cs_acad_yr, int cs_sem) {
		List<CourseSet> courseSetList = new ArrayList<>();
		String sql = "";
		if (cs_acad_yr < 0 && cs_sem < 0) {
			sql = "select * from tb_coz_set where coz_id like '%" + coz_id + "%'";
		} else if (cs_sem < 0) {
			sql = "select * from tb_coz_set where coz_id like '%" + coz_id + "%' and cs_acad_yr=" + cs_acad_yr;
		} else if (cs_acad_yr < 0){
			sql = "select * from tb_coz_set where coz_id like '%" + coz_id + "%' and cs_sem=" + cs_sem ;
		} else {
			sql = "select * from tb_coz_set where coz_id like '%" + coz_id + "%' and cs_acad_yr=" + cs_acad_yr + " and cs_sem=" + cs_sem ;
		}
		
		courseSetList = this.template.query(sql, new RowMapper<CourseSet>() {
			public CourseSet mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseSet cs = new CourseSet();
				cs.setCs_id(rs.getInt("cs_id"));
				cs.setCoz_id(rs.getString("coz_id"));
				cs.setCs_acad_yr(rs.getInt("cs_acad_yr"));
				cs.setCs_sem(rs.getInt("cs_sem"));
				return cs;
			}
		});
		return courseSetList;
	}

	@Override
	public void addCourseSet(String coz_id, int cs_acad_yr, int cs_sem) {
		template.update("insert into tb_coz_set (coz_id, cs_acad_yr, cs_sem)  "
				+ "values (?, ?, ?)", coz_id, cs_acad_yr, cs_sem);
	}

	@Override
	public void deleteCourseSetByCoz_id(int cs_id) {
		template.update("delete from tb_coz_set where cs_id = " + cs_id);
		
	}

	@Override
	public void updateCourseSet(int cs_id, String coz_id, int cs_acad_yr, int cs_sem) {
		template.update("update tb_coz_set set coz_id = ?, cs_acad_yr = ?, cs_sem = ?  "
				+ "where cs_id = ?", coz_id, cs_acad_yr, cs_sem, cs_id);
	}
}
