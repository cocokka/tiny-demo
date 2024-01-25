package com.tinydemo.democucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DocStringType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoStep {

	private ObjectMapper objectMapper = new ObjectMapper();

	@DocStringType
	public JsonNode json(String content) throws JsonProcessingException {
		return objectMapper.readTree(content);
	}

	record User(String firstname, String lastname) {

	}

	@Given("open the login page")
	public void openPage(JsonNode jsonNode) {
		// Write code here that turns the phrase above into concrete actions
		log.info("open the login page");
		log.info("jsonNode {}", jsonNode);
		log.info("jsonNode object {}", objectMapper.convertValue(jsonNode, User.class));
	}

	@When("i enter valid {string} and {string} in the input field")
	public void enterValidUsernameAndPasswordInInputField(String username, String password) {
		// Write code here that turns the phrase above into concrete actions
		log.info(username);
		log.info(password);
	}

	@Then("i can see login button is active")
	public void loginButtonIsActive() {
		// Write code here that turns the phrase above into concrete actions
		log.info("i can see login button is active");
	}

	@When("i click on login button")
	public void clickOnLoginButton() {
		// Write code here that turns the phrase above into concrete actions
		log.info("i click on login button");
	}

	@Then("I will enter home page")
	public void getHomePage() {
		// Write code here that turns the phrase above into concrete actions
		log.info("I will enter home page");
	}

	@Then("I can't enter home page")
	public void dontGetHomePage() {
		// Write code here that turns the phrase above into concrete actions
		log.info("I can't enter home page");
	}

	@Given("open the {string}")
	public void openChrome(String url) {
		// Write code here that turns the phrase above into concrete actions
		log.info(url);
	}

	@When("input {string}")
	public void inputKeyword(String keyword) {
		// Write code here that turns the phrase above into concrete actions
		log.info(keyword);
	}

	@Then("show {string}")
	public void showSearchedResult(String result) {
		// Write code here that turns the phrase above into concrete actions
		log.info(result);
	}

}
