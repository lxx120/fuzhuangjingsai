package com.match.dao.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.match.dao.LftrgtDao;

/** 
 * @author mengly 
 * @version 创建时间：2016年7月15日 下午4:05:14 
 * 类说明 
 */
@Service
public class LftrgtDaoImpl implements LftrgtDao {
	
//	@Resource
//	private HibernateDao hdao;
	@Resource(name="jdao")
	private JdbcTemplate jsdao;
	/**
	 * 
	 * @param table
	 * @param id
	 * @param pid
	 * @param ordering -1,最靠前，0，最靠后，其他值，表示处于其他兄弟的id的后面
	 * @param cons:条件
	 * @return 
	 * @author mengly 
	 * @version 创建时间：2016年7月15日 下午4:12:00
	 */
	@Override
	public boolean insert(String table,long id,long pid,long ordering,String[] cons)
	{
		if(checkinsertlftrgt(table,id))
		{
			return true;
		}
		pid=checkpid(table,pid);
		ordering=this.checkordering(table, ordering, pid);
		Lftrgt lftrgt=new Lftrgt();
		String clause=" 1=1 ";
		if(cons!=null&&cons.length>0)
			clause=clause+" and "+ StringUtils.join(cons, " and ");
		getparentLR(table,pid,lftrgt,clause);
		computebase(table, pid, lftrgt, clause, ordering);
		computelevel( table, pid, lftrgt, clause);
		
		String sql="select * from "+table+" where (lft>"+lftrgt.getBase()+" or rgt>"+lftrgt.getBase()+") and "+clause;
//		hdao.getSQLmapUpdate("select * from "+table+" where (lft>"+lftrgt.getBase()+" or rgt>"+lftrgt.getBase()+") and "+clause,null);
//		jsdao.queryForList(sql);
		sql="update "+table+" set lft=lft+2 where lft>"+lftrgt.getBase()+" and "+clause;
//		hdao.updateSQL(sql, null);
		jsdao.update(sql);
		sql="update "+table+" set rgt=rgt+2 where rgt>"+lftrgt.getBase()+" and "+clause;
//		hdao.updateSQL(sql, null);
		jsdao.update(sql);
		sql="update "+table+" set lft="+lftrgt.getLft()+",rgt="+lftrgt.getRgt()+",level="+lftrgt.getLevel()+" where id="+id;
//		hdao.updateSQL(sql, null);
		jsdao.update(sql);
//		hdao.flush();
//		hdao.commit();
		
		return true;
	}
	
	
	/**
	 * 
	 * @param table
	 * @param id
	 * @param pid
	 * @param ordering -1,最靠前，0，最靠后，其他值，表示处于其他兄弟的id的后面
	 * @param cons
	 * @return 
	 * @author mengly 
	 * @version 创建时间：2016年7月19日 上午11:45:46
	 */
	@Override
	public  boolean update(String table,long id,long pid,long ordering,String[] cons)
	{
		pid=this.checkpid(table, pid);  //check pid 是否存在
		if(!checkRing(table,id,pid))   //check 是否有环
			return false;
		ordering=this.checkordering(table, ordering, pid); //check ordering是否是合适的ordering,如果ordering不是pid的孩子，则ordering为0
		if(id==ordering)//如果id==ordering，即本就在pid下面，则不需要调整
			return true;
		Lftrgt lftrgt=new Lftrgt();
		String clause="  1=1 ";
		if(cons!=null&&cons.length>0)
			clause=clause+" and "+StringUtils.join(cons, " and ");
		getCurrent(table,id,lftrgt,clause,pid);  //取出当前id的lft、rgt，pid的plft、prgt
		if(checkchange(table,id,pid,ordering,lftrgt)) //判断，更加ordering lft plft，rgt prgt 判断是否需要修改
			return true;
		changeLevelAndMarkOrdering(table,id,pid,lftrgt,clause);
		firstDeleteLftRgt(table,id,pid,lftrgt,clause);
		getparentLR(table, pid, lftrgt, clause);
		int lft=lftrgt.getLft();//
		int rgt=lftrgt.getRgt();//
		computebase( table, pid, lftrgt, clause, ordering) ;//在这里已经计算了base，新的lft，rgt
		
		int tt=0;//
		if(rgt!=0&&lft!=0)
			tt=rgt-lft+1;//需要调整的数据
		else
			tt=2;
		String sql="update "+table+" set lft=lft+"+tt+" where lft>"+lftrgt.getBase()+" and (ordering!=1) and "+clause;
		jsdao.update(sql);
		sql="update "+table+" set rgt=rgt+"+tt+" where rgt>"+lftrgt.getBase()+" and (ordering!=1) and "+clause;
		jsdao.update(sql);
		tt=lftrgt.getBase()+1-lft;
		int ttr=0;
		if(rgt==0)
			ttr=tt+1;
		else
			ttr=tt;
		sql="update "+table+" set lft=lft+"+tt+",rgt=rgt+"+ttr+",ordering=0 where ordering=1 and "+clause;
		jsdao.update(sql);
		return true;
	}
	@Override
	public  boolean delete(String table,long id,String[] cons)
	{
		String clause=" 1=1 ";
		if(cons!=null&&cons.length>0)
			clause=clause+" and "+StringUtils.join(cons," and ");
		Lftrgt lftrgt=new Lftrgt();
		getCurrent(table,id,lftrgt);
		if(lftrgt.getLft()==0||lftrgt.getRgt()==0)
			return false;
		int tt=lftrgt.getRgt()-lftrgt.getLft()+1;
//		hdao.startTran();
		String sql="update "+table+" set lft=lft-"+tt+" where lft>"+lftrgt.getRgt()+" and "+clause;
//		hdao.updateSQL(sql, null);
		jsdao.update(sql);
		sql="update "+table+" set rgt=rgt-"+tt+" where rgt>"+lftrgt.getRgt()+" and "+clause;
//		hdao.updateSQL(sql, null);
		jsdao.update(sql);
//		hdao.commit();
		return true;
	}
	
