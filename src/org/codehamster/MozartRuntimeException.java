/*
 * 
 *  Mozart Digital Composer Version 0.1
 *  Copyright (C) 2017 Manny Peterson <me@mannypeterson.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 */
package org.codehamster;

public class MozartRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 2326066682067720903L;

	public MozartRuntimeException() {
		return;
	}

	public MozartRuntimeException(String message) {
		super(message);
		return;
	}

	public MozartRuntimeException(String message, Throwable cause) {
		super(message, cause);
		return;
	}

	public MozartRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		return;
	}

	public MozartRuntimeException(Throwable cause) {
		super(cause);
		return;
	}
}
