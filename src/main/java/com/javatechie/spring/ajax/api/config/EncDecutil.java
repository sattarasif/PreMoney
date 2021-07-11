package com.javatechie.spring.ajax.api.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Service;

@Service
public class EncDecutil {

		   private static final String MODE = "RSA/None/OAEPWithSHA256AndMGF1Padding";

		   private static String encryptText(String text, File keyPath) throws Exception {
		      Key publicKey = getRSAPublicFromPemFormat(keyPath);
		      Cipher cipher = Cipher.getInstance("RSA/None/OAEPWithSHA256AndMGF1Padding", "BC");
		      cipher.init(1, publicKey);
		      return new String(Base64.getUrlEncoder().encodeToString(cipher.doFinal(text.getBytes())));
		   }

		   private static String decryptText(String text, File keyPath) throws Exception {
		      Key privateKey = getRSAPrivateFromPemFormat(keyPath);
		      Cipher cipher = Cipher.getInstance("RSA/None/OAEPWithSHA256AndMGF1Padding", "BC");
		      cipher.init(2, privateKey);
		      return new String(cipher.doFinal(Base64.getUrlDecoder().decode(text.getBytes())));
		   }

		   private static PrivateKey getRSAPrivateFromPemFormat(File filename) throws Exception {
		      return (PrivateKey)getKeyFromPEMFile(filename, (data) -> {
		         return new PKCS8EncodedKeySpec(data);
		      }, (kf, spec) -> {
		         try {
		            return kf.generatePrivate(spec);
		         } catch (InvalidKeySpecException var4) {
		            System.out.println("Cannot generate PrivateKey from file: " + filename + var4);
		            return null;
		         }
		      });
		   }

		   private static PublicKey getRSAPublicFromPemFormat(File filename) throws Exception {
		      return (PublicKey)getKeyFromPEMFile(filename, (data) -> {
		         return new X509EncodedKeySpec(data);
		      }, (kf, spec) -> {
		         try {
		            return kf.generatePublic(spec);
		         } catch (InvalidKeySpecException var4) {
		            System.out.println("Cannot generate PublicKey from file: " + filename + var4);
		            return null;
		         }
		      });
		   }

		   private static Key getKeyFromPEMFile(File filename, Function<byte[], EncodedKeySpec> buildSpec, BiFunction<KeyFactory, EncodedKeySpec, ? extends Key> getKey) throws Exception {
		      try {
		         PemReader pemReader = new PemReader(new FileReader(filename));
		         PemObject pemObject = pemReader.readPemObject();
		         pemReader.close();
		         KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
		         return (Key)getKey.apply(kf, (EncodedKeySpec)buildSpec.apply(pemObject.getContent()));
		      } catch (Exception var6) {
		         throw new Exception(var6.getMessage());
		      }
		   }

		   public static String EncryptDecrypt(String text, File keyPath, String operation) throws Exception {
		      String resultText = null;

		      try {
		         Security.addProvider(new BouncyCastleProvider());
		         switch(operation.hashCode()) {
		         case -2034440851:
		            if (operation.equals("DECRYPT")) {
		               resultText = decryptText(text, keyPath);
		            }
		            break;
		         case -889274811:
		            if (operation.equals("ENCRYPT")) {
		               resultText = encryptText(text, keyPath);
		            }
		         }
		      } catch (GeneralSecurityException | IOException var5) {
		         var5.printStackTrace();
		      }

		      return resultText;
		   }
}