	private boolean changeLevelAndMarkOrdering(String table,long id,long pid,Lftrgt lftrgt,String clause){
		//ordering no use,so here It is used to mark which record is to be changed 
		if(lftrgt.getLft()==0&&lftrgt.getRgt()==0){
			int level=lftrgt.getPlevel()+1;
			String sql="update "+table+" set ordering=1,level="+level+" where id="+id;
//			hdao.updateSQL(sql,null);
			jsdao.update(sql);
			return true;
		}
		int levelchange=lftrgt.getPlevel() -lftrgt.getPrelevel()+1;
		String sql="update "+table+" set level=level+%d,ordering=1 where lft between %d and %d and "+clause;
		sql=String.format(sql,levelchange,lftrgt.getLft(),lftrgt.getRgt());
//		hdao.updateSQL(sql, null);
		jsdao.update(sql);
		return true;
	}
	
	/**
	 * 先建个要变更的做标记，并将变更之外的重新排列
	 * @param table
	 * @param id
	 * @param pid
	 * @param lftrgt
	 * @param clause
	 * @return 
	 * @author mengly 
	 * @version 创建时间：2016年7月19日 下午6:51:41
	 */
	private boolean firstDeleteLftRgt(String table,long id,long pid,Lftrgt lftrgt,String clause)
	{
		if(lftrgt.getRgt()==lftrgt.getLft())
			return true;
		int tttt=lftrgt.getRgt()-lftrgt.getLft()+1;
		//调整后面的节点的lft、rgt
		String sql="update "+table+" set lft=lft-"+tttt+" where lft >"+lftrgt.getRgt()+" and "+clause;
//		hdao.updateSQL(sql,null);
		jsdao.update(sql);
		sql="update "+table+" set rgt=rgt-"+tttt+" where rgt >"+lftrgt.getRgt()+" and "+clause;
//		hdao.updateSQL(sql,null);
		jsdao.update(sql);
		return true;
	}
	
