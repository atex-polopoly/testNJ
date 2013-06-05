package com.atex.testinject.test;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(Duck.class).toInstance(new SwedishBlueDuck());
    }

}
