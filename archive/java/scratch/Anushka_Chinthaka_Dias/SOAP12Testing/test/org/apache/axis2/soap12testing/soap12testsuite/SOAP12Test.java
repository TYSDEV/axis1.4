/*
* Copyright 2001-2004 The Apache Software Foundation.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.apache.axis2.soap12testing.soap12testsuite;

import junit.framework.TestCase;
import org.apache.axis2.soap12testing.server.SimpleServer;
import org.apache.axis2.soap12testing.client.SOAP12TestClient;
import org.apache.axis2.soap12testing.client.MessageComparator;

import java.io.InputStream;

public class SOAP12Test extends TestCase {
    private SimpleServer server;
    private MessageComparator comparator;
    private SOAP12TestClient client;

    public SOAP12Test() {
        server = new SimpleServer();
        server.start();
        comparator = new MessageComparator();
        client = new SOAP12TestClient();
    }

    protected void setUp() {

    }

    public void testWithoutFaults() {
        //System.out.println(client.getReply("SOAP12TestServiceC", "12"));
        assertTrue("SOAP 1.2 Test : - Test No. 1  Failed", comparator.compare("1", client.getRelpy(8008,"SOAP12TestServiceC", "1")));
        assertTrue("SOAP 1.2 Test : - Test No. 2  Failed", comparator.compare("2", client.getRelpy(8008,"SOAP12TestServiceC", "2")));
        assertTrue("SOAP 1.2 Test : - Test No. 3  Failed", comparator.compare("3", client.getRelpy(8008,"SOAP12TestServiceC", "3")));
        assertTrue("SOAP 1.2 Test : - Test No. 4  Failed", comparator.compare("4", client.getRelpy(8008,"SOAP12TestServiceC", "4")));
        assertTrue("SOAP 1.2 Test : - Test No. 5  Failed", comparator.compare("5", client.getRelpy(8008,"SOAP12TestServiceC", "5")));

        //Test No. 6 to 9 : - There are intermediaries

        assertTrue("SOAP 1.2 Test : - Test No. 10  Failed", comparator.compare("10", client.getRelpy(8008,"SOAP12TestServiceC", "10")));
        assertTrue("SOAP 1.2 Test : - Test No. 11  Failed", comparator.compare("11", client.getRelpy(8008,"SOAP12TestServiceC", "11")));

        //Test No. 12 - 14 : - Reply message has fault
        //assertTrue("SOAP 1.2 Test : - Test No. 12  Failed", comparator.compare("12", client.getRelpy(8008,"SOAP12TestServiceC", "12")));
        //assertTrue("SOAP 1.2 Test : - Test No. 13  Failed", comparator.compare("13", client.getRelpy(8008,"SOAP12TestServiceC", "13")));
        //assertTrue("SOAP 1.2 Test : - Test No. 14  Failed", comparator.compare("14", client.getRelpy("SOAP12TestService", "14")));

        assertTrue("SOAP 1.2 Test : - Test No. 15  Failed", comparator.compare("15", client.getRelpy(8008,"SOAP12TestServiceC", "15")));

        //Reply message has fault
        //assertTrue("SOAP 1.2 Test : - Test No. 16  Failed", comparator.compare("16", client.getRelpy("SOAP12TestServiceC", "16")));

        //Request sends to B and reply has a fault
        //assertTrue("SOAP 1.2 Test : - Test No. 17  Failed", comparator.compare("17", client.getRelpy("SOAP12TestServiceC", "17")));
        //There are intermediaries
        //assertTrue("SOAP 1.2 Test : - Test No. 18  Failed", comparator.compare("18", client.getRelpy("SOAP12TestServiceC", "18")));

        assertTrue("SOAP 1.2 Test : - Test No. 19  Failed", comparator.compare("19", client.getRelpy(8008,"SOAP12TestServiceC", "19")));


        //Test No. 20 missing

        //Request sends to B and reply has a fault
        //assertTrue("SOAP 1.2 Test : - Test No. 21  Failed", comparator.compare("21", client.getRelpy("SOAP12TestServiceC", "21")));

        assertTrue("SOAP 1.2 Test : - Test No. 22  Failed", comparator.compare("22", client.getRelpy(8008,"SOAP12TestServiceC", "22")));

        //mustUnderstand value is "wrong" and reply has a fault
        //assertTrue("SOAP 1.2 Test : - Test No. 23  Failed", comparator.compare("23", client.getRelpy("SOAP12TestServiceC", "23")));

        //soap envelope namespace uri incorrect  and reply has a fault
        //assertTrue("SOAP 1.2 Test : - Test No. 24  Failed", comparator.compare("24", client.getRelpy("SOAP12TestServiceC", "24")));

        //Has DTD  and reply has a fault
        //assertTrue("SOAP 1.2 Test : - Test No. 25  Failed", comparator.compare("25", client.getRelpy("SOAP12TestServiceC", "25")));

        //Has style sheet and reply has a fault
        //assertTrue("SOAP 1.2 Test : - Test No. 26  Failed", comparator.compare("26", client.getRelpy("SOAP12TestServiceC", "26")));

        assertTrue("SOAP 1.2 Test : - Test No. 29  Failed", comparator.compare("29", client.getRelpy(8008,"SOAP12TestServiceC", "29")));

        //Test 30 is not here, because Axis 2 is supported both SOAP 1.1 and SOAP 1.2

        assertTrue("SOAP 1.2 Test : - Test No. 31  Failed", comparator.compare("31", client.getRelpy(8008,"SOAP12TestServiceC", "31")));

        //In Test No. 32, headerblock value should be inserted to body
        //assertTrue("SOAP 1.2 Test : - Test No. 32  Failed", comparator.compare("32", client.getRelpy("SOAP12TestServiceC", "32")));

        //Test No. 33, body element has an error
        //assertTrue("SOAP 1.2 Test : - Test No. 33  Failed", comparator.compare("33", client.getRelpy("SOAP12TestServiceC", "33")));

        assertTrue("SOAP 1.2 Test : - Test No. 34  Failed", comparator.compare("34", client.getRelpy(8008,"SOAP12TestServiceC", "34")));

        //Test No. 35 - 36 : - Reply message has fault
        //assertTrue("SOAP 1.2 Test : - Test No. 35  Failed", comparator.compare("35", client.getRelpy("SOAP12TestServiceC", "35")));
        //assertTrue("SOAP 1.2 Test : - Test No. 36  Failed", comparator.compare("36", client.getRelpy("SOAP12TestServiceC", "36")));

        assertTrue("SOAP 1.2 Test : - Test No. 37  Failed", comparator.compare("37", client.getRelpy(8008,"SOAP12TestServiceC", "37")));

        //Test No. 38 has intermediaries

        //Test No. 39, mustUnderstand value is "9"
        //assertTrue("SOAP 1.2 Test : - Test No. 39  Failed", comparator.compare("39", client.getRelpy("SOAP12TestServiceC", "39")));

        assertTrue("SOAP 1.2 Test : - Test No. 40  Failed", comparator.compare("40", client.getRelpy(8008,"SOAP12TestServiceC", "40")));

        //Test No. 62 has intermediaries

        //Test No. 63, headerblock name is validateCountryCode

        //Test No. 64 has <!NOTATION ...>

        //Test No. 65 has <!ELEMENT ..> ..<
        assertTrue("SOAP 1.2 Test : - Test No. 66  Failed", comparator.compare("66", client.getRelpy(8008,"SOAP12TestServiceC", "66")));

        //Test No. 65 has <?xml version='1.0' standalone='yes'?>
        //assertTrue("SOAP 1.2 Test : - Test No. 67  Failed", comparator.compare("67", client.getRelpy("SOAP12TestServiceC", "67")));

        assertTrue("SOAP 1.2 Test : - Test No. 68  Failed", comparator.compare("68", client.getRelpy(8008,"SOAP12TestServiceC", "68")));

        //Test No. 69 sends message without body

        //Test No. 70 sends message with an element after the body element

        //Test No 71 sends message with non namespace qualified attribute in envelope element

        assertTrue("SOAP 1.2 Test : - Test No. 74  Failed", comparator.compare("74", client.getRelpy(8008,"SOAP12TestServiceC", "74")));

        //Test no. 75 sends a message with headerblock echoResolvedRef containing a relative reference
        assertTrue("SOAP 1.2 Test : - Test No. 78  Failed", comparator.compare("78", client.getRelpy(8008,"SOAP12TestServiceC", "78")));

        //Test No. 79 has intermediaries
        //System.out.println(client.getReply("SOAP12TestServiceC", "68"));
    }
}
