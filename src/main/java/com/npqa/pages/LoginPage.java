package com.npqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.npqa.elem.ElemHelper;

public class LoginPage {

  @FindBy( id = "identifierId" )
  private WebElement inputUser;

  @FindBy( id = "identifierNext" )
  private WebElement btnUserNext;

  @FindBy( xpath = "//input[@name='password']" )
  private WebElement inputPass;

  @FindBy( id = "passwordNext" )
  private WebElement btnPassNext;

  private ElemHelper elemHelper;
  private WebDriver thisDriver;

  public LoginPage( WebDriver driver, ElemHelper elemHelperTest ) {
    elemHelper = elemHelperTest;
    PageFactory.initElements( driver, this );
    thisDriver = driver;
  }

  public boolean isOpened() {
    return elemHelper.isElementPresent( inputUser );
  }

  public InboxPage logIn( String user, String pass ) {
    elemHelper.sendText( inputUser, user );
    elemHelper.clickElement( btnUserNext );
    elemHelper.sendText( inputPass, pass );
    elemHelper.clickElement( btnPassNext );
    InboxPage inbox = new InboxPage( thisDriver, elemHelper );
    return inbox;
  }
}
