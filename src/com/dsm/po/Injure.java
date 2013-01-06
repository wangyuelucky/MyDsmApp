package com.dsm.po;


public class Injure implements java.io.Serializable{

	private int id;
	private String name;
	private String sex;
	private String unit;                   // 单位
	private String workshop;               // 车间
	private String acci_date;           // 发生日期
	private String acci_time;              // 发生时间
	private String category;               // 事故类别
	private String factor;                 // 主要因素
	private String apply_date;          // 申报日期
	private String injury_condit;          // 负伤程度
	private String wktype;                 // 工种
	private String identity;               // 身份证号
	private int age;
	private String begin;               // 开始工作时间
	private String safety_edu;             // 何种安全教育
	private int stop_days;                 // 歇工天数
	private String comment;                // 备注
	private String story;                  // 负伤经过；
	private String lesson;                 // 预防
	private String unit_officer;           // 申报负责人
	private String safe_quard;             // 申报安全员
	private String audit_officer;          // 审核负责人
	
	public Injure()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWorkshop() {
		return workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	public String getAcci_date() {
		return acci_date;
	}

	public void setAcci_date(String acci_date) {
		this.acci_date = acci_date;
	}

	public String getAcci_time() {
		return acci_time;
	}

	public void setAcci_time(String acci_time) {
		this.acci_time = acci_time;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public String getApply_date() {
		return apply_date;
	}

	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}

	public String getInjury_condit() {
		return injury_condit;
	}

	public void setInjury_condit(String injury_condit) {
		this.injury_condit = injury_condit;
	}

	public String getWktype() {
		return wktype;
	}

	public void setWktype(String wktype) {
		this.wktype = wktype;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getSafety_edu() {
		return safety_edu;
	}

	public void setSafety_edu(String safety_edu) {
		this.safety_edu = safety_edu;
	}

	public int getStop_days() {
		return stop_days;
	}

	public void setStop_days(int stop_days) {
		this.stop_days = stop_days;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getUnit_officer() {
		return unit_officer;
	}

	public void setUnit_officer(String unit_officer) {
		this.unit_officer = unit_officer;
	}

	public String getSafe_quard() {
		return safe_quard;
	}

	public void setSafe_quard(String safe_quard) {
		this.safe_quard = safe_quard;
	}

	public String getAudit_officer() {
		return audit_officer;
	}

	public void setAudit_officer(String audit_officer) {
		this.audit_officer = audit_officer;
	}
	
	
}
