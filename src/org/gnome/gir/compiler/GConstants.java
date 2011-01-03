package org.gnome.gir.compiler;

public class GConstants {

	public static final String PACKAGE_PREFIX = "org.gnome.gir";
	public static final String GENERATION = "generated";
	public static final String SLASH = "/";
	public static final String LIBRARY_LOAD = ""
			+ "public static final @NAMESPACE lb = (@NAMESPACE) Native.loadLibrary(\"@LIBRARY\", @NAMESPACE.class, new HashMap<String, Object>() {\n "
			+ "\tprivate static final long serialVersionUID = 1L;" + "\n{"
			+ "\tput(Library.OPTION_TYPE_MAPPER, @MAPPER);" + "}\n" + "});\n";
	public static final String TAG_NAMESPACE = "@NAMESPACE";
	public static final String TAG_LIBRARY = "@LIBRARY";
	public static final String TAG_MAPPER = "@MAPPER";
	public static final String LIBRARY = "Library";
	public static final String ROUND_BRACKET_OPEN = "(";
	public static final String ROUND_BRACKET_CLOSED = ")";
	public static final String SQUARE_BRACKET_OPEN = "[";
	public static final String SQUARE_BRACKET_CLOSED = "]";
	public static final String BRACE_OPEN = "{";
	public static final String BRACE_CLOSED = "}";
	public static final String NEWLINE = "\n";
	public static final String TAB = "\t";
	public static final String RETURN = "return";
	public static final String SPACE = " ";
	public static final String DOT = ".";
	public static final String COMMA = ",";
	public static final String DOUBLEDOT = ";";
	public static final String PUBLIC = "public";
	public static final String CLASS = "class";
	public static final String INTERFACE = "interface";
	public static final String ABSTRACT = "abstract";
	public static final String POINTER = "Pointer";
	public static final String POINTERBR = "PointerByReference";
	public static final String ERROR = "error";
	public static final String GERROR = "GerrorStruct";
	public static final String EQUALS = "=";
	public static final String VOID = "void";
	public static final String EMPTY = "";
	public static final String IMPORT = "import";
	public static final String PACKAGE = "package";
	public static final String NEW = "new";
	public static final String EXTENDS = "extends";
	public static final String GOBJECT = "GObject";
	public static final String LB = "lb";
	public static final String ENUM = "enum";
	public static final String THIS = "this";
	public static final String SUPER = "super";
	public static final String PRIVATE = "private";
	public static final String OBJECT = "Object";

}