package de.bht.fb6.cg1.raytracer.geometricobjects;

import java.util.ArrayList;

import de.bht.fb6.cg1.raytracer.math.Normal;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.Vector3D;
import de.bht.fb6.cg1.raytracer.scene.GeometricObject;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;
import de.bht.fb6.cg1.raytracer.util.Maybe;

/**
 * A implementation of a {@link Plane}.
 * 
 * @author admin
 * 
 */
public class Plane implements GeometricObject {
  private final Material material;
  private final Point3D a;
  private final Normal normal;

  /**
   * Create a new {@link Plane}.
   * 
   * @param material
   *          The {@link Material}.
   * @param a
   *          A {@link Point3D} on the {@link Plane}.
   * @param normal
   *          The {@link Normal} of the {@link Plane}.
   */
  public Plane(final Material material, final Point3D a, final Normal normal) {
    if (material == null || a == null || normal == null) {
      throw new IllegalArgumentException("cannot be null");
    }
    this.material = material;
    this.a = a;
    this.normal = normal;
  }

  @Override
  public Material getMaterial() {
    return material;
  }

  /**
   * @return The Normal of the Plane.
   */
  public Normal getNormal() {
    return normal;
  }

  @Override
  public Maybe<ShadeRecord> hit(final Ray ray) {

    ArrayList<ShadeRecord> shadeRecords = new ArrayList<ShadeRecord>();

    final Point3D o = ray.getOrigin();
    final Vector3D d = ray.getDirection();

    double t = a.sub(o).dot(normal) / d.dot(normal);

    if (!Double.isNaN(t)) {

      Point3D point = ray.at(t);
      shadeRecords.add(new ShadeRecord(t, point, normal, this, ray));
    }

    return Maybe.listToMaybe(shadeRecords);
  }

  @Override
  public String toString() {
    return "Plane [material=" + material + ", a=" + a + ", normal=" + normal + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (a == null ? 0 : a.hashCode());
    result = prime * result + (material == null ? 0 : material.hashCode());
    result = prime * result + (normal == null ? 0 : normal.hashCode());
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
    Plane other = (Plane) obj;
    if (a == null) {
      if (other.a != null) {
        return false;
      }
    } else if (!a.equals(other.a)) {
      return false;
    }
    if (material == null) {
      if (other.material != null) {
        return false;
      }
    } else if (!material.equals(other.material)) {
      return false;
    }
    if (normal == null) {
      if (other.normal != null) {
        return false;
      }
    } else if (!normal.equals(other.normal)) {
      return false;
    }
    return true;
  }
}
