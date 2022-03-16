package application;

import java.util.Date;

public class Customer {
	private int cid;
	private String cname;
	private String job;
	private String gender;
	private String address;
	private Date date_of_birth;
	private String license_type;

	public Customer(int cid, String cname, String job, String gender, String address, Date date_of_birth,
			String license_type) {
		this.cname = cname;
		this.cid = cid;
		this.job = job;
		this.gender = gender;
		this.address = address;
		this.date_of_birth = date_of_birth;
		this.license_type = license_type;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getLicense_type() {
		return license_type;
	}

	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}

}
