package payroll;

import java.util.*;
import java.sql.*;
import java.time.LocalDate;

public class EmployeePayrollDBService {
    /**
     * this method will connect the database.
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String username = "root";
        String password = "";
        System.out.println("connecting to database");
        Connection connection = DriverManager.getConnection(jdbcURL,username,password);
        System.out.println("Connected Successfully!");
        return connection;
    }

    /**
     * this method will retrieve the data.
     * @return employeePayrollList
     */
    public List<EmpPayRollData> readData() {
        String sql = "SELECT * FROM employee_payroll";
        List<EmpPayRollData> employeePayrollList = new ArrayList<EmpPayRollData>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double basic_pay = resultSet.getDouble("basic_pay");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmpPayRollData(id, name, basic_pay, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }

}
