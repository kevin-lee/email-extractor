package com.lckymn.kevin.emailextractor.web.servlet;

import static org.elixirian.kommonlee.util.Strings.*;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.lckymn.kevin.emailextractor.pojo.EmailAddress;
import com.lckymn.kevin.emailextractor.service.EmailAddressExtractor;

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
@Singleton
public class EmailExtractServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailExtractServlet.class.getName());

  private static String prefix = "/WEB-INF/jsp/";

  private final EmailAddressExtractor emailAddressExtractor;

  @Inject
  public EmailExtractServlet(final EmailAddressExtractor emailAddressExtractor)
  {
    this.emailAddressExtractor = emailAddressExtractor;
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
      IOException
  {
    doPost(request, response);
  }

  private void forward(final HttpServletRequest request, final HttpServletResponse response, final String destination)
      throws ServletException, IOException
  {
    request.getRequestDispatcher(prefix + destination)
        .forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
      IOException
  {
    final String uri = request.getRequestURI();

    final int indexOfLastSlash = uri.lastIndexOf("/");

    if (0 > indexOfLastSlash || uri.length() == indexOfLastSlash + 1)
    {
      forward(request, response, "extractor.jsp");
      return;
    }
    final String action = uri.substring(indexOfLastSlash + 1);

    LOGGER.debug("requested action: {}", action);

    if (action.equalsIgnoreCase("clear"))
    {
      /* @formatter:off */
      response.sendRedirect(request
                           .getContextPath());
      /* @formatter:on */
      return;
    }
    if (action.equalsIgnoreCase("extract"))
    {
      final String inputValue = nullSafeTrim(request.getParameter("inputValue"));
      // LOGGER.debug("targetValue: {}", inputValue);

      if (inputValue.isEmpty())
      {
        request.setAttribute("message", "You did not submit any text.");
      }
      else
      {
        final StringBuilder stringBuilder = new StringBuilder();
        final Set<EmailAddress> emailAddressSet = emailAddressExtractor.extract(inputValue);
        for (final EmailAddress each : emailAddressSet)
        {
          stringBuilder.append(each)
              .append(", ");
        }
        final int length = stringBuilder.length();
        if (0 != length)
        {
          stringBuilder.delete(length - 2, length);
        }
        // LOGGER.debug("result: {}", stringBuilder.toString());
        request.setAttribute("inputValue", inputValue);
        final int size = emailAddressSet.size();
        request.setAttribute("resultMessage", "There "
            + (0 == size ? "is no email address found." : (1 == size ? "is 1 email address" : "are " + size
                + " email addresses" + " extracted from the given text.")));
        request.setAttribute("result", stringBuilder.toString());
      }
    }
    else
    {
      request.setAttribute("message", "Unknown action!");
    }
    forward(request, response, "extractor.jsp");
  }

}
