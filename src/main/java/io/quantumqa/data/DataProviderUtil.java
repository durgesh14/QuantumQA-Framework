package io.quantumqa.data;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
USAGE:
@Test(dataProvider = "loginData", dataProviderClass = DataProvider.class)
public void testLogin(String username, String password) {
// Test logic
}
 */
public class DataProviderUtil {

    @DataProvider(name = "loginData")
    public Iterator<Object[]> provideLoginData() {
        List<Object[]> testCases = new ArrayList<>();
        testCases.add(new Object[]{"user1", "password1"});
        testCases.add(new Object[]{"user2", "password2"});
        testCases.add(new Object[]{"user3", "password3"});
        return testCases.iterator();
    }

    @DataProvider(name = "searchData")
    public Iterator<Object[]> provideSearchData() {
        List<Object[]> testCases = new ArrayList<>();
        testCases.add(new Object[]{"Selenium"});
        testCases.add(new Object[]{"TestNG"});
        testCases.add(new Object[]{"Java"});
        return testCases.iterator();
    }


}