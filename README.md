Test NJ
=======

Framework for JUnit test injection and lifecycle hooks based on JUnit 4 and Guice.


Getting Started
===============

### 1. Add a Maven dependency to your project

```xml
<dependency>
  <groupId>com.polopoly</groupId>
  <artifactId>testNJ</artifactId>
  <version>${testNJ.version}</version>
</dependency>
```
    
### 2. Bindings

Craete a Guice module for your tests:


```java
package com.myproject;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(Duck.class).toInstance(new SwedishBlueDuck());
    }

}
```

**TestNJ** uses the Java Service Locator pattern to look for implementations of Guice modules in your projects (implementations of com.google.inject.Module). 

Add a file named ```com.google.inject.Module``` in your project's ```META-INF/services``` folder, this file should contain the names of the Guice module implementation classes, in our example:

```bash
echo 'com.project.Module' > ${prj.root}/META-INF/services/com.google.inject.Module
```



### 3. Write your test

```java
@RunWith(TestNJRunner.class)
public class SimpleInjectTest {

    @Inject
    private Duck duck;

    @Test
    public void duck_does_quack() {
        Assert.assertEquals("haloj", duck.quack());
    }

}
```


### 4. Use Callbacks

You can register test callbacks and have your custom code run before/after a single test run.

Simply implement your callback:

```java
package com.myproject;

import com.polopoly.testnj.TestCallbacks;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class DummyTestCallbacks implements TestCallbacks {

    @Override
    public void before(FrameworkMethod method, Object target, Statement statement) {
        System.err.println("before");
    }

    @Override
    public void after(FrameworkMethod method, RunNotifier notifier) {
        System.err.println("after");
    }

}
```


... and register it in your Guice Module:


```java
package com.myproject;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(Duck.class).toInstance(new SwedishBlueDuck());
        TestNJContext.addCallbacks(binder(), DummyTestCallbacks.class);
    }

}

```
