package de.bht.fb6.cg1.colorconverter;

import de.bht.fb6.cg1.ip.IColor;

/**
 * @author admin
 * 
 */
public class RGB implements IColor {

  /**
   * final attribute die den Zahlenbereich in dem fuer rgb moeglichen bereich
   * eingrenzen. erste r,g,b deklaration
   */
  private static final int MAX = 255;
  private static final int MIN = 0;
  private final int r;
  private final int g;
  private final int b;

  /**
   * 
   * @param r
   * @param g
   * @param b
   *          Konstruktor prueft Uebergebene rgb werte auf den gluetigen Bereich
   *          und speichert sie danach in variablen oder wirft eine Exeption.
   * 
   */

  public RGB(int r, int g, int b) {
    if (r > MAX) {
      r = MAX;
    } else if (r < MIN) {
      r = MIN;
    }
    if (g > MAX) {
      g = MAX;
    } else if (g < MIN) {
      g = MIN;
    }
    if (b > MAX) {
      b = MAX;
    } else if (b < MIN) {
      b = MIN;
    }

    if (r <= MAX && r >= MIN) {

      this.r = r;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer RGB_R");
    }
    if (g <= MAX && g >= MIN) {

      this.g = g;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer RGB_G");
    }

    if (b <= MAX && b >= MIN) {

      this.b = b;
    } else {
      throw new IllegalArgumentException("kein gueltiger Wert fuer RGB_B");
    }
  }

  /**
   * getter Methoden fuer rgb werte
   * 
   * @return
   */

  public int getRed() {
    return r;
  }

  /**
   * @return
   */
  public int getGreen() {
    return g;
  }

  /**
   * @return
   */
  public int getBlue() {
    return b;

  }

  @Override
  public int getBrightness() {
    return Math.round((r + b + g) / 3f);

  }

  /**
   * umrechnung von rgb zu CMY. return von einer cmy instanz
   * 
   * @return
   */
  public CMY toCMY() {

    int c = 255 - r;
    int m = 255 - g;
    int y = 255 - b;

    return new CMY(c, m, y);

  }

  /**
   * umrechnung von rgb zu yuv. Mit berechnung/round/cast werdn YUV werte
   * ermittelt. Erzeugen/ return von einer yuv instanz.
   * 
   * @return
   */
  public YUV toYUV() {
    float y = 0.299f * r + 0.587f * g + 0.114f * b;
    float u = (b - y) * 0.493f;
    float v = (r - y) * 0.877f;

    y = java.lang.Math.round(y);
    u = java.lang.Math.round(u);
    v = java.lang.Math.round(v);

    return new YUV((int) y, (int) u, (int) v);
  }

  /**
   * Diese Funktion rechnet einen gegebenen RGB wert zu HSV um. Es wird ein
   * neues HSV Objekt returnd.
   * 
   * @return
   */

  public HSV toHSV() {
    int min;
    int max;
    int delMax;

    if (r >= g && r >= b) {

      max = r;
    } else if (g >= r && g >= b) {
      max = g;

    } else if (b >= g && b >= r) {
      max = b;
    } else {
      max = -1;
    }

    if (r <= g && r <= b) {

      min = r;
    } else if (g <= r && g <= b) {
      min = g;

    } else if (b <= g && b <= r) {
      min = b;
    } else {
      min = -1;
    }

    delMax = max - min;

    float h;
    float s;
    float v = max;

    if (delMax == 0) {
      h = 0f;
    } else if (r == max) {
      h = (g - b) / (float) delMax * 60f;
    } else if (g == max) {
      h = (2f + (b - r) / (float) delMax) * 60f;
    } else if (b == max) {
      h = (4f + (r - g) / (float) delMax) * 60f;
    } else {
      h = -1;
      throw new IllegalArgumentException("Es konnte kein hsv berechnet werden.");
    }

    if (h < 0) {
      h = Math.round(h + 360);
    } else {
      h = Math.round(h);
    }

    if (max == 0) {
      s = 0;
    } else {
      s = Math.round(100 * (max - min) / max);
    }

    if (max == r) {
      v = Math.round(r / 2.55f);

    } else if (max == g) {
      v = Math.round(g / 2.55f);
    } else if (max == b) {
      v = Math.round(b / 2.55f);
    } else {
      v = -1;
    }

    return new HSV((int) h, (int) s, (int) v);
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
    result = prime * result + b;
    result = prime * result + g;
    result = prime * result + r;
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
    RGB other = (RGB) obj;
    if (b != other.b) {
      return false;
    }
    if (g != other.g) {
      return false;
    }
    if (r != other.r) {
      return false;
    }
    return true;
  }
}
