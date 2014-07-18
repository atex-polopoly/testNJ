package com.polopoly.testnj;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.rules.TestRule;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class TestNJRunner extends BlockJUnit4ClassRunner {

    private static Injector injector;
    private static Object initLock = new Object();

    private static TestCallbacks hooks;

    @Inject
    private Set<TestRule> methodRules;


    public TestNJRunner(Class<?> klass) throws InitializationError {
        super(klass);
        initInjectorIfNecessary();
        injector.injectMembers(this);
    }

    private void initInjectorIfNecessary() {
        synchronized (initLock) {
            if (injector == null) {
                injector = new TestNJContext().init();
                hooks = injector.getInstance(TestCallbacks.class);
            }
        }
    }

    @Override
    protected Object createTest() throws Exception {
        Object obj = super.createTest();
        injector.injectMembers(obj);
        return obj;
    }


    @SuppressWarnings("deprecation")
    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        Statement returnStatement = super.withBefores(method, target, statement);
        hooks.before(method, target, statement);
        return returnStatement;
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        try {
            super.runChild(method, notifier);
        } finally {
            hooks.after(method, notifier);
        }
    }

    @Override
    protected List<TestRule> getTestRules(final Object target)
    {
        List<TestRule> rules = super.getTestRules(target);
        List<TestRule> decoratedRules = new ArrayList<TestRule>();
        decoratedRules.addAll(rules);
        decoratedRules.addAll(methodRules);

        return decoratedRules;
    }


}
