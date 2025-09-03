package com.cristhiansj.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CommonHooks {
    @Before
    public void cleanDatabase(){
        System.out.println("******* Me ejecuto antes del escenario *******");
    }

    @After
    public void afterHook(){
        System.out.println("******* Me ejecuto despues del escenario *******");
    }
}
