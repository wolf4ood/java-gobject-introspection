package org.gnome.gir.generator;

import org.gnome.gir.compiler.GConstants;
import org.gnome.gir.repository.ArgInfo;
import org.gnome.gir.repository.FunctionInfo;

public class Method {

	protected FunctionInfo functionInfo;

	public Method(FunctionInfo functionInfo) {
		this.functionInfo = functionInfo;
		initMethod();
	}

	private void initMethod() {

	}

	public String methodToJava() {

		if (functionInfo.isContructor())
			return constructorToJava();
		return writeMethod();

	}

	private String writeMethod() {
		String ret = GConstants.VOID.equals(functionInfo.getReturn()) ? GConstants.EMPTY
				: GConstants.RETURN;
		String declaration = functionInfo.getReturn() + GConstants.SPACE
				+ signatureToJava() + GConstants.BRACE_OPEN
				+ GConstants.NEWLINE;
		declaration += GConstants.TAB + ret + GConstants.SPACE
				+ functionInfo.getNamespace() + GConstants.DOT + GConstants.LB
				+ GConstants.DOT + callMethodNative() + GConstants.DOUBLEDOT
				+ GConstants.NEWLINE;
		declaration += GConstants.BRACE_CLOSED + GConstants.NEWLINE;
		return declaration;
	}

	private String callArgs() {
		String callArgs = new String();
		for (ArgInfo a : functionInfo.getArgs()) {
			callArgs += a.getName() + GConstants.COMMA;
		}
		String error = functionInfo.isThrowError() ? GConstants.ERROR
				+ GConstants.COMMA : GConstants.EMPTY;
		callArgs += error;
		int last = callArgs.length() != 0 ? callArgs.length() - 1 : callArgs
				.length();
		return callArgs.substring(0, last);
	}

	private String declareArgs() {
		String callArgs = new String();
		for (ArgInfo a : functionInfo.getArgs()) {
			callArgs += a.getNativeToString() + GConstants.COMMA;
		}
		int last = callArgs.length() != 0 ? callArgs.length() - 1 : callArgs
				.length();
		return callArgs.substring(0, last);
	}

	private String constructorToJava() {
		String conString = GConstants.PUBLIC + GConstants.SPACE
				+ functionInfo.getReturn() + GConstants.ROUND_BRACKET_OPEN
				+ GConstants.ROUND_BRACKET_CLOSED + GConstants.BRACE_OPEN
				+ GConstants.NEWLINE;
		conString += GConstants.TAB + GConstants.POINTER.toLowerCase()
				+ GConstants.EQUALS + functionInfo.getNamespace()
				+ GConstants.DOT + GConstants.LB + GConstants.DOT
				+ callMethodNative() + GConstants.DOUBLEDOT
				+ GConstants.NEWLINE + GConstants.BRACE_CLOSED
				+ GConstants.NEWLINE;
		return conString;
	}

	private String signatureToJava() {
		String sign = functionInfo.getName();
		String[] splitted = sign.split("_");
		String sJava = new String();
		int i = 0;
		for (i = 0; i < splitted.length; i++) {
			if (i != 0) {
				sJava += splitted[i].substring(0, 1).toUpperCase()
						+ splitted[i].substring(1);
			} else {
				sJava += splitted[i];
			}
		}
		return sJava + GConstants.ROUND_BRACKET_OPEN + declareArgs()
				+ GConstants.ROUND_BRACKET_CLOSED;
	}

	private String callMethodNative() {
		return functionInfo.getIdentifier() + GConstants.ROUND_BRACKET_OPEN
				+ callArgs() + GConstants.ROUND_BRACKET_CLOSED;
	}

	public String methodNative() {
		return functionInfo.getIdentifier();
	}
}
