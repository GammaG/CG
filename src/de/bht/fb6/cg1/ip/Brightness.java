package de.bht.fb6.cg1.ip;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import de.bht.fb6.cg1.colorconverter.HSV;
import de.bht.fb6.cg1.colorconverter.RGB;
import de.bht.fb6.cg1.colorconverter.YUV;

/**
 * 
 * 
 * @author Marco Seidler
 */
public class Brightness implements PlugIn {

  private final ImagePlus imp;

  /**
   * This main method stars ImageJ and applies this plug in to the loaded image.
   * 
   * @param imp
   * 
   * @param args
   *          Ignored.
   */

  public Brightness(ImagePlus imp) {
    this.imp = imp;
  }

  @Override
  /**
   * Hier wird von einem Übergebenen Image in dem Jeweiligem Farbraum einen Neue Helligkeit gesetzt.
   * Dabei wird die helligkeitserhohüng auf 300% bzw 1% (komplett schwarzes bild) begrenzt wird.
   * Der Rgb wert des jeweiligen Pixels wird in den vorher ausgewählten farbraum umgerechnet und es erfolgt eine veränderung des wertes der für die helligkeit verantwortlich ist.
   *  In Rgb sind dies alle drei werte zusammen /3.
   *   
   */
  public void run(final String arg) {

    final String[] methods = { "RGB", "YUV", "HSV" };
    final GenericDialog gd = new GenericDialog("Brightness");
    gd.addChoice("Colorspace", methods, methods[0]);
    gd.addSlider("Percent", 1, 300, 100);

    gd.showDialog();

    if (gd.wasOKed()) {

      final int method = gd.getNextChoiceIndex();
      final int percent = (int) gd.getNextNumber();

      final ImageProcessor ipPic = imp.getProcessor();

      final ImagePlus newImage = NewImage.createRGBImage("Brightness " + percent + "% " + methods[method],
          ipPic.getWidth(), ipPic.getHeight(), 1, NewImage.FILL_BLACK);

      final ImageProcessor ipNew = newImage.getProcessor();
      int[] tempOld = (int[]) ipPic.getPixels();
      int[] tempNew = (int[]) ipNew.getPixels();

      for (int i = 0; i < tempOld.length; ++i) {
        // Decomposing pixel data
        int r = (tempOld[i] & 0x00ff0000) >> 16;
        int g = (tempOld[i] & 0x0000ff00) >> 8;
        int b = tempOld[i] & 0x000000ff;
        if (r == 0) {
          r++;
        }
        if (g == 0) {
          g++;
        }
        if (b == 0) {
          b++;
        }

        if (method == 0) {

          RGB rgb = new RGB(Math.round(r / 100f * percent), Math.round(g / 100f * percent), Math.round(b / 100f
              * percent));
          r = rgb.getRed();
          g = rgb.getGreen();
          b = rgb.getBlue();

        } else if (method == 1) {
          RGB rgb = new RGB(r, g, b);
          YUV yuv = rgb.toYUV();
          yuv.setY(Math.round(yuv.getY() / 100f * percent));
          rgb = yuv.toRGB();
          r = rgb.getRed();
          g = rgb.getGreen();
          b = rgb.getBlue();
        }

        else if (method == 2) {
          RGB rgb = new RGB(r, g, b);
          HSV hsv = rgb.toHSV();
          hsv.setV(Math.round(hsv.getV() / 100f * percent));
          rgb = hsv.toRGB();
          r = rgb.getRed();
          g = rgb.getGreen();
          b = rgb.getBlue();

        }

        tempNew[i] = 0xff << 24 | r << 16 | g << 8 | b;

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
    Brightness other = (Brightness) obj;
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
