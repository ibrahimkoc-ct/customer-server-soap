package com.ba.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ba.dto.CustomerDTO;
import com.ba.model.Customer;

public class CustomerDAO {
	private String kullanici_adi = "root";
	private String parola = "12345678";
	private String db_ismi = "restaurant";
	private String host = "restaurant";
	private PreparedStatement preparedStatement = null;
	private int port = 3306;
	private Connection con = null;
	private Statement statament = null;

	public CustomerDAO() {
		String url = "jdbc:mysql://localhost:3306/restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		System.out.println(url);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver Bulunamadý.");

		}
		try {
			con = DriverManager.getConnection(url, kullanici_adi, parola);
			System.out.println("Baðlantý Baþarýlý");
		} catch (SQLException ex) {
			printSQLException(ex);
		}
	}

	public void deleteCustomer(int id) {
		String sorgu = "Update Customer set deleted=1 where id =?";
		try {
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			printSQLException(ex);
		}
	}

	public ArrayList getAllCustomer() {
		ArrayList<Customer> cikti = new ArrayList<>();
		try {
			statament = con.createStatement();
			String sorgu = "Select *From Customer where deleted=0";
			ResultSet rs = statament.executeQuery(sorgu);
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				Customer customer = new Customer(id, name, surname, phoneNumber, address);
				cikti.add(customer);
			}
			return cikti;
		} catch (SQLException ex) {
			printSQLException(ex);
			return null;
		}
	}

	public void registerCustomer(Customer customer) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO Customer" + "  (id, name, surname, phoneNumber, address) VALUES "
				+ " (?, ?, ?, ?, ?);";

		// Step 2:Create a statement using connection object
		try {
			preparedStatement = con.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1, customer.getId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getSurname());
			preparedStatement.setString(4, customer.getPhoneNumber());
			preparedStatement.setString(5, customer.getAddress());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}
	public void updateCustomer(Customer customer){
        String sorgu ="Update Customer set name=? ,surname=? , phoneNumber=? ,address =? where id=?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setInt(5, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        	printSQLException(ex);
        }

    }
	

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
