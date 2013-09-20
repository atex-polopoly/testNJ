package com.atex.testinject;

import com.google.inject.Inject;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.Set;

public class CompositeTestHooks implements TestHooks {

    @Inject
    private Set<TestHooks> hooks;

    public void addTestRunCallbacks(TestHooks callback) {
        this.hooks.add(callback);
    }

    @Override
    public void before(FrameworkMethod method, Object target, Statement statement) {
        for (TestHooks hook : hooks) {
            hook.before(method, target, statement);
        }
    }

    @Override
    public void after(FrameworkMethod method, RunNotifier notifier) {
        for (TestHooks hook : hooks) {
            hook.after(method, notifier);
        }
    }
}
