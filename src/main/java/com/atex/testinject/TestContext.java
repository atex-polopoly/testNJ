package com.atex.testinject;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class TestContext {

    protected Injector init() {
        List<Module> modules = new ArrayList<Module>();
        initModulesFromServiceLoader(modules);

        modules.add(new Module() {
            @Override
            public void configure(Binder binder) {
                CompositeTestHooks callbacks = new CompositeTestHooks();
                initCallbacksFromServiceLoader(callbacks);
                binder.bind(TestHooks.class).toInstance(callbacks);
            }
        });
        return Guice.createInjector(modules);
    }

    private void initCallbacksFromServiceLoader(CompositeTestHooks callbacks) {
        ServiceLoader<TestHooks> serviceLoader = ServiceLoader.load(TestHooks.class);
        for (TestHooks callback : serviceLoader) {
            callbacks.addTestRunCallbacks(callback);
        }
    }

    private void initModulesFromServiceLoader(List<Module> modules) {
        ServiceLoader<Module> serviceLoader = ServiceLoader.load(Module.class);
        for (Module module : serviceLoader) {
            modules.add(module);
        }
    }



}
