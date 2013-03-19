/**
 *
 */
package com.lckymn.kevin.emailextractor.service;

import java.util.Set;

import com.lckymn.kevin.emailextractor.pojo.EmailAddress;

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
public interface EmailAddressExtractor
{
  Set<EmailAddress> extract(String value);
}