	private boolean checkchange(String table,long id,long pid,long ordering,Lftrgt lftrgt)
	{
		if(ordering==0)
		{
			//判断是否是parent下面最后一个
			if(lftrgt.getRgt()==lftrgt.getPrgt()-1)
				return true;
		}else if(ordering<0){
			//判断是否是parent下面最前一个
			if(lftrgt.getLft()==lftrgt.getPlft()+1)
				return true;
		}else{
			String sql="select lft,rgt from "+table+ " where id="+ordering;
			List<Map<String, Object>>  objl=jsdao.queryForList(sql);//hdao.getSQLOne(sql, null);
			int orgt=0;
			if(objl!=null&&objl.size()>0){
				Map<String, Object> obj=objl.get(0);
				if(obj!=null)
				{
//				Object[] objs=(Object[])obj;
					orgt=((Number)obj.get("rgt")).intValue();//((Number)objs[1]).intValue();
					if(lftrgt.getLft()==orgt+1)
						return true;
				}
			}
		}
		return false;
	}
	private boolean getCurrent(String table,long id,Lftrgt lftrgt)
	{
		String sql="select lft,rgt,level from "+table +" where id="+id;
		List<Map<String, Object>>  objl=jsdao.queryForList(sql);
		Map<String, Object> obj=null;//hdao.getSQLOne(sql, null);
		if(objl!=null&&objl.size()>0){
			obj=objl.get(0);
		}
		if(obj==null)
			return false;
//		Object[] objs=(Object[])obj;
		int lft=((Number)obj.get("lft")).intValue();//((Number)objs[0]).intValue();
		int rgt=((Number)obj.get("rgt")).intValue();//((Number)objs[1]).intValue();
		int prelevel=((Number)obj.get("level")).intValue();//(Integer)objs[2];
		lftrgt.setLft(lft);
		lftrgt.setRgt(rgt);
		lftrgt.setPrelevel(prelevel);
		return true;
	}
	private boolean getCurrent(String table,long id,Lftrgt lftrgt,String clause,long pid)
	{
		String sql="select lft,rgt,level from "+table +" where id="+id;
		List<Map<String, Object>>  objl=jsdao.queryForList(sql);
		Map<String, Object> obj=null;//hdao.getSQLOne(sql, null);
		if(objl!=null&&objl.size()>0){
			obj=objl.get(0);
		}
		if(obj==null)
			return false;
//		Object[] objs=(Object[])obj;
		int lft=((Number)obj.get("lft")).intValue();
		int rgt=((Number)obj.get("rgt")).intValue();
		int prelevel=((Number)obj.get("level")).intValue();
		lftrgt.setLft(lft);
		lftrgt.setRgt(rgt);
		lftrgt.setPrelevel(prelevel);
		/*首先获取pid对应的lft和rgt,这里去lft和rgt,是为了下面的ordering判断*/
		if(pid!=0){
			sql="select lft,rgt,level from "+table+ " where id="+pid;
		}else
		{
			sql="select 0 as lft,max(rgt) rgt,0 as level from "+table+" where "+clause;
		}
		List<Map<String, Object>>  objr=jsdao.queryForList(sql);
		obj=null;//hdao.getSQLOne(sql, null);
		if(objr!=null&&objr.size()>0){
			obj=objl.get(0);
		}
//		obj=jsdao.queryForMap(sql);//hdao.getSQLOne(sql, null);
		if(obj!=null)
		{
//			objs=(Object[])obj;
			int plft=((Number)obj.get("lft")).intValue();//((Number)objs[0]).intValue();
			int prgt=((Number)obj.get("rgt")).intValue();//(objs[1]==null)?0:((Number)objs[1]).intValue();
			int plevel=((Number)obj.get("level")).intValue();//((Number)objs[2]).intValue();
			lftrgt.setPlevel(plevel);
			lftrgt.setPlft(plft);
			if(prgt==0)
				prgt=Integer.MAX_VALUE;
			else if(pid==0)
				prgt=prgt+1;
			lftrgt.setPrgt(prgt);
		}
		return true;
	}
	/**
	 * 检查是否有环状
	 * @param table
	 * @param id
	 * @param pid
	 * @return 
	 * @author mengly 
	 * @version 创建时间：2016年7月19日 上午11:29:00
	 */
	private boolean checkRing(String table,long id,long pid)
	{
//		String sql="select (b.lft between a.lft and a.rgt) from "+table+" a , "+table+" b where a.id="+id+" and b.id="+pid;
		//sql server 写法
				String sql="select case when b.lft between a.lft and a.rgt then 1 else 0 end as abc"
						+ " from "+table+" a , "+table+" b where a.id="+id+" and b.id="+pid;
//		log.info("sql::::::"+sql);
		List<Map<String, Object>>  objl=jsdao.queryForList(sql);
		Map<String, Object> obj=null;//hdao.getSQLOne(sql, null);
		if(objl!=null&&objl.size()>0){
			obj=objl.get(0);
		}
		if(obj!=null && ((Number)obj.get("abc")).intValue()==1)
		{
//			hdao.rollback();
			return false;
		}
		return true;
	}
	private boolean computelevel(String table,long pid,Lftrgt lftrgt,String clause)
	{
		String sql="select level from "+table+" where id="+pid;
		int level=1;
		List<Map<String, Object>>  objl=jsdao.queryForList(sql);
		Map<String, Object> obj=null;//hdao.getSQLOne(sql, null);
		if(objl!=null&&objl.size()>0){
			obj=objl.get(0);
		}
		if(obj!=null)
			level=((Number)obj.get("level")).intValue()+1;
		lftrgt.setLevel(level);
		return true;
	}
	private boolean computebase(String table,long pid,Lftrgt lftrgt,String clause,long ordering)
	{
		if(ordering>0){
			String sql="select lft,rgt from "+table+" where id="+ordering;
			List<Map<String, Object>>  objl=jsdao.queryForList(sql);
			Map<String, Object> obj=null;//hdao.getSQLOne(sql, null);
			if(objl!=null&&objl.size()>0){
				obj=objl.get(0);
			}
			int orgt=0;
			if(obj==null)
			{
				orgt=lftrgt.getPlft();
			}else{
//				Object[] objs=(Object[])obj;
//				try{
//					orgt=((Number)objs[1]).intValue();
//				}catch(Exception e)
//				{
//				}
				orgt=((Number)obj.get("rgt")).intValue();
			}
			lftrgt.setBase(orgt);
		}else if(ordering==0){
			if(lftrgt.getPrgt()==Integer.MAX_VALUE||lftrgt.getPrgt()<=1)
				lftrgt.setBase(0);
			else
				lftrgt.setBase(lftrgt.getPrgt()-1);
		}else{
			lftrgt.setBase(lftrgt.getPlft());
		}
		lftrgt.setLft(lftrgt.getBase()+1);
		lftrgt.setRgt(lftrgt.getBase()+2);
		return true;
	}
	private boolean getparentLR(String table,long pid,Lftrgt lftrgt,String clause)
	{
		String sql="";
		if(pid>0)
		{
			sql="select lft,rgt,level from "+table+" where id="+pid;
			System.out.println("sql:"+sql);
		}else{
			sql="select 0 lft,max(rgt) rgt,0 as level from "+table+" where "+clause;
		}
		List<Map<String, Object>>  objl=jsdao.queryForList(sql);
		Map<String, Object> obj=null;//hdao.getSQLOne(sql, null);
		if(objl!=null&&objl.size()>0){
			obj=objl.get(0);
		}
//		Object[] objs=(Object[])obj;
		if(obj!=null)
		{
			try{
				int plft=((Number)(obj.get("lft")==null?"0":obj.get("lft"))).intValue();//((Number)objs[0]).intValue();
				int prgt=((Number)(obj.get("rgt")==null?"0":obj.get("rgt"))).intValue();//(objs[1]==null)?0:((Number)objs[1]).intValue();//(Integer)objs[1];
				int plevel=((Number)(obj.get("level")==null?"0":obj.get("level"))).intValue();//((Number)objs[2]).intValue();//(Integer)objs[1];
				if(prgt==0)
					prgt=Integer.MAX_VALUE;
				else if(pid==0)  //如果是pid为0的时候，就是要加1,为prgt的id
					prgt=prgt+1;
				lftrgt.setPlft(plft);
				lftrgt.setPrgt(prgt);
				lftrgt.setPlevel(plevel);
			}catch(Exception e){
				
			}
			return true;
		}else{
			lftrgt.setPlft(0);
			lftrgt.setPrgt(Integer.MAX_VALUE);
			lftrgt.setPlevel(0);
			return false;
		}
	}
	
	
	private long checkpid(String table,long pid)
	{
		String sql="select id from "+table+" where id="+pid;
		List<Map<String, Object>>  objl=jsdao.queryForList(sql);
		Map<String, Object> obj=null;//hdao.getSQLOne(sql, null);
		if(objl!=null&&objl.size()>0){
			obj=objl.get(0);
		}
		if(obj==null)
			return 0;
		return ((Number)obj.get("id")).longValue();
	}
	
