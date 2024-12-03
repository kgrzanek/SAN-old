// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.money.impl;

import java.math.BigDecimal;

import edu.san.bank.money.Currency;
import edu.san.bank.money.Money;
import edu.san.bank.money.MoneyFactory;

public final class MoneyFactoryImpl implements MoneyFactory {

  public static final MoneyFactoryImpl instance = new MoneyFactoryImpl();

  @Override
  public Money create(BigDecimal amount, Currency currency) {
    return new MoneyImpl(amount, currency);
  }

  private MoneyFactoryImpl() {}
}
