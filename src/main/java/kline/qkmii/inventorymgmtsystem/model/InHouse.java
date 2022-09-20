/*
 * FNAM: InHouse.java
 * DESC: Derived model class of Part; InHouse
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 19 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.model;

/** Subclass for an in-house part.
 * Inherits from the base class <code>Part</code>.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see Part
 */
public class InHouse extends Part {
  private int machineId;

  /** Model constructor specifying part information.
   * Calls <code>super</code> on part information.
   * Stores the machine code that made the part.
   *
   * @param id part id
   * @param name part name
   * @param price part price
   * @param stock current part inventory level
   * @param min minimum part inventory level
   * @param max maximum part inventory level
   * @param machineId machine code of part
   */
  public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
    super(id, name, price, stock, min, max);
    this.machineId = machineId;
  }

  /**
   * @return the machine code
   */
  public int getMachineId() {
    return this.machineId;
  }

  /**
   * @param machineId the code to set
   */
  public void setMachineId(int machineId) {
    this.machineId = machineId;
  }
}
