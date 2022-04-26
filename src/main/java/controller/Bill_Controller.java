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

@Path("/bill")
public class Bill_Controller {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String txt_json)
	{
		bill_management bill =new bill_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("user_id").getAsString()!=""&&data.get("unit_usage").getAsString()!=""&&data.get("date").getAsString()!=""&&data.get("unit_price").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", bill.addBillModel(Integer.parseInt(data.get("user_id").getAsString()),Integer.parseInt(data.get("unit_usage").getAsString()),data.get("date").getAsString(),Double.parseDouble(data.get("unit_price").getAsString())));
			
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

		bill_management bill =new bill_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("id").getAsString()!=""&&data.get("user_id").getAsString()!=""&&data.get("unit_usage").getAsString()!=""&&data.get("date").getAsString()!=""&&data.get("unit_price").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", bill.editBillModel(Integer.parseInt(data.get("id").getAsString()),Integer.parseInt(data.get("user_id").getAsString()),Integer.parseInt(data.get("unit_usage").getAsString()),data.get("date").getAsString(),Double.parseDouble(data.get("unit_price").getAsString())));
			
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

		bill_management bill =new bill_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();
		if(data.get("id").getAsString()!="") {
			
			JSONObject json = new JSONObject();
			json.put("success", 
					bill.deleteBillModel(Integer.parseInt(data.get("id").getAsString())));
	
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
		bill_management bill =new bill_management();
		return bill.getBillModel();
	}
	
}
