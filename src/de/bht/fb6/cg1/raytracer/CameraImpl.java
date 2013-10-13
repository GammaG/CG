package de.bht.fb6.cg1.raytracer;

import java.util.HashMap;
import java.util.Map;

import de.bht.fb6.cg1.raytracer.math.Point2D;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.Vector3D;
import de.bht.fb6.cg1.raytracer.math.impl.Point2DImpl;
import de.bht.fb6.cg1.raytracer.math.impl.RayImpl;
import de.bht.fb6.cg1.raytracer.scene.Camera;

/**
 * @author admin
 * 
 */
public class CameraImpl implements Camera {

  private final Point3D eye;
  private final Vector3D upVector;
  private final Point3D lookAt;
  private final boolean perspective;

  /**
   * @param eye
   *          - Position of the Camera
   * @param upVector
   *          - Upvector of the Camera
   * @param lookAt
   *          - Point where Camera direction is
   */
  public CameraImpl(final Point3D eye, final Vector3D upVector, final Point3D lookAt) {

    if (eye == null || upVector == null || lookAt == null) {
      throw new IllegalArgumentException("Can not be null!");
    }

    this.eye = eye;
    this.upVector = upVector;
    this.lookAt = lookAt;
    this.perspective = true;

    System.out.println("Eye:\n" + eye + "\nUp:\n" + upVector + "\nLook at:\n" + lookAt);
    System.out.println("\n\nW:\n" + getW() + "\nU:\n" + getU() + "\nV:\n" + getV());
  }

  /**
   * Create a new perspective or orthographic Camera.
   * 
   * @param eye
   *          The Position of the Camera
   * @param upVector
   *          The upVector of the Camera
   * @param lookAt
   *          The point where Camera direction is
   * @param perspective
   *          Perspective (true) or orthographic (false).
   */
  public CameraImpl(final Point3D eye, final Vector3D upVector, final Point3D lookAt, final boolean perspective) {

    if (eye == null || upVector == null || lookAt == null) {
      throw new IllegalArgumentException("Can not be null !");
    }

    this.eye = eye;
    this.upVector = upVector;
    this.lookAt = lookAt;
    this.perspective = perspective;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.scene.Camera#generateRaysFor(int, int)
   */
  @Override
  public Map<Point2D, Ray> generateRaysFor(final int width, final int height) {

    HashMap<Point2D, Ray> output = new HashMap<Point2D, Ray>();

    for (int currentY = 0; currentY < height; currentY++) {
      for (int currentX = 0; currentX < width; currentX++) {

        final Ray ray;

        if (perspective) {

          // 45.38

          Vector3D r = getW().mul(-1).mul(height / 2 / Math.tan(45));
          r = r.add(getU().mul(currentX - (width - 1) / 2.0));
          r = r.add(getV().mul(currentY - (height - 1) / 2.0));

          ray = new RayImpl(eye, r);

        } else {

          double aspectRatio = width * 1.0 / height;
          double s = 45;

          Point3D e = eye;
          e = e.add(getU().mul(aspectRatio * s * (currentX - (width - 1) / 2) / (width - 1)));
          e = e.add(getV().mul(s * (currentY - (height - 1) / 2) / (height - 1)));

          ray = new RayImpl(e, getW().mul(-1));

        }

        output.put(new Point2DImpl(currentX, currentY), ray);
      }
    }

    return output;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.scene.Camera#getEye()
   */
  @Override
  public Point3D getEye() {
    return eye;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.scene.Camera#getUp()
   */
  @Override
  public Vector3D getUp() {
    return upVector;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.scene.Camera#getLookAt()
   */
  @Override
  public Point3D getLookAt() {
    return lookAt;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.scene.Camera#getU()
   */
  @Override
  public Vector3D getU() {
    return upVector.cross(getW()).normalized();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.scene.Camera#getV()
   */
  @Override
  public Vector3D getV() {
    return getW().cross(getU()).normalized();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.scene.Camera#getW()
   */
  @Override
  public Vector3D getW() {
    return eye.sub(lookAt).normalized();
  }

  @Override
  public String toString() {
    return "CameraImpl [eye=" + eye + ", upVector=" + upVector + ", lookAt=" + lookAt + ", perspective=" + perspective
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (eye == null ? 0 : eye.hashCode());
    result = prime * result + (lookAt == null ? 0 : lookAt.hashCode());
    result = prime * result + (perspective ? 1231 : 1237);
    result = prime * result + (upVector == null ? 0 : upVector.hashCode());
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
    CameraImpl other = (CameraImpl) obj;
    if (eye == null) {
      if (other.eye != null) {
        return false;
      }
    } else if (!eye.equals(other.eye)) {
      return false;
    }
    if (lookAt == null) {
      if (other.lookAt != null) {
        return false;
      }
    } else if (!lookAt.equals(other.lookAt)) {
      return false;
    }
    if (perspective != other.perspective) {
      return false;
    }
    if (upVector == null) {
      if (other.upVector != null) {
        return false;
      }
    } else if (!upVector.equals(other.upVector)) {
      return false;
    }
    return true;
  }
}
