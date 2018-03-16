package io.vertx.mario.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

/**
 * Created by Sudhir on 26/11/16.
 */
public class TestInjector implements InjectorSource {
    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.DEVELOPMENT, CucumberModules.SCENARIO, new TestModule());
    }
}
