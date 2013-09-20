package com.polopoly.testnj.test;

import com.polopoly.testnj.TestCallbacks;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class MockTestCallbacks implements TestCallbacks {

    public static int befores = 0;
    public static int afters = 0;

    @Override
    public void before(FrameworkMethod method, Object target, Statement statement) {
        MockTestCallbacks.befores++;
        System.err.println("before from MockTestHooks");
    }

    @Override
    public void after(FrameworkMethod method, RunNotifier notifier) {
        MockTestCallbacks.afters++;
        System.err.println("after from MockTestHooks");
    }


}
