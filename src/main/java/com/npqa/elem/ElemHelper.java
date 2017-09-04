package com.npqa.elem;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class ElemHelper {
  private WebDriver driver;
  private int timeout = 10;
  private int pollTime = 500;
  private Logger logger = Logger.getLogger( ElemHelper.class );;

  public ElemHelper( WebDriver thisDriver ) {
    driver = thisDriver;
  }

  public void clickElement( WebElement element ) {
    if ( isElementPresent( element ) ) {
      element.click();
    } else {
      new ElementNotVisibleException( element.getTagName() + " was not clicked!" );
    }
  }

  public void sendText( WebElement element, String textToSend ) {
    if ( isElementPresent( element ) ) {
      element.sendKeys( textToSend );
    } else {
      new ElementNotVisibleException( element.getTagName() + " did not receive keys '" + textToSend + "'!" );
    }
  }

  private boolean waitForElementPresent( WebElement element ) {
    WebDriverWait wait = new WebDriverWait( driver, timeout, pollTime );
    final WebElement finalElem = element;
    try {
      wait.until( new ExpectedCondition<Boolean>() {
        public Boolean apply( WebDriver dr ) {
          boolean result = finalElem.isEnabled();
          if ( !result ) {
            logger.debug( driver.getPageSource() );
            logger.info( "Element " + finalElem.getTagName() + "was not found!" );
          }
          return result;
        }
      } );
    } catch ( Exception e ) {
      logger.debug( e.getMessage(), e.getCause() );
    }
    return true;
  }

  public boolean isElementPresent( WebElement element ) {
    waitForElementPresent( element );
    return element.isDisplayed();
  }
}
