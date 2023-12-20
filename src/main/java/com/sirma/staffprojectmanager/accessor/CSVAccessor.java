package com.sirma.staffprojectmanager.accessor;

import com.sirma.staffprojectmanager.exception.FileMissingException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CSVAccessor implements FileAccessor {

	private final static String EMPLOYEE_CSV_FILE_PATH = "src/main/resources/input/data.csv";

	@Override
	public List<String> read() {
		try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_CSV_FILE_PATH))) {
			return reader.lines().collect(Collectors.toList());
		} catch (IOException e) {
			throw new FileMissingException(EMPLOYEE_CSV_FILE_PATH);
		}
	}

	@Override
	public void write(List<String> lines) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_CSV_FILE_PATH, false))) {
			lines.forEach(line -> {
				try {
					writer.write(line + "\n");
				} catch (IOException e) {
					throw new FileMissingException(EMPLOYEE_CSV_FILE_PATH);
				}
			});
		} catch (IOException e) {
			throw new FileMissingException(EMPLOYEE_CSV_FILE_PATH);
		}
	}
}
