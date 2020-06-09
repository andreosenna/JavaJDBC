package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null; // criar objeto nulo

	// criar um objeto do tipo Properties para carregar os requisitos inseridos no
	// dbproperrties.txt
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.propriedades")) {
			Properties propriedades = new Properties();
			propriedades.load(fs);
			return propriedades;

		} catch (IOException e) {
			throw new DBException(e.getMessage());
								}

	}

	/// criando objeto conexão utilizando as propriedades carregadas no
	/// loadProperties
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = (Connection) DriverManager.getConnection(url, props);
			} catch (SQLException e) {

				throw new DBException(e.getMessage());
			}
		}
		return conn;
	}

	// ENCERRAMENTO DE SERVIÇOS

	// Método para Encerrar conexão com o Banco de dados
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	public static void closeStatement(Statement st) {
		try {
			if(st !=null) {
				st.close();	
			}
			
		} catch (SQLException e) {
			
			throw new DBException(e.getMessage());
		}
		
													}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs !=null) {
				rs.close();	
			}
			
		} catch (SQLException e) {
			
			throw new DBException(e.getMessage());// utilizar a DBEXception para lançar uma runtime exception 
		}
		
													}

}
