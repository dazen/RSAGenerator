package com.wangpengpro.rsa.utils;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.tuple.Pair;
import org.spongycastle.openssl.jcajce.JcaPEMWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

/**
 * @author Created by Wangpeng on 2019/5/14 20:34.
 * usage:
 */
public class KeyMaker {
    
    private KeyMaker() {
        super();
    }
    
    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }
    
    /**
     * @return Pair<PrivateKeyPairPemStr , PrivateKeyPemStr>
     */
    public static Pair<String, String> makePemPair() {
        try {
            KeyPairGenerator keyGen = java.security.KeyPairGenerator.getInstance("RSA", "SC");
            keyGen.initialize(2048);
            KeyPair keypair = keyGen.generateKeyPair();
            PrivateKey privateKey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();
            String privateKeyPem = exportKey(privateKey);
            String publicKeyPem = exportKey(publicKey);
            
            return Pair.of(privateKeyPem, publicKeyPem);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new ContextedRuntimeException(e);
        }
    }
    
    /**
     * @return String
     */
    private static String exportKey(Key key) {
        try {
            try (StringWriter fos = new StringWriter()) {
                try (JcaPEMWriter pem = new JcaPEMWriter(fos)) {
                    pem.writeObject(key);
                    pem.flush();
                }
                fos.flush();
                return fos.toString();
            }
        } catch (IOException e) {
            throw new ContextedRuntimeException(e);
        }
    }
    
}
