/**
 *
 */
package com.lckymn.kevin.emailextractor.config;

import static org.elixirian.kommonlee.util.collect.Maps.*;

import java.util.Collections;
import java.util.Map;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.lckymn.kevin.emailextractor.service.EmailAddressExtractor;
import com.lckymn.kevin.emailextractor.service.impl.SimpleEmailAddressExtractor;
import com.lckymn.kevin.emailextractor.web.servlet.CharacterEncodingFilter;
import com.lckymn.kevin.emailextractor.web.servlet.EmailExtractServlet;

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
public class EmailExtractServletModule extends ServletModule
{
  @Override
  protected void configureServlets()
  {
    /* @formatter:off */
    bind(CharacterEncodingFilter.class)
      .in(Singleton.class);

    bind(EmailAddressExtractor.class)
      .to(SimpleEmailAddressExtractor.class);

    final Map<String, String> map = newHashMap();
    map.put("charSetName", "UTF-8");

    filter("/*")
      .through(CharacterEncodingFilter.class,
               Collections.unmodifiableMap(map));

//    serve("/*")
//       .with(MainServlet.class);

    serve("/", "/extract", "/clear")
       .with(EmailExtractServlet.class);
    /* @formatter:on */
  }
}
