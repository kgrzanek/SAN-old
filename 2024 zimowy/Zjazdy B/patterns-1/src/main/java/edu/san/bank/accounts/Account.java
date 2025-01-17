// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.accounts;

import edu.san.bank.money.Currency;
import edu.san.bank.money.Money;

public interface Account {

  Currency currency(); // QUERY

  Money getBalance(); // QUERY

  void addToBalance(Money money); // COMMAND

  void subtractFromBalance(Money money); // COMMAND
}
