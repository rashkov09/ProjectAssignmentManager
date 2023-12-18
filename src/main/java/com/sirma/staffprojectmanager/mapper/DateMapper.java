package com.sirma.staffprojectmanager.mapper;

import java.time.LocalDate;
public class DateMapper implements Mapper<LocalDate> {
	private final static String YEAR_FIRST_SLASH_PATTERN = "^([0-9]{4})\\/([0][1-9]|[1][0-2])\\/([0-2][0-9]|[3][0-1])$";
	private final static String YEAR_LAST_SLASH_PATTERN = "^([0-2][0-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/([0-9]{4})$";
	private final static String YEAR_LAST_MONTH_FIRST_SLASH_PATTERN = "^([0][1-9]|[1][0-2])\\/([0-2][0-9]|[3][0-1])\\/(\\d{4})$";

	private final static String YEAR_FIRST_DASH_PATTERN = "^([0-9]{4})-([0][1-9]|[1][0-2])-([0-2][0-9]|[3][0-1])$";
	private final static String YEAR_LAST_DASH_PATTERN = "^([0-2][0-9]|[3][0-1])-([0][1-9]|[1][0-2])-([0-9]{4})$";
	private final static String YEAR_LAST_MONTH_FIRST_DASH_PATTERN = "^([0][1-9]|[1][0-2])-([0-2][0-9]|[3][0-1])-(\\d{4})$";

	/**
	 * This class will support the following formats:
	 * MM/DD/YYYY - Month/Day/Year (e.g., 12/31/2023)
	 * DD/MM/YYYY - Day/Month/Year (e.g., 31/12/2023)
	 * YYYY/MM/DD - Year/Month/Day (e.g., 2023/12/31)
	 * Month Day, Year - Written month, day, year (e.g., December 31, 2023)
	 * Day Month Year - Day, written month, year (e.g., 31 December 2023)
	 * MM-DD-YYYY - Month-Day-Year with dashes (e.g., 12-31-2023)
	 * DD-MM-YYYY - Day-Month-Year with dashes (e.g., 31-12-2023)
	 * YYYY-MM-DD - Year-Month-Day with dashes (e.g., 2023-12-31)
	 *
	 */
	@Override
	public LocalDate mapFromString(String input) {
		LocalDate result = null;
		if (input.matches(YEAR_FIRST_SLASH_PATTERN)){
			String[] data = input.split("/");
			result= LocalDate.of(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
		} else if (input.matches(YEAR_LAST_SLASH_PATTERN)){
			String[] data = input.split("/");
			result= LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0]));
		} else if (input.matches(YEAR_LAST_MONTH_FIRST_SLASH_PATTERN)){
			String[] data = input.split("/");
			result= LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[0]),Integer.parseInt(data[1]));
		} else if (input.matches(YEAR_FIRST_DASH_PATTERN)){
			String[] data = input.split("-");
			result= LocalDate.of(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
		} else if (input.matches(YEAR_LAST_DASH_PATTERN)){
			String[] data = input.split("-");
			result= LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0]));
		} else if (input.matches(YEAR_LAST_MONTH_FIRST_DASH_PATTERN)){
			String[] data = input.split("-");
			result= LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[0]),Integer.parseInt(data[1]));
		}
		return result;
	}


	@Override
	public String mapToString(LocalDate input) {
		return null;
	}
}
