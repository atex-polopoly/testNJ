package com.polopoly.testnj.test;


import com.polopoly.testnj.TestNJRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(TestNJRunner.class)
public class SimpleInjectTest {

    @Inject
    private Duck duck;

    @Test
    public void duck_does_quack() {
        Assert.assertEquals("haloj", duck.quack());
    }


    @After
    public void verify_hooks() {
        Assert.assertEquals("call expected on hooks.before", 1, MockTestCallbacks.befores);
    }


}
