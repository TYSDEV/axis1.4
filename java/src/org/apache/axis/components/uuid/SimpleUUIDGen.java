/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

/**
 * 
 *  UUIDGen adopted from the juddi project
 *  (http://sourceforge.net/projects/juddi/)
 * 
 */

package org.apache.axis.components.uuid;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Used to create new universally unique identifiers or UUID's (sometimes called
 * GUID's).  UDDI UUID's are allways formmated according to DCE UUID conventions.
 *
 * @author  Maarten Coene
 * @author  Steve Viens
 * @version 0.3.2 3/25/2001
 * @since   JDK1.2.2
 */
public class SimpleUUIDGen implements UUIDGen {
    private static final BigInteger countStart = new BigInteger("-12219292800000");  // 15 October 1582
    private static final int clock_sequence = (new Random()).nextInt(16384);
    private static final byte ZERO = (byte) 48; // "0"
    private static final byte ONE  = (byte) 49; // "1"


    /**
     * utility method which returns a bitString with left zero padding
     * for as many places as necessary to reach <tt>len</tt>; otherwise
     * returns bitString unaltered.
     *
     * @return a left zero padded string of at least <tt>len</tt> chars
     * @param bitString a String to pad
     * @param the length under which bitString needs padding
     */
    private static final String leftZeroPadString(String bitString, int len) {
        if (bitString.length() < len) {
            int nbExtraZeros = len - bitString.length();
            StringBuffer extraZeros = new StringBuffer();
            for (int i = 0; i < nbExtraZeros; i++) {
                extraZeros.append("0");
            }
            extraZeros.append(bitString);
            bitString = extraZeros.toString();
        }
        return bitString;
    }

    /**
     * Creates a new UUID. The algorithm used is described by The Open Group.
     * See <a href="http://www.opengroup.org/onlinepubs/009629399/apdxa.htm">
     * Universal Unique Identifier</a> for more details.
     * <p>
     * Due to a lack of functionality in Java, a part of the UUID is a secure
     * random. This results in a long processing time when this method is called
     * for the first time.
     */
    public String nextUUID() {
        // TODO: this method has to be checked for it's correctness. I'm not sure the standard is
        // implemented correctly.

        // the count of 100-nanosecond intervals since 00:00:00.00 15 October 1582
        BigInteger count;

        // the number of milliseconds since 1 January 1970
        BigInteger current = BigInteger.valueOf(System.currentTimeMillis());

        // the number of milliseconds since 15 October 1582
        BigInteger countMillis = current.subtract(countStart);

        // the result
        count = countMillis.multiply(BigInteger.valueOf(10000));
        byte[] bits = leftZeroPadString(count.toString(2), 60).getBytes();

        // the time_low field
        byte[] time_low = new byte[32];
        for (int i = 0; i < 32; i++)
            time_low[i] = bits[bits.length - i - 1];

        // the time_mid field
        byte[] time_mid = new byte[16];
        for (int i = 0; i < 16; i++)
            time_mid[i] = bits[bits.length - 32 - i - 1];

        // the time_hi_and_version field
        byte[] time_hi_and_version = new byte[16];
        for (int i = 0; i < 12; i++)
            time_hi_and_version[i] = bits[bits.length - 48 - i - 1];

        time_hi_and_version[12] = ONE;
        time_hi_and_version[13] = ZERO;
        time_hi_and_version[14] = ZERO;
        time_hi_and_version[15] = ZERO;

        // the clock_seq_low field
        BigInteger clockSequence = BigInteger.valueOf(clock_sequence);
        byte[] clock_bits = leftZeroPadString(clockSequence.toString(2), 14).getBytes();
        byte[] clock_seq_low = new byte[8];
        for (int i = 0; i < 8; i++) {
            clock_seq_low[i] = clock_bits[clock_bits.length - i - 1];
        }

        // the clock_seq_hi_and_reserved
        byte[] clock_seq_hi_and_reserved = new byte[8];
        for (int i = 0; i < 6; i++)
            clock_seq_hi_and_reserved[i] = clock_bits[clock_bits.length - 8 - i - 1];

        clock_seq_hi_and_reserved[6] = ZERO;
        clock_seq_hi_and_reserved[7] = ONE;

        String timeLow = Long.toHexString((new BigInteger(new String(reverseArray(time_low)), 2)).longValue());
        timeLow = leftZeroPadString(timeLow, 8);

        String timeMid = Long.toHexString((new BigInteger(new String(reverseArray(time_mid)), 2)).longValue());
        timeMid = leftZeroPadString(timeMid, 4);

        String timeHiAndVersion = Long.toHexString((new BigInteger(new String(reverseArray(time_hi_and_version)), 2)).longValue());
        timeHiAndVersion = leftZeroPadString(timeHiAndVersion, 4);

        String clockSeqHiAndReserved = Long.toHexString((new BigInteger(new String(reverseArray(clock_seq_hi_and_reserved)), 2)).longValue());
        clockSeqHiAndReserved = leftZeroPadString(clockSeqHiAndReserved, 2);

        String clockSeqLow = Long.toHexString((new BigInteger(new String(reverseArray(clock_seq_low)), 2)).longValue());
        clockSeqLow = leftZeroPadString(clockSeqLow, 2);

        // problem: the node should be the IEEE 802 ethernet address, but can not
        // be retrieved in Java yet.
        // see bug ID 4173528
        // workaround (also suggested in bug ID 4173528)
        // If a system wants to generate UUIDs but has no IEE 802 compliant
        // network card or other source of IEEE 802 addresses, then this section
        // describes how to generate one.
        // The ideal solution is to obtain a 47 bit cryptographic quality random
        // number, and use it as the low 47 bits of the node ID, with the most
        // significant bit of the first octet of the node ID set to 1. This bit
        // is the unicast/multicast bit, which will never be set in IEEE 802
        // addresses obtained from network cards; hence, there can never be a
        // conflict between UUIDs generated by machines with and without network
        // cards.
        Random secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (Exception e) {
            secureRandom = new Random();
        }

        long nodeValue = secureRandom.nextLong();
        nodeValue = Math.abs(nodeValue);
        while (nodeValue > 140737488355328L) {
            nodeValue = secureRandom.nextLong();
            nodeValue = Math.abs(nodeValue);
        }

        BigInteger nodeInt = BigInteger.valueOf(nodeValue);

        byte[] node_bits = leftZeroPadString(nodeInt.toString(2), 47).getBytes();
        byte[] node = new byte[48];
        for (int i = 0; i < 47; i++)
            node[i] = node_bits[node_bits.length - i - 1];

        node[47] = ONE;
        String theNode = Long.toHexString((new BigInteger(new String(reverseArray(node)), 2)).longValue());
        theNode = leftZeroPadString(theNode, 12);

        StringBuffer result = new StringBuffer(timeLow);
        result.append("-");
        result.append(timeMid);
        result.append("-");
        result.append(timeHiAndVersion);
        result.append("-");
        result.append(clockSeqHiAndReserved);
        result.append(clockSeqLow);
        result.append("-");
        result.append(theNode);
        return result.toString().toUpperCase();
    }

    private static byte[] reverseArray(byte[] bits) {
        byte[] result = new byte[bits.length];
        for (int i = 0; i < result.length; i++)
            result[i] = bits[result.length - 1 - i];

        return result;
    }

    public void destroy() {
    }

    public void init() {
    }
}
