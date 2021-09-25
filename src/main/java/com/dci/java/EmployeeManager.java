package com.dci.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class EmployeeManager {
	private List<Employee> empList = new ArrayList<Employee>();

	public static void main(String[] args) {
		EmployeeManager EM = new EmployeeManager();
		EM.initialize();
		EM.sortByName();
	}

	public static String getOrganizationName() {
		return "Digital Career Institute";
	}

	private String loadJsonData() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("./data/employees.json"));
			StringBuffer content = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
			return content.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// e.printStackTrace();
				}
			}
		}

		return "";
	}

	private void initialize() {
		this.initialize(this.loadJsonData());
	}

	public void initialize(String jsonData) {
		try {
			JSONArray emps = getJSONRecords(jsonData);
			for (Object obj : emps) {
				if (obj instanceof JSONObject) {
					JSONObject empObj = (JSONObject) obj;
					Employee emp = new Employee();
					emp.setName(empObj.get("name").toString());
					emp.setEmail(empObj.get("email").toString());
					this.empList.add(emp);
				}
			}
			System.out.println(this.empList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONArray getJSONRecords(String data) {
		JSONArray emps = new JSONArray();
		try {
			emps = (JSONArray) JSONValue.parse((data == null) ? data : this.loadJsonData());
		} catch (Exception e) {
		}
		return emps;
	}

	public List<Employee> getEmployeeList() {
		return this.empList;
	}

	public void sortByName() {
		this.empList.sort(new EmployeeNameComparator());
		System.out.println(this.empList);
	}
}
