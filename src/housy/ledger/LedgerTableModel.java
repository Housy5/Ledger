package housy.ledger;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.swing.table.AbstractTableModel;

/**
 * Just a TableModel custom made to be able to show the ledgers.
 */
public final class LedgerTableModel extends AbstractTableModel {

  private final int COLUMN_COUNT = 3; // Date, Value, and Message.
  
  public static final int DATE_INDEX = 0;
  public static final int VALUE_INDEX = 2;
  public static final int MESSAGE_INDEX = 1;
  
  private final Ledger ledger;
  
  private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
  
  private String formatDecimal(BigDecimal decimal) {
    DecimalFormat format = new DecimalFormat("#,##0.00");
    return format.format(decimal.doubleValue()) + " " +ledger.getCurrencySymbol();
  }
  
  public LedgerTableModel(Ledger ledger) {
    this.ledger = ledger;
  }
  
  @Override
  public int getRowCount() {
    return ledger.size();
  }

  @Override
  public int getColumnCount() {
    return COLUMN_COUNT;
  }
  
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Transaction transaction = ledger.get(rowIndex);
    return switch(columnIndex) {
      case DATE_INDEX -> {yield dateFormatter.format(transaction.getDate());}
      case VALUE_INDEX -> {yield formatDecimal(transaction.getValue());}
      case MESSAGE_INDEX -> {yield transaction.getMessage();}
      default -> {yield "";}
    }; 
  }
  
  @Override
  public String getColumnName(int index) {
    return switch(index) {
      case DATE_INDEX -> {yield "Date";}
      case VALUE_INDEX -> {yield "Value";}
      case MESSAGE_INDEX -> {yield "Message";}
      default -> {
        throw new IndexOutOfBoundsException();
      }
    };
  }
  
  
}
