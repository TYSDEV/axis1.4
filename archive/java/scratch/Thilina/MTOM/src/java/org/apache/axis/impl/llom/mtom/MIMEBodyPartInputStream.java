package org.apache.axis.impl.llom.mtom;

import java.io.IOException;
import java.io.InputStream;

/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 */

/**
 * @author Thilina Gunarathne thil747@yahoo.com
 */

public class MIMEBodyPartInputStream extends InputStream {

	MIMEInputStream inStream;

	String boundry;

	boolean done = false;

	boolean began = false;

	public MIMEBodyPartInputStream(MIMEInputStream inStream, String boundry) {
		super();
		this.inStream = inStream;
		this.boundry = boundry;

	}

	public int read() throws IOException {

		if (done) {
			return -1;
		} else {
			int value = 0;
			String line = "";

			value = inStream.read();

			while (value == 13 & !began) {
				value = inStream.read();
				value = inStream.read();
			}
			began = true;

			char readChar = (char) value;
			if (readChar == '-') {
				inStream.mark();
				char readNextChar = ' ';

				readNextChar = (char) inStream.read();

				if (readNextChar == '-') {
					line = inStream.read(boundry.length());
					if (line.equals(boundry)) {
						done = true;
						return -1;

					} else {
						inStream.reset();
					}
				} else {
					inStream.reset();
				}
			}
			return value;
		}
	}
}