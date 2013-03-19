/**
 *
 */
package com.lckymn.kevin.emailextractor.pojo;

import static org.elixirian.kommonlee.util.Objects.*;

/**
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-04-05)
 */
public class EmailAddress
{
  private final String emailAddress;

  public EmailAddress(final String emailAddress)
  {
    this.emailAddress = emailAddress;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  @Override
  public int hashCode()
  {
    /* @formatter:off */
    return hash(null == emailAddress ?
                null :
                emailAddress.toLowerCase());
    /* @formatter:on */
  }

  @Override
  public boolean equals(final Object emailAddress)
  {
    if (this == emailAddress)
    {
      return true;
    }
    final EmailAddress that = castIfInstanceOf(EmailAddress.class, emailAddress);
    /* @formatter:off */
    return null != that &&
        (this.emailAddress == that.getEmailAddress() ||
        (null != this.emailAddress &&
         this.emailAddress.equalsIgnoreCase(that.getEmailAddress())));
    /* @formatter:on */
  }

  @Override
  public String toString()
  {
    return emailAddress;
  }
}
