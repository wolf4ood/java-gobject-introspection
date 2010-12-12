package org.gnome.gir.compiler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gnome.gir.repository.ArgInfo;
import org.gnome.gir.repository.BaseInfo;
import org.gnome.gir.repository.BoxedInfo;
import org.gnome.gir.repository.CallbackInfo;
import org.gnome.gir.repository.ConstantInfo;
import org.gnome.gir.repository.EnumInfo;
import org.gnome.gir.repository.FlagsInfo;
import org.gnome.gir.repository.FunctionInfo;
import org.gnome.gir.repository.InterfaceInfo;
import org.gnome.gir.repository.ObjectInfo;
import org.gnome.gir.repository.Repository;
import org.gnome.gir.repository.StructInfo;
import org.gnome.gir.repository.UnionInfo;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class CodeGenerator {
	
	public static List<String> compile(Repository repo, String namespace, String version) {
		BaseInfo[] infos = repo.getInfos(namespace);
		for (BaseInfo baseInfo : infos) {
			System.out.println(baseInfo.getName() + " ->" + getMethods(baseInfo));
		}
		return null;
	}
	public static String getMethods(BaseInfo baseInfo) {
		if (baseInfo instanceof EnumInfo) {
		} else if (baseInfo instanceof FlagsInfo) {
		} else if (baseInfo instanceof ObjectInfo) {
			String s = "";
			for (FunctionInfo f : ((ObjectInfo) baseInfo).getMethods()) {
				s += "\t" + f.getNativeToString() + "\n";
			}
			return s;
		} else if (baseInfo instanceof FunctionInfo) {
		} else if (baseInfo instanceof StructInfo) {
			String s = "";
			for (FunctionInfo f : ((StructInfo) baseInfo).getMethods()) {
				s += f.getName() + " id:" + f.getIdentifier() + ",";

			}
			return s;
		} else if (baseInfo instanceof UnionInfo) {
		} else if (baseInfo instanceof BoxedInfo) {
		} else if (baseInfo instanceof InterfaceInfo) {
			String s = "";
			for (FunctionInfo f : ((InterfaceInfo) baseInfo).getMethods()) {
				s += f.getName() + " ";
			}
			return s;
		} else if (baseInfo instanceof CallbackInfo) {
		} else if (baseInfo instanceof ConstantInfo) {
			return ((ConstantInfo) baseInfo).getType().toString();
		}
		return "";

	}
}