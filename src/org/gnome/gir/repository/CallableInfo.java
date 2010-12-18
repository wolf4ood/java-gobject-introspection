
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

package org.gnome.gir.repository;
import org.gnome.gir.compiler.helper.Resolver;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class CallableInfo extends BaseInfo {
	protected CallableInfo(Initializer init) {
		super(init);
	}	
	public TypeInfo getReturnType() {
		return Repository.getNativeLibrary().g_callable_info_get_return_type(this);
	}
	public Transfer getCallerOwns() {
		return Repository.getNativeLibrary().g_callable_info_get_caller_owns(this);
	}
	public boolean getMayReturnNull() {
		return Repository.getNativeLibrary().g_callable_info_get_may_return_null(this);
	}
	public int getNArgs() {
		return Repository.getNativeLibrary().g_callable_info_get_n_args(this);
	}
	public ArgInfo getArg(int n) {
		return Repository.getNativeLibrary().g_callable_info_get_arg(this, n);
	}
	
	public ArgInfo[] getArgs() {
		int n = getNArgs();
		ArgInfo[] ret = new ArgInfo[n];
		for (int i = 0; i < n; i++)
			ret[i] = getArg(i);
		return ret;
	}
	
	public String getIdentifier() {
		return getNamespace() + '/' + getName();
	}
	
	public Boolean isContructor(){
		return getName().startsWith("new");
	}
	public String getReturn(){
		return Resolver.resolveToNative(getReturnType()) ;
	}
	@Override
	public String getNativeToString() {
		String signature = new String() ;
		String  retType = getName().equals("new") ? "Pointer" : Resolver.resolveToNative(getReturnType())  ;
		signature += retType  + " " + getIdentifier() + "(";
		String args = new String();
		int i=1;
		for(ArgInfo a : getArgs()){
			String sep = getArgs().length != i ? "," : "";
			args += a.getNativeToString() + sep;
			i++;
		}
		signature += args + ");";
		return signature;
	}
}