/**
 *
 */
package com.lckymn.kevin.emailextractor.config;

import java.util.regex.Pattern;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.lckymn.kevin.emailextractor.service.impl.SimpleEmailAddressExtractor;

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
 * @version 0.0.1 (2010-04-06)
 */
public final class EmailExtractServiceModule extends AbstractModule
{
  @Override
  protected void configure()
  {
    /* @formatter:off */
    bind(Pattern.class)
        .annotatedWith(Names
                      .named("emailAddressPattern"))
        .toInstance(SimpleEmailAddressExtractor.DEFAULT_EMAIL_ADDRESS_PATTERN);
    /* @formatter:on */
  }

}
