package com.suman.kennelservice;

import com.suman.kennelservice.BLL.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class LoginTest {


    @Test
    public void testLogin(){
    LoginBLL loginBLL = new LoginBLL("suman123","suman");
    boolean result = loginBLL.checkUser();
    assertEquals(true,result);
    }


}
