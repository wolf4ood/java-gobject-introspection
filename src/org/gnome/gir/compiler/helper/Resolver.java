package org.gnome.gir.compiler.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import org.gnome.gir.compiler.GConstants;
import org.gnome.gir.repository.TypeInfo;
import org.gnome.gir.repository.TypeTag;

public class Resolver {

	public static String resolveToNative(TypeInfo info) {
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

	public static void writeClassFiles(String clazz, String pack, String content) {
		String base = GConstants.GENERATION;
		createFolder(base);
		String[] b = pack.replace(".", "/").split("/");
		for (String s : b) {
			base += File.separator + s;
			createFolder(base);
		}
		createFile(base + File.separator + clazz + GConstants.DOT + "java",content);
	}

	public static void createFolder(String name) {
		File f = new File(name);
		if (!f.exists()) {
			f.mkdir();
		}
	}

	public static void createFile(String name,String content) {
		File f = new File(name);
		if (f.exists())
			f.delete();
		try {
			f.createNewFile();
			FileWriter writer = new FileWriter(f);
			writer.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
