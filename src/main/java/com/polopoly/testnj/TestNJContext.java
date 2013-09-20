package com.polopoly.testnj;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class TestNJContext {

    protected Injector init() {
        List<Module> modules = new ArrayList<Module>();
        initModulesFromServiceLoader(modules);

        modules.add(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(TestCallbacks.class).to(CompositeTestCallbacks.class);
            }
        });
        return Guice.createInjector(modules);
    }

    private void initModulesFromServiceLoader(List<Module> modules) {
        ServiceLoader<Module> serviceLoader = ServiceLoader.load(Module.class);
        for (Module module : serviceLoader) {
            modules.add(module);
        }
    }


    public static void addCallbacks(Binder binder, Class<? extends TestCallbacks> hooks) {
        Multibinder<TestCallbacks> testHooksBindings =
                Multibinder.newSetBinder(binder, TestCallbacks.class);
        testHooksBindings.addBinding().to(hooks);
    }


    public static void addCallbacks(Binder binder, TestCallbacks hooks) {
        Multibinder<TestCallbacks> testHooksBindings =
                Multibinder.newSetBinder(binder, TestCallbacks.class);
        testHooksBindings.addBinding().toInstance(hooks);
    }



}
