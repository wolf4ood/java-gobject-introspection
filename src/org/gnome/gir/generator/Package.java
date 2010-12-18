package org.gnome.gir.generator;

import org.gnome.gir.compiler.GConstants;

public class Package {

	private String name;
	
	public Package(String name) {
		this.name = name;
	}
	
	public String packageToJava(){
		return GConstants.PACKAGE + GConstants.SPACE + name + GConstants.DOUBLEDOT + GConstants.NEWLINE ;
	}
	public String importToJava() {
		return GConstants.IMPORT + GConstants.SPACE + name + GConstants.DOUBLEDOT + GConstants.NEWLINE ;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Package other = (Package) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
