package de.bht.fb6.cg1.raytracer.math.impl.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.bht.fb6.cg1.raytracer.math.impl.NormalImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Point3DImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Vector3DImpl;

/**
 * @author Marco Seidler/ Test for
 * @link {@link de.bht.fb6.cg1.raytracer.math.impl.Point3DImpl}
 */

public class Point3DImplTest {

  private final Point3DImpl point = new Point3DImpl(17, 18, 19);

  /**
   * 
   */
  @Test
  public void testAdd() {
    assertEquals(new Point3DImpl(19, 20, 21), point.add(new Vector3DImpl(2, 2, 2)));
  }

  /**
   * 
   */
  @Test
  public void testAsNormal() {
    assertEquals(new NormalImpl(17, 18, 19), point.asNormal());
  }

  /**
   * 
   */
  @Test
  public void testAsVector() {
    assertEquals(new Vector3DImpl(17, 18, 19), point.asVector());
  }

  /**
   * 
   */
  @Test
  public void testDot() {
    assertEquals(54.0, point.dot(new NormalImpl(1, 1, 1)), 0.00001);
  }

  /**
   * 
   */
  @Test
  public void testSubPoint3D() {
    assertEquals(new Vector3DImpl(15, 16, 17), point.sub(new Point3DImpl(2, 2, 2)));
  }

  /**
   * 
   */
  @Test
  public void testSubVector3D() {
    assertEquals(new Point3DImpl(15, 16, 17), point.sub(new Vector3DImpl(2, 2, 2)));
  }

}
