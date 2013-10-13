package de.bht.fb6.cg1.ComponentRemover;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import de.bht.fb6.cg1.colorconverter.CMY;
import de.bht.fb6.cg1.colorconverter.RGB;
import de.bht.fb6.cg1.ip.ImageObserver;

/**
 * This class represents a plug in for ImageJ. It removes color components from
 * an image.
 * 
 * @author Marco Seidler
 */
public class CMYComponentRemover implements PlugIn {

  /**
   * The ImagePlus object of the loaded image.
   */
  private final ImagePlus imp;

  /**
   * This constructor creates a new CMYComponentRemover.
   * 
   * @param imp
   *          The ImagePlus object. Must not be null!
   */
  public CMYComponentRemover(final ImagePlus imp) {

    this.imp = imp;
  }

  @Override
  /**
   * Hier wird anhand der Componente die enfernt werden soll das bild
   * entsprechend entfernt, und das bild neu generiert. (non-Javadoc)
   * 
   * @see ij.plugin.PlugIn#run(java.lang.String)
   */
  public void run(final String arg) {

    /**
     * Dialog wird aufgerufen.
     */

    final GenericDialog gd = new GenericDialog("Remove Components");
    gd.addCheckbox("C", false);
    gd.addCheckbox("M", false);
    gd.addCheckbox("Y", false);
    gd.showDialog();

    /**
     * nur wenn ok gesagt wurde wird weitergerechnet
     */
    if (gd.wasOKed()) {

      final boolean c = gd.getNextBoolean();
      final boolean m = gd.getNextBoolean();
      final boolean y = gd.getNextBoolean();

      String text = "Removed: C = " + c + " M = " + m + " Y = " + y;

      final ImageProcessor ipOld = imp.getProcessor();
      final ImagePlus newImage = NewImage.createRGBImage(text, ipOld.getWidth(), ipOld.getHeight(), 1,
          NewImage.FILL_BLACK);

      final ImageProcessor ipNew = newImage.getProcessor();

      final int[] pixOld = (int[]) ipOld.getPixels();
      final int[] pixNew = (int[]) ipNew.getPixels();

      for (int i = 0; i < pixNew.length; ++i) {
        // Decomposing pixel data
        int r = (pixOld[i] & 0x00ff0000) >> 16;
        int g = (pixOld[i] & 0x0000ff00) >> 8;
        int b = pixOld[i] & 0x000000ff;

        RGB rgb = new RGB(r, g, b);
        // Removing components
        CMY cmy = rgb.toCMY();

        if (c) {
          cmy.setC(255);
        }
        if (m) {
          cmy.setM(255);
        }
        if (y) {
          cmy.setY2(255);
        }

        rgb = cmy.toRGB();

        // Composing new pixel value
        pixNew[i] = 0xff << 24 | rgb.getRed() << 16 | rgb.getGreen() << 8 | rgb.getBlue();
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
    CMYComponentRemover other = (CMYComponentRemover) obj;
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
