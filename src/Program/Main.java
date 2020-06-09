package Program;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Main {

	public static void main(String[] args) throws SQLException {

			Connection conn = DB.getConnection();
			
			Statement st = null;;
			ResultSet rs = null;

			st = conn.createStatement();
			rs = st.executeQuery("Select * from department");
			while(rs.next()) {
			System.out.println(rs.getInt("Id")+ "  "+ rs.getString("Name"));
			
			}
			
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
			
	}

	
}
