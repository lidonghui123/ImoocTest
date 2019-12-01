package com.course;

import okio.Timeout;
import org.testng.annotations.Test;

public class TimeOutTest {
    @Test(timeOut = 3000)
    public void testSuccess(){

    }
    public void TestFailed(){

    }
}
