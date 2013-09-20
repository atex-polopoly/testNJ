package com.atex.testinject;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public interface TestHooks {

    void before(FrameworkMethod method, Object target, Statement statement);

    void after(FrameworkMethod method, RunNotifier notifier);

}
