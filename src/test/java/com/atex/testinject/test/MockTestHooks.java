package com.atex.testinject.test;

import com.atex.testinject.TestHooks;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class MockTestHooks implements TestHooks {

    public static int befores = 0;
    public static int afters = 0;

    @Override
    public void before(FrameworkMethod method, Object target, Statement statement) {
        MockTestHooks.befores++;
        System.err.println("before from MockTestHooks");
    }

    @Override
    public void after(FrameworkMethod method, RunNotifier notifier) {
        MockTestHooks.afters++;
        System.err.println("after from MockTestHooks");
    }


}
