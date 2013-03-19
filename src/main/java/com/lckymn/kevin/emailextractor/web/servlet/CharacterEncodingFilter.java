/**
 *
 */
package com.lckymn.kevin.emailextractor.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
public final class CharacterEncodingFilter implements Filter
{
  private static final String DEFAULT_CHAR_SET = "UTF-8";

  private String charSet = DEFAULT_CHAR_SET;

  @Override
  public void destroy()
  {
  }

  /**
	 *
	 */
  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
      throws IOException, ServletException
  {
    try
    {
      request.setCharacterEncoding(charSet);
      response.setCharacterEncoding(charSet);
    }
    finally
    {
      filterChain.doFilter(request, response);
    }
  }

  /**
   * Gets initial character set name from web.xml and set it as the character set of this filter.
   */
  @Override
  public void init(final FilterConfig filterConfig) throws ServletException
  {
    final String charSet = filterConfig.getInitParameter("charSetName");

    if (null != charSet && 0 != charSet.length())
    {
      synchronized (this)
      {
        this.charSet = charSet;
      }
    }
  }

}