package org.gnome.gir.generator;

import java.util.ArrayList;
import java.util.List;

import org.gnome.gir.compiler.GConstants;
import org.gnome.gir.repository.ArgInfo;

public class Field {

	protected String type;
	protected String name;
	protected List<String> paraList = new ArrayList<String>();

	public Field(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	public Field(String type, String name, String param) {
		this.type = type;
		this.name = name;
		paraList.add(param);
	}

	public String writeToJava() {
		return type + GConstants.SPACE + name + inizialize()
				+ GConstants.DOUBLEDOT + GConstants.NEWLINE;
	}

	public String getType() {
		return type;
	}

	private String inizialize() {
		return paraList.isEmpty() ? GConstants.EMPTY : GConstants.EQUALS
				+ GConstants.SPACE + GConstants.NEW + GConstants.SPACE + type
				+ constructArgs();
	}

	private String constructArgs() {
		String callArgs = new String();
		for (String a : paraList) {
			callArgs += a + GConstants.COMMA;
		}
		int last = callArgs.length() != 0 ? callArgs.length() - 1 : callArgs
				.length();
		return GConstants.ROUND_BRACKET_OPEN + callArgs.substring(0, last) + GConstants.ROUND_BRACKET_CLOSED;
	}
}
