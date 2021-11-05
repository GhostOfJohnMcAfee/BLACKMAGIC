import java.lang.*;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class CryptoEngine 
{
	
	public static String[] englishAlphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	public int alphabetLength = englishAlphabet.length;
	
	public static String[] cipherText = {"S", "E", "C", "R", "E", "T"};
		
	public static String key = "SECRET";
	
	public static ArrayList<String> cipherTextA;
	public static char characterArray[];
	
	private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
	private static final String SALT = "ssshhhhhhhhhhh!!!!";
	
	
	//For 3DES
	static byte[] encryptedMessageBytes;
	static String decryptedMessage;
	static String encodedMessage; 

	public static void main(String[] args) throws Exception
	{
		
		

	}
	
	public static void testAES256(String originalString)
	{
		
		String encryptedString = encryptWithAES256(originalString);
		//encryptedString = encryptWithAES256(encryptedString);

	    String decryptedString = decryptWithAES256(encryptedString);
	 
	    System.out.println("Plaintext: " + originalString);
	    System.out.println("AES-256 Encrypted: " + encryptedString);
	    System.out.println("AES-256 Decrypted: " + decryptedString);
	    
	    System.out.println();
		
	}
	
	public static void test3DES(String originalString)
	{
		
		String encryptedString = encryptWith3DES(originalString);
	    String decryptedString = decryptWith3DES();
	 
	    System.out.println("Plaintext: " + originalString);
	    System.out.println("3DES Encrypted: " + encodedMessage);
	    System.out.println("3DES Decrypted: " + decryptedMessage);
		
	}
	
	//Algorithm to apply a rotation of some value [shiftValue] to the supplied array.
	//IF doEncrypt == true it will encrypt, else it will decrypt.
	public static void rotationalShift(String[] message, int shiftValue, boolean doEncrypt)
	{
		
		if(doEncrypt == true)
		{
			for(int i =0; i < message.length; i++)
			{
					
		
				for(int j = 0; j < englishAlphabet.length; j++)
				{
				
					if(message[i].toString() == englishAlphabet[j].toString())
					{
						
						cipherText[i] = englishAlphabet[(j + shiftValue) % 26];
						break;

					}

				}
			 
			
			}
		}
		else
		{
			int placeHolder;
			
			for(int i =0; i < message.length; i++)
			{
						
			
					for(int j = 0; j < englishAlphabet.length; j++)
					{
					
						if(message[i].toString() == englishAlphabet[j].toString())
						{
							
						
							if(j - shiftValue >= 0)
							{
							
								cipherText[i] = englishAlphabet[(j - shiftValue) % 26];
							
							}
							else
							{
								
								//Assign the placeholder
								placeHolder = j - shiftValue;
								//Take the absolute value of place holder so we can properly shift to decrypt. Then apply the modulus operation.
								cipherText[i] = englishAlphabet[englishAlphabet.length - Math.abs(placeHolder) % 26];
				
							}
							
							break;

						}

					}
				
			}
		}
		
		
	}
	
	//create another set of functions that does multiple iterations of this on the string using a for loop and an int parameter
	public static String encryptWithAES256(String strToEncrypt) {
	    try {
	      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	      IvParameterSpec ivspec = new IvParameterSpec(iv);
	 
	      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
	      SecretKey tmp = factory.generateSecret(spec);
	      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	 
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	      return Base64.getEncoder()
	          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
	    } catch (Exception e) {
	      System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	  }
	
	//AES256 encryption function that allows multiple iterations.
	public static String encryptWithAES256(String strToEncrypt, int iterations) 
	{

		String encryptedString = strToEncrypt;
		
		try
		{
			for(int i = 0; i < iterations; i++)
			{
				
				encryptedString = encryptWithAES256(encryptedString);	
				
			}
			
			return encryptedString;
		
		} catch (Exception e) {
			
	      System.out.println("Error while encrypting: " + e.toString());
	      
	    }
		
		return null;
	
	}
	
	public static String decryptWithAES256(String strToDecrypt) {
	    try {
	      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	      IvParameterSpec ivspec = new IvParameterSpec(iv);
	 
	      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
	      SecretKey tmp = factory.generateSecret(spec);
	      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	 
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	    } catch (Exception e) {
	      System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	  }
	
	//AES256 decryption function that allows multiple iterations.
		public static String decryptWithAES256(String strToDecrypt, int iterations) 
		{

			String decryptedString = strToDecrypt;
			
			try
			{
				for(int i = 0; i < iterations; i++)
				{
					
					decryptedString = decryptWithAES256(decryptedString);	
					
				}
				
				return decryptedString;
			
			} catch (Exception e) {
				
		      System.out.println("Error while encrypting: " + e.toString());
		      
		    }
			
			return null;
		
		}
	
	public static String encryptWith3DES(String strToEncrypt)
	{
		try
		{
			
			byte[] secretKey = "9mng65v8jf4lxn93nabf981m".getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");
			byte[] iv = "a76nb5h9".getBytes();
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			
			String secretMessage = strToEncrypt;
			
			Cipher encryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
			byte[] secretMessagesBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
			encryptedMessageBytes = encryptCipher.doFinal(secretMessagesBytes);
			encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
			return encodedMessage;
			
		} catch (Exception e) {
		      System.out.println("Error while encrypting: " + e.toString());
		    }
		return null;
		
	}
	
	public static String encryptWith3DES(String strToEncrypt, int iterations) 
	{

		String encryptedString = strToEncrypt;
		
		try
		{
			for(int i = 0; i < iterations; i++)
			{
				
				encryptedString = encryptWith3DES(encryptedString);	
				
			}
			
			return encryptedString;
		
		} catch (Exception e) {
			
	      System.out.println("Error while encrypting: " + e.toString());
	      
	    }
		
		return null;
	
	}
	
	
	
	public static String decryptWith3DES()
	{
		try
		{
			byte[] secretKey = "9mng65v8jf4lxn93nabf981m".getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");
			byte[] iv = "a76nb5h9".getBytes();
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			
			Cipher decryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
			byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
			decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
			//Assertions.assertEquals(secretMessage, decryptedMessage);
			return decryptedMessage;
			
		}
		catch (Exception e) {
		      System.out.println("Error while decrypting: " + e.toString());
		    }
		return null;
	}
	
	public static String decryptWith3DES(String strToDecrypt, int iterations) 
	{

		String decryptedString = strToDecrypt;
		
		try
		{
			for(int i = 0; i < iterations; i++)
			{
				
				decryptedString = decryptWith3DES();	
				
			}
			
			return decryptedString;
		
		} catch (Exception e) {
			
	      System.out.println("Error while encrypting: " + e.toString());
	      
	    }
		
		return null;
	
	}
	
	public static byte[] encryptWithRSA (String plainText,PublicKey publicKey ) throws Exception
	{
	    //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
	    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
	        
	    //Initialize Cipher for ENCRYPT_MODE
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	        
	    //Perform Encryption
	    byte[] cipherText = cipher.doFinal(plainText.getBytes()) ;

	    return cipherText;
	}
	    
	public static String decryptWithRSA (byte[] cipherTextArray, PrivateKey privateKey) throws Exception
	{
	    //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
	    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
	        
	    //Initialize Cipher for DECRYPT_MODE
	    cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        
	    //Perform Decryption
	    byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);
	        
	    return new String(decryptedTextArray);
	}
		
	//Method to output ciphertext.
	public static void outputCipherText(String message)
	{
		System.out.println(message);
	}
	
	//Method to output ciphertext.
	public static void outputCipherText(String[] message)
	{
		for(int i =0; i < message.length; i++)
		{
			
			System.out.print(message[i]);
			
			
		}
		
		System.out.println();
		
	}
	
	//Method to clear the cipherText Array.
	public static void clearCipherText()
	{
	
	}

}