	private long checkordering(String table,long ordering,long pid)
	{
		if(ordering<=0)
			return ordering;
		String sql="select pid from "+table+" where id="+ordering;
		List<Map<String, Object>>  objl=jsdao.queryForList(sql);
		Map<String, Object> obj212=null;//hdao.getSQLOne(sql, null);
		if(objl!=null&&objl.size()>0){
			obj212=objl.get(0);
		}
		if (obj212 == null && pid!=0) {
				ordering=0;
		}else if(obj212!=null&&((Number)obj212.get("pid")).longValue()!=pid)
		{
			ordering=0;
		}
		return ordering;
	}
	private boolean checkinsertlftrgt(String table,long id)
	{
		String sql="select lft,rgt from "+table+" where id="+id;
		List<Map<String, Object>>  objl=jsdao.queryForList(sql);
		Map<String, Object> obj=null;//hdao.getSQLOne(sql, null);
		if(objl!=null&&objl.size()>0){
			obj=objl.get(0);
		}
		if(obj==null)
			return false;
//		Object[] objs=(Object[])obj;
		try{
			int lft=((Number)obj.get("lft")).intValue();
			int rgt=((Number)obj.get("rgt")).intValue();
			if(lft>0&&rgt>0)
				return true;
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
	
	
	
	
	private class Lftrgt{
		private int plft;
		private int prgt;
		private int lft;
		private int rgt;
		private int base;
		private int level;
		
		private int plevel;
		private int prelevel;
		public Lftrgt() {
			plft=0;
			prgt=0;
			lft=0;
			rgt=0;
			base=0;
			level=1;
		}
		public int getPlft() {
			return plft;
		}
		public void setPlft(int plft) {
			this.plft = plft;
		}
		public int getPrgt() {
			return prgt;
		}
		public void setPrgt(int prgt) {
			this.prgt = prgt;
		}
		public int getLft() {
			return lft;
		}
		public void setLft(int lft) {
			this.lft = lft;
		}
		public int getRgt() {
			return rgt;
		}
		public void setRgt(int rgt) {
			this.rgt = rgt;
		}
		public int getBase() {
			return base;
		}
		public void setBase(int base) {
			this.base = base;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public int getPlevel() {
			return plevel;
		}
		public void setPlevel(int plevel) {
			this.plevel = plevel;
		}
		public int getPrelevel() {
			return prelevel;
		}
		public void setPrelevel(int prelevel) {
			this.prelevel = prelevel;
		}
		
	}
}
