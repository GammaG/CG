package de.bht.fb6.cg1.colorconverter;

import de.bht.fb6.cg1.ip.IColor;

/**
 * @author admin
 * 
 */
public class CMY implements IColor {
  /**
   * Es wird ein Wertebereich fuer die moeglichen CMY werte angegeben. Es werden
   * die cmy variablen erzeugt.
   */
  private static final int MAX = 255;
  private static final int MIN = 0;
  private int c;
  private int m;
  private int y;

  /**
   * Konstruktor CMY. Es werden die uebergebenen Werte auf gluetigkeit und
   * einhalten des Wertebereiches geprueft. Variaben werden gespeichert, oder es
   * wird eine Exception geworfen.
   * 
   * @param c
   * @param m
   * @param y
   */
  public CMY(final int c, final int m, final int y) {
    if (y <= MAX && y >= MIN) {
      this.y = y;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer CMY_Y");
    }
    if (m <= MAX && m >= MIN) {
      this.m = m;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer CMY_M");
    }

    if (c <= MAX && c >= MIN) {
      this.c = c;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer CMY_C");
    }
  }

  /**
   * Getter Methoden. Es werden die Werte fuer CMY returnd.
   * 
   * @return
   */
  public int getC() {
    return c;
  }

  /**
   * @return
   */
  public int getM() {
    return m;
  }

  /**
   * @return
   */
  public int getY2() {
    return y;
  }

  /**
   * @param i
   */
  public void setC(final int i) {
    c = i;
  }

  /**
   * @param i
   */
  public void setM(final int i) {
    m = i;
  }

  /**
   * @param i
   */
  public void setY2(final int i) {
    y = i;
  }

  @Override
  public int getBrightness() {

    return Math.round((c + m + y) / 3f);
  }

  /**
   * Konvertierung von CMY zu RGB. Umrechnung/ es wird eine rgb instanz erzeugt
   * und returnd.
   * 
   * @return
   */
  public RGB toRGB() {
    int r = 255 - c;
    int g = 255 - m;
    int b = 255 - y;

    return new RGB(r, g, b);
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
    result = prime * result + c;
    result = prime * result + m;
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
    CMY other = (CMY) obj;
    if (c != other.c) {
      return false;
    }
    if (m != other.m) {
      return false;
    }
    if (y != other.y) {
      return false;
    }
    return true;
  }

}
