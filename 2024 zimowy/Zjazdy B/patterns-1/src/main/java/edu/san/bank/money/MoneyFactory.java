// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.money;

import java.math.BigDecimal;

//ABSTRACT FACTORY:
public interface MoneyFactory {

  Money create(BigDecimal amount, Currency currency);

}
