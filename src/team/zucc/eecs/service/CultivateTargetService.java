package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.CultivationTargerDetail;
import team.zucc.eecs.model.CultivationTarget;
import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.model.graduationReaquirement;

public interface CultivateTargetService {

	List<CultivationTarget> loadCulTarget();

	String lookcontent(int ct_id);

	void editCultarget(int ct_id, String content, int year, int spec_id) throws Exception;

	void addCulTarget(int spec_id, int ct_year, String ct_cul_target) throws Exception;

	void deleteCulTarget(int ct_id) throws Exception;

	List<CultivationTarget> loadCulTargetHis(int spec_id);

	List<CultivationTarget> loadSubCulTarget(String spec_id, String ct_year);

	List<CultivationTargerDetail> loadCulTargetDetail(int ct_id);

	void addCulTargetDetail(int ct_id, String ctd_content) throws Exception;

	void deleteCulTargetDetail(int ctd_id, int ct_id,int ctd_code) throws Exception;

	void editCulTargetDetail(int ct_id, int ctd_id, String ctd_content) throws Exception;

	List<graduationReaquirement> loadGradReq();

	void addGradReq(int ct_id, String gr_title, String gr_content) throws Exception;

	void deleteGradReq(int gr_id, int ct_id, int gr_code) throws Exception;

	void editGradReq(int gr_id, String gr_title, String gr_content) throws Exception;

	List<graduationReaquirement> loadSubGradReq(String ct_id, String gr_title, String gr_content);

	List<IndexPoint> loadIdxPt();

	void addIdxPt(int gr_id, String ip_content) throws Exception;

	void deleteIdxPt(int ip_id, int gr_id, int ip_code) throws Exception;

	void editIdxPt(int ip_id, String ip_content) throws Exception;

	List<IndexPoint> loadSubIdxPt(String gr_id, String ip_code, String ip_content);

}
