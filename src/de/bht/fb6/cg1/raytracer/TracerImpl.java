package de.bht.fb6.cg1.raytracer;

import java.util.ArrayList;
import java.util.Collections;

import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.scene.GeometricObject;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;
import de.bht.fb6.cg1.raytracer.scene.World;
import de.bht.fb6.cg1.raytracer.util.Maybe;

/**
 * @author admin
 * 
 */
public class TracerImpl implements Tracer {
  private final World world;

  /**
   * The value of delta.
   */
  public final double delta = 0.00000001;

  /**
   * Create a new Tracer.
   * 
   * @param world
   *          The world you want to trace.
   * 
   */
  public TracerImpl(World world) {
    this.world = world;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.Tracer#traceRay(de.bht.fb6.cg1.raytracer.math.Ray)
   */
  @Override
  public RGBColor traceRay(final Ray ray) {

    // Get the nearest Object

    ArrayList<ShadeRecord> allRecords = new ArrayList<ShadeRecord>();

    for (int i = 0; i < world.size(); i++) {
      GeometricObject geometric = world.get(i);
      Maybe<ShadeRecord> maybeObject = geometric.hit(ray);

      if (maybeObject.isJust()) {
        ShadeRecord firstHit = maybeObject.fromJust();

        if (firstHit.t > delta) {
          allRecords.add(firstHit);
        }
      }
    }

    Collections.sort(allRecords);
    // Return Color of the nearest Object
    if (allRecords.size() > 0) {
      ShadeRecord nearest = allRecords.get(0);

      return nearest.geometricObject.getMaterial().getColorAt(nearest, this);
    }

    // Nothing was hit

    return world.backgroundColor;

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.Tracer#hitsAnything(de.bht.fb6.cg1.raytracer.math
   * .Ray)
   */
  @Override
  public boolean hitsAnything(final Ray ray) {

    for (int i = 0; i < world.size(); i++) {
      GeometricObject geometric = world.get(i);
      Maybe<ShadeRecord> maybeObject = geometric.hit(ray);

      if (maybeObject.isJust()) {
        return true;
      }
    }

    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.Tracer#isPointVisible(de.bht.fb6.cg1.raytracer
   * .math.Ray, de.bht.fb6.cg1.raytracer.math.Point3D)
   */
  @Override
  public boolean isPointVisible(final Ray ray, Point3D point) {

    if (ray.tOf(point) > delta) {
      return true;
    }

    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.Tracer#getWorld()
   */
  @Override
  public World getWorld() {
    return world;
  }

  @Override
  public String toString() {
    return "TracerImpl [world=" + world + ", delta=" + delta + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(delta);
    result = prime * result + (int) (temp ^ temp >>> 32);
    result = prime * result + (world == null ? 0 : world.hashCode());
    return result;
  }

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
    TracerImpl other = (TracerImpl) obj;
    if (Double.doubleToLongBits(delta) != Double.doubleToLongBits(other.delta)) {
      return false;
    }
    if (world == null) {
      if (other.world != null) {
        return false;
      }
    } else if (!world.equals(other.world)) {
      return false;
    }
    return true;
  }

}
