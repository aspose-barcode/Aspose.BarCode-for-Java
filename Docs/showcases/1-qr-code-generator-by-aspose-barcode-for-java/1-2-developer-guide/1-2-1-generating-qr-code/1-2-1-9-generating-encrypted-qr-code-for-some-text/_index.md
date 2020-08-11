---
title: 1.2.1.9. Generating Encrypted QR Code for Some Text
type: docs
weight: 90
url: /java/1-2-1-9-generating-encrypted-qr-code-for-some-text/
---

## **How to generate Encrypted QR Code for some Text?**
To generate an encrypted QR Code for some text, please refer to the following steps:

1. Select the Type: **Encrypted QR Code**
1. Provide the following information in content section: Encrypted Text 
   1. Password (Key): enter your password that you want to used for encrypting the text message
   1. Text: enter the short text message that you want to encrypt 
1. Customize QR Code Settings (Optional)
1. Click **Generate Preview** button

That's It. You will see the generated encrypted QR Code under the preview section.
## **How it works?**
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<Short Message Text>&passKey=<Password>foreColor=&bgColor=&ecc=&size=&format=


API Example:

\------------

http://localhost:8080/qrcodegen/api/qrcode/generate?data=Aspose.BarCode&passKey=aspose&foreColor=&bgColor=&ecc=&size=&format=


{{< /highlight >}}

**Generated SMS QR Code**

|<p>![todo:image_alt_text](1-2-1-9-generating-encrypted-qr-code-for-some-text_1.png)</p><p></p>|
| :- |
**Decryption Using OpenSSL**

{{< highlight java >}}

 [user@aspose]$ echo <encrypted_text> | openssl enc -aes-128-cbc -a -d


Example:

[user@aspose]$ echo "U2FsdGVkX1+ntJUYuMJKFAOrvreTSo/uvKSEnDUnw3c="|openssl enc -aes-128-cbc -a -d

enter aes-128-cbc decryption password: aspose

Aspose.BarCode

{{< /highlight >}}
#### **Java Script**
JS code spinet to generate encrypted QR Code from content input fields.

{{< highlight java >}}

      if(page == 'encryption'){



     var passKey = this.$('.input-encryption-password').val();

   if (passKey == '' || passKey == null ) {

   console.log("Password is required");

   alert("Password is required");

   this.$('.qrcode-preview-processing').addClass('hide');

     this.$('.qrcode-preview-image').removeClass('hide');

   return false;

   }



     var requestString= this.$('.input-encryption-message').val();



     if (requestString == '' || requestString == null ) {

   console.log("Message Text is required");

   alert("Message Text is required");

   this.$('.qrcode-preview-processing').addClass('hide');

     this.$('.qrcode-preview-image').removeClass('hide');

   return false;

   }



     requestString = requestString + '&passKey=' + passKey;

     requestString = this.addRequestSettings(requestString);



     var that = this;



     this.$('.qrcode-preview-image').load(function(){

     that.$('.qrcode-preview-processing').addClass('hide');

     that.$('.qrcode-preview-image').removeClass('hide');

     })    

     .error(function(){

     that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');

     })

     .attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);



     this.updateDownloadTag(requestString);

     }



{{< /highlight >}}
#### **Java**
**Core API Method - QRCodeManagementController.generateQRCode** 

{{< highlight java >}}

 @RequestMapping(value = "generate", method = RequestMethod.GET,

    		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType_IMAGE_TIFF_VALUE, MediaType_IMAGE_BMP_VALUE})

    @ApiOperation(value = "Generate QR Code.")

    public ResponseEntity<byte[]>  generateQRCode(

    		@ApiParam( value = "data", name="data" , required = true)

    		@RequestParam("data") String data,

        HttpServletRequest request,HttpServletResponse response,

        @ApiParam( value = "A user-chosen password that can be used with password-based encryption (PBE) Algo PBEWITHMD5AND128BITAES-CBC-OPENSSL)", name="passKey", required=false) @RequestParam(required=false, value="passKey") String passKey,

        @ApiParam( value = "ForeColor e.g #000000 (Black - RGB(hex))", name="foreColor", required=false) @RequestParam(required=false, value="foreColor") String foreColor,

        @ApiParam( value = "BackgroundColor e.g #FFFFFF (White - RGB(hex))", name="bgColor", required=false) @RequestParam(required=false, value="bgColor") String bgColor,

        @ApiParam( value = "L|M|Q|H - Reed-Solomon error correctionCode Level(from low to high) default=Low", name="ecc", required=false) @RequestParam(required=false, value="ecc") String ecc,

        @ApiParam( value = "Image Size e.g #150x150", name="size", required=false) @RequestParam(required=false, value="size") String size,

        @ApiParam( value = "jpeg|tiff|gif|png|bmp - default=png", name="format", required=false) @RequestParam(required=false, value="format") String format,

        @ApiParam( value = "true|false default=false", name="download", required=false) @RequestParam(required=false, value="download") boolean download,

        @ApiIgnore @Value("#{request.getHeader('" +  ACCEPT_HEADER + "')}") String acceptHeaderValue) throws Exception {


{{< /highlight >}}

**Main** **encrypt Method - StringEncryptor.encrypt**

{{< highlight java >}}

 	public  static String encrypt(String Data, String password) throws Exception {

		Security.addProvider(new BouncyCastleProvider());

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

		final Random r = new SecureRandom();

		byte[] salt = new byte[8];

		r.nextBytes(salt);

		SecretKeyFactory fact = SecretKeyFactory.getInstance("PBEWITHMD5AND128BITAES-CBC-OPENSSL", "BC");

		c.init(Cipher.ENCRYPT_MODE, fact.generateSecret(new PBEKeySpec(password.toCharArray(), salt, 100)));

		 	byte[] encVal = c.doFinal(Data.getBytes());

		 	ByteArrayOutputStream bos = new ByteArrayOutputStream();

		 	//writing encrypted data along with the salt in the format readable by open ssl api

		 	bos.write("Salted__".getBytes());

		 	bos.write(salt);

		 	bos.write(encVal);

		    String encryptedValue = new BASE64Encoder().encode(bos.toByteArray());

		    bos.close();

		    return encryptedValue;


	}

{{< /highlight >}}
