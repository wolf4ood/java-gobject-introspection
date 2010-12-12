package org.gnome.gir.couchdb;

public class TestCouch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Couchdb.db.g_type_init();
		Session session = new Session("http://localhost:5984");
		Boolean r = session.createDatabase("bunga");
		r = session.deleteDatabase("bunga");
	}

}
