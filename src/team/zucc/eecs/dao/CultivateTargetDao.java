package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.CultivationTargerDetail;
import team.zucc.eecs.model.CultivationTarget;
import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.model.graduationReaquirement;

public interface CultivateTargetDao {
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<CultivationTarget> loadCulTarget();
	
	@Transactional(propagation = Propagation.REQUIRED)
	String lookcontent(int ct_id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	void editCultarget(int ct_id, String content, int year, int spec_id) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void addCulTarget(int spec_id, int ct_year, String ct_cul_target) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteCulTarget(int ct_id) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<CultivationTarget> loadCulTargetHis(int spec_id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<CultivationTarget> loadSubCulTarget(String spec_id, String ct_year);
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<CultivationTargerDetail> loadCulTargetDetail(int ct_id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	void addCulTargetDetail(int ct_id, String ctd_content) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteCulTargetDetail(int ctd_id, int ct_id,int ctd_code) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void editCulTargetDetail(int ct_id, int ctd_id, String ctd_content) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<graduationReaquirement> loadGradReq();
	
	@Transactional(propagation = Propagation.REQUIRED)
	void addGradReq(int ct_id, String gr_title, String gr_content) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteGradReq(int gr_id, int ct_id, int gr_code) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void editGradReq(int gr_id, String gr_title, String gr_content) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<graduationReaquirement> loadSubGradReq(String ct_id, String gr_title, String gr_content);
	
	@Transactional(propagation = Propagation.REQUIRED)
	List<IndexPoint> loadIdxPt();

	@Transactional(propagation = Propagation.REQUIRED)
	void addIdxPt(int gr_id, String ip_content) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void deleteIdxPt(int ip_id, int gr_id, int ip_code) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	void editIdxPt(int ip_id, String ip_content) throws Exception;

	@Transactional(propagation = Propagation.REQUIRED)
	List<IndexPoint> loadSubIdxPt(String gr_id, String ip_code, String ip_content);

}
