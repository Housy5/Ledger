package housy.ledger;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * A filter to ensure only numbers are entered in a JTextField - Might also work with other components that use a these documents -
 */
public class IntegerDocumentFilter extends DocumentFilter  {

  private boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  } 

  @Override
  public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
    if (!isInteger(string) && !string.equals(".")){
      return;
    }
    
    super.insertString(fb, offset, string, attr);
  }

  @Override
  public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
    if (!isInteger(text) && !text.equals(".")){
      return;
    }
    
    super.insertString(fb, offset, text, attrs);
  }

  @Override
  public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
    super.remove(fb, offset, length);
  }
}
