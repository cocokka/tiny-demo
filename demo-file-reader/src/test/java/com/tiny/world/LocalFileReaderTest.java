package com.tiny.world;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

class LocalFileReaderTest {

	@Test
	void test() {
		String fileName = "E:\\workspace\\tiny-demo\\demo-file-reader\\src\\test\\resources\\input\\description.json";

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				Assertions.assertThat(line).isNotNull();
			}
		}
		catch (Exception e) {
			System.err.println("Unable to read file: " + e.getMessage());
		}
	}

}
