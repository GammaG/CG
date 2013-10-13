package de.bht.fb6.cg1.ComponentRemover;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import de.bht.fb6.cg1.ip.ImageObserver;

/**
 * This class represents a plug in for ImageJ. It removes color components from
 * an image.
 * 
 * @author Marco Seidler
 */
public class RGBComponentRemover implements PlugIn {

  /**
   * The ImagePlus object of the loaded image.
   */
  private final ImagePlus imp;

  /**
   * This constructor creates a new RGBComponentRemover.
   * 
   * @param imp
   *          The ImagePlus object. Must not be null!
   */
  public RGBComponentRemover(final ImagePlus imp) {

    this.imp = imp;
  }

  /**
   * Hier wird anhand der Componente die enfernt werden soll das bild
   * entsprechend entfernt, und das bild neu generiert. (non-Javadoc)
   * 
   * @see ij.plugin.PlugIn#run(java.lang.String)
   */
  @Override
  public void run(final String arg) {

    /**
     * Dialog wird aufgerufen.
     */

    final GenericDialog gd = new GenericDialog("Remove Components");
    gd.addCheckbox("Red", false);
    gd.addCheckbox("Green", false);
    gd.addCheckbox("Blue", false);
    gd.showDialog();

    /**
     * nur wenn ok gesagt wurde wird weitergerechnet
     */
    if (gd.wasOKed()) {

      final boolean red = gd.getNextBoolean();
      final boolean green = gd.getNextBoolean();
      final boolean blue = gd.getNextBoolean();

      final ImageProcessor ipOld = imp.getProcessor();
      String text = "Removed: R = " + red + " G = " + green + " B = " + blue;
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

        // Removing components
        if (red) {
          r = 0;
        }
        if (green) {
          g = 0;
        }
        if (blue) {
          b = 0;
        }

        // Composing new pixel value
        pixNew[i] = 0xff << 24 | r << 16 | g << 8 | b;
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
    RGBComponentRemover other = (RGBComponentRemover) obj;
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
