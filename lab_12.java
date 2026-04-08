import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class lab_12 {

    public static void main(String[] args) {

        String url      = "jdbc:mariadb://localhost:3306/" + args[2]; // uses command line arguments
        String user     = args[0];
        String password = args[1];

        ArrayList<SalesPerson> salesPersonList = new ArrayList<>(); //create array list

        // connects to mariadb and creates a statement to execute SQL queries
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt  = conn.createStatement();

            System.out.println("Connection to MariaDB established successfully!");

            // sql query joining salesman and orders to get each salesman's total sales
            String sql = "SELECT s.name, s.city, s.commission, " +
                         "SUM(o.purchase_amt) AS total_sales " +
                         "FROM salesman s " +
                         "LEFT JOIN orders o ON s.salesman_id = o.salesman_id " +
                         "GROUP BY s.salesman_id, s.name, s.city, s.commission " +
                         "ORDER BY s.name";

            // executes statement and stores results
            ResultSet rs = stmt.executeQuery(sql);

            // loop through each row in result set 
            while (rs.next()) {
                String name       = rs.getString("name");
                String city       = rs.getString("city");
                double commission = rs.getDouble("commission");
                double totalSales = rs.getDouble("total_sales");
                // add each to the list
                salesPersonList.add(new SalesPerson(name, city, commission, totalSales));
            }
            // Question 1 asked for each salesPerson and their total earnings, and question 2 asked for each salesPerson and their total commissions.
            // These two questions seem to ask the saem thing as the sales person is only earning what they make in commsissions.

            // Q1: Names+Total Earnings 
            System.out.println("\n Salesperson Total Earnings");
            System.out.println("Salesperson        | Total Earnings");
            System.out.println("-".repeat(35));
            // stream-based operations using functional interfaces
            salesPersonList.stream().forEach(s -> System.out.printf("%-18s | $%.2f%n", s.name, s.totalSales * s.commission));

            // Q2: Names+Total Commissions 
            System.out.println("\n Salesperson Total Commissions");
            System.out.println("Salesperson        | Total Commission");
            System.out.println("-".repeat(55));
            // stream-based operations using functional interfaces
            salesPersonList.stream().forEach(s -> System.out.printf("%-17s  | $%.2f%n", s.name, s.totalSales * s.commission));

            // test toString()
           //System.out.println("\ntoString() test:");
            //for (SalesPerson sp : salesPersonList) {
            //System.out.println(sp);
            //}

        } catch (SQLException e) {
            System.err.println("Database connection failed:");
            e.printStackTrace();
        }
    }
}

// SalesPerson class
class SalesPerson {
    String name;
    String city;
    double commission;
    double totalSales;

    // constructor
    SalesPerson(String name, String city, double commission, double totalSales) {
        this.name = name;
        this.city = city;
        this.commission = commission;
        this.totalSales = totalSales;
    }

    // neat toString
    public String toString() {
        return String.format("%-18s | %-15s | %-10.2f | $%10.2f", name, city, commission, totalSales);
    }
}