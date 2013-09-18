package com.atex.testinject;

import com.google.inject.Inject;

import java.util.Set;

public class CompositeTestHooks implements TestHooks {

    @Inject
    private Set<TestHooks> hooks;

    public void addTestRunCallbacks(TestHooks callback) {
        this.hooks.add(callback);
    }

    @Override
    public void before() {
        for (TestHooks hook : hooks) {
            hook.before();
        }
    }

    @Override
    public void after() {
        for (TestHooks hook : hooks) {
            hook.after();
        }
    }
}
