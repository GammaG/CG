package de.bht.fb6.cg1.ip;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

/**
 * This class represents a plug in for ImageJ. It scales an image.
 * 
 * @author Marco Seidler
 */
public class ScaleImage implements PlugIn {

  /**
   * The ImagePlus object of the loaded image.
   */
  private final ImagePlus imp;

  /**
   * This constructor creates a new ScaleImage object.
   * 
   * @param imp
   *          The ImagePlus object. Must not be null!
   */
  public ScaleImage(final ImagePlus imp) {

    this.imp = imp;
  }

  @Override
  /**
   * Hier wird ein übergebenes Bild sklaiert. Entwerder nach der Methode Nearest-Neighbor, oder nach der Bilinear Methode.
   * Die zur umrechnung notwendigen werte X,Y können dabei mit einem Slider ausgewälht werden.
   * Bei der Nearest -Neighbor methode wird einfach der drushnitt der nahbarn für deie farbwertbestimmtung des dazugekommenen Pixels verwendet.
   * Bei der Bilinear methode werden noch die kommawerte mit einbezogn um den genauen nachbarn zu ermitteln.
   * Dafür werden die dierketen nachbarn nach dem kommwert unterschiedlich stark gewichtet.
   */
  public void run(final String arg) {

    final ImageProcessor ipOld = imp.getProcessor();

    final String[] methods = { "Nearest-Neighbor", "Bilinear" };
    final GenericDialog gd = new GenericDialog("Scale");
    gd.addChoice("Sampling method", methods, methods[0]);
    gd.addSlider("X", 1, 4000, ipOld.getWidth());
    gd.addSlider("Y", 1, 4000, ipOld.getHeight());

    gd.showDialog();

    if (gd.wasOKed()) {
      int counter = 0;
      final int method = gd.getNextChoiceIndex();
      final int sx = (int) gd.getNextNumber();
      final int sy = (int) gd.getNextNumber();

      final ImagePlus newImage = NewImage.createRGBImage("Scaled Pic: " + sx + "X " + sy + "Y", sx, sy, 1,
          NewImage.FILL_BLACK);

      final ImageProcessor ipNew = newImage.getProcessor();

      final int[] pixOld = (int[]) ipOld.getPixels();
      final int[] pixNew = (int[]) ipNew.getPixels();

      for (int i = 0; i < ipNew.getWidth(); ++i) {
        for (int j = 0; j < ipNew.getHeight(); ++j) {
          if (method == 0) {
            final int oldX = i * ipOld.getWidth() / ipNew.getWidth();
            final int oldY = j * ipOld.getHeight() / ipNew.getHeight();
            pixNew[j * ipNew.getWidth() + i] = pixOld[oldY * ipOld.getWidth() + oldX];
          }

          if (method == 1) {
            float restAfterCommaX = 1f * i * ipOld.getWidth() / ipNew.getWidth();
            float restAfterCommaY = 1f * j * ipOld.getHeight() / ipNew.getHeight();

            final int oldXl = (int) Math.floor(restAfterCommaX);
            final int oldYu = (int) Math.floor(restAfterCommaY);

            restAfterCommaX -= oldXl;
            restAfterCommaY -= oldYu;

            restAfterCommaX = 1 - restAfterCommaX;
            restAfterCommaY = 1 - restAfterCommaY;

            final int oldXr = Math.min(ipOld.getWidth() - 1, oldXl + 1);
            final int oldYd = Math.min(ipOld.getHeight() - 1, oldYu + 1);

            /*
             * Hier werden die einzelnen Pixel 4 gelesen und der Durchschnitt
             * berechnet.
             */
            // up -left
            final int pixelUL = pixOld[oldYu * ipOld.getWidth() + oldXl];

            final int rUL = (pixelUL & 0x00ff0000) >> 16;
            final int gUL = (pixelUL & 0x0000ff00) >> 8;
            final int bUL = pixelUL & 0x000000ff;

            // down -left
            final int pixelDL = pixOld[oldYd * ipOld.getWidth() + oldXl];

            final int rDL = (pixelDL & 0x00ff0000) >> 16;
            final int gDL = (pixelDL & 0x0000ff00) >> 8;
            final int bDL = pixelDL & 0x000000ff;

            // up -right

            final int pixelUR = pixOld[oldYu * ipOld.getWidth() + oldXr];

            final int rUR = (pixelUR & 0x00ff0000) >> 16;
            final int gUR = (pixelUR & 0x0000ff00) >> 8;
            final int bUR = pixelUR & 0x000000ff;

            // down - right

            final int pixelDR = pixOld[oldYd * ipOld.getWidth() + oldXr];

            final int rDR = (pixelDR & 0x00ff0000) >> 16;
            final int gDR = (pixelDR & 0x0000ff00) >> 8;
            final int bDR = pixelDR & 0x000000ff;

            // neu berechnung der pixel

            final float factorUL = restAfterCommaX * restAfterCommaY;
            final float factorUR = (1f - restAfterCommaX) * restAfterCommaY;
            final float factorDL = restAfterCommaX * (1f - restAfterCommaY);
            final float factorDR = (1f - restAfterCommaX) * (1f - restAfterCommaY);

            int r = Math.round(factorUL * rUL + factorUR * rUR + factorDL * rDL + factorDR * rDR);
            int g = Math.round(factorUL * gUL + factorUR * gUR + factorDL * gDL + factorDR * gDR);
            int b = Math.round(factorUL * bUL + factorUR * bUR + factorDL * bDL + factorDR * bDR);

            pixNew[j * ipNew.getWidth() + i] = 0xff << 24 | r << 16 | g << 8 | b;
            ++counter;
            if (counter == 1000) {
              float x = i * 100 / ipNew.getWidth();
              System.out.println(Math.round(++x) + "%");
              counter = 0;
            }

          }
        }
      }

      newImage.show();
      ImageObserver.getInstance().sendMessage(1);
    }
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
    result = prime * result + (imp == null ? 0 : imp.hashCode());
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
    ScaleImage other = (ScaleImage) obj;
    if (imp == null) {
      if (other.imp != null) {
        return false;
      }
    } else if (!imp.equals(other.imp)) {
      return false;
    }
    return true;
  }
}
