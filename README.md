testinject
==========

framework for JUnit test injection and lifecycle hooks


Few example
===========




An injected test class:

```java
@RunWith(TestInjectRunner.class)
public class SimpleInjectTest {

    @Inject
    private Duck duck;

    @Test
    public void duck_does_quack() {
        Assert.assertEquals("haloj", duck.quack());
    }

}
```

