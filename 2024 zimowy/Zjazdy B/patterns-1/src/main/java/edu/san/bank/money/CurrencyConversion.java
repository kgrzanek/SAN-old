// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.money;

import java.math.BigDecimal;

public interface CurrencyConversion {

  default Money convert(Money money, Currency targetCurrency, MoneyFactory moneyFactory) {
    var targetAmount = computeAmount(money.amount(), money.currency(),
        targetCurrency);

    return moneyFactory.create(targetAmount, targetCurrency);
  }

  BigDecimal computeAmount(BigDecimal amount, Currency currency,
      Currency targetCurrency);

  // TYPOWANIE STRUKTURALNE
  // foo :: (a -> b) -> b
  // convert :: Money -> Currency -> Money

  // TYPOWANIE NOMINALNE
  // type CurrencyConversion = Money -> Currency -> Money
  // convert :: CurrencyConversion
}
