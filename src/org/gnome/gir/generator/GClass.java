package org.gnome.gir.generator;

import java.util.ArrayList;
import java.util.List;

import org.gnome.gir.compiler.GConstants;
import org.gnome.gir.repository.FunctionInfo;
import org.gnome.gir.repository.ObjectInfo;

public class GClass {

	protected List<Package> packages = new ArrayList<Package>();
	protected List<Field> fields = new ArrayList<Field>();
	protected List<Method> methods = new ArrayList<Method>();
	protected ObjectInfo objectInfo;

	public GClass(ObjectInfo objectInfo) {
		this.objectInfo = objectInfo;
		initClass();
	}

	private void initClass() {
		for (FunctionInfo f : objectInfo.getMethods()) {
			methods.add(new Method(f));
		}
		packages.add(new Package(GConstants.PACKAGE_PREFIX + GConstants.DOT + objectInfo.getNamespace().toLowerCase()));
		packages.add(new Package("com.sun.jna.Library"));
		packages.add(new Package("com.sun.jna.Native"));
		packages.add(new Package("com.sun.jna.Pointer"));
		packages.add(new Package("com.sun.jna.ptr.PointerByReference"));
		fields.add(new Field("Pointer", "pointer"));
		fields.add(new Field("PointerByReference", "error","null"));
	}

	public String getName(){
		return objectInfo.getName();
	}
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public String writeClass() {
		StringBuilder builder = new StringBuilder();
		for (Package p : packages) {
			if(packages.indexOf(p)==0)
				builder.append(p.packageToJava());
			else
				builder.append(p.importToJava());
		}
		builder.append(writeClassDeclaration());
		for (Field f : fields) {
			builder.append(f.writeToJava());
		}
		for (Method m : methods) {
			builder.append(m.methodToJava());
		}
		builder.append(GConstants.BRACE_CLOSED);
		return builder.toString();
	}

	protected String writeClassDeclaration() {
		return GConstants.PUBLIC + GConstants.SPACE + GConstants.CLASS
				+ GConstants.SPACE + objectInfo.getName() + GConstants.SPACE
				+ GConstants.BRACE_OPEN + GConstants.NEWLINE;
	}
}
