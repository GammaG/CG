package de.bht.fb6.cg1.ip;

import ij.ImagePlus;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

/**
 * This class represents a plug in for ImageJ. It changes the Color from the
 * image to the inverse.
 * 
 * @author Marco Seidler
 */
public class Inverse implements PlugIn {

  /**
   * The ImagePlus object of the loaded image.
   */
  private final ImagePlus imp;

  /**
   * This constructor creates a new Inverse.
   * 
   * @param imp
   *          The ImagePlus object. Must not be null!
   */
  public Inverse(final ImagePlus imp) {

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

    final ImageProcessor ipOld = imp.getProcessor();

    final ImagePlus newImage = NewImage.createRGBImage("Inverse", ipOld.getWidth(), ipOld.getHeight(), 1,
        NewImage.FILL_BLACK);

    final ImageProcessor ipNew = newImage.getProcessor();

    final int[] pixOld = (int[]) ipOld.getPixels();
    final int[] pixNew = (int[]) ipNew.getPixels();

    for (int i = 0; i < pixNew.length; ++i) {
      // Decomposing pixel data
      int red = (pixOld[i] & 0x00ff0000) >> 16;
      int green = (pixOld[i] & 0x0000ff00) >> 8;
      int blue = pixOld[i] & 0x000000ff;

      // Removing components
      int r = 255 - red;
      int g = 255 - green;
      int b = 255 - blue;

      // Composing new pixel value
      pixNew[i] = 0xff << 24 | r << 16 | g << 8 | b;
    }

    newImage.show();
    ImageObserver.getInstance().sendMessage(1);
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
    Inverse other = (Inverse) obj;
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
