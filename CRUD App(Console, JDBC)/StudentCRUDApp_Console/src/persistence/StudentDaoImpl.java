package persistence;

import java.io.IOException;
import java.sql.*;
import dto.Student;
import jdbcutil.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {

    private Connection connection;
    private PreparedStatement pstmt;

    @Override
    public String addStudent(String sname, Integer sage, String saddress) {
        String sqlQuery = "insert into studentdb (name,age,address) values (?,?,?)";
        try {
            connection = JdbcUtil.getJdbcConnection();
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlQuery);
            }
            if (pstmt != null) {
                pstmt.setString(1, sname);
                pstmt.setInt(2, sage);
                pstmt.setString(3, saddress);
                int rowAffected = pstmt.executeUpdate();
                if (rowAffected == 1) {
                    return "Success! Record Inserted Successfully ";
                }
            }
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Failure! Record Not Inserted";
    }

    @Override
    public Student searchStudent(Integer sid) {
        Student std;
        String sqlQuery = "select id,name,age,address from studentdb where id=?";
        try {
            connection = JdbcUtil.getJdbcConnection();
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlQuery);
                pstmt.setInt(1, sid);
            }
            if (pstmt != null) {
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    std = new Student();
                    std.setSid(rs.getInt(1));
                    std.setSname(rs.getString(2));
                    std.setSage(rs.getInt(3));
                    std.setSaddress(rs.getString(4));
                    return std;
                }
            }
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student updateStudent(Student studentIn) {

        Student studentOut;
        String sqlQuery1 = "update studentdb set name=?,age=?, address=? where id = ?";
        String sqlQuery2 = "select id,name,age,address from studentdb where id=?";

        try {
            connection = JdbcUtil.getJdbcConnection();
            if (connection != null) {

                pstmt = connection.prepareStatement(sqlQuery1);
                pstmt.setInt(4, studentIn.getSid());
                pstmt.setString(1, studentIn.getSname());
                pstmt.setInt(2, studentIn.getSage());
                pstmt.setString(3, studentIn.getSaddress());
                pstmt.execute();

            }
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlQuery2);
                pstmt.setInt(1, studentIn.getSid());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    studentOut = new Student();
                    studentOut.setSid(rs.getInt(1));
                    studentOut.setSname(rs.getString(2));
                    studentOut.setSage(rs.getInt(3));
                    studentOut.setSaddress(rs.getString(4));
                    return studentOut;
                }
            }
            return null;
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteStudent(Integer sid) {
        String sqlQuery = "delete from studentdb where id=?";
        try {
            connection = JdbcUtil.getJdbcConnection();
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlQuery);
            }
            if (pstmt != null) {
                
                pstmt.setInt(1, sid);

                int rowAffected = pstmt.executeUpdate();
                if (rowAffected == 1) {
                    return "Success! Record Deleted Successfully";
                }
            }
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Record not found for id: "+sid;
    }

}
