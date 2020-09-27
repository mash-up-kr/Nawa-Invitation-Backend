package com.mashup.backend.nawa_invitation_project.common.customUtil;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class CustomUtil {

  static public String getHashCode(Long input) {
    try {
      MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
      sha256Digest.update(input.toString().getBytes());
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

  static public String getBase64Uuid() {
    UUID uuid = UUID.randomUUID();
    ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
    byteBuffer.putLong(uuid.getMostSignificantBits());
    byteBuffer.putLong(uuid.getLeastSignificantBits());
    byte[] uuidBytes = byteBuffer.array();
    return Base64.getUrlEncoder().withoutPadding().encodeToString(uuidBytes);
  }
}
