package com.atex.testinject.test;


import com.atex.testinject.TestInjectRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(TestInjectRunner.class)
public class SimpleInjectTest {

    @Inject
    private Duck duck;

    @Test
    public void duck_does_quack() {
        Assert.assertEquals("haloj", duck.quack());
        Assert.assertEquals("call expected on hooks.before", 1, MockTestHooks.befores);
        Assert.assertEquals("call expected on hooks.after", 1, MockTestHooks.afters);
    }


}
