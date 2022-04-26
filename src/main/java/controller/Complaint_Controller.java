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

@Path("/com")
public class Complaint_Controller {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String txt_json)
	{
		complaint_management com =new complaint_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("user_id").getAsString()!=""&&data.get("type").getAsString()!=""&&data.get("complaint").getAsString()!=""&&data.get("date").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", com.addComplaintModel(Integer.parseInt(data.get("user_id").getAsString()),data.get("type").getAsString(),data.get("complaint").getAsString(),data.get("date").getAsString()));
			
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

		complaint_management com =new complaint_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("id").getAsString()!=""&&data.get("user_id").getAsString()!=""&&data.get("type").getAsString()!=""&&data.get("complaint").getAsString()!=""&&data.get("date").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", com.editComplaintModel(Integer.parseInt(data.get("id").getAsString()),Integer.parseInt(data.get("user_id").getAsString()),data.get("type").getAsString(),data.get("complaint").getAsString(),data.get("date").getAsString()));
			
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

		complaint_management com =new complaint_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();
		if(data.get("id").getAsString()!="") {
			
			JSONObject json = new JSONObject();
			json.put("success", 
					com.deleteComplaintModel(Integer.parseInt(data.get("id").getAsString())));
	
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
		complaint_management com =new complaint_management();
		return com.getComplaintModel();
	}
	
}
