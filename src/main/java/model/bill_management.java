package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class bill_management {

	PreparedStatement preStatement;
	
	public String addBillModel(int user_id,int unit_usage,String date,double unit_price) {
		Connection con;
		PreparedStatement preStatement;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("insert into bill (user_id,unit_usage,date,unit_price,total) values (?,?,?,?,?)");
			preStatement.setInt(1, user_id);
			preStatement.setInt(2, unit_usage);
			preStatement.setString(3, date);
			preStatement.setDouble(4, unit_price);
			double total=0;
			if(unit_usage<90) {
				total=unit_usage*unit_price;
			}else {
				total=90*unit_price+((unit_usage-90)*(unit_price+50));
			}
			preStatement.setDouble(5, total);
			preStatement.execute();
			preStatement.close();
			con.close();
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
	public String getBillModel() {
		
		Connection con;
		String data="";
		
		try {
			
			con = Connect.getConnection();
			preStatement = con.prepareStatement("SELECT * FROM bill");
			
			ResultSet resultSet = preStatement.executeQuery();
			
			data = "<table style='border: 1px solid black;'>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Bill ID</th>"
	                +"<th style='border: 1px solid black;'>Unit Usage</th>"
	                +"<th style='border: 1px solid black;'>Date</th>"
	                +"<th style='border: 1px solid black;'>Unit Price</th>"
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

	public String editBillModel(int id,int user_id,int unit_usage,String date,double unit_price) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
				preStatement = con.prepareStatement("UPDATE bill SET user_id=?,unit_usage=?,date=?,unit_price=?,total=? where id=?");
				preStatement.setInt(1, user_id);
				preStatement.setInt(2, unit_usage);
				preStatement.setString(3, date);
				preStatement.setDouble(4, unit_price);
				double total=0;
				if(unit_usage<90) {
					total=unit_usage*unit_price;
				}else {
					total=90*unit_price+((unit_usage-90)*(unit_price+50));
				}
				preStatement.setDouble(5, total);
				preStatement.setInt(6,id);
				preStatement.execute();
				preStatement.close();
				con.close();
				return "Successful";
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}

	public String deleteBillModel(int id) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("DELETE FROM bill WHERE id=?");
			preStatement.setInt(1, id);
			preStatement.execute();
			
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
}
