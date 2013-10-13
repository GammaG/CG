package de.bht.fb6.cg1.colorconverter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This class represents the ColorConverter.
 * 
 * An application to convert colors between different color models.
 * 
 * @author Stephan Rehfeld <rehfeld (-at-) beuth-hochschule.de> & Marco Seidler
 *         s780347
 */
public class ColorConverterJFrame extends JFrame implements ChangeListener {

  /**
   * generated SerialVersionUID for long
   */
  private static final long serialVersionUID = 1835107045061098380L;

  /**
   * The main method.
   * 
   * @param args
   *          Console parameters. This main method does not consume any
   *          parameters.
   */
  public static void main(final String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      @Override
      public void run() {
        new ColorConverterJFrame().setVisible(true);
      }

    });
  }

  /**
   * 
   * @author Marco Seidler. The Dokumentlistener is been implemented. The
   *         variable with says it was a Text value is been set to true. The
   *         document source is been gives to the called functions.
   */
  public class actionText implements DocumentListener {

    /**
     * @param e
     */
    public void change(final DocumentEvent e) {
      text = true;
      documentChange(e);

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
      change(e);

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
      change(e);

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
      change(e);

    }

  }

  /**
   * final Integer are gone created. With these the color space can be checked.
   * Max- and minimal of the Sliders are been set in constants.ColorSpace saves
   * the used color space. -1 is the default value & the value if there are
   * errors while parsing.
   */

  private static final int MAX_0 = 255;
  private static final int MAX_1 = 157;
  private static final int MIN_1 = -157;
  private static final int MAX_2 = 111;
  private static final int MIN_2 = -111;

  private static final int RGB = 0;
  private static final int CMY = 1;
  private static final int YUV = 2;
  private static final int HSV = 3;

  private int colorSpace = -1;

  /**
   * Variable with is been using to check if the event source was a slider or a
   * TextField. Is been instanced.
   * 
   * 
   */
  private boolean text = false;
  /**
   * This attribute indicates that the program is currently updating the slider
   * and should not react on value changes.
   */
  private boolean update;

  /**
   * This is the canvas that shows the current color.
   */
  private Canvas colorCanvas;

  /**
   * The current color.
   */
  private Color currentColor;

  /**
   * The panel that holds all controls for RGB color.
   */
  private JPanel rgbPanel;

  /**
   * The panel that holds all controls for YUV color.
   */
  private JPanel yuvPanel;

  /**
   * This slider controls the r component of RGB.
   */
  private JSlider rSlider;

  /**
   * This text field shows the current value of the r component of RGB.
   */
  private JTextField rTextField;

  /**
   * This slider controls the g component of RGB.
   */
  private JSlider gSlider;

  /**
   * This text field shows the current value of the g component of RGB.
   */
  private JTextField gTextField;

  /**
   * This slider controls the b component of RGB.
   */
  private JSlider bSlider;

  /**
   * This text field shows the current value of the b component of RGB.
   */
  private JTextField bTextField;

  /**
   * This slider controls the y component of YUV.
   */
  private JSlider ySlider;

  /**
   * This text field shows the current value of the y component of YUV.
   */
  private JTextField yTextField;

  /**
   * This slider controls the u component of YUV.
   */
  private JSlider uSlider;

  /**
   * This text field shows the current value of the u component of YUV.
   */
  private JTextField uTextField;

  /**
   * This slider controls the v component of YUV.
   */
  private JSlider vSlider;

  /**
   * This text field shows the current value of the v component of YUV.
   */
  private JTextField vTextField;

  /**
   * This Panel contains all the attributes of the CMY controll.
   */

  private JPanel cmyPanel;

  /**
   * This text field shows the current value of the c component of CMY.
   */

  private JTextField cTextField;

  /**
   * This Panel shows the current value of the Slider with attribute c of CMY.
   */

  private JSlider cSlider;
  /**
   * This Panel shows the current value of the Slider with attribute m of CMY.
   */

  private JSlider mSlider;

  /**
   * This text field shows the current value of the m component of CMY.
   */
  private JTextField mTextField;

  /**
   * This Panel shows the current value of the Slider with attribute y of CMY.
   */

  private JSlider y2Slider;
  /**
   * This text field shows the current value of the y component of CMY.
   */

  private JTextField y2TextField;
  /**
   * This Panel contains all the attributes of the HSV controll.
   */

  private JPanel hsvPanel;
  /**
   * This Panel shows the current value of the Slider with attribute h of HSV.
   */

  private JSlider hSlider;
  /**
   * This text field shows the current value of the h component of HSV.
   */
  private JTextField hTextField;
  /**
   * This Panel shows the current value of the Slider with attribute s of HSV.
   */
  private JSlider sSlider;
  /**
   * This text field shows the current value of the s component of HSV.
   */
  private JTextField sTextField;
  /**
   * This Panel shows the current value of the Slider with attribute v of HSV.
   */
  private JSlider v2Slider;
  /**
   * This text field shows the current value of the v component of HSV.
   */
  private JTextField v2TextField;

  /**
   * This constructor creates a new Frame of the ColorConverter with the size
   * 640x480. It already close it self if requested and has the correct frame
   * title.
   * 
   */
  public ColorConverterJFrame() {
    super("Color Converter");
    this.update = false;
    this.setSize(640, 480);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new GridLayout(0, 1));

    this.currentColor = Color.WHITE;

    this.createColorCanvas();
    this.createRGBPanel();
    this.createYUVPanel();
    this.createCMYPanel();
    this.createHSVPanel();
    this.pack();

  }

  /**
   * This method creates the color canvas
   */
  private void createColorCanvas() {
    this.colorCanvas = new Canvas();
    this.colorCanvas.setBackground(this.currentColor);
    this.add(this.colorCanvas);

  }

  /**
   * This method creates the panel for RGB
   */
  private void createRGBPanel() {
    this.rgbPanel = new JPanel();
    this.rgbPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    this.add(this.rgbPanel);
    this.rgbPanel.setLayout(new GridLayout(3, 3));

    final JLabel rLabel = new JLabel("R");
    this.rgbPanel.add(rLabel);

    this.rSlider = new JSlider(0, MAX_0, MAX_0);
    this.rgbPanel.add(this.rSlider);
    this.rSlider.addChangeListener(this);

    this.rTextField = new JTextField();
    this.rgbPanel.add(this.rTextField);
    this.rTextField.setText("255");
    this.rTextField.setEnabled(true);
    this.rTextField.getDocument().addDocumentListener(new actionText());

    final JLabel gLabel = new JLabel("G");
    this.rgbPanel.add(gLabel);

    this.gSlider = new JSlider(0, MAX_0, MAX_0);
    this.rgbPanel.add(this.gSlider);
    this.gSlider.addChangeListener(this);

    this.gTextField = new JTextField();
    this.rgbPanel.add(this.gTextField);
    this.gTextField.setText("255");
    this.gTextField.setEnabled(true);
    this.gTextField.getDocument().addDocumentListener(new actionText());

    final JLabel bLabel = new JLabel("B");
    this.rgbPanel.add(bLabel);

    this.bSlider = new JSlider(0, MAX_0, MAX_0);
    this.rgbPanel.add(this.bSlider);
    this.bSlider.addChangeListener(this);

    this.bTextField = new JTextField();
    this.rgbPanel.add(this.bTextField);
    this.bTextField.setText("255");
    this.bTextField.setEnabled(true);
    this.bTextField.getDocument().addDocumentListener(new actionText());
  }

  /**
   * This method creates the panel for YUV
   */
  private void createYUVPanel() {
    this.yuvPanel = new JPanel();
    this.yuvPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    this.add(this.yuvPanel);
    this.yuvPanel.setLayout(new GridLayout(3, 3));

    final JLabel yLabel = new JLabel("Y");
    this.yuvPanel.add(yLabel);

    this.ySlider = new JSlider(0, MAX_0, MAX_0);
    this.yuvPanel.add(this.ySlider);
    this.ySlider.addChangeListener(this);

    this.yTextField = new JTextField();
    this.yuvPanel.add(this.yTextField);
    this.yTextField.setText("255");
    this.yTextField.setEnabled(true);
    this.yTextField.getDocument().addDocumentListener(new actionText());

    final JLabel uLabel = new JLabel("U");
    this.yuvPanel.add(uLabel);

    this.uSlider = new JSlider(MIN_2, MAX_2, 0);
    this.yuvPanel.add(this.uSlider);

    this.uTextField = new JTextField();
    this.yuvPanel.add(this.uTextField);
    this.uTextField.setText("0");
    this.uTextField.setEnabled(true);
    this.uTextField.getDocument().addDocumentListener(new actionText());

    this.uSlider.addChangeListener(this);

    final JLabel vLabel = new JLabel("V");
    this.yuvPanel.add(vLabel);

    this.vSlider = new JSlider(MIN_1, MAX_1, 0);
    this.yuvPanel.add(this.vSlider);

    this.vTextField = new JTextField();
    this.yuvPanel.add(this.vTextField);
    this.vTextField.setText("0");
    this.vTextField.setEnabled(true);
    this.vSlider.addChangeListener(this);
    this.vTextField.getDocument().addDocumentListener(new actionText());
  }

  /**
   * This method creates the panel for CMY.
   */
  private void createCMYPanel() {
    this.cmyPanel = new JPanel();
    this.cmyPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    this.add(this.cmyPanel);
    this.cmyPanel.setLayout(new GridLayout(3, 3));

    final JLabel cLabel = new JLabel("C");
    this.cmyPanel.add(cLabel);

    this.cSlider = new JSlider(0, MAX_0, MAX_0);
    this.cmyPanel.add(this.cSlider);
    this.cSlider.addChangeListener(this);

    this.cTextField = new JTextField();
    this.cmyPanel.add(this.cTextField);
    this.cTextField.setText("255");
    this.cTextField.setEnabled(true);
    this.cTextField.getDocument().addDocumentListener(new actionText());

    final JLabel mLabel = new JLabel("M");
    this.cmyPanel.add(mLabel);

    this.mSlider = new JSlider(0, MAX_0, MAX_0);
    this.cmyPanel.add(this.mSlider);
    this.mSlider.addChangeListener(this);

    this.mTextField = new JTextField();
    this.cmyPanel.add(this.mTextField);
    this.mTextField.setText("255");
    this.mTextField.setEnabled(true);
    this.mTextField.getDocument().addDocumentListener(new actionText());

    final JLabel yLabel = new JLabel("Y");
    this.cmyPanel.add(yLabel);

    this.y2Slider = new JSlider(0, MAX_0, MAX_0);
    this.cmyPanel.add(this.y2Slider);
    this.y2Slider.addChangeListener(this);

    this.y2TextField = new JTextField();
    this.cmyPanel.add(this.y2TextField);
    this.y2TextField.setText("255");
    this.y2TextField.setEnabled(true);
    this.y2TextField.getDocument().addDocumentListener(new actionText());

  }

  /**
   * This method creates the panel for HSV
   */
  private void createHSVPanel() {
    this.hsvPanel = new JPanel();
    this.hsvPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    this.add(this.hsvPanel);
    this.hsvPanel.setLayout(new GridLayout(3, 3));

    final JLabel hLabel = new JLabel("H");
    this.hsvPanel.add(hLabel);

    this.hSlider = new JSlider(0, 360, 0);
    this.hsvPanel.add(this.hSlider);
    this.hSlider.addChangeListener(this);

    this.hTextField = new JTextField();
    this.hsvPanel.add(this.hTextField);
    this.hTextField.setText("0");
    this.hTextField.setEnabled(true);
    this.hTextField.getDocument().addDocumentListener(new actionText());

    final JLabel sLabel = new JLabel("S");
    this.hsvPanel.add(sLabel);

    this.sSlider = new JSlider(0, 100, 0);
    this.hsvPanel.add(this.sSlider);
    this.sSlider.addChangeListener(this);

    this.sTextField = new JTextField();
    this.hsvPanel.add(this.sTextField);
    this.sTextField.setText("0");
    this.sTextField.setEnabled(true);
    this.sTextField.getDocument().addDocumentListener(new actionText());

    final JLabel vLabel = new JLabel("V");
    this.hsvPanel.add(vLabel);

    this.v2Slider = new JSlider(0, 100, 100);
    this.hsvPanel.add(this.v2Slider);
    this.v2Slider.addChangeListener(this);

    this.v2TextField = new JTextField();
    this.hsvPanel.add(this.v2TextField);
    this.v2TextField.setText("100");
    this.v2TextField.setEnabled(true);
    this.v2TextField.getDocument().addDocumentListener(new actionText());

  }

  /**
   * 
   * @param e
   *          The changed document is been sourced. The color space will been
   *          set and the update function is been called.
   * 
   */
  public void documentChange(final DocumentEvent e) {
    if (!this.update) {
      if (e.getDocument() == this.rTextField) {
        colorSpace = RGB;
        this.rSlider.setValue(Integer.parseInt(this.rTextField.getText()));
      } else if (e.getDocument() == this.gTextField) {
        colorSpace = RGB;
        this.gSlider.setValue(Integer.parseInt(this.gTextField.getText()));
      } else if (e.getDocument() == this.bTextField) {
        colorSpace = RGB;
        this.bSlider.setValue(Integer.parseInt(this.bTextField.getText()));
      } else if (e.getDocument() == this.yTextField) {
        colorSpace = YUV;
        this.ySlider.setValue(Integer.parseInt(this.yTextField.getText()));

      } else if (e.getDocument() == this.uTextField) {
        colorSpace = YUV;
        this.uSlider.setValue(Integer.parseInt(this.uTextField.getText()));
      } else if (e.getDocument() == this.vTextField) {
        colorSpace = YUV;
        this.vSlider.setValue(Integer.parseInt(this.vTextField.getText()));
      } else if (e.getDocument() == this.cTextField) {
        colorSpace = CMY;
        this.cSlider.setValue(Integer.parseInt(this.cTextField.getText()));
      } else if (e.getDocument() == this.mTextField) {
        colorSpace = CMY;
        this.mSlider.setValue(Integer.parseInt(this.mTextField.getText()));
      } else if (e.getDocument() == this.y2TextField) {
        colorSpace = CMY;
        this.y2Slider.setValue(Integer.parseInt(this.y2TextField.getText()));

      } else if (e.getDocument() == this.hTextField) {
        colorSpace = HSV;
        this.hSlider.setValue(Integer.parseInt(this.hTextField.getText()));

      } else if (e.getDocument() == this.sTextField) {
        colorSpace = HSV;
        this.sSlider.setValue(Integer.parseInt(this.sTextField.getText()));

      } else if (e.getDocument() == this.v2TextField) {
        colorSpace = HSV;
        this.v2Slider.setValue(Integer.parseInt(this.v2TextField.getText()));

      }
      this.update();
    }

  }

  /**
   * Here the values of the sliders are been readout. The source of change will
   * been checked. The color space will been set.and the update function is
   * called.
   */
  @Override
  public void stateChanged(final ChangeEvent e) {
    if (!this.update) {
      text = false;

      if (e.getSource() == this.rSlider && Integer.parseInt(this.rTextField.getText()) != this.rSlider.getValue()) {
        colorSpace = RGB;
        this.rTextField.setText("" + this.rSlider.getValue());

      } else if (e.getSource() == this.gSlider
          && Integer.parseInt(this.gTextField.getText()) != this.gSlider.getValue()) {
        colorSpace = RGB;
        this.gTextField.setText("" + this.gSlider.getValue());

      } else if (e.getSource() == this.bSlider
          && Integer.parseInt(this.bTextField.getText()) != this.bSlider.getValue()) {
        colorSpace = RGB;
        this.bTextField.setText("" + this.bSlider.getValue());

      } else if (e.getSource() == this.ySlider
          && Integer.parseInt(this.yTextField.getText()) != this.ySlider.getValue()) {
        colorSpace = YUV;
        this.yTextField.setText("" + this.ySlider.getValue());
      } else if (e.getSource() == this.uSlider
          && Integer.parseInt(this.uTextField.getText()) != this.uSlider.getValue()) {
        colorSpace = YUV;
        this.uTextField.setText("" + this.uSlider.getValue());
      } else if (e.getSource() == this.vSlider
          && Integer.parseInt(this.vTextField.getText()) != this.vSlider.getValue()) {
        colorSpace = YUV;
        this.vTextField.setText("" + this.vSlider.getValue());
      } else if (e.getSource() == this.cSlider
          && Integer.parseInt(this.cTextField.getText()) != this.cSlider.getValue()) {
        colorSpace = CMY;
        this.cTextField.setText("" + this.cSlider.getValue());
      } else if (e.getSource() == this.mSlider
          && Integer.parseInt(this.mTextField.getText()) != this.mSlider.getValue()) {
        colorSpace = CMY;
        this.mTextField.setText("" + this.mSlider.getValue());
      } else if (e.getSource() == this.y2Slider
          && Integer.parseInt(this.y2TextField.getText()) != this.y2Slider.getValue()) {
        colorSpace = CMY;
        this.y2TextField.setText("" + this.y2Slider.getValue());
      } else if (e.getSource() == this.hSlider
          && Integer.parseInt(this.hTextField.getText()) != this.hSlider.getValue()) {
        colorSpace = HSV;
        this.hTextField.setText("" + this.hSlider.getValue());
      } else if (e.getSource() == this.sSlider
          && Integer.parseInt(this.sTextField.getText()) != this.sSlider.getValue()) {
        colorSpace = HSV;
        this.sTextField.setText("" + this.sSlider.getValue());
      } else if (e.getSource() == this.v2Slider
          && Integer.parseInt(this.v2TextField.getText()) != this.v2Slider.getValue()) {
        colorSpace = HSV;
        this.v2TextField.setText("" + this.v2Slider.getValue());
      }

      this.update();
    }
  }

  /**
   * This method updates all other components if one component has changed.
   * Furthermore the color of the background panel is been setting. RGB CMY YUV
   * HSV are been instancted. There is a check if the Change was by TextField or
   * Flieder. The gained values gone convert and syncronised to the other
   * colorspaces. This is be shown in TextFields and Sliders. With using of the
   * update boolean, unwanted value changes, when convert is unfished, are
   * stopped. The Exceptionhanding is catching the thrown Exceptions and
   * resprond to the source of thrown the TextField of source will be set
   * backgroundcolor to red. With every new Vaule the Bckgrounds are been
   * setting white if there is no Exception.
   */
  private void update() {
    this.update = true;
    RGB rgb = null;
    HSV hsv = null;
    YUV yuv = null;
    CMY cmy = null;

    try {

      if (colorSpace == RGB) {
        makeWhite();
        if (text) {

          rgb = new RGB(Integer.parseInt(this.rTextField.getText()), Integer.parseInt(this.gTextField.getText()),
              Integer.parseInt(this.bTextField.getText()));
          rSlider.setValue(Integer.parseInt(this.rTextField.getText()));
          gSlider.setValue(Integer.parseInt(this.gTextField.getText()));
          bSlider.setValue(Integer.parseInt(this.bTextField.getText()));
        } else {
          rgb = new RGB(rSlider.getValue(), gSlider.getValue(), bSlider.getValue());
        }

        hsv = rgb.toHSV();
        yuv = rgb.toYUV();
        cmy = rgb.toCMY();

        hSlider.setValue(hsv.getH());
        sSlider.setValue(hsv.getS());
        v2Slider.setValue(hsv.getV());
        hTextField.setText("" + hsv.getH());
        sTextField.setText("" + hsv.getS());
        v2TextField.setText("" + hsv.getV());
        ySlider.setValue(yuv.getY());
        uSlider.setValue(yuv.getU());
        vSlider.setValue(yuv.getV());
        yTextField.setText("" + yuv.getY());
        uTextField.setText("" + yuv.getU());
        vTextField.setText("" + yuv.getV());

        cSlider.setValue(cmy.getC());
        mSlider.setValue(cmy.getM());
        y2Slider.setValue(cmy.getY2());
        cTextField.setText("" + cmy.getC());
        mTextField.setText("" + cmy.getM());
        y2TextField.setText("" + cmy.getY2());

        this.currentColor = new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        this.colorCanvas.setBackground(this.currentColor);

      }
      if (colorSpace == YUV) {
        makeWhite();

        if (text) {

          yuv = new YUV(Integer.parseInt(this.yTextField.getText()), Integer.parseInt(this.uTextField.getText()),
              Integer.parseInt(this.vTextField.getText()));
          ySlider.setValue(Integer.parseInt(this.yTextField.getText()));
          uSlider.setValue(Integer.parseInt(this.uTextField.getText()));
          vSlider.setValue(Integer.parseInt(this.vTextField.getText()));
        } else {
          yuv = new YUV(ySlider.getValue(), uSlider.getValue(), vSlider.getValue());
        }

        rgb = yuv.toRGB();
        hsv = rgb.toHSV();
        cmy = rgb.toCMY();

        hSlider.setValue(hsv.getH());
        sSlider.setValue(hsv.getS());
        v2Slider.setValue(hsv.getV());
        hTextField.setText("" + hsv.getH());
        sTextField.setText("" + hsv.getS());
        v2TextField.setText("" + hsv.getV());

        rSlider.setValue(rgb.getRed());
        gSlider.setValue(rgb.getGreen());
        bSlider.setValue(rgb.getBlue());
        rTextField.setText("" + rgb.getRed());
        gTextField.setText("" + rgb.getGreen());
        bTextField.setText("" + rgb.getBlue());

        cSlider.setValue(cmy.getC());
        mSlider.setValue(cmy.getM());
        y2Slider.setValue(cmy.getY2());
        cTextField.setText("" + cmy.getC());
        mTextField.setText("" + cmy.getM());
        y2TextField.setText("" + cmy.getY2());

        this.currentColor = new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        this.colorCanvas.setBackground(this.currentColor);

      }
      if (colorSpace == CMY) {
        makeWhite();

        if (text) {

          cmy = new CMY(Integer.parseInt(this.cTextField.getText()), Integer.parseInt(this.mTextField.getText()),
              Integer.parseInt(this.y2TextField.getText()));
          cSlider.setValue(Integer.parseInt(this.cTextField.getText()));
          mSlider.setValue(Integer.parseInt(this.mTextField.getText()));
          y2Slider.setValue(Integer.parseInt(this.y2TextField.getText()));
        } else {
          cmy = new CMY(cSlider.getValue(), mSlider.getValue(), y2Slider.getValue());
        }

        rgb = cmy.toRGB();
        hsv = rgb.toHSV();
        yuv = rgb.toYUV();

        hSlider.setValue(hsv.getH());
        sSlider.setValue(hsv.getS());
        v2Slider.setValue(hsv.getV());
        hTextField.setText("" + hsv.getH());
        sTextField.setText("" + hsv.getS());
        v2TextField.setText("" + hsv.getV());

        rSlider.setValue(rgb.getRed());
        gSlider.setValue(rgb.getGreen());
        bSlider.setValue(rgb.getBlue());
        rTextField.setText("" + rgb.getRed());
        gTextField.setText("" + rgb.getGreen());
        bTextField.setText("" + rgb.getBlue());

        ySlider.setValue(yuv.getY());
        uSlider.setValue(yuv.getU());
        vSlider.setValue(yuv.getY());
        yTextField.setText("" + yuv.getY());
        uTextField.setText("" + yuv.getU());
        vTextField.setText("" + yuv.getV());

        this.currentColor = new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        this.colorCanvas.setBackground(this.currentColor);

      }
      if (colorSpace == HSV) {
        makeWhite();

        if (text) {

          hsv = new HSV(Integer.parseInt(this.hTextField.getText()), Integer.parseInt(this.sTextField.getText()),
              Integer.parseInt(this.v2TextField.getText()));
          hSlider.setValue(Integer.parseInt(this.hTextField.getText()));
          sSlider.setValue(Integer.parseInt(this.sTextField.getText()));
          v2Slider.setValue(Integer.parseInt(this.v2TextField.getText()));
        } else {
          hsv = new HSV(hSlider.getValue(), sSlider.getValue(), v2Slider.getValue());
        }
        rgb = hsv.toRGB();
        yuv = rgb.toYUV();
        cmy = rgb.toCMY();

        rSlider.setValue(rgb.getRed());
        gSlider.setValue(rgb.getGreen());
        bSlider.setValue(rgb.getBlue());
        rTextField.setText("" + rgb.getRed());
        gTextField.setText("" + rgb.getGreen());
        bTextField.setText("" + rgb.getBlue());

        ySlider.setValue(yuv.getY());
        uSlider.setValue(yuv.getU());
        vSlider.setValue(yuv.getV());
        yTextField.setText("" + yuv.getY());
        uTextField.setText("" + yuv.getU());
        vTextField.setText("" + yuv.getV());

        cSlider.setValue(cmy.getC());
        mSlider.setValue(cmy.getM());
        y2Slider.setValue(cmy.getY2());
        cTextField.setText("" + cmy.getC());
        mTextField.setText("" + cmy.getM());
        y2TextField.setText("" + cmy.getY2());

        this.currentColor = new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        this.colorCanvas.setBackground(this.currentColor);
      }

    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      if (e.getMessage().contains("RGB_R")) {
        rTextField.setBackground(Color.RED);
      }
      if (e.getMessage().contains("RGB_G")) {
        gTextField.setBackground(Color.RED);
      }
      if (e.getMessage().contains("RGB_B")) {
        bTextField.setBackground(Color.RED);
      }
      if (e.getMessage().contains("YUV_Y")) {
        yTextField.setBackground(Color.RED);
      }
      if (e.getMessage().contains("YUV_U")) {
        uTextField.setBackground(Color.RED);
      }
      if (e.getMessage().contains("YUV_V")) {
        vTextField.setBackground(Color.RED);
      }
      if (e.getMessage().contains("CMY_C")) {
        cTextField.setBackground(Color.RED);
      }
      if (e.getMessage().contains("CMY_M")) {
        mTextField.setBackground(Color.RED);
      }
      if (e.getMessage().contains("CMY_Y")) {
        y2TextField.setBackground(Color.RED);

      }
      if (e.getMessage().contains("HSV_H")) {
        hTextField.setBackground(Color.RED);

      }
      if (e.getMessage().contains("HSV_S")) {
        sTextField.setBackground(Color.RED);

      }
      if (e.getMessage().contains("HSV_V")) {
        v2TextField.setBackground(Color.RED);

      }
    }

    catch (Exception e) {

    }

    this.update = false;

  }

  /**
   * This funktion sets the backgrund of all TextField to white.
   */

  private void makeWhite() {
    yTextField.setBackground(Color.WHITE);
    uTextField.setBackground(Color.WHITE);
    vTextField.setBackground(Color.WHITE);
    rTextField.setBackground(Color.WHITE);
    gTextField.setBackground(Color.WHITE);
    bTextField.setBackground(Color.WHITE);
    cTextField.setBackground(Color.WHITE);
    mTextField.setBackground(Color.WHITE);
    y2TextField.setBackground(Color.WHITE);
    hTextField.setBackground(Color.WHITE);
    sTextField.setBackground(Color.WHITE);
    v2TextField.setBackground(Color.WHITE);

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
    result = prime * result + (bSlider == null ? 0 : bSlider.hashCode());
    result = prime * result + (bTextField == null ? 0 : bTextField.hashCode());
    result = prime * result + (cSlider == null ? 0 : cSlider.hashCode());
    result = prime * result + (cTextField == null ? 0 : cTextField.hashCode());
    result = prime * result + (cmyPanel == null ? 0 : cmyPanel.hashCode());
    result = prime * result + (colorCanvas == null ? 0 : colorCanvas.hashCode());
    result = prime * result + colorSpace;
    result = prime * result + (currentColor == null ? 0 : currentColor.hashCode());
    result = prime * result + (gSlider == null ? 0 : gSlider.hashCode());
    result = prime * result + (gTextField == null ? 0 : gTextField.hashCode());
    result = prime * result + (hSlider == null ? 0 : hSlider.hashCode());
    result = prime * result + (hTextField == null ? 0 : hTextField.hashCode());
    result = prime * result + (hsvPanel == null ? 0 : hsvPanel.hashCode());
    result = prime * result + (mSlider == null ? 0 : mSlider.hashCode());
    result = prime * result + (mTextField == null ? 0 : mTextField.hashCode());
    result = prime * result + (rSlider == null ? 0 : rSlider.hashCode());
    result = prime * result + (rTextField == null ? 0 : rTextField.hashCode());
    result = prime * result + (rgbPanel == null ? 0 : rgbPanel.hashCode());
    result = prime * result + (sSlider == null ? 0 : sSlider.hashCode());
    result = prime * result + (sTextField == null ? 0 : sTextField.hashCode());
    result = prime * result + (text ? 1231 : 1237);
    result = prime * result + (uSlider == null ? 0 : uSlider.hashCode());
    result = prime * result + (uTextField == null ? 0 : uTextField.hashCode());
    result = prime * result + (update ? 1231 : 1237);
    result = prime * result + (v2Slider == null ? 0 : v2Slider.hashCode());
    result = prime * result + (v2TextField == null ? 0 : v2TextField.hashCode());
    result = prime * result + (vSlider == null ? 0 : vSlider.hashCode());
    result = prime * result + (vTextField == null ? 0 : vTextField.hashCode());
    result = prime * result + (y2Slider == null ? 0 : y2Slider.hashCode());
    result = prime * result + (y2TextField == null ? 0 : y2TextField.hashCode());
    result = prime * result + (ySlider == null ? 0 : ySlider.hashCode());
    result = prime * result + (yTextField == null ? 0 : yTextField.hashCode());
    result = prime * result + (yuvPanel == null ? 0 : yuvPanel.hashCode());
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
    ColorConverterJFrame other = (ColorConverterJFrame) obj;
    if (bSlider == null) {
      if (other.bSlider != null) {
        return false;
      }
    } else if (!bSlider.equals(other.bSlider)) {
      return false;
    }
    if (bTextField == null) {
      if (other.bTextField != null) {
        return false;
      }
    } else if (!bTextField.equals(other.bTextField)) {
      return false;
    }
    if (cSlider == null) {
      if (other.cSlider != null) {
        return false;
      }
    } else if (!cSlider.equals(other.cSlider)) {
      return false;
    }
    if (cTextField == null) {
      if (other.cTextField != null) {
        return false;
      }
    } else if (!cTextField.equals(other.cTextField)) {
      return false;
    }
    if (cmyPanel == null) {
      if (other.cmyPanel != null) {
        return false;
      }
    } else if (!cmyPanel.equals(other.cmyPanel)) {
      return false;
    }
    if (colorCanvas == null) {
      if (other.colorCanvas != null) {
        return false;
      }
    } else if (!colorCanvas.equals(other.colorCanvas)) {
      return false;
    }
    if (colorSpace != other.colorSpace) {
      return false;
    }
    if (currentColor == null) {
      if (other.currentColor != null) {
        return false;
      }
    } else if (!currentColor.equals(other.currentColor)) {
      return false;
    }
    if (gSlider == null) {
      if (other.gSlider != null) {
        return false;
      }
    } else if (!gSlider.equals(other.gSlider)) {
      return false;
    }
    if (gTextField == null) {
      if (other.gTextField != null) {
        return false;
      }
    } else if (!gTextField.equals(other.gTextField)) {
      return false;
    }
    if (hSlider == null) {
      if (other.hSlider != null) {
        return false;
      }
    } else if (!hSlider.equals(other.hSlider)) {
      return false;
    }
    if (hTextField == null) {
      if (other.hTextField != null) {
        return false;
      }
    } else if (!hTextField.equals(other.hTextField)) {
      return false;
    }
    if (hsvPanel == null) {
      if (other.hsvPanel != null) {
        return false;
      }
    } else if (!hsvPanel.equals(other.hsvPanel)) {
      return false;
    }
    if (mSlider == null) {
      if (other.mSlider != null) {
        return false;
      }
    } else if (!mSlider.equals(other.mSlider)) {
      return false;
    }
    if (mTextField == null) {
      if (other.mTextField != null) {
        return false;
      }
    } else if (!mTextField.equals(other.mTextField)) {
      return false;
    }
    if (rSlider == null) {
      if (other.rSlider != null) {
        return false;
      }
    } else if (!rSlider.equals(other.rSlider)) {
      return false;
    }
    if (rTextField == null) {
      if (other.rTextField != null) {
        return false;
      }
    } else if (!rTextField.equals(other.rTextField)) {
      return false;
    }
    if (rgbPanel == null) {
      if (other.rgbPanel != null) {
        return false;
      }
    } else if (!rgbPanel.equals(other.rgbPanel)) {
      return false;
    }
    if (sSlider == null) {
      if (other.sSlider != null) {
        return false;
      }
    } else if (!sSlider.equals(other.sSlider)) {
      return false;
    }
    if (sTextField == null) {
      if (other.sTextField != null) {
        return false;
      }
    } else if (!sTextField.equals(other.sTextField)) {
      return false;
    }
    if (text != other.text) {
      return false;
    }
    if (uSlider == null) {
      if (other.uSlider != null) {
        return false;
      }
    } else if (!uSlider.equals(other.uSlider)) {
      return false;
    }
    if (uTextField == null) {
      if (other.uTextField != null) {
        return false;
      }
    } else if (!uTextField.equals(other.uTextField)) {
      return false;
    }
    if (update != other.update) {
      return false;
    }
    if (v2Slider == null) {
      if (other.v2Slider != null) {
        return false;
      }
    } else if (!v2Slider.equals(other.v2Slider)) {
      return false;
    }
    if (v2TextField == null) {
      if (other.v2TextField != null) {
        return false;
      }
    } else if (!v2TextField.equals(other.v2TextField)) {
      return false;
    }
    if (vSlider == null) {
      if (other.vSlider != null) {
        return false;
      }
    } else if (!vSlider.equals(other.vSlider)) {
      return false;
    }
    if (vTextField == null) {
      if (other.vTextField != null) {
        return false;
      }
    } else if (!vTextField.equals(other.vTextField)) {
      return false;
    }
    if (y2Slider == null) {
      if (other.y2Slider != null) {
        return false;
      }
    } else if (!y2Slider.equals(other.y2Slider)) {
      return false;
    }
    if (y2TextField == null) {
      if (other.y2TextField != null) {
        return false;
      }
    } else if (!y2TextField.equals(other.y2TextField)) {
      return false;
    }
    if (ySlider == null) {
      if (other.ySlider != null) {
        return false;
      }
    } else if (!ySlider.equals(other.ySlider)) {
      return false;
    }
    if (yTextField == null) {
      if (other.yTextField != null) {
        return false;
      }
    } else if (!yTextField.equals(other.yTextField)) {
      return false;
    }
    if (yuvPanel == null) {
      if (other.yuvPanel != null) {
        return false;
      }
    } else if (!yuvPanel.equals(other.yuvPanel)) {
      return false;
    }
    return true;
  }

}
