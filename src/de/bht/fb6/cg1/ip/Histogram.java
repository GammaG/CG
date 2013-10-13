package de.bht.fb6.cg1.ip;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import de.bht.fb6.cg1.colorconverter.CMY;
import de.bht.fb6.cg1.colorconverter.HSV;
import de.bht.fb6.cg1.colorconverter.RGB;
import de.bht.fb6.cg1.colorconverter.YUV;

/**
 * This class represents a plug in for ImageJ. It removes color components from
 * an image.
 * 
 * @author Stephan Rehfeld <rehfeld (-at-) beuth-hochschule.de >
 */
public class Histogram implements PlugIn {

  private final ImagePlus imp;

  /**
   * This main method stars ImageJ and applies this plug in to the loaded image.
   * 
   * @param imp
   * 
   * @param args
   *          Ignored.
   */

  public Histogram(ImagePlus imp) {
    this.imp = imp;
  }

  /**
   * Hier wird anhand des im dialog angegebenen Farbraumes ein historgramm
   * erzeugt. Dafür werdne die einzelenen Farbwerte der RGB punkt in den
   * jeweiligen Farbbraum umgerechnet. Diese returnen dann die helligkeit. Dabei
   * wird ein counter in ienem array erhöht. Es wird ein maximalwert und später
   * alle werte durch diesen geteilt. Damit wird das historgramm gelichmäßig. Es
   * wid ein komplett neues Image für das historgramm erzeugt und es darin
   * erzeugt.
   */
  @Override
  public void run(final String arg) {

    final String[] methods = { "RGB", "CMY", "YUV", "HSV" };
    final GenericDialog gd = new GenericDialog("Scale");
    gd.addChoice("Histogram ColorSpace", methods, methods[0]);

    gd.showDialog();

    if (gd.wasOKed()) {

      final int method = gd.getNextChoiceIndex();

      int maxInt = 0;
      int brightness = 0;

      final ImageProcessor ipOld = imp.getProcessor();

      final int[] pixOld = (int[]) ipOld.getPixels();

      final int[] histogramData = new int[256];

      for (int i = 0; i < pixOld.length; ++i) {
        // Decomposing pixel data
        int r = (pixOld[i] & 0x00ff0000) >> 16;
        int g = (pixOld[i] & 0x0000ff00) >> 8;
        int b = pixOld[i] & 0x000000ff;

        RGB rgb = new RGB(r, g, b);

        if (method == 0) {
          brightness = rgb.getBrightness();
        } else if (method == 1) {
          CMY cmy = rgb.toCMY();
          brightness = cmy.getBrightness();
        } else if (method == 2) {
          YUV yuv = rgb.toYUV();
          brightness = yuv.getBrightness();
        } else if (method == 3) {
          HSV hsv = rgb.toHSV();
          brightness = hsv.getBrightness();
        }

        maxInt = Math.max(histogramData[brightness], maxInt);
        ++histogramData[brightness];

      }

      final ImagePlus newImage = NewImage.createRGBImage("Histogram", 255, 150, 1, NewImage.FILL_BLACK);
      final ImageProcessor ipNew = newImage.getProcessor();
      final int[] pixNew = (int[]) ipNew.getPixels();

      for (int y = 0; y < ipNew.getHeight(); ++y) {
        for (int x = 0; x < ipNew.getWidth(); x++) {

          final int r;
          final int g;
          final int b;

          final int histogramIndex = Math.round(x * histogramData.length / ipNew.getWidth());

          if (ipNew.getHeight() - y < histogramData[histogramIndex] * ipNew.getHeight() / maxInt) {

            r = 122;
            g = 114;
            b = 73;

          } else {

            r = 255;
            g = 237;
            b = 138;

          }
          // Composing new pixel value

          final int index = y * ipNew.getWidth() + x;

          pixNew[index] = 0xff << 24 | r << 16 | g << 8 | b;
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
    Histogram other = (Histogram) obj;
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
