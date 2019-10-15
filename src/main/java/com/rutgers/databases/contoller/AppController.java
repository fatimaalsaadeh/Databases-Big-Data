package com.rutgers.databases.contoller;

import com.google.gson.Gson;
import com.rutgers.databases.entity.QueryObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class AppController {
    @Value("${spring.datasource.url}")
    String mysqlUrl;

    @Value("${spring.datasource.username}")
    String mysqlUsername;

    @Value("${spring.datasource.password}")
    String mysqlPassword;

    @Value("${spring.datasource.driver-class-name}")
    String mysqlClassName;

    @Value("${spring.datasource2.url}")
    String redshiftUrl;

    @Value("${spring.datasource2.username}")
    String redshiftUsername;

    @Value("${spring.datasource2.password}")
    String redshiftPassword;

    @Value("${spring.datasource2.driver-class-name}")
    String redshiftClassName;

    QueryObject queryObject;
    Connection con;

    @GetMapping("/home")
    public String homePage(Model model) {

        model.addAttribute("queryObject", new QueryObject());
        return "home.html";
    }

    @RequestMapping("/results/{pageNumber}/{direction}")
    public String results(@PathVariable("pageNumber") Integer pageNumber,
                          @PathVariable("direction") String direction,
                          Model model, @ModelAttribute QueryObject queryObject) {

        try {
            if (queryObject.getisMySQL()) {
                con = getMySQLConnection();
            } else {
                con = getRedShiftConnection();
            }
            this.queryObject = queryObject;
            List<ArrayList<String>> list = getQueryResult(pageNumber, direction, queryObject);
            queryObject.setList(list);
            Gson gson = new Gson();
            queryObject.setResults(gson.toJson(list));
            model.addAttribute("queryObject", queryObject);
        } catch (Exception ex) {
            queryObject.setError(true);

        }

        return "home.html";
    }

    @RequestMapping("/direction/{pageNumber}/{direction}")
    public String direction(@PathVariable("pageNumber") Integer pageNumber,
                            @PathVariable("direction") String direction,
                            Model model) {
        try {
            List<ArrayList<String>> list = getQueryResult(pageNumber, direction, queryObject);
            queryObject.setList(list);
            Gson gson = new Gson();
            queryObject.setResults(gson.toJson(list));
            model.addAttribute("queryObject", queryObject);
        } catch (Exception ex) {
            queryObject.setError(true);

        }
        return "home.html";
    }

    private Connection getMySQLConnection() throws Exception {
        Class.forName(mysqlClassName);

        return DriverManager.getConnection(mysqlUrl, mysqlUsername, mysqlPassword);
    }
    private Connection getRedShiftConnection() throws Exception {
        Class.forName(redshiftClassName);
        Properties props = new Properties();

        props.setProperty("user", redshiftUsername);
        props.setProperty("password", redshiftPassword);

        return DriverManager.getConnection(redshiftUrl, props);
    }


    public List<ArrayList<String>> getQueryResult(Integer pageNumber, String direction, QueryObject queryObject) {

        String query = queryObject.getQuery().replaceAll(";", "") + " LIMIT ";
        if (direction.equals("next")) {
            queryObject.setCurrentPage(pageNumber);
        } else {
            queryObject.setCurrentPage(pageNumber);

        }

        query = getResultsLimit(query, pageNumber);

        try {
            Statement st = con.createStatement();

            List<ArrayList<String>> results = new ArrayList<>();
            ArrayList<String> result;


            long startTime = System.currentTimeMillis();
            ResultSet rs = st.executeQuery(query);

            ResultSetMetaData rsm = rs.getMetaData();
            result = new ArrayList<>();
            for (int y = 1; y <= rsm.getColumnCount(); y++) {
                result.add(rsm.getColumnName(y));
            }
            queryObject.setColumns(result);
            while (rs.next()) {
                result = new ArrayList<>();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {

                    result.add(rs.getString(i));
                }
                results.add(result);
            }

            long endTime = System.currentTimeMillis();

            queryObject.setTimeElapsed(endTime - startTime);

            return results;


        } catch (SQLException e) {
            queryObject.setError(true);
        } catch (Exception e) {
            queryObject.setError(true);
        }
        queryObject.setError(true);
        return null;
    }

    private String getResultsLimit(String query, Integer pageNumber) {
        switch (pageNumber) {
            case 1:
                query += 50;
                break;
            default:
                if (queryObject.getisMySQL()) {
                    query += ((pageNumber - 1) * 50) + ", " + 50;
                } else {
                    query += 50 + " OFFSET " + ((pageNumber - 1) * 50 + 1);
                }
                break;
        }

        return query;

    }

}
