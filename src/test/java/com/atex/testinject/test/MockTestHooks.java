package com.atex.testinject.test;

import com.atex.testinject.TestHooks;

public class MockTestHooks implements TestHooks {

    public static int befores = 0;
    public static int afters = 0;

    @Override
    public void before() {
        MockTestHooks.befores++;
        System.err.println("before from MockTestHooks");
    }

    @Override
    public void after() {
        MockTestHooks.afters++;
        System.err.println("after from MockTestHooks");
    }


}
