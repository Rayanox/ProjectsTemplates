package com.karavel.batch.seo.linking.common;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GenerationType {
	SL(1), 
	FP(2), 
	TOP_HOTEL_POUR_SEJLIST(5);
	
	
	
	private int code;
	
	GenerationType(int code) {
		setCode(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static Optional<GenerationType> buildFromStringValue(String generationTypeString) throws NumberFormatException {
		int codeGeneration = Integer.valueOf(generationTypeString);
		return Stream.of(values())
				.filter(item -> item.getCode() == codeGeneration)
				.findFirst();
	}

	public static String toStringCodeValues() {
		return Stream.of(values())
				.map(item -> String.valueOf(item.getCode()))
				.collect(Collectors.joining(", "));
	}
	
}
