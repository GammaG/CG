package de.bht.fb6.cg1.raytracer.math.impl.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.impl.Point3DImpl;
import de.bht.fb6.cg1.raytracer.math.impl.RayImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Vector3DImpl;

/**
 * @author Marco Seidler/ Test for
 * @link {@link de.bht.fb6.cg1.raytracer.math.impl.RayImpl}
 */
public class RayImplTest {

  Ray ray = new RayImpl(new Point3DImpl(-1, 1, 2), new Vector3DImpl(1, -1, -2));

  @SuppressWarnings("javadoc")
  @Test
  public void testRayImpl() {
    assertEquals(new Point3DImpl(0, 0, 0), ray.at(1));
  }

  /**
   * 
   */
  @Test
  public void testAt() {
    assertEquals(new Point3DImpl(0, 0, 0), ray.at(1));
  }

  /**
   * 
   */
  @Test
  public void testTOf() {
    assertEquals(1, ray.tOf(new Point3DImpl(0, 0, 0)), 0);
  }
}
