package com.npqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.npqa.elem.ElemHelper;

public class ComposePage {

  @FindBy( xpath = "//textarea[@name='to']" )
  private WebElement inputTo;

  @FindBy( xpath = "//input[@name='subjectbox']" )
  private WebElement inputSubject;

  @FindBy( xpath = "//div[@aria-label='Message Body']" )
  private WebElement inputBody;

  @FindBy( xpath = "//div[contains(@aria-label,'Send')]" )
  private WebElement btnSend;

  private ElemHelper elemHelper;

  public ComposePage( WebDriver driver, ElemHelper elemHelperTest ) {
    elemHelper = elemHelperTest;
    PageFactory.initElements( driver, this );
  }

  public boolean isOpened() {
    return elemHelper.isElementPresent( inputBody );
  }

  public void composeEmail( String to, String subject, String body ) {
    elemHelper.sendText( inputTo, to );
    elemHelper.sendText( inputSubject, subject );
    elemHelper.sendText( inputBody, body );
  }

  public void sendEmail() {
    elemHelper.clickElement( btnSend );
  }

}
