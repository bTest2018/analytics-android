/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Segment.io, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.segment.analytics;

import android.content.Context;
import com.segment.analytics.internal.ISO8601Time;
import com.segment.analytics.json.JsonMap;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.segment.analytics.internal.Utils.getDeviceId;

/**
 * Traits can be anything you want, but some of them have semantic meaning and we treat them in
 * special ways. For example, whenever we see an email trait, we expect it to be the user's email
 * address. And we'll send this on to integrations that need an email, like Mailchimp. For that
 * reason, you should only use special traits for their intended purpose.
 * <p/>
 * This is persisted to disk, and will be remembered between sessions.
 */
public class Traits extends JsonMap {

  Traits(Context context) {
    String id = getDeviceId(context);
    // todo: kick off task to get AdvertisingId
    putUserId(id);
    putAnonymousId(id);
  }

  Traits(String json) {
    super(json);
  }

  public Traits() {
  }

  private static final String ADDRESS_KEY = "address";
  private static final String ADDRESS_CITY_KEY = "city";
  private static final String ADDRESS_COUNTRY_KEY = "country";
  private static final String ADDRESS_POSTAL_CODE_KEY = "postalCode";
  private static final String ADDRESS_STATE_KEY = "state";
  private static final String ADDRESS_STREET_KEY = "street";

  public Traits putAddress(String city, String country, String postalCode, String state,
      String street) {
    Map<String, String> address = new LinkedHashMap<String, String>(5);
    address.put(ADDRESS_CITY_KEY, city);
    address.put(ADDRESS_COUNTRY_KEY, country);
    address.put(ADDRESS_POSTAL_CODE_KEY, postalCode);
    address.put(ADDRESS_STATE_KEY, state);
    address.put(ADDRESS_STREET_KEY, street);
    return putValue(ADDRESS_KEY, address);
  }

  private static final String AVATAR_KEY = "avatar";
  private static final String CREATED_AT_KEY = "createdAt";
  private static final String DESCRIPTION_KEY = "description";
  private static final String EMAIL_KEY = "email";
  private static final String FAX_KEY = "fax";
  private static final String ANONYMOUS_ID_KEY = "anonymousId";
  private static final String ID_KEY = "userId";
  private static final String NAME_KEY = "name";
  private static final String PHONE_KEY = "phone";
  private static final String WEBSITE_KEY = "website";

  // For Identify Calls
  private static final String AGE_KEY = "age";
  private static final String BIRTHDAY_KEY = "birthday";
  private static final String FIRST_NAME_KEY = "firstName";
  private static final String GENDER_KEY = "gender";
  private static final String LAST_NAME_KEY = "lastName";
  private static final String TITLE_KEY = "title";
  private static final String USERNAME_KEY = "username";

  // For Group calls
  private static final String EMPLOYEES_KEY = "employees";
  private static final String INDUSTRY_KEY = "industry";

  public Traits putAvatar(String avatar) {
    return putValue(AVATAR_KEY, avatar);
  }

  public String avatar() {
    return getString(AVATAR_KEY);
  }

  public Traits putCreatedAt(String createdAt) {
    return putValue(CREATED_AT_KEY, createdAt);
  }

  public Traits putDescription(String description) {
    return putValue(DESCRIPTION_KEY, description);
  }

  public Traits putEmail(String email) {
    return putValue(EMAIL_KEY, email);
  }

  public String email() {
    return getString(EMAIL_KEY);
  }

  public Traits putFax(String fax) {
    return putValue(FAX_KEY, fax);
  }

  /**
   * Private API, users should call {@link Analytics#identify(String, Options)} instead. Note that
   * this is unable to enforce it, users can easily do {@code traits.put(id, 1231);}
   */
  Traits putUserId(String id) {
    return putValue(ID_KEY, id);
  }

  public String userId() {
    return getString(ID_KEY);
  }

  Traits putAnonymousId(String id) {
    return putValue(ANONYMOUS_ID_KEY, id);
  }

  String anonymousId() {
    return getString(ANONYMOUS_ID_KEY);
  }

  public Traits putName(String name) {
    return putValue(NAME_KEY, name);
  }

  public String name() {
    // todo: concat first name/last name if available
    return getString(NAME_KEY);
  }

  public Traits putPhone(String phone) {
    return putValue(PHONE_KEY, phone);
  }

  public Traits putWebsite(String website) {
    return putValue(WEBSITE_KEY, website);
  }

  public Traits putAge(int age) {
    return putValue(AGE_KEY, age);
  }

  public Integer age() {
    return getInteger(AGE_KEY);
  }

  public Traits putBirthday(Date birthday) {
    return putValue(BIRTHDAY_KEY, ISO8601Time.from(birthday).toString());
  }

  public Traits putFirstName(String firstName) {
    return putValue(FIRST_NAME_KEY, firstName);
  }

  public Traits putGender(String gender) {
    return putValue(GENDER_KEY, gender);
  }

  public String gender() {
    return getString(GENDER_KEY);
  }

  public Traits putLastName(String lastName) {
    return putValue(LAST_NAME_KEY, lastName);
  }

  public Traits putTitle(String title) {
    return putValue(TITLE_KEY, title);
  }

  public Traits putUsername(String username) {
    return putValue(USERNAME_KEY, username);
  }

  public Traits putEmployees(long employees) {
    return putValue(EMPLOYEES_KEY, employees);
  }

  public Traits putIndustry(String industry) {
    return putValue(INDUSTRY_KEY, industry);
  }

  @Override public Traits putValue(String key, Object value) {
    super.putValue(key, value);
    return this;
  }
}