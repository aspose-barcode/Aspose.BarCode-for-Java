package com.aspose.barcode.examples.barcodemysql;

//ExStart: Main
public class Main {
	public static void main(String[] args) {
		// To insert and update barcode image in MySQL DB
		GenerateAndSaveBarCode sample1 = new GenerateAndSaveBarCode();
		sample1.PerformInsertExample();
		sample1.PerformUpdateExample();

		// To fetch the image from DB and recognize codetext
		FetchAndRecognizeBarCode sample2 = new FetchAndRecognizeBarCode();
		sample2.PerformRecognition();
	}
}
//ExEnd: Main