// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import java.util.Optional;
import java.util.regex.Pattern;

import telsos.newtype.Newtype;

public final class LastName extends Newtype<String> {

  public static Optional<LastName> of(String s) {
    return of(s, LastName::isValid, LastName::new);
  }

  public static boolean isValid(String s) {
    final var isNullValid = false;
    return isValid(s, isNullValid);
  }

  public static boolean isValid(String s, boolean isNullValid) {
    return null == s ? isNullValid : PATTERN.matcher(s).matches();
  }

  private LastName(String value) {
    super(value);
  }

  private static final Pattern PATTERN = Pattern
      .compile(
          "^\\p{IsUppercase}[\\p{IsLowercase}\\s']{1,126}\\p{IsLowercase}$",
          Pattern.UNICODE_CHARACTER_CLASS);

}
