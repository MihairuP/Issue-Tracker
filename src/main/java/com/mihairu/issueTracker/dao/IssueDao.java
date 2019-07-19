package com.mihairu.issueTracker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.annotation.*;
import java.util.List;

@Repository
public class IssueDao {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public IssueDto showIssueId(int id) {
        return jdbcTemplate.query("SELECT * FROM issues WHERE id = ?", new Object[]{id}, (rs) -> {
            rs.next();
            IssueDto issueDto = new IssueDto(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("estimation_time"),
                    rs.getString("spent_time")
            );
            return issueDto;
        });
    }

    public List<IssueDto> showAllIssues() {
        return jdbcTemplate.query("SELECT * FROM issues",
                (rs, rowNum) -> {
                    IssueDto issueDto = new IssueDto(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("estimation_time"),
                            rs.getString("spent_time"));
                    return issueDto;
                }
        );
    }

    public IssueDto makeNewIssue(IssueDto incomingIssueDto) {
        incomingIssueDto.setId(jdbcTemplate.queryForObject("INSERT INTO issues(name, description, estimation_time, spent_time)VALUES(?, ?, ?, ?)RETURNING id;",
                new Object[]{
                        incomingIssueDto.getName(),
                        incomingIssueDto.getdescription(),
                        incomingIssueDto.getEstimationTime(),
                        incomingIssueDto.getSpentTime()},
                Integer.class));
        return incomingIssueDto;
    }

    public IssueDto deleteIssueByID(int id) {
        IssueDto outPutIssue = showIssueId(id);
        jdbcTemplate.update("DELETE FROM issues WHERE id = ?", id);
        return outPutIssue;
    }

    public IssueDto updateIssue(IssueDto incomingIssueDto, int id) {
        jdbcTemplate.update("UPDATE issues SET name = ?, description = ?, estimation_time = ?, spent_time = ? WHERE id = ?;",
        new Object[]{
                incomingIssueDto.getName(),
                incomingIssueDto.getdescription(),
                incomingIssueDto.getEstimationTime(),
                incomingIssueDto.getSpentTime(),
                id
        });
        return showIssueId(id);
    }
}


