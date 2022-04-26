package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class power_consumption {

	PreparedStatement preStatement;
	
	public String addPowerModel(String employeeID,String name,String districtName,String zipCode,int units,int total) {
		Connection con;
		PreparedStatement preStatement;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("insert into power (employeeID,name,districtName,zipCode,units,total) values (?,?,?,?,?,?)");
			preStatement.setString(1, employeeID);
			preStatement.setString(2, name);
			preStatement.setString(3, districtName);
			preStatement.setString(4, zipCode);
			preStatement.setInt(5, units);
			preStatement.setInt(6, total);
			preStatement.execute();
			preStatement.close();
			con.close();
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
	public String getPowerModel() {
		
		Connection con;
		String data="";
		
		try {
			
			con = Connect.getConnection();
			preStatement = con.prepareStatement("SELECT * FROM power");
			
			ResultSet resultSet = preStatement.executeQuery();
			
			data = "<table style='border: 1px solid black;'>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Employee ID</th>"
	                +"<th style='border: 1px solid black;'>Name</th>"
	                +"<th style='border: 1px solid black;'>District Name</th>"
	                +"<th style='border: 1px solid black;'>Zip Code</th>"
	                +"<th style='border: 1px solid black;'>Units</th>"
	                +"<th style='border: 1px solid black;'>Total</th>"
	                +"<th style='border: 1px solid black;'>Edit/Delete</th>"
	                +"</tr>";
			
			while (resultSet.next()) {
				
				String button = "<button>Delete</button>";
				
				data = data+"<tr><td style='border: 1px solid black;'>"+resultSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(4)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(5)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(6)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(7)+"</td>"
						+ "<td style='border: 1px solid black;'><button>Edit</button><button>Delete</button></td>"
					  + "</tr>";
				
			}
			
			preStatement.close();
			con.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return data+"</table>";
	}

	public String editPowerModel(int id,String employeeID,String name,String districtName,String zipCode,int units,int total) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
				preStatement = con.prepareStatement("UPDATE power SET employeeID=?,name=?,districtName=?,zipCode=?,units=?,total=? where id=?");
				preStatement.setString(1, employeeID);
				preStatement.setString(2, name);
				preStatement.setString(3, districtName);
				preStatement.setString(4, zipCode);
				preStatement.setInt(5, units);
				preStatement.setInt(6, total);
				preStatement.setInt(7,id);
				preStatement.execute();
				preStatement.close();
				con.close();
				return "Successful";
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}

	public String deletePowerModel(int id) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("DELETE FROM power WHERE id=?");
			preStatement.setInt(1, id);
			preStatement.execute();
			
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
}
