/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package housy.ledger;

import java.util.*;
import javax.swing.AbstractListModel;

public class CurrencyListModel extends AbstractListModel {

  private static List<String> data;
  
  public CurrencyListModel() {
    List<String> currencies = Currency.getAvailableCurrencies().stream().map(Currency::getSymbol).toList();
    data = currencies.stream().sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder())).toList(); 
  }
  
  @Override
  public int getSize() {
    return data.size();
  }

  @Override
  public Object getElementAt(int index) {
    return data.get(index);
  }
}
