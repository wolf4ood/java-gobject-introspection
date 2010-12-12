package org.gnome.gir.couchdb;

import gobject.internals.GTypeMapper;

import java.util.HashMap;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface Couchdb extends Library {


	public static final Couchdb db = (Couchdb) Native.loadLibrary("couchdb-glib-1.0", Couchdb.class, new HashMap<String, Object>() {
		private static final long serialVersionUID = 1L;
		{
			put(Library.OPTION_TYPE_MAPPER, new GTypeMapper());
		}
	});
	void g_type_init();
	void g_thread_init();
	Pointer couchdb_session_new(String uri);
	Boolean couchdb_session_create_database(Pointer p,String name,PointerByReference error);
	Boolean couchdb_session_delete_database(Pointer p,String name,PointerByReference error);
	
	/*public static  void init() {
		Function f = library.getFunction("g_type_init");		
		library.getFunction("g_thread_init");
		f.invoke(new Object[0]);
	}*/
}
