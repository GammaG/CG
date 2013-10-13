package de.bht.fb6.cg1.ip;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

/**
 * Hier wird die Klasse Diffuser erstellt.
 * 
 * @author Marco Seidler
 * 
 */
public class Diffuser implements PlugIn {

  private final ImagePlus imp;

  /**
   * @param imp
   */
  public Diffuser(final ImagePlus imp) {
    this.imp = imp;

  }

  /**
   * Hier wird für ein übergebenes Image eine weichzeichnung vorgenommen. Dabei
   * wird für den benachbarten pixel jeweils anhand der nebenliegenden 13
   * pixel(12 + M) in durchschnittswert berechnet, nach diesen neuem Farbwert
   * wird ein Bild erzeugt. Der vorgang kann prinzipell belibig oft widerholt
   * werden (Rounds). Allerings ist es ratsam ein limit anzugeben da der vorgang
   * sonst sehr lange dauern kann, zum buffer overflow führen kann. (Hier auf 20
   * rounds begrenzt.)
   * 
   */
  @Override
  public void run(String arg0) {

    final GenericDialog gd = new GenericDialog("Diffuser-Counter");
    gd.addSlider("Rounds", 1, 100, 1);
    gd.showDialog();

    if (gd.wasOKed()) {

      int rounds = (int) gd.getNextNumber();

      final ImageProcessor ipPic = imp.getProcessor();

      final ImagePlus newImage = NewImage.createRGBImage("Diffused/ " + rounds + " -Times", ipPic.getWidth(),
          ipPic.getHeight(), 1, NewImage.FILL_BLACK);

      final ImageProcessor ipNew = newImage.getProcessor();
      int[] tempOld = (int[]) ipPic.getPixels();
      int[] tempNew = (int[]) ipNew.getPixels();

      for (int i = 0; i < rounds; ++i) {
        for (int nx = 0; nx < ipNew.getWidth(); ++nx) {
          for (int ny = 0; ny < ipNew.getHeight(); ++ny) {
            // l = left , m = middle, r = right
            int ll = Math.max(0, nx - 2);
            int lx = Math.max(0, nx - 1);
            int mx = nx;
            int rx = Math.min(ipNew.getWidth() - 1, nx + 1);
            int rr = Math.min(ipNew.getWidth() - 1, nx + 2);

            // u = up , m = middle , d = down

            int uu = Math.max(0, ny - 2);
            int uy = Math.max(0, ny - 1);
            int my = ny;
            int dy = Math.min(ipNew.getHeight() - 1, ny + 1);
            int dd = Math.min(ipNew.getHeight() - 1, ny + 2);

            /*
             * Here the 12 surrounding Pixel and the middle point = 13. Are been
             * taken for RGB channel
             */

            // #1 up-left

            final int pixelUL = tempOld[uy * ipPic.getWidth() + lx];

            final int rUL = (pixelUL & 0x00ff0000) >> 16;
            final int gUL = (pixelUL & 0x0000ff00) >> 8;
            final int bUL = pixelUL & 0x000000ff;

            // #2 up-middle

            final int pixelUM = tempOld[uy * ipPic.getWidth() + mx];

            final int rUM = (pixelUM & 0x00ff0000) >> 16;
            final int gUM = (pixelUM & 0x0000ff00) >> 8;
            final int bUM = pixelUM & 0x000000ff;

            // #3 up-right

            final int pixelUR = tempOld[uy * ipPic.getWidth() + rx];

            final int rUR = (pixelUR & 0x00ff0000) >> 16;
            final int gUR = (pixelUR & 0x0000ff00) >> 8;
            final int bUR = pixelUR & 0x000000ff;

            // #4 Middle-left

            final int pixelML = tempOld[my * ipPic.getWidth() + lx];

            final int rML = (pixelML & 0x00ff0000) >> 16;
            final int gML = (pixelML & 0x0000ff00) >> 8;
            final int bML = pixelML & 0x000000ff;

            // #5 Middle-middle

            final int pixelMM = tempOld[my * ipPic.getWidth() + mx];

            final int rMM = (pixelMM & 0x00ff0000) >> 16;
            final int gMM = (pixelMM & 0x0000ff00) >> 8;
            final int bMM = pixelMM & 0x000000ff;

            // #6 Middle-right

            final int pixelMR = tempOld[my * ipPic.getWidth() + rx];

            final int rMR = (pixelMR & 0x00ff0000) >> 16;
            final int gMR = (pixelMR & 0x0000ff00) >> 8;
            final int bMR = pixelMR & 0x000000ff;

            // #7 Down-left

            final int pixelDL = tempOld[dy * ipPic.getWidth() + lx];

            final int rDL = (pixelDL & 0x00ff0000) >> 16;
            final int gDL = (pixelDL & 0x0000ff00) >> 8;
            final int bDL = pixelDL & 0x000000ff;

            // #8 Down-middle

            final int pixelDM = tempOld[dy * ipPic.getWidth() + mx];

            final int rDM = (pixelDM & 0x00ff0000) >> 16;
            final int gDM = (pixelDM & 0x0000ff00) >> 8;
            final int bDM = pixelDM & 0x000000ff;

            // #9 Down-right

            final int pixelDR = tempOld[dy * ipPic.getWidth() + rx];

            final int rDR = (pixelDR & 0x00ff0000) >> 16;
            final int gDR = (pixelDR & 0x0000ff00) >> 8;
            final int bDR = pixelDR & 0x000000ff;

            // #10 Up-Up

            final int pixelUU = tempOld[uu * ipPic.getWidth() + mx];

            final int rUU = (pixelUU & 0x00ff0000) >> 16;
            final int gUU = (pixelUU & 0x0000ff00) >> 8;
            final int bUU = pixelUU & 0x000000ff;

            // #11 Left-Left

            final int pixelLL = tempOld[my * ipPic.getWidth() + ll];

            final int rLL = (pixelLL & 0x00ff0000) >> 16;
            final int gLL = (pixelLL & 0x0000ff00) >> 8;
            final int bLL = pixelLL & 0x000000ff;

            // # 12 Right-Right

            final int pixelRR = tempOld[my * ipPic.getWidth() + rr];

            final int rRR = (pixelRR & 0x00ff0000) >> 16;
            final int gRR = (pixelRR & 0x0000ff00) >> 8;
            final int bRR = pixelRR & 0x000000ff;

            // # 13 Down-Down

            final int pixelDD = tempOld[dd * ipPic.getWidth() + mx];

            final int rDD = (pixelDD & 0x00ff0000) >> 16;
            final int gDD = (pixelDD & 0x0000ff00) >> 8;
            final int bDD = pixelDD & 0x000000ff;

            /*
             * Build average.
             */
            int r = Math.round((rUL + rUM + rUR + rML + rMM + rMR + rDL + rDM + rDR + rUU + rLL + rRR + rDD) / 13f);
            int g = Math.round((gUL + gUM + gUR + gML + gMM + gMR + gDL + gDM + gDR + gUU + gLL + gRR + gDD) / 13f);
            int b = Math.round((bUL + bUM + bUR + bML + bMM + bMR + bDL + bDM + bDR + bUU + bLL + bRR + bDD) / 13f);

            tempNew[ny * ipNew.getWidth() + nx] = 0xff << 24 | r << 16 | g << 8 | b;

          }

        }
        tempOld = tempNew;

        float x = i * 100 / rounds;
        if (!(i == rounds - 1)) {
          System.out.println(Math.round(++x) + "%");
        } else {
          System.out.println("100%");
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
    Diffuser other = (Diffuser) obj;
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
