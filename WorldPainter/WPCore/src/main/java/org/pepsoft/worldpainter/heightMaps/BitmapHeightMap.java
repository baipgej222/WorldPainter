/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pepsoft.worldpainter.heightMaps;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;

/**
 *
 * @author SchmitzP
 */
public final class BitmapHeightMap extends AbstractHeightMap {
    public BitmapHeightMap(String name, BufferedImage image) {
        this(name, image, 0, null);
    }

    public BitmapHeightMap(String name, BufferedImage image, int channel) {
        this(name, image, channel, null);
    }

    public BitmapHeightMap(String name, BufferedImage image, File imageFile) {
        this(name, image, 0, imageFile);
    }

    public BitmapHeightMap(String name, BufferedImage image, int channel, File imageFile) {
        super(name);
        this.image = image;
        raster = image.getRaster();
        extent = new Rectangle(0, 0, image.getWidth(), image.getHeight());
        this.channel = channel;
        this.imageFile = imageFile;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    // HeightMap
    
    @Override
    public float getHeight(int x, int y) {
        if (extent.contains(x, y)) {
            return raster.getSample(x, y, channel);
        } else {
            return 0f;
        }
    }

    @Override
    public float getBaseHeight() {
        return 0;
    }

    @Override
    public Rectangle getExtent() {
        return extent;
    }

    @Override
    public int getColour(int x, int y) {
        if (extent.contains(x, y)) {
            return image.getRGB(x, y);
        } else {
            return 0;
        }
    }

    public File getImageFile() {
        return imageFile;
    }

    private final BufferedImage image;
    private final int channel;
    private final Raster raster;
    private final Rectangle extent;
    private final File imageFile;
}