package com.sirma.staffprojectmanager.accessor;

import com.sirma.staffprojectmanager.exception.FileMissingException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CSVAccessor implements FileAccessor {
	private final static String BACKUP_CSV_FILE_PATH = "src/main/resources/input/dataBackup.csv";

	@Override
	public List<String> read(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			return reader.lines().collect(Collectors.toList());
		} catch (IOException e) {
			throw new FileMissingException(filePath);
		}
	}

	@Override
	public void write(List<String> lines) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(BACKUP_CSV_FILE_PATH, false))) {
			lines.forEach(line -> {
				try {
					writer.write(line + "\n");
				} catch (IOException e) {
					throw new FileMissingException(BACKUP_CSV_FILE_PATH);
				}
			});
		} catch (IOException e) {
			throw new FileMissingException(BACKUP_CSV_FILE_PATH);
		}
	}
}
