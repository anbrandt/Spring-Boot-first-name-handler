package pl.sda.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//main class that starts an application
//changing starting port in application.properties
//@SpringBootApplication(scanBasePackages = "pl.sda")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}








