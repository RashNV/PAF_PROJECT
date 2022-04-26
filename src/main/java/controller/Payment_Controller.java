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

@Path("/payment")
public class Payment_Controller {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String txt_json)
	{
		payment_management payment =new payment_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("payment_id").getAsString()!=""&&data.get("card_number").getAsString()!=""&&data.get("card_type").getAsString()!=""&&data.get("amount").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", payment.addPaymentModel(Integer.parseInt(data.get("payment_id").getAsString()),data.get("card_number").getAsString(),data.get("card_type").getAsString(),Double.parseDouble(data.get("amount").getAsString())));
			
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

		payment_management payment =new payment_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("id").getAsString()!=""&&data.get("payment_id").getAsString()!=""&&data.get("card_number").getAsString()!=""&&data.get("card_type").getAsString()!=""&&data.get("amount").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", payment.editPaymentModel(Integer.parseInt(data.get("id").getAsString()),Integer.parseInt(data.get("payment_id").getAsString()),data.get("card_number").getAsString(),data.get("card_type").getAsString(),Double.parseDouble(data.get("amount").getAsString())));
			
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

		payment_management payment =new payment_management();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();
		if(data.get("id").getAsString()!="") {
			
			JSONObject json = new JSONObject();
			json.put("success", 
					payment.deletePaymentModel(Integer.parseInt(data.get("id").getAsString())));
	
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
		payment_management payment =new payment_management();
		return payment.getPaymentModel();
	}
	
}
