package ro.itschool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public void readDataBase() throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/feedback?" + "user=root&password=");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from comments");
			for (Comment c : getResultSet(resultSet)) {
				System.out.println(c);
			}

		} catch (SQLException e) {
			throw e;

		} finally {
			close();
		}

	}

	private List<Comment> getResultSet(ResultSet resultSet) throws SQLException {
		List<Comment> comments = new ArrayList<>();
		while (resultSet.next()) {
			Comment comment = new Comment();
			comment.setUser(resultSet.getString("myuser"));
			comment.setWebsite(resultSet.getString("webpage"));
			comment.setSummary(resultSet.getString("summary"));
			comment.setDate(resultSet.getDate("datum"));
			comment.setComment(resultSet.getString("comments"));
			comments.add(comment);
		}

		return comments;
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
		}
	}
}
