package com.dco.java.test;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dci.java.EmployeeManager;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.dci.java.EmployeeManager")
@PowerMockIgnore("jdk.internal.reflect.*")
public class EmployeeManagerTest {

	@Test()
	public void test_EmployeeManager_getOrganizationName() {
		String expected = "DCI";
		PowerMockito.mockStatic(EmployeeManager.class);

		PowerMockito.when(EmployeeManager.getOrganizationName()).thenReturn(expected);
		String actual = EmployeeManager.getOrganizationName();
		assertEquals(expected, actual);
	}

	@Test()
	public void test_EmployeeManager_getJSONRecords() throws Exception {

		String data = """
					[
						{"name":"Shyam", "email":"shyamjaiswal@gmail.com"},  
					]
				""";
		int expectation = 1;

		Object empArray = JSONValue.parse(data);
		
		EmployeeManager mock = PowerMockito.spy(new EmployeeManager());
		PowerMockito.doReturn(empArray).when(mock, "getJSONRecords", data );
		mock.initialize(data);
		
		assertEquals(expectation, mock.getEmployeeList().size());
	}
}
