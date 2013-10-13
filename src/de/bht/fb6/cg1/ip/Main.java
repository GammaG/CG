package de.bht.fb6.cg1.ip;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.io.FileSaver;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.bht.fb6.cg1.ComponentRemover.CMYComponentRemover;
import de.bht.fb6.cg1.ComponentRemover.HSVComponentRemover;
import de.bht.fb6.cg1.ComponentRemover.RGBComponentRemover;
import de.bht.fb6.cg1.ComponentRemover.YUVComponentRemover;

/**
 * Hier wird die hauptverwaltung aller funktionen der Programms realisiert.
 * 
 * @author Marco Seidler
 * 
 */
public class Main implements Observer {

  /**
   * The ImagePlus object of the loaded image. Hier werden die notwenigen
   * instanzen als Field deklariert.
   */
  private ImagePlus imp;
  private final JFrame frame;
  private JButton bRemover;
  private JButton bHistogram;
  private JButton bopen;
  private JButton bdiffuser;
  private JButton bclose;
  private JButton bScale;
  private JButton bBright;
  private JButton bsave;
  private JButton bcopy;
  @SuppressWarnings("javadoc")
  public int count = 0;
  private JButton binverse;
  private JButton bcloseAll;

  /**
   * Die Static main startet eine Instanz des der Main Klasse.
   * 
   * @param args
   */
  public static void main(final String args[]) {

    new Main();

  }

  /**
   * Hier wird eine interne Klasse erzeugt welche auf die Buttonklicks reagiert
   * und je nachdem unteschieliche funktionen startet. Zudem lädt die auch die
   * zuletzt angeklcikte instant des Bildes welches danach weiterverarbeitet
   * werden kann.
   * 
   * @author admin
   * 
   */

