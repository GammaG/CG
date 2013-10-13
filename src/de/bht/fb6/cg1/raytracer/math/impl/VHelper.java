package de.bht.fb6.cg1.raytracer.math.impl;

import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * @author Marco Seidler
 * 
 */
public abstract class VHelper {
  /**
   * 
   * überprüft welchen winkel die Vektoren zueinander haben und wie viel licht
   * zurückkommen muss
   * 
   * @param vector1
   * @param vector2
   * @return
   */
  public static double angleBetween(final Vector3D vector1, final Vector3D vector2) {
    return Math.toDegrees(Math.acos(vector1.dot(vector2) / (vector1.getMagnitude() * vector2.getMagnitude())));
  }
}