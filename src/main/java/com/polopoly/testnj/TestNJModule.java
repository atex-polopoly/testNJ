package com.polopoly.testnj;

import com.google.inject.AbstractModule;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.multibindings.Multibinder;
import org.junit.rules.TestRule;


/**
 * A module base class with convenience methods for setting up TestNJ. Using this is completely
 * optional, but it will make life easier when adding callbacks or method rules.
 */
public abstract class TestNJModule
    extends AbstractModule
{
    /**
     * Use this to add a TestRule that should be applied for each test method executed.
     *
     * @return
     */
    public LinkedBindingBuilder<TestRule> addMethodRuleBinding()
    {
        Multibinder<TestRule> methodRuleBinding =
            Multibinder.newSetBinder(binder(), TestRule.class);
        return methodRuleBinding.addBinding();
    }

    /**
     * Use this to add a TestCallbacks object.
     *
     * @return
     */
    public LinkedBindingBuilder<TestCallbacks> addTestCallbackBinding()
    {
        Multibinder<TestCallbacks> methodRuleBinding =
            Multibinder.newSetBinder(binder(), TestCallbacks.class);
        return methodRuleBinding.addBinding();
    }
}
