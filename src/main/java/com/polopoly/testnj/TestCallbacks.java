package com.polopoly.testnj;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public interface TestCallbacks {

    void before(FrameworkMethod method, Object target, Statement statement);

    void after(FrameworkMethod method, RunNotifier notifier);

}
