/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pepsoft.worldpainter.objects;

import javax.vecmath.Point3i;
import java.io.Serializable;
import java.util.Map;

/**
 * Abstract base class for {@link WPObject} implementations.
 *
 * @author pepijn
 */
public abstract class AbstractObject implements WPObject {
    @Override
    @SuppressWarnings("unchecked") // Responsibility of caller
    public <T extends Serializable> T getAttribute(String key, T _default) {
        Map<String, Serializable> attributes = getAttributes();
        if ((attributes != null) && attributes.containsKey(key)) {
            return (T) attributes.get(key);
        } else {
            return _default;
        }
    }

    @Override
    public AbstractObject clone() {
        try {
            return (AbstractObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public final Point3i getOffset() {
        return getAttribute(ATTRIBUTE_OFFSET, new Point3i(0, 0, 0));
    }

    private static final long serialVersionUID = -5872104411389620683L;
}