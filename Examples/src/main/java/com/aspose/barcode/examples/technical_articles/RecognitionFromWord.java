package com.aspose.barcode.examples.technical_articles;

//ExStart: RecognitionFromWord
import java.text.MessageFormat;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.words.ImageType;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;

public class RecognitionFromWord {
	public static void main(String[] args) {
		try {
			String dataDir = Utils.getDataDir(RecognitionFromWord.class) + "TechnicalArticles/";

			// Generate barcode image
			BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39_STANDARD);

			generator.setCodeText("test-123");
			String strBarCodeImageSave = dataDir + "img.jpg";
			generator.save(strBarCodeImageSave);

			// Add the image to a Word doc
			com.aspose.words.Document doc = new com.aspose.words.Document();
			com.aspose.words.DocumentBuilder docBuilder = new com.aspose.words.DocumentBuilder(doc);
			docBuilder.insertImage(strBarCodeImageSave);
			String strWordFile = "docout.doc";
			doc.save(dataDir + strWordFile);

			// Recognition part
			// Extract image from the Word document
			NodeCollection<com.aspose.words.Shape> shapes = doc.getChildNodes(NodeType.SHAPE, true);
			int imageIndex = 0;

			for (com.aspose.words.Shape shape : shapes) {
				if (shape.hasImage()) {
					// If this shape is an image, extract image to file
					String extension = ImageTypeToExtension(shape.getImageData().getImageType());
					String imageFileName = MessageFormat.format("Image.ExportImages.{0} Out.{1}", imageIndex,
							extension);
					String strBarCodeImageExtracted = "" + imageFileName;
					shape.getImageData().save(strBarCodeImageExtracted);

					// Recognize barcode from this image
					// BufferedImage img = (BufferedImage)
					// Toolkit.getDefaultToolkit().getImage(strBarCodeImageExtracted);

					BarCodeReader reader = new BarCodeReader(strBarCodeImageSave, DecodeType.CODE_39_STANDARD);
					for (BarCodeResult result : reader.readBarCodes()) {
						System.out.println("CodeText: " + result.getCodeText());
						System.out.println("Symbology type: " + result.getCodeType());
					}
					imageIndex++;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static String ImageTypeToExtension(int imageType) throws Exception {
		switch (imageType) {
		case ImageType.BMP:
			return "bmp";
		case ImageType.EMF:
			return "emf";
		case ImageType.JPEG:
			return "jpeg";
		case ImageType.PICT:
			return "pict";
		case ImageType.PNG:
			return "png";
		case ImageType.WMF:
			return "wmf";
		default:
			throw new Exception("Unknown image type.");
		}
	}

}
//ExEnd: RecognitionFromWord