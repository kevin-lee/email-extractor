/**
 *
 */
package com.lckymn.kevin.emailextractor.service.impl;

import static org.elixirian.kommonlee.util.Strings.*;

import java.util.Collections;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elixirian.kommonlee.util.collect.Sets;

import com.google.inject.Inject;
import com.google.inject.name.Named;
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
public class SimpleEmailAddressExtractor implements EmailAddressExtractor
{
  public static final Pattern DEFAULT_EMAIL_ADDRESS_PATTERN = Pattern.compile("([\\w-_.]+@[\\w-_]+(?:[.][\\w-_]+)+)");

  private final Pattern pattern;

  public SimpleEmailAddressExtractor()
  {
    this(DEFAULT_EMAIL_ADDRESS_PATTERN);
  }

  @Inject
  public SimpleEmailAddressExtractor(@Named("emailAddressPattern") final Pattern pattern)
  {
    this.pattern = pattern;
  }

  @Override
  public Set<EmailAddress> extract(final String value)
  {
    final Set<EmailAddress> emailAddressSet = Sets.newLinkedHashSet();
    for (final String each : value.split("[\\s]+"))
    {
      final Matcher matcher = pattern.matcher(each);
      if (matcher.find())
      {
        final String emailAddress = nullSafeTrim(matcher.group(0));
        if (!emailAddress.isEmpty())
        {
          emailAddressSet.add(new EmailAddress(emailAddress));
        }
      }
    }
    return Collections.unmodifiableSet(emailAddressSet);
  }
}
