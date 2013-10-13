package de.bht.fb6.cg1.raytracer.geometricobjects;

import java.util.ArrayList;

import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.impl.NormalImpl;
import de.bht.fb6.cg1.raytracer.math.impl.VHelper;
import de.bht.fb6.cg1.raytracer.scene.GeometricObject;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;
import de.bht.fb6.cg1.raytracer.util.Maybe;

/**
 * A implementation of a {@link AxisAlignedBox}.
 * 
 * @author admin
 * 
 */
public class Box implements GeometricObject {
  private final Material material;
  private final Point3D a;
  private final Point3D b;

  private final Plane downPlane;
  private final Plane upPlane;
  private final Plane leftPlane;
  private final Plane nearPlane;
  private final Plane rightPlane;
  private final Plane farPlane;

  /**
   * Create a new {@link AxisAlignedBox}.
   * 
   * @param material
   *          The {@link Material}.
   * @param a
   *          The {@link Point3D} with lower coordinates.
   * @param b
   *          The {@link Point3D} with higher coordinates.
   * 
   */
  public Box(final Material material, final Point3D a, final Point3D b) {
    if (!(a.getX() < b.getX() && a.getY() < b.getY() && a.getZ() < b.getZ())) {
      throw new IllegalArgumentException("Every coordinat of a should be lower than the same of b.");
    }

    this.material = material;
    this.a = a;
    this.b = b;

    downPlane = new Plane(material, a, new NormalImpl(0, -1, 0));
    upPlane = new Plane(material, b, new NormalImpl(0, 1, 0));
    rightPlane = new Plane(material, b, new NormalImpl(1, 0, 0));
    leftPlane = new Plane(material, a, new NormalImpl(-1, 0, 0));
    nearPlane = new Plane(material, a, new NormalImpl(0, 0, -1));
    farPlane = new Plane(material, b, new NormalImpl(0, 0, 1));
  }

  @Override
  public Material getMaterial() {
    return material;
  }

  @Override
  public Maybe<ShadeRecord> hit(final Ray ray) {

    ArrayList<Plane> possiblePlanes = new ArrayList<Plane>();

    if (VHelper.angleBetween(downPlane.getNormal().asVector(), ray.getDirection().mul(-1)) < 90) {
      possiblePlanes.add(downPlane);
    }

    if (VHelper.angleBetween(upPlane.getNormal().asVector(), ray.getDirection().mul(-1)) < 90) {
      possiblePlanes.add(upPlane);
    }

    if (VHelper.angleBetween(leftPlane.getNormal().asVector(), ray.getDirection().mul(-1)) < 90) {
      possiblePlanes.add(leftPlane);
    }

    if (VHelper.angleBetween(nearPlane.getNormal().asVector(), ray.getDirection().mul(-1)) < 90) {
      possiblePlanes.add(nearPlane);
    }

    if (VHelper.angleBetween(rightPlane.getNormal().asVector(), ray.getDirection().mul(-1)) < 90) {
      possiblePlanes.add(rightPlane);
    }

    if (VHelper.angleBetween(farPlane.getNormal().asVector(), ray.getDirection().mul(-1)) < 90) {
      possiblePlanes.add(farPlane);
    }

    ShadeRecord farest = null;

    for (Plane plane : possiblePlanes) {
      Maybe<ShadeRecord> rec = plane.hit(ray);
      if (rec.isJust()) {
        ShadeRecord convertedRec = rec.fromJust();
        if (farest == null || convertedRec.t > farest.t) {
          farest = convertedRec;
        }
      }
    }

    ArrayList<ShadeRecord> shadeRecords = new ArrayList<ShadeRecord>();

    if (check(farest.localHitPoint)) {
      shadeRecords.add(farest);
    }

    return Maybe.listToMaybe(shadeRecords);
  }

  /**
   * Proof reather the Point is on the {@link AxisAlignedBox}.
   * 
   * @param localHitPoint
   *          {@link Point3D} to check.
   * @return If the {@link Point3D} is on the {@link AxisAlignedBox}.
   */
  private boolean check(final Point3D localHitPoint) {

    boolean out = true;

    if (localHitPoint.getX() > b.getX() + 0.0001 || localHitPoint.getX() < a.getX() - 0.0001) {
      out = false;
    }

    if (localHitPoint.getY() > b.getY() + 0.0001 || localHitPoint.getY() < a.getY() - 0.0001) {
      out = false;
    }

    if (localHitPoint.getZ() > b.getZ() + 0.0001 || localHitPoint.getZ() < a.getZ() - 0.0001) {
      out = false;
    }

    return out;
  }

  @Override
  public String toString() {
    return "Box [material=" + material + ", a=" + a + ", b=" + b + ", downPlane=" + downPlane + ", upPlane=" + upPlane
        + ", leftPlane=" + leftPlane + ", nearPlane=" + nearPlane + ", rightPlane=" + rightPlane + ", farPlane="
        + farPlane + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (a == null ? 0 : a.hashCode());
    result = prime * result + (b == null ? 0 : b.hashCode());
    result = prime * result + (downPlane == null ? 0 : downPlane.hashCode());
    result = prime * result + (farPlane == null ? 0 : farPlane.hashCode());
    result = prime * result + (leftPlane == null ? 0 : leftPlane.hashCode());
    result = prime * result + (material == null ? 0 : material.hashCode());
    result = prime * result + (nearPlane == null ? 0 : nearPlane.hashCode());
    result = prime * result + (rightPlane == null ? 0 : rightPlane.hashCode());
    result = prime * result + (upPlane == null ? 0 : upPlane.hashCode());
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
    Box other = (Box) obj;
    if (a == null) {
      if (other.a != null) {
        return false;
      }
    } else if (!a.equals(other.a)) {
      return false;
    }
    if (b == null) {
      if (other.b != null) {
        return false;
      }
    } else if (!b.equals(other.b)) {
      return false;
    }
    if (downPlane == null) {
      if (other.downPlane != null) {
        return false;
      }
    } else if (!downPlane.equals(other.downPlane)) {
      return false;
    }
    if (farPlane == null) {
      if (other.farPlane != null) {
        return false;
      }
    } else if (!farPlane.equals(other.farPlane)) {
      return false;
    }
    if (leftPlane == null) {
      if (other.leftPlane != null) {
        return false;
      }
    } else if (!leftPlane.equals(other.leftPlane)) {
      return false;
    }
    if (material == null) {
      if (other.material != null) {
        return false;
      }
    } else if (!material.equals(other.material)) {
      return false;
    }
    if (nearPlane == null) {
      if (other.nearPlane != null) {
        return false;
      }
    } else if (!nearPlane.equals(other.nearPlane)) {
      return false;
    }
    if (rightPlane == null) {
      if (other.rightPlane != null) {
        return false;
      }
    } else if (!rightPlane.equals(other.rightPlane)) {
      return false;
    }
    if (upPlane == null) {
      if (other.upPlane != null) {
        return false;
      }
    } else if (!upPlane.equals(other.upPlane)) {
      return false;
    }
    return true;
  }
}
