package com.atex.testinject;

import java.util.ArrayList;
import java.util.List;

public class CompositeTestHooks implements TestHooks {

    private List<TestHooks> hooks = new ArrayList<TestHooks>();

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
