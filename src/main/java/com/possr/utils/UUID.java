package com.possr.utils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class UUID {
    private static final SecureRandom random = new SecureRandom();

    private UUID() {
    }

    public static String generate() {
        byte[] value = randomBytes();
        ByteBuffer buf = ByteBuffer.wrap(value);
        long high = buf.getLong();
        long low = buf.getLong();
        return String.format("%016x-%016x", high, low);
    }

    private static byte[] randomBytes(){
        byte[] value = new byte[16];
        random.nextBytes(value);
        ByteBuffer timestamp = ByteBuffer.allocate(Long.BYTES);
        timestamp.putLong(System.currentTimeMillis());
        System.arraycopy(timestamp.array(), 2, value, 0, 6);
        value[6] = (byte) (value[6] & 0x0f | 0x70);
        value[8] = (byte) (value[8] & 0x3f | 0x80);
        return value;
    }
}
