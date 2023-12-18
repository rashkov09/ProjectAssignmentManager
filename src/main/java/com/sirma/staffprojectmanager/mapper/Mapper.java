package com.sirma.staffprojectmanager.mapper;

public interface Mapper<T> {

	T mapFromString(String input);
	String mapToString(T input);

}
