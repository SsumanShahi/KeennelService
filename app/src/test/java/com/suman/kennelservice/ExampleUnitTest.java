package com.suman.kennelservice;

import com.suman.kennelservice.BLL.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    //loginpass
    @Test
    public void loginpass()
    {
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checklogin("suman123","suman");
        assertEquals(true,result);
    }
}

