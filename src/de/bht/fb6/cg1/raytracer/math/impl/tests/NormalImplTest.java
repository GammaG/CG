package de.bht.fb6.cg1.raytracer.math.impl.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.bht.fb6.cg1.raytracer.math.impl.NormalImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Point3DImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Vector3DImpl;

/**
 * @author Marco Seidler/ Test for
 * @link {@link de.bht.fb6.cg1.raytracer.math.impl.NormalImpl}
 */

public class NormalImplTest {

  private final NormalImpl normal1 = new NormalImpl(5, 17, 22);
  private final NormalImpl normal2 = new NormalImpl(2, 2, 2);

  /**
   * 
   */
  @Test
  public void testAdd() {
    assertEquals(new NormalImpl(7, 19, 24), normal1.add(normal2));
  }

  /**
   * 
   */
  @Test
  public void testAsPoint() {
    assertEquals(new Point3DImpl(2, 2, 2), normal2.asPoint());
  }

  /**
   * 
   */
  @Test
  public void testAsVector() {
    assertEquals(new Vector3DImpl(2, 2, 2), normal2.asVector());
  }

  /**
   * 
   */
  @Test
  public void testMul() {
    assertEquals(new NormalImpl(4, 4, 4), normal2.mul(2));
  }

  /**
   * 
   */
  @Test
  public void testNormalized() {
    assertEquals(1, new NormalImpl(4.56, 3.1, -6.235).normalized().asVector().getMagnitude(), 0.000001);
  }

  /**
   * 
   */
  @Test
  public void testReflect() {
    NormalImpl normal11 = new NormalImpl(1, 0, 0);
    NormalImpl normal12 = new NormalImpl(453.21, 0, 0);
    assertEquals(normal11.reflect(new Vector3DImpl(4, 0, 2)).normalized(), normal12.reflect(new Vector3DImpl(2, 0, 1))
        .normalized());
  }

}
