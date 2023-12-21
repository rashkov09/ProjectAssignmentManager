package com.sirma.staffprojectmanager.accessor;

import java.io.IOException;
import java.util.List;

public interface FileAccessor {

	List<String> read(String filePath);

	void write(List<String> lines) throws IOException;

}
