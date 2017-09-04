package com.npqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.npqa.elem.ElemHelper;

public class InboxPage {

  @FindBy( xpath = "//div[text()='COMPOSE']" )
  private WebElement btnCompose;

  @FindBy( xpath = "//div[contains(text(),'Your message has been sent.')]" )
  private WebElement txtSendSuccess;

  private ElemHelper elemHelper;
  private WebDriver thisDriver;

  public InboxPage( WebDriver driver, ElemHelper elemHelperTest ) {
    elemHelper = elemHelperTest;
    PageFactory.initElements( driver, this );
    thisDriver = driver;
  }

  public boolean isOpened() {
    return elemHelper.isElementPresent( btnCompose );
  }

  public ComposePage clickCompose() {
    elemHelper.clickElement( btnCompose );
    ComposePage compose = new ComposePage( thisDriver, elemHelper );
    return compose;
  }

  public boolean verifyMessageSent() {
    return elemHelper.isElementPresent( txtSendSuccess );
  }

}
