package demo_day2;
import java.sql.*;
import java.util.ArrayList;
public class ContactModel {
	private static Connection conn = null;

	private final static String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
	private final static String USER = "root";
	private final static String PW = "";

	private ContactModel() {

	}

	// Tao ket noi
	private static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(DB_URL, USER, PW);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}

	//
	public static ArrayList<Contact> all() {
		ArrayList<Contact> list = new ArrayList<Contact>();
		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM contact";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Contact item = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("phone"));
				list.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public static Contact get(int id) {
		Contact item = null;
		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM contact WHERE id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				item = new Contact(id, rs.getString("name"), rs.getString("phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	public static boolean update(Contact item) {

		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = String.format("UPDATE contact SET name='%s', phone='%s' WHERE id ='%d'", item.getName(),
					item.getPhone(), item.getId());
			int rs = stmt.executeUpdate(sql);
			if(rs == 0) return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public static boolean delete(int id) {
		return true;
	}

	public static boolean delete(Contact item) {
		return true;
	}
}
