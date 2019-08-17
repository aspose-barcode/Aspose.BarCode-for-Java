package com.aspose.barcode.examples;

public class ApplyMeteredKey {
	public static void ApplyMeteredKey() throws Exception {
		//ExStart: ApplyMeteredKey
		// set metered public and private keys
		com.aspose.barcode.metered.Metered objMeter = new com.aspose.barcode.metered.Metered();
		
		// Access the setMeteredKey property and pass public and private keys as parameters
		objMeter.setMeteredKey("PublicKey", "PrivateKey");
		 
		// DO PROCESSING
		
		// get metered data amount
		double amount = com.aspose.barcode.metered.Metered.getConsumptionQuantity();
		
		// Display information
		System.out.println("Amount consumed: " + amount);
		//ExEnd: ApplyMeteredKey
	}
}
