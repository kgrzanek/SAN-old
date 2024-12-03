// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.money.impl;

import java.math.BigDecimal;
import java.util.Objects;

import edu.san.bank.money.Currency;
import edu.san.bank.money.Money;

record MoneyImpl(BigDecimal amount, Currency currency) implements Money {

  MoneyImpl {
    Objects.requireNonNull(amount);
    Objects.requireNonNull(currency);
  }

}
