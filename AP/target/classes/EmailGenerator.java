package main.resources;
import java.util.UUID;

public class EmailGenerator {
	
	public static String GenerateEmeail(){
	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	String email = uuid + "@" + uuid + ".com";
	return email;
	}
}
