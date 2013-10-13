package de.bht.fb6.cg1.raytracer.math.impl.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.bht.fb6.cg1.raytracer.math.Vector3D;
import de.bht.fb6.cg1.raytracer.math.impl.NormalImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Point3DImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Vector3DImpl;

/**
 * @author Marco Seidler/ Test for
 * @link {@link de.bht.fb6.cg1.raytracer.math.impl.Vector3DImpl}
 */
public class Vector3DImplTest {

  Vector3D vector1 = new Vector3DImpl(5, -5, 5);
  Vector3D vector2 = new Vector3DImpl(-5, 5, -5);

  /**
   * 
   */
  @Test
  public void testAddVector3D() {
    assertEquals(new Vector3DImpl(0, 0, 0), vector1.add(vector2));
  }

  /**
   * 
   */
  @Test
  public void testAddNormal() {
    assertEquals(new Vector3DImpl(0, 0, 0), vector1.add(new NormalImpl(-5, 5, -5)));
  }

  /**
   * 
   */
  @Test
  public void testAsNormal() {
    assertEquals(new NormalImpl(5, -5, 5), vector1.asNormal());
  }

  /**
   * 
   */
  @Test
  public void testAsPoint() {
    assertEquals(new Point3DImpl(5, -5, 5), vector1.asPoint());
  }

  /**
   * 
   */
  @Test
  public void testCross() {
    assertEquals(new Vector3DImpl(0, 0, 0), vector1.cross(vector2));
  }

  /**
   * 
   */
  @Test
  public void testDiv() {
    assertEquals(new Vector3DImpl(3, -7, 3), vector1.div(2));
  }

  /**
   * 
   */
  @Test
  public void testDotNormal() {
    assertEquals(5.0, vector1.dot(new NormalImpl(1, 1, 1)), 0.0001);
  }

  /**
   * 
   */
  @Test
  public void testDotVector3D() {
    assertEquals(5.0, vector1.dot(new Vector3DImpl(1, 1, 1)), 0.0001);
  }

  /**
   * 
   */
  @Test
  public void testGetMagnitude() {
    assertEquals(Math.sqrt(75), vector1.getMagnitude(), 0.0001);
  }

  /**
   * 
   */
  @Test
  public void testMul() {
    assertEquals(vector1, vector1.mul(1));
  }

  /**
   * 
   */
  @Test
  public void testNormalized() {
    assertEquals(1, new Vector3DImpl(4.56, 3.1, -6.235).normalized().getMagnitude(), 0.000001);
  }

  /**
   * 
   */
  @Test
  public void testSub() {
    assertEquals(new Vector3DImpl(10, -10, 10), vector1.sub(vector2));
  }

}
