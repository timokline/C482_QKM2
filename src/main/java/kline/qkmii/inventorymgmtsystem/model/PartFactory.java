package kline.qkmii.inventorymgmtsystem.model;

public class PartFactory {
  private static int partUID = -1;
  private final boolean isNew;
  private int id;

  public PartFactory() {
    isNew = true;
  }

  public PartFactory(Part selectedPart) {
    isNew = false;
    id = selectedPart.getId();
  }

  private int getPartUID() {
    return ++partUID;
  }

  public InHouse makePart(String name, double price, int stock, int min, int max, int machineID) {
    return new InHouse(getPartUID(), name, price, stock, min, max, machineID);
  }

  public OutSourced makePart(String name, double price, int stock, int min, int max, String companyName) {
    return new OutSourced(getPartUID(), name, price, stock, min, max, companyName);
  }

  public Part makePart(PartSrcType source, String name, double price, int stock, int min, int max, Object srcValue) {
    var id = (isNew) ? getPartUID() : this.id;
    return switch (source) {
      case IN_HOUSE -> new InHouse(id, name, price, stock, min, max, (int) srcValue);
      case OUTSOURCED -> new OutSourced(id, name, price, stock, min, max, (String) srcValue);
    };
  }

  public enum PartSrcType {IN_HOUSE, OUTSOURCED}
}
