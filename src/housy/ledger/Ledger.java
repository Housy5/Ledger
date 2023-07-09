package housy.ledger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * This class serves as an immutable ledger to record transactions.
 * CAUTION: When something is added to this ledger it can't be altered.
 */
public final class Ledger implements Serializable {
  
  private final List<Transaction> transactions;
  
  private String currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();
  
  public Ledger() {
    transactions = new ArrayList<>();
  }
  
  /**
   * Returns the number of records that are contained in this ledger.
   */
  public int size() {
    return transactions.size();
  }
  
  /**
   * Returns the record in the specified index.
   * Or IndexOutOfBoundsException when the index is invalid.
   */
  public Transaction get(int index) {
    return transactions.get(index);
  }
  
  /**
   * Makes a record of a transaction.
   * (Or in english: puts the transaction to the back of the ledger)
   * @param transaction the transaction to be recorded
   */
  public void record(Transaction transaction) {
    transactions.add(transaction);
  }
  
  public boolean remove(int index) {
    return transactions.remove(index) != null;
  }
  
  /**
   * Calculates and returns the sum of all recorded transactions in this ledger.
   * @return 
   */
  public BigDecimal getTotal() {
    BigDecimal result = new BigDecimal(0);
    
    for (Transaction transaction : transactions) {
      result = result.add(transaction.getValue());
    }
    
    return result;
  }
  
  /**
   * Returns an immutable copy of the underlying list of transactions.
   * @return 
   */
  public List<Transaction> getTransactions() {
    return List.copyOf(transactions);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 47 * hash + Objects.hashCode(this.transactions);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final Ledger other = (Ledger) obj;
    return Objects.equals(this.transactions, other.transactions);
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }
  
  public void setCurrencySymbol(String str) {
    this.currencySymbol = str;
  }
}
