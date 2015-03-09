/**
 * 
 */
package entity;

/**
 * @author Romain
 * Enum is use to put a sexe type to an entity.
 */
public enum Sexe {
  HOMME(Values.HOMME), FEMME(Values.FEMME);

  private Sexe(final String val) {
    if (!this.name().equals(val)) {
      throw new IllegalArgumentException("Incorrect use of Sexe");
    }
  }

  public static class Values {

    private Values() {
      //empty constructor
    }

    public static final String HOMME = "HOMME";
    public static final String FEMME = "FEMME";
  }
}
