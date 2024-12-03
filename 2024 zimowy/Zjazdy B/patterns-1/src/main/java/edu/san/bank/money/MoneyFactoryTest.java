// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.money;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.bank.money.impl.MoneyFactoryImpl;

class MoneyFactoryTest {

  MoneyFactory moneyFactory;

  @BeforeEach
  void setUp() {
    moneyFactory = MoneyFactoryImpl.instance;
  }

  @Test
  void test() {
    var money1 = moneyFactory.create(BigDecimal.valueOf(1256.5), Currency.PLN);
    System.out.println(money1);
  }

}
