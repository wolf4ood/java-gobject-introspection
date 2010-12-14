/* 
 * Copyright (c) 2008 Colin Walters <walters@verbum.org>
 * 
 * This file is part of java-gobject-introspection.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330, 
 * Boston, MA  02111-1307  USA.
 *
 */
/**
 * 
 */
package org.gnome.gir.repository;

public enum TypeTag {

	VOID("void"), BOOLEAN("Boolean"), INT8("Byte"), UINT8("Character"), INT16("Short"), UINT16(
			"Short"), INT32("Integer"), UINT32("Integer"), INT64("Integer"), UINT64("Integer"), SHORT("Short"), USHORT("Short"), INT("Integer"), UINT("Long"), LONG("Long"), ULONG(
			"Long"), SSIZE(""), SIZE(""), FLOAT("Float"), DOUBLE("Double"), TIMET(
			""), GTYPE("GType"), UTF8("String"), FILENAME("String"), ARRAY(""), INTERFACE(
			"Iface"), GLIST(""), GSLIST(""), GHASH(""), ERROR("GErrorStruct");
	private String name;
	TypeTag(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
}