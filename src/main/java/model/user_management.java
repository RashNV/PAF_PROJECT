package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class user_management {

	PreparedStatement preStatement;
	
	public String addUserModel(String name,String nic,String address,String phone,String email) {
		Connection con;
		PreparedStatement preStatement;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("insert into users (name,nic,address,phone,email) values (?,?,?,?,?)");
			preStatement.setString(1, name);
			preStatement.setString(2, nic);
			preStatement.setString(3, address);
			preStatement.setString(4, phone);
			preStatement.setString(5, email);
			preStatement.execute();
			preStatement.close();
			con.close();
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
	public String getUserModel() {
		
		Connection con;
		String data="";
		
		try {
			
			con = Connect.getConnection();
			preStatement = con.prepareStatement("SELECT * FROM users");
			
			ResultSet resultSet = preStatement.executeQuery();
			
			data = "<table style='border: 1px solid black;'>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Name</th>"
	                +"<th style='border: 1px solid black;'>NIC</th>"
	                +"<th style='border: 1px solid black;'>Address</th>"
	                +"<th style='border: 1px solid black;'>Phone</th>"
	                +"<th style='border: 1px solid black;'>Email</th>"
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

	public String editUserModel(int id,String name,String nic,String address,String phone,String email) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
				preStatement = con.prepareStatement("UPDATE users SET name=?,nic=?,address=?,phone=?,email=? where id=?");
				preStatement.setString(1, name);
				preStatement.setString(2, nic);
				preStatement.setString(3, address);
				preStatement.setString(4, phone);
				preStatement.setString(5, email);
				preStatement.setInt(6,id);
				preStatement.execute();
				preStatement.close();
				con.close();
				return "Successful";
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}

	public String deleteUserModel(int id) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("DELETE FROM users WHERE id=?");
			preStatement.setInt(1, id);
			preStatement.execute();
			
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
}
