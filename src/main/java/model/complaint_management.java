package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class complaint_management {

	PreparedStatement preStatement;
	
	public String addComplaintModel(int user_id,String type,String complaint,String date) {
		Connection con;
		PreparedStatement preStatement;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("insert into complaint (user_id,type,complaint,date) values (?,?,?,?)");
			preStatement.setInt(1, user_id);
			preStatement.setString(2, type);
			preStatement.setString(3, complaint);
			preStatement.setString(4, date);
			preStatement.execute();
			preStatement.close();
			con.close();
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
	public String getComplaintModel() {
		
		Connection con;
		String data="";
		
		try {
			
			con = Connect.getConnection();
			preStatement = con.prepareStatement("SELECT * FROM complaint");
			
			ResultSet resultSet = preStatement.executeQuery();
			
			data = "<table style='border: 1px solid black;'>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Complaint ID</th>"
	                +"<th style='border: 1px solid black;'>Type</th>"
	                +"<th style='border: 1px solid black;'>Complaint</th>"
	                +"<th style='border: 1px solid black;'>Date</th>"
	                +"<th style='border: 1px solid black;'>Edit/Delete</th>"
	                +"</tr>";
			
			while (resultSet.next()) {
				
				String button = "<button>Delete</button>";
				
				data = data+"<tr><td style='border: 1px solid black;'>"+resultSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(4)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(5)+"</td>"
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

	public String editComplaintModel(int id,int user_id,String type,String complaint,String date) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
				preStatement = con.prepareStatement("UPDATE complaint SET name=?,nic=?,address=?,phone=?,email=? where id=?");
				preStatement.setInt(1, user_id);
				preStatement.setString(2, type);
				preStatement.setString(3, complaint);
				preStatement.setString(4, date);
				preStatement.setInt(5,id);
				preStatement.execute();
				preStatement.close();
				con.close();
				return "Successful";
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}

	public String deleteComplaintModel(int id) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("DELETE FROM complaint WHERE id=?");
			preStatement.setInt(1, id);
			preStatement.execute();
			
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
}
