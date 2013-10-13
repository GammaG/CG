package de.bht.fb6.cg1.ip;

import java.util.Observable;

/**
 * @author admin
 * 
 */
public class ImageObserver extends Observable {
  private static ImageObserver INSTANCE = new ImageObserver();

  /**
   * 
   */
  public ImageObserver() {
    super();

  }

  /**
   * @return
   */
  public static ImageObserver getInstance() {
    return INSTANCE;
  }

  /**
   * @param count
   */
  public void sendMessage(final int count) {
    setChanged();
    this.notifyObservers(count);

  }
}
