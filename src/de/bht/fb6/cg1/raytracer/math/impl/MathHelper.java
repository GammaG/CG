package de.bht.fb6.cg1.raytracer.math.impl;

/**
 * @author Marco Seidler
 * 
 */
public abstract class MathHelper {

  /**
   * The value of delta.
   */
  public static final double DELTA = 0.00001;

  /**
   * Round a double value so it has only 5 numbers after comma.
   * 
   * @param value
   *          The double value you want to round.
   * @return The value in format of delta.
   */
  public static double roundDouble(final double value) {

    long tempValue = Math.round(value * 1 / DELTA);

    return tempValue * DELTA;
  }
}
