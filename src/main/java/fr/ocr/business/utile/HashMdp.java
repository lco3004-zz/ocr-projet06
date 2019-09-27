/*
 * **********************************************************
 * Projet 06
 * Utile : méthode de hash du MdP
 *
 * Merci JM.Doudoux
 * ************************************************************
 */


package fr.ocr.business.utile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMdp {
    private String strMdp;

    public String getStrMdp() {
        return strMdp;
    }

    private String bytesToHex(byte[] b) {
        char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        StringBuffer buf = new StringBuffer();
        for (byte value : b) {
            buf.append(hexDigit[(value >> 4) & 0x0f]);
            buf.append(hexDigit[value & 0x0f]);
        }
        return buf.toString();
    }

    public HashMdp(String mdp) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
             strMdp = bytesToHex(sha.digest(mdp.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
