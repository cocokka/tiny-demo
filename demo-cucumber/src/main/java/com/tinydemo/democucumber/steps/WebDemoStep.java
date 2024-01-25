package com.tinydemo.democucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebDemoStep {

//	private ChromeDriver chromeDriver;

	private WebDriver webDriver;

	@Given("open chrome")
	public void openChrome() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		// 自动管理并设置ChromeDriver
		WebDriverManager.chromedriver().setup();
		// 创建一个新的Chrome浏览器实例
		webDriver = new ChromeDriver();
		// 打开一个网页
		webDriver.get("https://www.baidu.com");

		// 手动下载driver
		// System.setProperty("webdriver.chrome.driver",
		// "src/test/resources/chromedriver.exe");
		// chromeDriver = new ChromeDriver();
		// chromeDriver.get("https://www.baidu.com");
		//
		TimeUnit.SECONDS.sleep(1);
	}

	@When("Input {string} in the search box and click on the search button")
	public void inputKeyword(String keyword) {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = webDriver.findElement(new By.ById("kw"));
		element.sendKeys(keyword);
		element.submit();
	}

	@Then("Verify that the result page is displayed with the searched {string}")
	public void showSearchedResult(String content) {
		// Write code here that turns the phrase above into concrete actions
		new WebDriverWait(webDriver, Duration.ofSeconds(3))
			.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[text()='" + content + "']"))));
	}

	@After
	public void close() throws InterruptedException {
		if (webDriver != null) {
			TimeUnit.SECONDS.sleep(3);
			webDriver.quit();
		}
	}

}
