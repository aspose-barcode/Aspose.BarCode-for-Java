package com.aspose.barcode.examples.technical_articles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;

public class ReadAndSortBarcodesInSpecificOrder {

	public static void main(String[] args) {
		// ExStart: ReadAndSortBarcodesInSpecificOrder
		String dataDir = Utils.getDataDir(ReadAndSortBarcodesInSpecificOrder.class) + "TechnicalArticles/";
		String path = dataDir + "barcode.png";
		List<FoundBarCodes> found = new ArrayList<FoundBarCodes>();

		BarCodeReader reader = new BarCodeReader(path, DecodeType.CODE_128);
		
		for (BarCodeResult result : reader.readBarCodes())
		{
			found.add(new FoundBarCodes(result.getCodeText(), result.getRegion()));
		}
		
		Comparator<FoundBarCodes> foundComparator = new Comparator<FoundBarCodes>() {
			@Override
			public int compare(FoundBarCodes e1, FoundBarCodes e2) {
				return e1.getCodeText().compareTo(e2.getCodeText());
			}
		};

		found.sort(foundComparator);

		int i = 1;
		for (FoundBarCodes barcode : found) {
			Point[] point = barcode.BarCodeRegion.getPoints();
			System.out.println("Codetext ( " + i + " ): " + barcode.CodeText);
			System.out.println("Top left coordinates: X = " + point[0].getX() + ", Y = " + point[0].getY());
			System.out.println("Bottom left coordinates: X = " + point[1].getX() + ", Y = " + point[1].getY());
			System.out.println("Bottom right coordinates: X = " + point[2].getX() + ", Y = " + point[2].getY());
			System.out.println("Top right coordinates: X = " + point[3].getX() + ", Y = " + point[3].getY());
			System.out.println();
			i++;
		}
		// ExEnd: ReadAndSortBarcodesInSpecificOrder
	}

}
