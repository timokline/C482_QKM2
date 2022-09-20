/*
 * FNAM: OutSourced.java
 * DESC: Derived model class of Part; OutSourced
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 19 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.model;

/** Subclass for an outsourced part.
 * Inherits from the base class <code>Part</code>.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see Part
 */
public class OutSourced extends Part {
  private String companyName;

  /** Model constructor specifying part information.
   * Calls <code>super</code> on part information.
   * Stores the name of the company that provides part.
   *
   * @param id part id
   * @param name part name
   * @param price part price
   * @param stock current part inventory level
   * @param min minimum part inventory level
   * @param max maximum part inventory level
   * @param companyName company name of part
   */
  public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
    super(id, name, price, stock, min, max);
    this.companyName = companyName;
  }

  /**
   * @return the company name
   */
  public String getCompanyName() {
    return this.companyName;
  }

  /**
   * @param companyName the name to set
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
}
