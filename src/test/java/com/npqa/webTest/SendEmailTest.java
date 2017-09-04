package com.npqa.webTest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.npqa.elem.ElemHelper;
import com.npqa.pages.ComposePage;
import com.npqa.pages.InboxPage;
import com.npqa.pages.LoginPage;

public class SendEmailTest {
  private WebDriver driver;
  private ElemHelper elemHelper;
  private String user = "";
  private String pass = "";
  private String to = "";
  private String subject = "";
  private String body = "";
  private LoginPage login;
  private InboxPage inbox;
  private ComposePage compose;

  @BeforeClass( )
  public void setUpDriver() {
    driver = new FirefoxDriver();
    driver.get( "http://www.gmail.com" );
    elemHelper = new ElemHelper( driver );
  }

  @Test( )
  public void login() {
    login = new LoginPage( driver, elemHelper );
    assertTrue( login.isOpened(), "Text field to input user name is not present!" );
    inbox = login.logIn( user, pass );
    assertTrue( inbox.isOpened(), "Compose button is not present!" );
  }

  @Test( )
  public void clickCompose() {
    compose = inbox.clickCompose();
    assertTrue( compose.isOpened(), "Field to input e-mail body is not present!" );
  }

  @Test( )
  public void composeEmail() {
    compose = inbox.clickCompose();
    assertTrue( compose.isOpened(), "Field to input e-mail body is not present!" );
    compose.composeEmail( to, subject, body );
  }

  @Test( )
  public void sendEmail() {
    compose.sendEmail();
    assertTrue( inbox.verifyMessageSent(), "Message successfuly sent text was not shown!" );
  }

  @AfterClass( )
  public void quitDriver() {
    driver.close();
  }

}
