package com.studio.core;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public class FontLoader {
    public static Font loadCustomFont(String path, float size) {
        try {
            // Load the font file from your classpath resources
            InputStream is = FontLoader.class.getResourceAsStream(path);
            if (is == null) {
                System.err.println("Font file not found at " + path);
                return new Font("SansSerif", Font.PLAIN, (int) size);
            }

            // Create the base font object
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);

            // Register the font with the local Graphics Environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(baseFont);

            // Return the font with your specified size and plain style
            return baseFont.deriveFont(Font.PLAIN, size);

        } catch (Exception e) {
            e.printStackTrace();
            // Fallback font in case of failure
            return new Font("SansSerif", Font.PLAIN, (int) size);
        }
    }
}
