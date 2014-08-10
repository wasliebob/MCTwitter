package mctwitter.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import mctwitter.main.MCTwitter;

public class FileHelper{
	public static String getSlash(){
		if(System.getProperty("os.name") != null){
			String os = System.getProperty("os.name");
			if(os.startsWith("Windows")){
				return "\\";
			}else if(os.startsWith("windows")){
				return "\\";
			}else{
				return "/";
			}
		}else{
			return "\\";
		}
	}
	
	public static void createPinFile(String pin){
		String fileName = "twitterPin";
		String extension = "pin";
		
		File file;
		try {
			file = new File(MCTwitter.configLocation + getSlash() + fileName + "." + extension);
			file.createNewFile();
			PrintWriter writer = new PrintWriter(MCTwitter.configLocation + getSlash() + fileName + "." + extension, "UTF-8");
			
			writer.println("PIN:" + pin);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean pinFileExists(){
		String fileName = "twitterPin";
		String extension = "pin";
		return new File(MCTwitter.configLocation + getSlash() + fileName + "." + extension).exists();
	}
	
	public static String getPin(){
		String fileName = "twitterPin";
		String extension = "pin";
		
		File file;
		try {
			file = new File(MCTwitter.configLocation + getSlash() + fileName + "." + extension);

			FileReader reader = new FileReader(file);
			
			Scanner scanner = new Scanner(reader);
			
			String[] full = scanner.nextLine().split(":");
			String key  = full[0];
			String value = full[1];
			
			while(value != null){
				scanner.close();
				return value;
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void createTokenFile(String token, String tokenSecret){
		String fileName = "twitterTokens";
		String extension = "tokens";
		
		File file;
		try {
			file = new File(MCTwitter.configLocation + getSlash() + fileName + "." + extension);
			file.createNewFile();
			PrintWriter writer = new PrintWriter(MCTwitter.configLocation + getSlash() + fileName + "." + extension, "UTF-8");
			
			writer.println("TokenAccess:" + token);
			writer.println("TokenSecret:" + tokenSecret);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean tokenFileExists(){
		String fileName = "twitterTokens";
		String extension = "tokens";
		return new File(MCTwitter.configLocation + getSlash() + fileName + "." + extension).exists();
	}
	
	public static String getToken(){
		String fileName = "twitterTokens";
		String extension = "tokens";
		
		File file;
		try {
			file = new File(MCTwitter.configLocation + getSlash() + fileName + "." + extension);

			FileReader reader = new FileReader(file);
			
			Scanner scanner = new Scanner(reader);
			while(scanner.hasNextLine()){
				String[] full = scanner.nextLine().split(":");
				String key  = full[0];
				String value = full[1];
				
				while(value != null && key.contains("TokenAccess")){
					scanner.close();
					return value;
				}
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getTokenSecret(){
		String fileName = "twitterTokens";
		String extension = "tokens";
		
		File file;
		try {
			file = new File(MCTwitter.configLocation + getSlash() + fileName + "." + extension);

			FileReader reader = new FileReader(file);
			
			Scanner scanner = new Scanner(reader);
			while(scanner.hasNextLine()){
				String[] full = scanner.nextLine().split(":");
				String key  = full[0];
				String value = full[1];
				
				while(value != null && key.contains("TokenSecret")){
					scanner.close();
 					return value;
				}
			}
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}