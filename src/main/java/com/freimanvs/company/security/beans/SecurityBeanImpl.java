package com.freimanvs.company.security.beans;

import com.freimanvs.company.security.beans.interfaces.SecurityBean;

import javax.ejb.Stateless;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class SecurityBeanImpl implements SecurityBean {

    @Override
    public String encodeSha(String str) {
        String result = str;

        try {
            MessageDigest m = MessageDigest.getInstance("SHA");
            m.reset();
            m.update(str.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            result = hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }
}
