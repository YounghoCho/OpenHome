/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OpenhomeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OpenhomeApplication.class, args);
	}
}
