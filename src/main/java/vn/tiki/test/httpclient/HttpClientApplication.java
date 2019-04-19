package vn.tiki.test.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HttpClientApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HttpClientApplication.class, args);
	}

}
