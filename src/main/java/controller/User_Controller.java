package controller;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import org.json.simple.*;

import model.*;

import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/user_model")
public class User_Controller {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String txt_json)
	{
		user_management user_model =new user_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("name").getAsString()!=""&&data.get("nic").getAsString()!=""&&data.get("address").getAsString()!=""&&data.get("phone").getAsString()!=""&&data.get("email").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", user_model.addUserModel(data.get("name").getAsString(),data.get("nic").getAsString(),data.get("address").getAsString(),data.get("phone").getAsString(),data.get("email").getAsString()));
			
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "validation_error");
			
			return json.toString();
			
		}
			
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String txt_json)
	{

		user_management user_model =new user_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("id").getAsString()!=""&&data.get("name").getAsString()!=""&&data.get("nic").getAsString()!=""&&data.get("address").getAsString()!=""&&data.get("phone").getAsString()!=""&&data.get("email").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", user_model.editUserModel(Integer.parseInt(data.get("id").getAsString()),data.get("name").getAsString(),data.get("nic").getAsString(),data.get("address").getAsString(),data.get("phone").getAsString(),data.get("email").getAsString()));
			
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "validation_error");
			
			return json.toString();
			
		}
			
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String txt_json)
	{

		user_management user_model =new user_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();
		if(data.get("id").getAsString()!="") {
			
			JSONObject json = new JSONObject();
			json.put("success", user_model.deleteUserModel(Integer.parseInt(data.get("id").getAsString())));
	
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "validation_error");
			
			return json.toString();
			
		}
		
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String txt_json)
	{
		user_management user_model =new user_management();
		return user_model.getUserModel();
	}
	
}
