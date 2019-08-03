package com.aspose.barcode.examples.barcode_image.rendering_features;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.aspose.barcode.generation.BarcodeGenerator;
//ExStart: RenderBarcodeToPrinter
public class RenderBarcodeToPrinter {

	public static void main(String[] args) {
		// Create frame instance
		Frame f = new Frame();
		// Set frame size
		f.setSize(300, 300);
		// Create and add barcode instance to frame
		f.add(new RenderBarCode());
		// Display frame
		f.setVisible(true);
	}

}

class RenderBarCode extends java.awt.Canvas {

	public void paint(Graphics g) {

		BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "1234567");

		BufferedImage bimg = (BufferedImage) bb.generateBarCodeImage();
		int w = bimg.getWidth();
		int h = bimg.getHeight();
		int[] rgb = new int[w * h];
		bimg.getRGB(0, 0, w, h, rgb, 0, w);

		if (rgb.length > 0) {
			System.out.println("RGB OK.");
		}
		g.drawImage(bimg, 0, 0, this);
	}

	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}
}
//ExEnd: RenderBarcodeToPrinter