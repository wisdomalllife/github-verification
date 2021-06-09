package com.example.githubclient.service;

import com.example.githubclient.model.dbStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<dbStudent> getStudents() {
        return jdbcTemplate.query(
                "SELECT * FROM student",
                (rs, rowNum) ->
                        new dbStudent(
                                rs.getInt("student_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("login")
                        )
        );
    }

    public void deleteStudent(int id){
        String sqlQuery = "DELETE FROM student WHERE student_id=?";
        jdbcTemplate.update(sqlQuery, id);

    }
    public void addStudent(int id, String firstName, String lastName, String login){
        String sql = "INSERT INTO student(student_id, first_name, last_name, login) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, id, firstName, lastName, login);
    }
}