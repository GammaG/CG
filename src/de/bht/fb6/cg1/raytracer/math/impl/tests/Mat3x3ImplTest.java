package de.bht.fb6.cg1.raytracer.math.impl.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.bht.fb6.cg1.raytracer.math.impl.Mat3x3Impl;
import de.bht.fb6.cg1.raytracer.math.impl.Point3DImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Vector3DImpl;

/**
 * @author Marco Seidler/ Test for
 * @link {@link de.bht.fb6.cg1.raytracer.math.impl.Mat3x3Impl}
 */

public class Mat3x3ImplTest {

  private final Mat3x3Impl matrix1 = new Mat3x3Impl(new Vector3DImpl(10, 10, 10), new Vector3DImpl(-17, 5, 8.4),
      new Vector3DImpl(77, 45, -17));
  private final Mat3x3Impl matrix2 = new Mat3x3Impl(new Vector3DImpl(65, 7.9, 55), new Vector3DImpl(4.7, 88, 8.4),
      new Vector3DImpl(77, 45, -17));
  private final Mat3x3Impl matrix3 = new Mat3x3Impl(new Vector3DImpl(0.5, 1, 487), new Vector3DImpl(55, 15, -8.4),
      new Vector3DImpl(87, 17, -3));

  private final Vector3DImpl vector1 = new Vector3DImpl(-14, 7.8, 68);
  private final Vector3DImpl vector2 = new Vector3DImpl(22, -88, 16);
  private final Vector3DImpl vector3 = new Vector3DImpl(-1.9, -77, -8);

  /**
   * 
   */
  @Test
  public void testGetDeterminant() {
    assertEquals(-477117, Math.round(matrix2.getDeterminant()));
  }

  /**
   * 
   */
  @Test
  public void testMulMat3x3() {
    assertEquals(new Mat3x3Impl(new Vector3DImpl(142.5, 33, 475.6), new Vector3DImpl(142.5, 33, 475.6),
        new Vector3DImpl(142.5, 33, 475.6)), new Mat3x3Impl(new Vector3DImpl(1, 1, 1), new Vector3DImpl(1, 1, 1),
        new Vector3DImpl(1, 1, 1)).mul(matrix3));
  }

  /**
   * 
   */
  @Test
  public void testMulPoint3D() {
    assertEquals(new Point3DImpl(2948, 500, -791.2), matrix1.mul(vector2.asPoint()));
  }

  /**
   * 
   */
  @Test
  public void testMulVector3D() {
    assertEquals(new Vector3DImpl(2948, 500, -791.2), matrix1.mul(vector2));
  }

  /**
   * 
   */
  @Test
  public void testReplace() {
    assertEquals(new Mat3x3Impl(vector1, vector2, vector3), matrix2.replaceColumn0(vector1).replaceColumn1(vector2)
        .replaceColumn2(vector3));

  }

}
