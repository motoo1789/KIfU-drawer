package com.objetdirect.gwt.umldrawer.server.dao;
 * @author J10-8011
 *
 */
public class Dao extends DriverAccessor{

	private Connection connection;

	}


	public Student getStudent(String studentId) {
		try{

			String sql = "select * from student where student_id = ?";

			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();

		}catch(SQLException e){
			this.closeConnection(this.connection);
			e.printStackTrace();

		} finally {
		}

		return student;
	}
}