  public class actionEvent implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent evt) {

      try {
        if (evt.getActionCommand().equals("Copyright")) {
          JOptionPane.showMessageDialog(null, "© by Marco Seidler \n CG-2012 \n s780347", "Copyright",
              JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        /**
         * System exit beendet alle instanzen und schließt auch alle bilder die
         * noch geöffnet sind.
         */
        if (evt.getActionCommand().equals("Exit")) {

          System.exit(0);

        }

        if (evt.getActionCommand().equals("Close all Images")) {

          while (count > 0) {
            if (IJ.getImage() instanceof ImagePlus) {
              IJ.getImage().close();
              count--;

            }
          }
          return;
        }

        /**
         * Ein bild wird geladen und der instanz imp zugewiesen.
         */
        if (evt.getActionCommand().equals("Open New")) {
          IJ.open("");
          imp = IJ.getImage();

          if (imp == null) {
            throw new IllegalArgumentException("The parameter 'imp' must not be null!");
          }

          imp.show();
          count++;
          return;

        }

        /**
         * zuletzt geklickte bild wird ausgewählt.
         */
        if (count > 0) {
          imp = IJ.getImage();
        }

        if (evt.getActionCommand().equals("Save")) {
          if (count > 0) {
            new FileSaver(imp).saveAsPng();

          }
          return;

        }
        /**
         * Die verscchiedenen Funktionen werden instanziert und die run methode
         * aufgerufen, sowie das Image(imp) übergeben.
         */
        if (evt.getActionCommand().equals("Histogram")) {
          Histogram histogram = new Histogram(imp);
          histogram.run(" ");
          return;

        }
        if (evt.getActionCommand().equals("ComponentRemover")) {
          startRemover();
          return;

        }

        if (evt.getActionCommand().equals("Diffuser")) {
          Diffuser diffuser = new Diffuser(imp);
          diffuser.run(" ");
          return;
        }
        if (evt.getActionCommand().equals("ScaleImage")) {
          ScaleImage scaler = new ScaleImage(imp);
          scaler.run(" ");
          return;
        }

        if (evt.getActionCommand().equals("Brightness")) {
          Brightness bright = new Brightness(imp);
          bright.run(" ");
          return;
        }
        if (evt.getActionCommand().equals("Inverse")) {
          Inverse inv = new Inverse(imp);
          inv.run(" ");
          return;
        }

      } catch (IllegalArgumentException ex) {
        System.out.println(ex);
      }

    }
  }

  /**
   * Listned darauf ob das fenster geschlossen wird. Damit auch ohne das der
   * Exit button benutzt wurde alle instanzen beendet werden.
   * 
   * @author admin
   * 
   */
  public class FensterEvent extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent arg0) {
      System.exit(0);
    }
  }

  /**
   * Konstruktor von main, hier wirden der frame erstellt und befüllt. Auf die
   * buttons sowie den frame an sich werden listener gelegt. Es wir gesagt das
   * sich frame immer im vordergrund befinden soll, und das fram sichbar sein
   * soll.
   */

  public Main() {

    frame = new JFrame("Photoshop");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTabbedPane tabbedArea = new JTabbedPane();
    tabbedArea.add("Mainfunctions", makePanelMain());
    tabbedArea.add("Filter", makePanelFilter());
    tabbedArea.setBackground(Color.WHITE);
    frame.setBackground(Color.WHITE);
    frame.add(tabbedArea);
    bHistogram.addActionListener(new actionEvent());
    bRemover.addActionListener(new actionEvent());
    bsave.addActionListener(new actionEvent());
    bopen.addActionListener(new actionEvent());
    bdiffuser.addActionListener(new actionEvent());
    bclose.addActionListener(new actionEvent());
    bScale.addActionListener(new actionEvent());
    bBright.addActionListener(new actionEvent());
    bcopy.addActionListener(new actionEvent());
    binverse.addActionListener(new actionEvent());
    bcloseAll.addActionListener(new actionEvent());
    frame.addWindowListener(new FensterEvent());
    frame.pack();
    frame.setVisible(true);
    frame.setAlwaysOnTop(true);
    ImageObserver.getInstance().addObserver(this);

  }

  /**
   * Hier werden den buttons instanzen zugewiesen und ein eindeutiger name
   * vergeben. Diese weden auf ein pannel gesetzt, welches danach returnd wird.
   * 
   * @return
   */
  private JPanel makePanelMain() {
    JPanel panel = new JPanel(new GridLayout(3, 2));
    panel.setBackground(Color.white);

    bopen = new JButton("Open New");
    bclose = new JButton("Exit");
    bsave = new JButton("Save");
    bcopy = new JButton("Copyright");
    bcloseAll = new JButton("Close all Images");

    panel.add(bopen);
    panel.add(bsave);
    panel.add(bcloseAll);
    panel.add(new JButton());
    panel.add(bcopy);
    panel.add(bclose);

    return panel;
  }

  /**
   * Hier werden den buttons instanzen zugewiesen und ein eindeutiger name
   * vergeben. Diese weden auf ein pannel gesetzt, welches danach returnd wird.
   * 
   * @return
   */

  private JPanel makePanelFilter() {
    JPanel panel = new JPanel(new GridLayout(3, 2));
    panel.setBackground(Color.white);

    bRemover = new JButton("ComponentRemover");
    bHistogram = new JButton("Histogram");
    bdiffuser = new JButton("Diffuser");
    bScale = new JButton("ScaleImage");
    bBright = new JButton("Brightness");
    binverse = new JButton("Inverse");

    panel.add(bRemover);
    panel.add(bHistogram);
    panel.add(bdiffuser);
    panel.add(bBright);
    panel.add(bScale);
    panel.add(binverse);
    return panel;
  }

  /**
   * Hier wird die Removerklasse gestartet, dabei wird ein auswahldialog
   * erzeugt, welches nach der aswahl des Farbraums eine instanz vom jeweiligem
   * remover erstellt und das Image (imp) übergibt.
   */
  private void startRemover() {
    final String[] methods = { "RGB", "CMY", "YUV", "HSV" };
    final GenericDialog gd = new GenericDialog("Remove");
    gd.addChoice("Choose ColorSpace", methods, methods[0]);

    gd.showDialog();

    final int method = gd.getNextChoiceIndex();
    if (gd.wasOKed()) {
      if (method == 0) {
        RGBComponentRemover rgb = new RGBComponentRemover(imp);
        rgb.run(" ");
      }
      if (method == 1) {
        CMYComponentRemover cmy = new CMYComponentRemover(imp);
        cmy.run(" ");
      }
      if (method == 2) {
        YUVComponentRemover yuv = new YUVComponentRemover(imp);
        yuv.run(" ");
      }
      if (method == 3) {
        HSVComponentRemover hsv = new HSVComponentRemover(imp);
        hsv.run(" ");
      }
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    count++;

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
    result = prime * result + (bBright == null ? 0 : bBright.hashCode());
    result = prime * result + (bHistogram == null ? 0 : bHistogram.hashCode());
    result = prime * result + (bRemover == null ? 0 : bRemover.hashCode());
    result = prime * result + (bScale == null ? 0 : bScale.hashCode());
    result = prime * result + (bclose == null ? 0 : bclose.hashCode());
    result = prime * result + (bcloseAll == null ? 0 : bcloseAll.hashCode());
    result = prime * result + (bcopy == null ? 0 : bcopy.hashCode());
    result = prime * result + (bdiffuser == null ? 0 : bdiffuser.hashCode());
    result = prime * result + (binverse == null ? 0 : binverse.hashCode());
    result = prime * result + (bopen == null ? 0 : bopen.hashCode());
    result = prime * result + (bsave == null ? 0 : bsave.hashCode());
    result = prime * result + count;
    result = prime * result + (frame == null ? 0 : frame.hashCode());
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
    Main other = (Main) obj;
    if (bBright == null) {
      if (other.bBright != null) {
        return false;
      }
    } else if (!bBright.equals(other.bBright)) {
      return false;
    }
    if (bHistogram == null) {
      if (other.bHistogram != null) {
        return false;
      }
    } else if (!bHistogram.equals(other.bHistogram)) {
      return false;
    }
    if (bRemover == null) {
      if (other.bRemover != null) {
        return false;
      }
    } else if (!bRemover.equals(other.bRemover)) {
      return false;
    }
    if (bScale == null) {
      if (other.bScale != null) {
        return false;
      }
    } else if (!bScale.equals(other.bScale)) {
      return false;
    }
    if (bclose == null) {
      if (other.bclose != null) {
        return false;
      }
    } else if (!bclose.equals(other.bclose)) {
      return false;
    }
    if (bcloseAll == null) {
      if (other.bcloseAll != null) {
        return false;
      }
    } else if (!bcloseAll.equals(other.bcloseAll)) {
      return false;
    }
    if (bcopy == null) {
      if (other.bcopy != null) {
        return false;
      }
    } else if (!bcopy.equals(other.bcopy)) {
      return false;
    }
    if (bdiffuser == null) {
      if (other.bdiffuser != null) {
        return false;
      }
    } else if (!bdiffuser.equals(other.bdiffuser)) {
      return false;
    }
    if (binverse == null) {
      if (other.binverse != null) {
        return false;
      }
    } else if (!binverse.equals(other.binverse)) {
      return false;
    }
    if (bopen == null) {
      if (other.bopen != null) {
        return false;
      }
    } else if (!bopen.equals(other.bopen)) {
      return false;
    }
    if (bsave == null) {
      if (other.bsave != null) {
        return false;
      }
    } else if (!bsave.equals(other.bsave)) {
      return false;
    }
    if (count != other.count) {
      return false;
    }
    if (frame == null) {
      if (other.frame != null) {
        return false;
      }
    } else if (!frame.equals(other.frame)) {
      return false;
    }
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
