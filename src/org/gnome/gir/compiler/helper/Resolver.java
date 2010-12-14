package org.gnome.gir.compiler.helper;

import org.gnome.gir.repository.InfoType;
import org.gnome.gir.repository.TypeInfo;
import org.gnome.gir.repository.TypeTag;

public class Resolver {

	public static String resolveToNative(TypeInfo info){
		TypeTag tag = info.getTag();
		String ret = new String();
		switch (tag) {
		case INTERFACE:
			ret = info.getInterface().getName();
			break;

		case SSIZE:
			ret = info.getInterface().getName();
			break;
		
		default:
			ret = tag.toString();
			break;
		}
		return ret;
	}
}
