package com.mashup.backend.nawa_invitation_project.common.customUtil;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomUtil {

  static public String getHashCode(Long input) {
    try {
      byte[] bytes = ByteBuffer.allocate(4).putInt(1695609641).array();
      MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
      sha256Digest.update(bytes);
      byte[] sha256Hash = sha256Digest.digest();
      StringBuilder hexSHA256hash = new StringBuilder();
      for (byte b : sha256Hash) {
        String hexString = String.format("%02x", b);
        hexSHA256hash.append(hexString);
      }
      return hexSHA256hash.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
}
