package org.gnome.gir.generator;

import java.util.ArrayList;
import java.util.List;

import org.gnome.gir.compiler.GConstants;

public class Library {

	protected String namespace;
	protected String library;
	protected String mapper;
	protected List<Package> packages = new ArrayList<Package>();

	public Library(String namespace, String library, String mapper) {
		this.namespace = namespace;
		this.library = library;
		this.mapper = mapper;
		initLibrary();
	}

	protected void initLibrary() {
		packages.add(new Package(GConstants.PACKAGE_PREFIX + GConstants.DOT + namespace.toLowerCase()));
		packages.add(new Package("com.sun.jna.Library"));
		packages.add(new Package("com.sun.jna.Native"));
		packages.add(new Package("com.sun.jna.Pointer"));
		packages.add(new Package("com.sun.jna.ptr.PointerByReference"));
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
		for (Package p : packages) {
			if(packages.indexOf(p)==0)
				builder.append(p.packageToJava());
			else
				builder.append(p.importToJava());
		}
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

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
}
