package org.apache.axis.impl.llom.mtom;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

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

public class MIMEInputStream extends InputStream {

    InputStream inStream;

    LinkedList buffer;

    boolean mark = false;

    boolean reset = false;

    int point = 0;

    public MIMEInputStream(InputStream inStream) {
        super();
        this.inStream = inStream;
        buffer = new LinkedList();
    }

    public int read() throws IOException {
        int temp = readMain();
        return temp;
    }

    public String readLine() throws IOException {
        String line = "";
        char character = ' ';
        do {
            try {
                character = (char) readMain();
                line += character;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (character != '\n');

        return line;
    }

    public String read(int length) throws IOException {
        String line = "";
        char character = ' ';
        for (int i = 0; i < length; i++) {
            character = (char) readMain();
            line += character;
        }
        return line;
    }

    public int readMain() throws IOException {
        int temp;
        if (mark & reset) {
            Integer byteOb = (Integer) buffer.get(point);
            temp = byteOb.intValue();
            if (buffer.get(point).equals(buffer.getLast())) {
                reset = false;
                point = 0;
            } else {
                point++;
            }
        } else if (mark) {
            temp = inStream.read();
            buffer.add(new Integer(temp));
        } else if (reset) {
            if (buffer.isEmpty()) {
                reset = false;
                temp = inStream.read();
            } else {
                Integer byteOb = (Integer) buffer.getFirst();
                temp = byteOb.intValue();
                buffer.removeFirst();
            }
        } else {
            temp = inStream.read();
        }
        return temp;
    }

    public void mark() {
        if (!reset) {
            buffer.clear();
        }
        mark = true;
    }

    public void reset() throws IOException {
        if (mark) {
            reset = true;
            mark = false;
            point = 0;
        } else {
            throw new IOException("not marked");
        }
    }
}