package org.gnome.gir.compiler;

import java.util.ArrayList;
import java.util.List;

import org.gnome.gir.compiler.helper.Resolver;
import org.gnome.gir.generator.GClass;
import org.gnome.gir.generator.GEnum;
import org.gnome.gir.generator.Library;
import org.gnome.gir.repository.BaseInfo;
import org.gnome.gir.repository.BoxedInfo;
import org.gnome.gir.repository.CallbackInfo;
import org.gnome.gir.repository.ConstantInfo;
import org.gnome.gir.repository.EnumInfo;
import org.gnome.gir.repository.FlagsInfo;
import org.gnome.gir.repository.FunctionInfo;
import org.gnome.gir.repository.InterfaceInfo;
import org.gnome.gir.repository.MethodInterface;
import org.gnome.gir.repository.ObjectInfo;
import org.gnome.gir.repository.Repository;
import org.gnome.gir.repository.StructInfo;
import org.gnome.gir.repository.UnionInfo;

public class CodeGenerator {

	public static List<String> compile(Repository repo, String namespace,
			String version) {
		String[] deps = repo.getDependencies(namespace);
		/*for (String string : deps) {
			String names = string.split("-")[0];
			BaseInfo[] infos = repo.getInfos(names);
			String shared = repo.getSharedLibrary(namespace);
			List<String> allInfo = new ArrayList<String>();
			for (BaseInfo baseInfo : infos) {
				allInfo.addAll(getMethods(baseInfo));
			}
			Library l = new Library(namespace, "libvala-0.12.so",
					"GMapper");
			Resolver.writeClassFiles(namespace, GConstants.PACKAGE_PREFIX
					+ GConstants.DOT + namespace.toLowerCase(),
					l.writeLibrary(allInfo));
		*/
		BaseInfo[] infos = repo.getInfos(namespace);
		String shared = repo.getSharedLibrary(namespace);
		List<String> allInfo = new ArrayList<String>();
		for (BaseInfo baseInfo : infos) {
			allInfo.addAll(getMethods(baseInfo));
		}
		Library l = new Library(namespace, "libvala-0.12.so",
				"GMapper");
		Resolver.writeClassFiles(namespace, GConstants.PACKAGE_PREFIX
				+ GConstants.DOT + namespace.toLowerCase(),
				l.writeLibrary(allInfo));
		return allInfo;
	}

	public static List<String> getMethods(BaseInfo baseInfo) {
		List<String> infos = new ArrayList<String>();
		if (baseInfo instanceof EnumInfo) {
			GEnum e = new GEnum((EnumInfo) baseInfo);
			String g = e.writeEnum();
			Resolver.writeClassFiles(e.getName(), GConstants.PACKAGE_PREFIX
					+ GConstants.DOT + baseInfo.getNamespace().toLowerCase(), g);
		} else if (baseInfo instanceof FlagsInfo) {

		} else if (baseInfo instanceof ObjectInfo) {

			GClass c = new GClass((MethodInterface) baseInfo);
			String g = c.writeClass();
			Resolver.writeClassFiles(c.getName(), GConstants.PACKAGE_PREFIX
					+ GConstants.DOT + baseInfo.getNamespace().toLowerCase(), g);
			// System.out.println(g);
			for (FunctionInfo f : ((ObjectInfo) baseInfo).getMethods()) {
				String s = "\t" + f.getNativeToString() + "\n";
				infos.add(s);
			}
			return infos;
		} else if (baseInfo instanceof FunctionInfo) {
			String s = "\t" + baseInfo.getNativeToString() + "\n";
			infos.add(s);
		} else if (baseInfo instanceof StructInfo) {

			if (!baseInfo.getName().endsWith("Class") && !baseInfo.getName().endsWith("Private")) {
				GClass c = new GClass((MethodInterface) baseInfo);
				String g = c.writeClass();
				Resolver.writeClassFiles(c.getName(), GConstants.PACKAGE_PREFIX
						+ GConstants.DOT
						+ baseInfo.getNamespace().toLowerCase(), g);
				for (FunctionInfo f : ((StructInfo) baseInfo).getMethods()) {

					String s = "\t" + f.getNativeToString() + "\n";
					infos.add(s);
				}
			}
			return infos;
		} else if (baseInfo instanceof UnionInfo) {
		} else if (baseInfo instanceof BoxedInfo) {
		} else if (baseInfo instanceof InterfaceInfo) {
			for (FunctionInfo f : ((InterfaceInfo) baseInfo).getMethods()) {
				String s = f.getName() + " ";
				// infos.add(s);
			}
			return infos;
		} else if (baseInfo instanceof CallbackInfo) {
			String s = "\t" + baseInfo.getNativeToString() + "\n";
			infos.add(s);
		} else if (baseInfo instanceof ConstantInfo) {
			// infos.add(((ConstantInfo) baseInfo).getType().toString());
			return infos;
		}
		return infos;
	}
}