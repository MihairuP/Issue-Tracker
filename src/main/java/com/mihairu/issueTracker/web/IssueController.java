package com.mihairu.issueTracker.web;

import java.util.Arrays;
import java.util.List;

import com.mihairu.issueTracker.dao.IssueDao;
import com.mihairu.issueTracker.dao.IssueDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
public class IssueController {

	private final Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Autowired
	private IssueDao issueDao;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain")
	public String index() {
		logger.debug("index() is executed!");
		return "Index directory \n go to \n " +
				"http://localhost:8080/Issue-tracker/issue\n " +
				"http://localhost:8080/Issue-tracker/issue/id\n " +
				"http://localhost:8080/Issue-tracker/delissue/id";
	}
	@RequestMapping(value = "/issue", method = RequestMethod.GET, produces = "application/json")
	public List<IssueDto> getIssueList() {
		logger.debug("GETTING ISSSUE LIST");
		return issueDao.showAllIssues();
	}
	@RequestMapping(value = "/issue/{id}", method = RequestMethod.GET, produces = "application/json")
	public IssueDto getIssueById(@PathVariable("id") int id) {
		logger.debug("ISSUE ID");
		return issueDao.showIssueId(id);
	}
	@RequestMapping(value = "/issue", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public IssueDto newIssue(@Valid @RequestBody IssueDto newIssue) {
		logger.debug("POST NEW ISSUE");
		return issueDao.makeNewIssue(newIssue);
	}
	@RequestMapping(value = "/delissue/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public IssueDto delIssue(@PathVariable("id") int id) {
		logger.debug("DELETE ISSUE by id "+ id);
		IssueDto issueDto = issueDao.deleteIssueByID(id);
		return issueDto;
	}
	@RequestMapping(value = "/updateissue/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public IssueDto updateIssue(@RequestBody IssueDto updateIssue, @PathVariable("id") int id) {
		logger.debug("update ISSUE");
		return issueDao.updateIssue(updateIssue, id);
	}



}