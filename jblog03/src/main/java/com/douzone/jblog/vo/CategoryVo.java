package com.douzone.jblog.vo;

public class CategoryVo {
	
	private Long no;
	private String name;
	private String desc;
	private String blogID;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBlogID() {
		return blogID;
	}
	public void setBlogID(String blogID) {
		this.blogID = blogID;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", desc=" + desc + ", blogID=" + blogID + "]";
	}
	
	

}