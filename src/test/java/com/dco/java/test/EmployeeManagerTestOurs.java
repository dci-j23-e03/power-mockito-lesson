package com.dco.java.test;

import com.dci.java.EmployeeManager;
import org.json.simple.JSONValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.dci.java.EmployeeManager")
@PowerMockIgnore("jdk.internal.reflect.*")
public class EmployeeManagerTestOurs {

    @Test()
    public void test_EmployeeManager_getJSONRecords() throws Exception {

        // given
        String data = "	[\n" +
                      "		{\"name\":\"Shyam\", \"email\":\"shyamjaiswal@gmail.com\"},\n" +
                      "	]\n"; // 1
        int expectation = 1; // 2

        Object empArray = JSONValue.parse(data); // 3

        EmployeeManager employeeMangerMock = PowerMockito.spy(new EmployeeManager()); // 4
        PowerMockito.doReturn(empArray).when(employeeMangerMock, "getJSONRecords", data ); // 5

        // when
        employeeMangerMock.initialize(data); // 6

        assertEquals(expectation, employeeMangerMock.getEmployeeList().size()); // 7
        assertEquals("Shyam", employeeMangerMock.getEmployeeList().get(0).getName());
        assertEquals("shyamjaiswal@gmail.com", employeeMangerMock.getEmployeeList().get(0).getEmail());
    }

}
