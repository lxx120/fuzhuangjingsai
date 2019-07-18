package com.match.mall.persist;

import java.util.Date;

public class Order {

	/**
	 * 订单
	 */
	private static final long serialVersionUID = 5231134212346077681L;
	
	private long id;        //id
	
	private String code;      //订单编号
	
	private long memberid;  //下单人id
	
	private String membername;    //下单人用户名
	
	private Date ordertime;     //下单时间
	
	private int status;// 0:新建订单 1.正式单 2.无效单 3.关闭单 4.待退单  5.已退单6.已删除,7.取消订单,8.退货待批准，9.待审核,10.价格待确认
	
	private int cargo;// 0不发货, 1.待发货 2.已发货,待确认收货3.已收货 4.已退货5.拒收6.已出库
	
	private int finance;// 0:不付款,1 待付款,2.已付款,3 已收款,4 已退款
	
	private String delivertime; //发货时间
	
	private String receiveperson; //收货人
	
	private String province; //省
	
	private String city; //省
	
	private String area; //取
	
	private String detail; //具体地址
	
	private String phone; //联系方式
	
	private double total;
	
	private String note;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getMemberid() {
		return memberid;
	}

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}	

	public String getDelivertime() {
		return delivertime;
	}

	public void setDelivertime(String delivertime) {
		this.delivertime = delivertime;
	}

	public String getReceiveperson() {
		return receiveperson;
	}

	public void setReceiveperson(String receiveperson) {
		this.receiveperson = receiveperson;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	public int getFinance() {
		return finance;
	}

	public void setFinance(int finance) {
		this.finance = finance;
	}
	
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
