package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

	MAN(1, "男", "Male"), WOMAN(2, "女", "Female"), OTHER(3, "その他", "Other"), UNKNOWN(0, "不明", "Unknown");

	private int code;

	private String japaneseName;

	private String englishName;

	public static Gender fromCode(int code) {
		for (Gender g : values()) {
			if (g.getCode() == code) {
				return g;
			}
		}
		return UNKNOWN;
	}

	public static boolean isValid(int code) {
		return fromCode(code) != UNKNOWN;
	}

	public static String codeToName(int code) {
		return fromCode(code).getJapaneseName();
	}
}
