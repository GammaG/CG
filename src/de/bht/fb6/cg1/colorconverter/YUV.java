package de.bht.fb6.cg1.colorconverter;

import de.bht.fb6.cg1.ip.IColor;

/**
 * @author admin
 * 
 */
public class YUV implements IColor {

  /**
   * anganze eines moeglichen werteraumes fuer yuv. erste deklaration von yuv
   * variablen.
   */

  private int y;
  private int u;
  private int v;

  /**
   * Konstruktor von YUV. prueft Uebergebene Werte auf glueltigen Wertebereich.
   * Speichert variablen ab, oder wirft eine Expection.
   * 
   * @param y
   * @param u
   * @param v
   */
  public YUV(final int y, final int u, final int v) {
    if (y >= 0 && y <= 255) {
      this.y = y;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer YUV_Y");
    }
    if (u >= -111 && u <= 111) {
      this.u = u;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer YUV_U");
    }

    if (v >= -157 && v <= 157) {
      this.v = v;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer YUV_V");
    }
  }

  /**
   * Getter Methoden. Geben den YUV wert zurueck.
   * 
   * @return
   */
  public int getY() {
    return y;
  }

  /**
   * @return
   */
  public int getU() {
    return u;
  }

  /**
   * @return
   */
  public int getV() {
    return v;
  }

  /**
   * @param y
   *          the y to set
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * @param u
   *          the u to set
   */
  public void setU(int u) {
    this.u = u;
  }

  /**
   * @param v
   *          the v to set
   */
  public void setV(int v) {
    this.v = v;
  }

  @Override
  public int getBrightness() {

    return y;
  }

  /**
   * Konvertierung von YUV zu rgb. Mit berechnung/ round / cast werden rgb werte
   * ermittelt. Es wird eine rgb instanz erzeugt/ zurueckgegeben.
   * 
   * @return
   */

  public RGB toRGB() {
    double b = y + u / 0.493;
    double r = y + v / 0.877;
    double g = y - 0.39466 * u - 0.5806 * v;

    r = java.lang.Math.round(r);
    g = java.lang.Math.round(g);
    b = java.lang.Math.round(b);
    return new RGB((int) r, (int) g, (int) b);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + u;
    result = prime * result + v;
    result = prime * result + y;
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    YUV other = (YUV) obj;
    if (u != other.u) {
      return false;
    }
    if (v != other.v) {
      return false;
    }
    if (y != other.y) {
      return false;
    }
    return true;
  }
}
