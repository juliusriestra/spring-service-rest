package com.company.rest.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

		private Long id;
		private String name;
		private String email;
		private String password;
		private	String role;
		private Integer active;
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public Integer getActive() {
			return active;
		}

		public void setActive(Integer active) {
			this.active = active;
		}

}
