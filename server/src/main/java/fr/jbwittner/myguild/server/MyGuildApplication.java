package fr.jbwittner.myguild.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Launcher of the application
 * @author Jean-Baptiste WITTNER
 */
@SpringBootApplication
public class MyGuildApplication {

    /**
     * Main of the application
     */
    public static void main(final String[] args) {
		SpringApplication.run(MyGuildApplication.class, args);
	}

}
