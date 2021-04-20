package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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

	//[EN] Writing file into array-list
	//[RU] Записываем файл в массив-список
	//@org.jetbrains.annotations.NotNull
    public static ArrayList<String> fullText(BufferedReader br) throws IOException {
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

	public static void printChanges(int i, int max, String txt, ArrayList<String> file) {
		for (;i<max;i++) {
			System.out.println(txt+i+": "+file.get(i));
		}
	}

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);

		//[EN] Files
		//[RU] Файлы
		ArrayList<String> file1, file2;

		try (FileReader fr = new FileReader(fileName()); BufferedReader br = new BufferedReader(fr)) {
			file1 = fullText(br);
		}
		catch (IOException ex) {
			throw new IOException(ex);
		}

		try (FileReader fr = new FileReader(fileName()); BufferedReader br = new BufferedReader(fr)) {
			file2 = fullText(br);
		}
		catch (IOException ex) {
			throw new IOException(ex);
		}

		//[EN] First, get changes in same lines
		//[RU] Сначала регистрируем изменения тех же линий обоих файлов
		int i;
		int len = Math.min(file1.size(), file2.size());
		for (i=0;i<len;i++) {
			if (!file1.get(i).equals(file2.get(i)))
				System.out.println(" Modded line "+i+": "+file2.get(i));
		}

		//[EN] Then all changes that out of range of the first file detected
		//[RU] Затем засекаем все изменения вне диапазона файла
		len = Math.max(file1.size(), file2.size());
		if (len != file1.size()) {
			printChanges(i, len, "  Added line ", file2);
		} else {
			printChanges(i, len, "Deleted line ", file1);
		}
	}
}
