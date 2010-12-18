package org.gnome.gir.compiler;

import gobject.internals.GObjectAPI;
import gobject.runtime.GErrorException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.zip.ZipOutputStream;
import org.gnome.gir.repository.Repository;

public class CodeGeneratorMain {

	static final Logger logger = Logger.getLogger("org.gnome.gir.Compiler");	
	public static void compileAll() throws GErrorException, IOException {
		File[] typelibs = getTypelibDir().listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".typelib");
			}
		});
		for (File typelib : typelibs) {
			String base = typelib.getName();

			NsVer nsver = NsVer.parse(base);
			if (namespaceIsExcluded(nsver.namespace))
				continue;

			compile(nsver.namespace, nsver.version);
		}
	}
	public static File getTypelibDir() {
		return new File(System.getenv("TYPELIBDIR"));
	}
	public static boolean namespaceIsExcluded(String namespace) {
		return namespace.equals("GLib") || namespace.equals("GObject");
	}

	public static void main(String[] args) throws Exception {
		GObjectAPI.gobj.g_type_init();
		if (args.length > 0) {
			if (args[0].equals("--compileall"))
				compileAll();
			else if (args[0].equals("--verifyall"))
				verifyAll();
			else if (args[0].endsWith(".typelib")) {
				File typelib = new File(args[0]);
				String base = typelib.getName();
				NsVer nsver = NsVer.parse(base);
				String parent = typelib.getParent();
				if (parent == null)
					parent = System.getProperty("user.dir");
				Repository.getDefault().prependSearchPath(parent);
				compile(nsver.namespace, nsver.version);
			} else {
				String namespace = args[0];
				String version = args[1];
				if (!namespaceIsExcluded(namespace))
					compile(namespace, version);
			}
		} else {
			usage();
		}
		
	}

	private static void usage() {
		System.out.println("Nothing to do.");
		System.out.println("Possibilities are:");
		System.out.println("\t* --compileall");
		System.out.println("\t* --verifyall");
		System.out.println("\t* some .typelib file");

	}
	public static File compile(String namespace, String version) throws GErrorException, IOException {
		Repository repo = new Repository();
		File destFile = null;

		repo.require(namespace, version);
		String typelibPathName = repo.getTypelibPath(namespace);
		File typelibPath = new File(typelibPathName);
		long typelibLastModified = typelibPath.lastModified();

		if (destFile == null) {
			destFile = getJarPath(repo, namespace);
			if (destFile == null)
				return null;
			logger.info("=== Compiling " + destFile + " ===");
		}

		if (destFile.exists() && destFile.lastModified() > typelibLastModified) {
			logger.info("Skipping already-compiled namespace: " + namespace);
			return destFile;
		}

		logger.info(String.format("Compiling namespace: %s version: %s", namespace, version));
		CodeGenerator.compile(repo, namespace, version);
		Set<String> classNames = new HashSet<String>();
		return destFile;
	}
	public static void verifyAll() throws Exception {
		File[] jars = getTypelibDir().listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar");
			}
		});
		Repository.getDefault().disableRequires();
		//verifyJarFiles(Arrays.asList(jars));
	}

	public static File getJarPath(Repository repo, String namespace) {
		String path = repo.getTypelibPath(namespace);
		if (path == null)
			return null;
		File typelibPath = new File("./lib");
		String version = repo.getNamespaceVersion(namespace);
		return new File(typelibPath, String.format("%s-%s.jar", namespace, version));
	}
	public static final class NsVer {
		public String namespace;
		public String version;

		public static NsVer parse(String base) {
			int dash = base.indexOf('-');
			NsVer nsver = new NsVer();
			nsver.namespace = base.substring(0, dash);
			nsver.version = base.substring(dash + 1, base.lastIndexOf('.'));
			return nsver;
		}
	}
}
