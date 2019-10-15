package com.rutgers.databases.entity;

import java.util.ArrayList;
import java.util.List;

public class QueryObject {
    private String query;
    private String results;
    private Boolean error;
    private Boolean isMySQL;
    private ArrayList<String> databaseType;
    private List<ArrayList<String>> list;
    private ArrayList<String> columns;
    private Integer totalPages;
    private Integer currentPage;
    private long timeElapsed;

    public QueryObject() {
        databaseType = new ArrayList<>();
        databaseType.add("MySql");
        databaseType.add("RedShift");
        currentPage=1;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public List<ArrayList<String>> getList() {
        return list;
    }

    public void setList(List<ArrayList<String>> list) {
        this.list = list;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
    }

    public ArrayList<String> getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(ArrayList<String> databaseType) {
        this.databaseType = databaseType;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public Boolean getisMySQL() {
        return isMySQL;
    }

    public void setisMySQL(Boolean mySQL) {
        isMySQL = mySQL;
    }
}
