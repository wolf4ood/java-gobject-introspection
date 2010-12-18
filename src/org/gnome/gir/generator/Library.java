package org.gnome.gir.generator;

import java.util.List;

import org.gnome.gir.compiler.GConstants;

public class Library {

	protected String namespace;
	protected String library;
	protected String mapper;

	public Library(String namespace, String library, String mapper) {
		this.namespace = namespace;
		this.library = library;
		this.mapper = mapper;
	}

	public String writeLibrary(List<String> methods) {
		StringBuilder builder = new StringBuilder();
		builder.append(writePackages());
		builder.append(writeDeclaration());
		builder.append(writeLibraryLoad());
		for (String m : methods) {
			builder.append(m);
		}
		builder.append(GConstants.BRACE_CLOSED);
		return builder.toString();
	}
	private String writePackages() {
		StringBuilder builder = new StringBuilder();
		builder.append(GConstants.PACKAGE + GConstants.SPACE
				+ GConstants.PACKAGE_PREFIX + GConstants.DOT
				+ namespace.toLowerCase() + GConstants.NEWLINE);

		return builder.toString();
	}

	private String writeLibraryLoad() {
		String load = GConstants.LIBRARY_LOAD;
		load = load.replace(GConstants.TAG_NAMESPACE, namespace);
		load = load.replace(GConstants.TAG_LIBRARY, library);
		load = load.replace(GConstants.TAG_MAPPER, GConstants.NEW
				+ GConstants.SPACE + mapper + GConstants.ROUND_BRACKET_OPEN
				+ GConstants.ROUND_BRACKET_CLOSED);

		return load;
	}

	private String writeDeclaration() {
		StringBuilder builder = new StringBuilder();
		builder.append(GConstants.PUBLIC + GConstants.SPACE
				+ GConstants.INTERFACE + GConstants.SPACE + namespace
				+ GConstants.SPACE + GConstants.EXTENDS + GConstants.SPACE
				+ GConstants.LIBRARY + GConstants.BRACE_OPEN
				+ GConstants.NEWLINE);
		return builder.toString();
	}
}
