package org.sda.authentication.hash;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA256 implements HashFunction {
    @Override
    public String hash(char[] text){
        return DigestUtils.sha256Hex(new String(text));
    }
}
