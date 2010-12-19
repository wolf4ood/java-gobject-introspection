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

import org.gnome.gir.compiler.GConstants;
import org.gnome.gir.compiler.helper.Resolver;

public class FunctionInfo extends CallableInfo implements FunctionInfoFlags {
	protected FunctionInfo(Initializer init) {
		super(init);
	}

	public String getSymbol() {
		return Repository.getNativeLibrary().g_function_info_get_symbol(this);
	}

	public int getFlags() {
		return Repository.getNativeLibrary().g_function_info_get_flags(this);
	}

	@Override
	public String getIdentifier() {
		return getSymbol();
	}

	@Override
	public String getNativeToString() {
		String signature = new String();
		String retType = isContructor() ? GConstants.POINTER : Resolver
				.resolveToNative(getReturnType());
		signature += retType + GConstants.SPACE + getIdentifier()
				+ GConstants.ROUND_BRACKET_OPEN;
		String callArgs = new String();
		for (ArgInfo a : getArgs()) {
			callArgs += a.getNativeToString() + GConstants.COMMA;
		}
		String error = isThrowError() ? GConstants.POINTERBR + GConstants.SPACE
				+ GConstants.ERROR + GConstants.COMMA : GConstants.EMPTY;
		callArgs += error;
		int last = callArgs.length() != 0 ? callArgs.length() - 1 : callArgs
				.length();
		callArgs = callArgs.substring(0, last);
		signature += callArgs + GConstants.ROUND_BRACKET_CLOSED
				+ GConstants.DOUBLEDOT;
		return signature;
	}

	public boolean isThrowError() {
		return (THROWS | IS_METHOD) == getFlags();
	}
}