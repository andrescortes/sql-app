package co.com.app;

import co.com.app.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        final String jdbcUrl = "jdbc:h2:~/test;DB_CLOSE_DELAY=-1";
        final String username = "sa";
        final String password = "";
        final String scriptPath = "src/main/resources/schema.sql";
        final String query = "select e.id, e.name, e.email, e.department_id, d.name AS department_name from employees e join departments d on e.department_id = d.id";


        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Statement statement = connection.createStatement();
            boolean execute = statement.execute("RUNSCRIPT FROM '".concat(scriptPath).concat("'"));
            System.out.println("Query result " + execute);
            ResultSet resultSet = statement.executeQuery(query);
            List<EmployeeDto> employeeDtos = new ArrayList<>();
            while (resultSet.next()) {
                EmployeeDto employeeDtoBuilder = EmployeeDto.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .departmentId(resultSet.getInt("department_id"))
                        .departmentName(resultSet.getString("department_name"))
                        .build();
                employeeDtos.add(employeeDtoBuilder);
            }
            employeeDtos.forEach(employee -> {
                System.out.println("Employee: " + employee);
                System.out.println();
            });
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
}