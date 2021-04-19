package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//[EN] Libraries to work with
//[RU] Библиотеки для работы с файлами
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;



@SpringBootApplication
public class DemoApplication {

	//[EN] Search file by name and directory
	//[RU] Поиск файла по имени и местонахождению
	public static String FileName() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		try
		{
			s = br.readLine();
		}
		catch (IOException ex) {
			throw new IOException(ex);
		}
		return s;
	}

	//[EN] Writing file into array-list
	//[RU] Записываем файл в массив-список
	public static ArrayList<String> FullText(BufferedReader br) throws IOException {
		String line;
		ArrayList<String> text=new ArrayList<>();
		try {
			while ((line=br.readLine())!=null) {
				text.add(line);
			}
		}
		catch (IOException ex) {
			throw new IOException(ex);
		}
		return text;
	}

	public static void main(String[] args) throws IOException {

		//[EN] See this later
		//[RU] Рассмотрим это позже
		SpringApplication.run(DemoApplication.class, args);


		//[EN] First, get changes in same lines
		//[RU] Сначала регистрируем изменения тех же линий обоих файлов
		BufferedReader br = new BufferedReader(new FileReader(FileName()));
		ArrayList<String> File1 = FullText(br);


		br = new BufferedReader(new FileReader(FileName()));
		ArrayList<String> File2 = FullText(br);
		int i;

		//[EN] First, get changes in same lines
		//[RU] Сначала регистрируем изменения тех же линий обоих файлов
		int len = Math.min(File1.size(), File2.size());
		for (i=0;i<len;i++) {
			if (!File1.get(i).equals(File2.get(i)))
				System.out.println("On line "+i+" placed changes: "+File2.get(i));
		}
		System.out.println("Begin");

		//[EN] Then all changes that out of range of the first file detected
		//[RU] Затем засекаем все изменения вне диапазона файла
		len = Math.max(File1.size(), File2.size());
		for (;i<len;i++) {
			System.out.println("On line "+i+" placed changes: "+ ((len==File1.size()) ? File1.get(i) : File2.get(i)));
		}
	}

}
