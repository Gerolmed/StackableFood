package net.endrealm.stackablefood.api;

import lombok.Data;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Data
public final class RenderTransform {
    private final Vector3f preTranslate;
    private final Vector3f postTranslate;

    private final Quaternionf rotate;
    private final Vector3f rotateVec;


    public static final RenderTransform ITEM_TRANSFORM = new RenderTransform(
            new Vector3f(0, 1 / 32f, 0),
            new Vector3f(0, 1 / 32f, 0),
            new Quaternionf(new AxisAngle4f((float) (90 * Math.PI / 180), new Vector3f(1, 0, 0))),
            new Vector3f(1, 0, 0)
    );

    public static final RenderTransform BLOCK_TRANSFORM = new RenderTransform(
            new Vector3f(.5f, -(1 / 32f) * 1.5f, 0),
            new Vector3f(0, 0.25f, 0),
            new Quaternionf(),
            new Vector3f(0, 0, 0)
    );
}
