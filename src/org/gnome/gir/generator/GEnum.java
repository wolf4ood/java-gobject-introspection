package org.gnome.gir.generator;

import org.gnome.gir.compiler.GConstants;
import org.gnome.gir.repository.EnumInfo;
import org.gnome.gir.repository.ValueInfo;

public class GEnum {

	private EnumInfo info;

	public GEnum(EnumInfo info) {
		this.info = info;
	}

	public String writeEnum() {
		String en = new String();
		en += writePackage() + GConstants.NEWLINE;
		en += writeDeclaration() + GConstants.NEWLINE;
		en += writeValue() + GConstants.NEWLINE;
		en += GConstants.BRACE_CLOSED;
		return en;
	}

	private String writePackage() {

		return GConstants.PACKAGE + GConstants.SPACE
				+ GConstants.PACKAGE_PREFIX + GConstants.DOT
				+ info.getNamespace().toLowerCase() +  GConstants.DOUBLEDOT;
	}

	private String writeDeclaration() {
		return GConstants.PUBLIC + GConstants.SPACE + GConstants.ENUM
				+ GConstants.SPACE + info.getName() + GConstants.BRACE_OPEN;
	}

	private String writeValue() {
		String value = new String();
		for (ValueInfo v : info.getValueInfo()) {
			value += v.getName().toUpperCase() + GConstants.COMMA;
		}

		return value;
	}

	public String getName() {
		return info.getName();
	}
}
