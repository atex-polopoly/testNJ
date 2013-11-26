package com.polopoly.testnj.test;

import com.google.inject.AbstractModule;
import com.polopoly.testnj.TestNJContext;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(Duck.class).toInstance(new SwedishBlueDuck());
        TestNJContext.addCallback(new MockTestCallbacks());
    }

}
