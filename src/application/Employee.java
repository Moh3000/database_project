package application;

public class Employee {

	private int jid;
	private boolean still_employed;
	private String ename;
	private String DOB;
	private String gender;
	private double total_salary;
	private String address;
	private String email;
	private int phone_num;
	private String job_type;
	
	
	
	public Employee(String ename, String gender, double total_salary, boolean still_employed, String address,
			String email, int phone_num, String job_type) {
		this.ename = ename;
		this.gender = gender;
		this.total_salary = total_salary;
		this.still_employed = still_employed;
		this.address = address;
		this.email = email;
		this.phone_num = phone_num;
		this.job_type = job_type;
	}
	
	
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	public boolean isStill_employed() {
		return still_employed;
	}
	public void setStill_employed(boolean still_employed) {
		this.still_employed = still_employed;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getTotal_salary() {
		return total_salary;
	}
	public void setTotal_salary(double total_salary) {
		this.total_salary = total_salary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(int phone_num) {
		this.phone_num = phone_num;
	}
	
}
