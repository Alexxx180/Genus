package com.example.demo;

import compare.FileCompare;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//[EN] Libraries to work with
//[RU] Библиотеки для работы с файлами
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import dfmhph.DMP;

@SpringBootApplication
public class DemoApplication {

	//[EN] Search file by name and directory
	//[RU] Поиск файла по имени и местонахождению
	public static String fileName() throws IOException {
		String s;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			s = br.readLine();
		}
		catch (IOException ex) {
			throw new IOException(ex);
		}
		return s;
	}

	//[EN] Main class
	//[RU] Главный класс
	public static void main(String[] args) throws IOException, IndexOutOfBoundsException {
		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		String f1, f2;
		try {
			f1 = args[0];
			f2 = args[1];
		}
		catch (IndexOutOfBoundsException ex) {
			System.err.println("Attributes are unknown or don't exist.\nPlease enter File names correctly.\nOne then another.");
			f1 = fileName();
			f2 = fileName();
		}
		FileCompare fc = new FileCompare(f1, f2);
	}
}
