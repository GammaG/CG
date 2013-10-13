package de.bht.fb6.cg1.colorconverter;

import de.bht.fb6.cg1.ip.IColor;

/**
 * @author admin
 * 
 */
public class HSV implements IColor {
  /**
   * Hier werden die Variablen fuer die Klasse HSV deklariert.
   * 
   */

  private int h;
  private int s;
  private int v;

  /**
   * Konstruktor der HSV Klasse, hier werden die Uebergebenen Werte auf
   * Gueltigkeit Ueberprueft und wenn gueltig in der lokalen Variable
   * gespeichert.
   * 
   * @param h
   * @param s
   * @param v
   */
  public HSV(final int h, final int s, final int v) {

    if (h >= 0 && h <= 360) {
      this.h = h;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer HSV_H");
    }
    if (s >= 0 && s <= 100) {
      this.s = s;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer HSV_S");
    }
    if (v >= 0 && v <= 100) {
      this.v = v;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer HSV_V");
    }

  }

  /**
   * Hier werden die Getter Methoden der Klasse HSV erstellt.
   * 
   * @return
   * 
   */
  public int getH() {
    return h;
  }

  /**
   * @return
   */
  public int getS() {
    return s;
  }

  /**
   * @return
   */
  public int getV() {
    return v;
  }

  /**
   * @param h
   *          the h to set
   */
  public void setH(int h) {
    this.h = h;
  }

  /**
   * @param s
   *          the s to set
   */
  public void setS(int s) {
    this.s = s;
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

    return Math.round(v * 2.55f);
  }

  /**
   * Hier werden die vorhandenen Werte der HSV Instanz zu den dazugehoerigen RGB
   * werten umgerechnet. Es wird ein RGB objekt returnd.
   * 
   * @return
   */

  public RGB toRGB() {

    float r = 0;
    float g = 0;
    float b = 0;
    int h = this.h;
    float s = this.s;
    float v = this.v;

    s /= 100f;
    v /= 100f;

    final int i = Math.abs(h / 60);
    final float f = h / 60f - i;
    final float p = v * (1 - s);
    final float q = v * (1 - s * f);
    final float t = v * (1 - s * (1 - f));

    switch (i) {
    case 0:
    case 6:
      r = v;
      g = t;
      b = p;
      break;

    case 1:
      r = q;
      g = v;
      b = p;
      break;

    case 2:
      r = p;
      g = v;
      b = t;
      break;

    case 3:
      r = p;
      g = q;
      b = v;
      break;

    case 4:
      r = t;
      g = p;
      b = v;
      break;

    case 5:
      r = v;
      g = p;
      b = q;
    }

    r = Math.round(r * 255);
    g = Math.round(g * 255);
    b = Math.round(b * 255);

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
    result = prime * result + h;
    result = prime * result + s;
    result = prime * result + v;
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
    HSV other = (HSV) obj;
    if (h != other.h) {
      return false;
    }
    if (s != other.s) {
      return false;
    }
    if (v != other.v) {
      return false;
    }
    return true;
  }

}
