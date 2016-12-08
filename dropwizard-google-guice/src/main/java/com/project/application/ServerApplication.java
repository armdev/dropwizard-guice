package com.project.application;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.project.resources.MainResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ServerApplication extends Application<ServerConfiguration> {
    
    // java -jar target/dropwizard-google-guice.jar server src/main/resources/config.yml

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        GuiceBundle<ServerConfiguration> guiceBundle = GuiceBundle.<ServerConfiguration>newBuilder()
                .addModule(new ServerModule())
              
                .setConfigClass(ServerConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {        
        environment.jersey().register(new MainResource(configuration.getMessage()));        

    }

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }

}
