package housy.ledger;

import java.io.*;

/**
 * This static class is an utility class to save and load ledgers to and from a file.
 */
public final class Serializer {
  
  private Serializer(){}
  
  /**
   * Attempts to save a ledger to the file 
   */
  public static void save(File saveFile, Ledger ledger) throws IOException {
    try (FileOutputStream fout = new FileOutputStream(saveFile);
         ObjectOutputStream objOut = new ObjectOutputStream(fout)) {
      objOut.writeObject(ledger);
    }
  }
  
  /**
   * Attempts to load a ledger from the provided file.
   * Returns an empty ledger if it somehow fails.
   */
  public static Ledger load(File saveFile) {
    try (FileInputStream fin = new FileInputStream(saveFile);
         ObjectInputStream objIn = new ObjectInputStream(fin)) {
      Ledger ledger = (Ledger) objIn.readObject();
      return ledger;
    } catch (ClassNotFoundException | IOException ex) {
      return new Ledger();
    }
  }
}
