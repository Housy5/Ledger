package housy.ledger;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class LedgerFileFilter extends FileFilter {

  public static final String EXTENSION = ".ldg";
  
  @Override
  public boolean accept(File f) {
    return f.getName().endsWith(EXTENSION);
  }

  @Override
  public String getDescription() {
    return "Ledger files (" + EXTENSION + ")";
  }
  
}
