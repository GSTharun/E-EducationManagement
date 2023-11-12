package com.E.Education.Management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.E.Education.Management.Service.FilesStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class Application  implements  CommandLineRunner {
	
	 @Resource
	  FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	  public void run(String... arg) throws Exception {
//	    storageService.deleteAll();
	    storageService.init();
	  }

}
