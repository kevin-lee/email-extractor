/**
 *
 */
package com.lckymn.kevin.emailextractor.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.lckymn.kevin.emailextractor.config.EmailExtractServiceModule;
import com.lckymn.kevin.emailextractor.config.EmailExtractServletModule;

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
public class DefaultGuiceContextListener extends GuiceServletContextListener
{
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGuiceContextListener.class.getName());

  @Override
  protected Injector getInjector()
  {
    LOGGER.info("Guice is about to run!");
    return Guice.createInjector(new EmailExtractServletModule(), new EmailExtractServiceModule());
  }

}
