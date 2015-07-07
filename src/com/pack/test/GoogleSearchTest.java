package com.pack.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleSearchTest {
	
	private WebDriver driver; 
	private String appURL;
	private By searchBox = By.xpath("//input[@id='lst-ib']");
	private By searchBtn = By.xpath("//button[@value = 'Search']");
	
	@BeforeClass
	public void testSetUp() {
		appURL = "http://google.com";
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void verifyGooglePageTittle() {
		driver.navigate().to(appURL);
		String getTitle = driver.getTitle();
		System.out.println("Page Title: "+getTitle);
		Assert.assertEquals(getTitle, "Google");
	}
	
	@Test (dependsOnMethods = { "verifyGooglePageTittle" })
	public void setSearchKeyword(){
		driver.findElement(searchBox).sendKeys("Jenkins Tutorial");
	}
	
	@Test (dependsOnMethods = { "setSearchKeyword" }) 
	public void clicOnSearchBtn(){
		WebElement element = driver.findElement(searchBtn);
		if (element.isDisplayed()||element.isEnabled())
			element.click();
		else 
			System.out.println("Element not found");
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
