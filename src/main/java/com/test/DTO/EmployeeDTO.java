package com.test.DTO;

public class EmployeeDTO {
	  public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String name;
	    private String role;
	    private long insertTime;
	    private AddressDTO address;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public long getInsertTime() {
			return insertTime;
		}
		public void setInsertTime(long insertTime) {
			this.insertTime = insertTime;
		}
		public AddressDTO getAddress() {
			return address;
		}
		public void setAddress(AddressDTO address) {
			this.address = address;
		}
		public EmployeeDTO(String name, String role, long insertTime, AddressDTO address) {
			super();
			this.name = name;
			this.role = role;
			this.insertTime = insertTime;
			this.address = address;
		}
}
