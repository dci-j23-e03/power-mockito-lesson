package com.dci.java;

public class Main {

    public static void main(String[] args) {

    }

    public static String someMethod() {
        String orgName = EmployeeManager.getOrganizationName();
        System.out.println(orgName);
        return orgName;
    }
}
