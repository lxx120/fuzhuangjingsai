package com.match.reviewresult.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.match.baciss.cl.UserChangLiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.reviewresult.cl.PeizhiChnagLiang;
import com.match.reviewresult.mapper.ExcellentThemeWorkMapper;
import com.match.reviewresult.mapper.PeizhiMapper;
import com.match.reviewresult.model.ExcellentThemeWork;
import com.match.reviewresult.service.ExcellentThemeWorkService;

@Service
public class ExcellentThemeWorkServiceImpl implements ExcellentThemeWorkService {

	@Autowired
	private  ExcellentThemeWorkMapper  ExcellentThemeWorkMapper;
	
	@Autowired
	private  PeizhiMapper  PeizhiMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Override
	public int addBatchExcellentThemeWork(long competitionid,List<Map<String,Object>> list) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = PeizhiMapper.findPeiZhi(PeizhiChnagLiang.excellentThemeWork);
		if(map==null)
		{
			return 2;  //请先配置个数
		}
		int count= PeizhiMapper.countPeizhi(PeizhiChnagLiang.excellentThemeWork);
		int sum = Integer.parseInt(map.get("pagesize").toString());
		if(sum<count+list.size())
		{
			return 3;  //优秀作品数量超过上限
		}
		List<ExcellentThemeWork>  list1 = new ArrayList<ExcellentThemeWork>();
		for (Map<String, Object> map2 : list) {
			ExcellentThemeWork excellentThemeWork = new ExcellentThemeWork();
			excellentThemeWork.setCompetitionid(competitionid);
			excellentThemeWork.setMtime(UserChangLiang.mtime());
			long workid = UserChangLiang.doubletolong((double)map2.get("workid"));
			excellentThemeWork.setWorkid(workid);
			list1.add(excellentThemeWork);
		}
		int i = ExcellentThemeWorkMapper.addBatchExcellentThemeWork(list1);
		if(i>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteExcellentThemeWork(long id) throws Exception {
		// TODO Auto-generated method stub
		return ExcellentThemeWorkMapper.deleteExcellentThemeWork(id);
	}

	@Override
	public List<Map<String, Object>> findExcellentThemeWork() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map1 = CompetitionMapper.findCurrnetCompetition3();
		if(map1==null)
		{
			return  null;
		}
		long competitionid = Integer.parseInt(map1.get("id").toString());
		Map<String,Object> map = PeizhiMapper.findPeiZhi(PeizhiChnagLiang.excellentThemeWork);
		if(map!=null)
		{
			return ExcellentThemeWorkMapper.findExcellentThemeWork(Integer.parseInt(map.get("pagesize").toString()), competitionid);
		}
		return ExcellentThemeWorkMapper.findExcellentThemeWork(PeizhiChnagLiang.moren,competitionid);
	}

	@Override
	public PageResult<Map<String, Object>> findOtherThemeWork(long competitionid, String stwcode, String code,
			String phone, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=ExcellentThemeWorkMapper.countfindOtherThemeWork(competitionid, stwcode, code, phone);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = ExcellentThemeWorkMapper.findOtherThemeWork(competitionid, stwcode, code, phone, pageModel.getPage(), pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

}
