package de.bht.fb6.cg1.raytracer;

import ij.gui.GenericDialog;

import javax.swing.JOptionPane;

import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.geometricobjects.Box;
import de.bht.fb6.cg1.raytracer.geometricobjects.Plane;
import de.bht.fb6.cg1.raytracer.geometricobjects.Sphere;
import de.bht.fb6.cg1.raytracer.lights.AmbientLightImpl;
import de.bht.fb6.cg1.raytracer.lights.DiffuseLight;
import de.bht.fb6.cg1.raytracer.lights.SpotLight;
import de.bht.fb6.cg1.raytracer.materials.LightMaterial;
import de.bht.fb6.cg1.raytracer.materials.ReflectivNoLightMaterial;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;
import de.bht.fb6.cg1.raytracer.math.impl.NormalImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Point3DImpl;
import de.bht.fb6.cg1.raytracer.math.impl.Vector3DImpl;
import de.bht.fb6.cg1.raytracer.scene.World;
import de.bht.fb6.cg1.raytracer.ui.RayTracerWindow;

/**
 * @author admin
 * 
 */
public abstract class Raytracer {

  /**
   * Amount of ambient reflectivity.
   */
  public static final float CA = 0.1f;

  /**
   * @param args
   * 
   */
  public static void main(final String[] args) {
    // World
    try {
      final GenericDialog gd = new GenericDialog("Setting");

      gd.addSlider("Setting", 0, 3, 0);
      gd.addSlider("Light", 0, 2, 0);
      gd.addSlider("Reflect Plane", 0, 1, 0);

      gd.showDialog();
      if (gd.wasOKed()) {

        final int method = (int) gd.getNextNumber();
        final int light = (int) gd.getNextNumber();
        final int plane = (int) gd.getNextNumber();

        World world = new World(new RGBColor(0.0f));

        if (method == 0) {
          world.add(new Sphere(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 2, 0.4, 8),
              new Point3DImpl(40, 0, -15), 15));
          world.add(new Sphere(new LightMaterial(new RGBColor(1f, 0f, 0f), 0.3f, 2, 0.4, 8), new Point3DImpl(60, 0, 5),
              15));
          world.add(new Sphere(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 2, 0.4, 8),
              new Point3DImpl(110, 0, 30), 15));
          world.add(new Sphere(new LightMaterial(new RGBColor(1f, 0f, 0f), 0.3f, 2, 0.4, 8),
              new Point3DImpl(140, 0, 60), 15));

        }

        if (method == 1) {
          world.add(new Sphere(new LightMaterial(new RGBColor(1f, 0f, 0f), 0.3f, 2, 0.4, 8), new Point3DImpl(40, 20,
              -15), 15));
          world.add(new Sphere(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 3, 0.4, 10),
              new Point3DImpl(80, 0, 70), 18));
          world.add(new Sphere(new LightMaterial(new RGBColor(1f, 0f, 0f), 0.3f, 4, 0.4, 8), new Point3DImpl(100, 0,
              105), 18));
          world.add(new Sphere(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 4, 0.4, 10), new Point3DImpl(140, 0,
              60), 18));

        }

        if (method == 2) {

          world.add(new Box(new LightMaterial(new RGBColor(1f, 0f, 0f), 0.3f, 4, 0.4, 10), new Point3DImpl(40, 0, 20),
              new Point3DImpl(50, 30, 30)));
          world.add(new Sphere(new LightMaterial(new RGBColor(30f, 30f, 8f), 0.9f, 4, 0.4, 10), new Point3DImpl(120,
              60, -15), 50));
          world.add(new Sphere(new LightMaterial(new RGBColor(1f, 0f, 0f), 0.3f, 4, 0.4, 10), new Point3DImpl(25, 10,
              -20), 3));

          world.add(new Sphere(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 4, 0.4, 10),
              new Point3DImpl(30, 0, -5), 1));

        }

        if (method == 3) {

          world.add(new Box(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 4, 0.4, 10),
              new Point3DImpl(20, -10, -20), new Point3DImpl(25, 20, -15)));
          world.add(new Box(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 4, 0.4, 10),
              new Point3DImpl(20, -10, -5), new Point3DImpl(25, 20, 0)));
          world.add(new Box(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 4, 0.4, 10), new Point3DImpl(20, 3, -15),
              new Point3DImpl(25, 7, -5)));

          world.add(new Box(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 4, 0.4, 10),
              new Point3DImpl(20, -10, 10), new Point3DImpl(25, 10, 15)));
          world.add(new Box(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.3f, 4, 0.4, 10), new Point3DImpl(20, 13, 10),
              new Point3DImpl(25, 18, 15)));

        }

        // Light
        if (light == 0) {
          world.add(new DiffuseLight(new Point3DImpl(-50, 100, -10), new RGBColor(1, 1, 1)));
          world.add(new AmbientLightImpl(new RGBColor(1f, 0.7f, 0f)));
        }
        if (light == 1) {
          world.add(new DiffuseLight(new Point3DImpl(50, -100, 35), new RGBColor(1, 1, 1)));
          world.add(new AmbientLightImpl(new RGBColor(1f, 0.7f, 0f)));
        }
        if (light == 2) {

          world.add(new SpotLight(new Point3DImpl(50, 0, 0), new RGBColor(1, 1, 1)));
          world.add(new AmbientLightImpl(new RGBColor(1f, 0.7f, 0f)));
        }
        // Plane

        // Ambient

        if (plane == 0) {
          world.add(new Plane(new ReflectivNoLightMaterial(new RGBColor(0.5f), 0.3f), new Point3DImpl(0, -15, 0),
              new NormalImpl(0, 1, 0)));
        }

        // no reflect
        if (plane == 1) {
          world.add(new Plane(new LightMaterial(new RGBColor(0f, 0f, 1f), 0.1f, 1, 0.1f, 1),
              new Point3DImpl(0, -15, 0), new NormalImpl(0, 1, 0)));
        }

        // Camera
        final Point3D eye = new Point3DImpl(0, 0, 0);
        final Vector3D upVector = new Vector3DImpl(0, 1, 0);
        final Point3D lookAt = new Point3DImpl(40, 0, 0);

        // Window
        new RayTracerWindow(new TracerImpl(world), world, new CameraImpl(eye, upVector, lookAt, true));
      } else {
        JOptionPane.showMessageDialog(null, "Abgebrochen \n System Exit");
        System.exit(0);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "Raytracer []";
  }
}
