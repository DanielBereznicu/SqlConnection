package ro.itschool;

import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
//abcde
	}

}
