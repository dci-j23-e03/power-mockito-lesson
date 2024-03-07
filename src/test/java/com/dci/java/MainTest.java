package com.dci.java;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.dci.java.EmployeeManager")
@PowerMockIgnore("jdk.internal.reflect.*")
public class MainTest extends TestCase {

    @Test
    public void test_Main_someMethod() {
        // given
        String expected = "DCI";
        mockStatic(EmployeeManager.class);
        when(EmployeeManager.getOrganizationName()).thenReturn("DCI");

        // when
        String actual = Main.someMethod();

        // then
        assertEquals(expected, actual);
    }
}