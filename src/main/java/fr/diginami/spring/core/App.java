package fr.diginami.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {

    public App() {
        System.out.println("Start");
    }
}
