package com.polopoly.testnj;

import com.google.inject.Inject;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.Set;

public class CompositeTestCallbacks implements TestCallbacks {

    @Inject
    private Set<TestCallbacks> hooks;

    public void addCallbacks(TestCallbacks callback) {
        this.hooks.add(callback);
    }

    @Override
    public void before(FrameworkMethod method, Object target, Statement statement) {
        for (TestCallbacks hook : hooks) {
            hook.before(method, target, statement);
        }
    }

    @Override
    public void after(FrameworkMethod method, RunNotifier notifier) {
        for (TestCallbacks hook : hooks) {
            hook.after(method, notifier);
        }
    }
}
