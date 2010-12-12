package org.gnome.gir.couchdb;
import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class Session {

	Pointer p;
	PointerByReference error;
	
	public Session(String uri) {
		p = Couchdb.db.couchdb_session_new(uri);
	}
	public Boolean createDatabase(String database) {
		return Couchdb.db.couchdb_session_create_database(p,database,error);
	}
	public Boolean deleteDatabase(String database){
		return Couchdb.db.couchdb_session_delete_database(p, database, error);
	}
}
