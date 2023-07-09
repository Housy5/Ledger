package housy.ledger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class serves as an immutable transaction to be recorded in the Ledger class contained in this same package.
 */
public final class Transaction implements Serializable {
  
  private String value;
  private String message;
  private LocalDate date;
  
  /**
   * Tests if a string is a valid number.
   * @param str
   * @return true if the provided String is a valid number.
   */
  private boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
  
  /**
   * Creates a transaction with the specified value (as String) and message.
   * Automatically sets the creation date to the current system date.
   * When message is left as null an empty string is assumed.
   * When the provided value isn't a number an IllegalArgumentException will be thrown.
   */
  public Transaction(String value, String message) {
    if (!isNumeric(value)) {
      throw new IllegalArgumentException("The provided string must be a valid number!");
    }
    
    if (message == null) {
      message = "";
    }
    
    this.message = message;
    this.value = value;
    date = LocalDate.now();
    
  }
  
  /**
   * Returns the date on which this transaction was created.
   * @return 
   */
  public LocalDate getDate() {
    return date;
  }
  
  /**
   * Returns the underlying value as an BigDecimal - DON'T USE FLOATING POINT FOR CURRENCY -
   * @return 
   */
  public BigDecimal getValue() {
    return new BigDecimal(value);
  }

  /**
   * Returns the message (if any) attached to this transaction.
   * @return the message or empty string if no message was set.
   */
  public String getMessage() {
    return message;
  }
  
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.value);
    hash = 89 * hash + Objects.hashCode(this.date);
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
    final Transaction other = (Transaction) obj;
    if (!Objects.equals(this.value, other.value))
      return false;
    return Objects.equals(this.date, other.date);
  }
}
