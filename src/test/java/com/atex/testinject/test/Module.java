package com.atex.testinject.test;

import com.atex.testinject.TestHooks;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<TestHooks> testHooksBinder =
                Multibinder.newSetBinder(binder(), TestHooks.class);
        testHooksBinder.addBinding().to(MockTestHooks.class);
        bind(Duck.class).toInstance(new SwedishBlueDuck());
    }

}
