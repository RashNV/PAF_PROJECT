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

@Path("/power")
public class Power_Controller {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String txt_json)
	{
		power_consumption power =new power_consumption();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("employeeID").getAsString()!=""&&data.get("name").getAsString()!=""&&data.get("districtName").getAsString()!=""&&data.get("zipCode").getAsString()!=""&&data.get("units").getAsString()!=""&&data.get("unit_price").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", power.addPowerModel(data.get("employeeID").getAsString(),data.get("name").getAsString(),data.get("districtName").getAsString(),data.get("zipCode").getAsString(),Integer.parseInt(data.get("units").getAsString()),Integer.parseInt(data.get("total").getAsString())));
			
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

		power_consumption power =new power_consumption();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("id").getAsString()!=""&&data.get("employeeID").getAsString()!=""&&data.get("name").getAsString()!=""&&data.get("districtName").getAsString()!=""&&data.get("zipCode").getAsString()!=""&&data.get("units").getAsString()!=""&&data.get("unit_price").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", power.editPowerModel(Integer.parseInt(data.get("id").getAsString()),data.get("employeeID").getAsString(),data.get("name").getAsString(),data.get("districtName").getAsString(),data.get("zipCode").getAsString(),Integer.parseInt(data.get("units").getAsString()),Integer.parseInt(data.get("total").getAsString())));
			
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

		power_consumption power =new power_consumption();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();
		if(data.get("id").getAsString()!="") {
			
			JSONObject json = new JSONObject();
			json.put("success", power.deletePowerModel(Integer.parseInt(data.get("id").getAsString())));
	
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
		power_consumption power =new power_consumption();
		return power.getPowerModel();
	}
	
}
