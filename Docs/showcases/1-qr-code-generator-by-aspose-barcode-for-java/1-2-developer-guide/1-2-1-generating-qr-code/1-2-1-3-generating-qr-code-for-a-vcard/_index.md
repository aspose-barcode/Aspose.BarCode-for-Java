---
title: 1.2.1.3. Generating QR Code for a VCard
type: docs
weight: 20
url: /java/1-2-1-3-generating-qr-code-for-a-vcard/
---

## **How to generate QR Code for a VCard?**
To generate a QR Code for a URL, please refer to the following steps:

1. Select the Type: **VCard**
1. Fill out the content section: VCard
1. Customize QR Code Settings (Optional)
1. Click **Generate Preview** button

That's It. You will see the generated QR Code under the preview section.
## **How it works?**
We have followed VCard Version 3.0 specification in this VCard QR Code generation. Please check the complete specification on this [URL](https://www.ietf.org/rfc/rfc2426.txt).
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<VCard>&foreColor=&bgColor=&ecc=&size=&format=

Sample VCard

\------------

BEGIN:VCARD

VERSION:3.0

N:Farooq;Sheikh;;;

FN:Farooq Sheikh

ORG:aspose.com

EMAIL;type=INTERNET;type=WORK:farooq.sheikh@aspose.com

END:VCARD

API Example:

\------------

http://localhost:8080/qrcodegen/api/qrcode/generate?data=BEGIN%3AVCARD%0D%0AVERSION%3A3.0%0D%0AN%3AFarooq%3BSheikh%3B%3B%3B%0D%0AFN%3AFarooq%20Sheikh%0D%0AORG%3Aaspose.com%0D%0AEMAIL%3Btype%3DINTERNET%3Btype%3DWORK%3Afarooq.sheikh%40aspose.com%0D%0AEND%3AVCARD&foreColor=&bgColor=&ecc=&size=&format=


{{< /highlight >}}

**Generated VCard QR Code**

|<p>![todo:image_alt_text](1-2-1-3-generating-qr-code-for-a-vcard_1.png)</p><p></p>|
| :- |
#### **Java Script**
*generateVCard* function is used to generate VCard data from vcard content input fields. Here is the code spinet. 

{{< highlight java >}}

 generateVCard : function(){



  var beginVCard = "BEGIN:VCARD\r\nVERSION:3.0\r\n";

  var endVCard = "END:VCARD";

   var firstName = this.$('.input-vcard-firstname').val();

   var lastName = this.$('.input-vcard-lastname').val();

   if (firstName == '' || firstName == null || lastName == '' || lastName == null) {

   console.log("First and Lastname are required");

   alert("First and Lastname are required");

   return null;

   }



   var vcardN = "N:" + firstName + ";" + lastName + ";;;\r\n";

   var vcardFN = "FN:" + firstName + " " + lastName + "\r\n";

   var generatedVCard = beginVCard + vcardN + vcardFN;

   var org = this.$('.input-vcard-organization').val();

   if (org != "undefined" && org != '' && org != null ){

   generatedVCard = generatedVCard + "ORG:" + org + "\r\n";

   }

   var title = this.$('.input-vcard-title').val();

   if (title != "undefined" && title != '' && title != null ){

   generatedVCard = generatedVCard + "TITLE:" + title + "\r\n";

   }

   var emailHome = this.$('.input-vcard-email-personal').val();

   if (emailHome != "undefined" && emailHome != '' && emailHome != null ){

   generatedVCard = generatedVCard + "EMAIL;type=INTERNET;type=HOME:" + emailHome + "\r\n";

   }

   var emailWork = this.$('.input-vcard-email-business').val();

   if (emailWork != "undefined" && emailWork != '' && emailWork != null ){

   generatedVCard = generatedVCard + "EMAIL;type=INTERNET;type=WORK:" + emailWork + "\r\n";

   }

   var phoneCell = this.$('.input-vcard-phone-mobile').val();

   if (phoneCell != "undefined" && phoneCell != '' && phoneCell != null ){

   generatedVCard = generatedVCard + "TEL;TYPE=CELL,VOICE:" + phoneCell + "\r\n";

   }

   var phoneHome = this.$('.input-vcard-phone-home').val();

   if (phoneHome != "undefined" && phoneHome != '' && phoneHome != null ){

   generatedVCard = generatedVCard + "TEL;TYPE=HOME,VOICE:" + phoneHome + "\r\n";

   }

   var addressVCard=";;";

   var street = this.$('.input-vcard-address-street').val();

   if (street != "undefined" && street != '' && street != null ){

   addressVCard = addressVCard + street + ";"

   }else{addressVCard = addressVCard + ";";}

   var city = this.$('.input-vcard-address-city').val();

   if (city != "undefined" && city != '' && city != null ){

   addressVCard = addressVCard + city + ";"

   }else{addressVCard = addressVCard + ";";}

   //for region

   addressVCard = addressVCard + ";"

   var postalcode = this.$('.input-vcard-address-postalcode').val();

   if (postalcode != "undefined" && postalcode != '' && postalcode != null ){

   addressVCard = addressVCard + postalcode + ";"

   }else{addressVCard = addressVCard + ";";}

   var country = this.$('.input-vcard-address-country').val();

   if (country != "undefined" && country != '' && country != null ){

   addressVCard = addressVCard + country + ";"

   }else{addressVCard = addressVCard + ";";}

   if(addressVCard !=null && addressVCard != ";;;;;;;"){

   addressVCard = "ADR:" + addressVCard  + "\r\n";

   generatedVCard = generatedVCard + addressVCard ;

   }


   var weburl = this.$('.input-vcard-url').val();

   if (weburl != "undefined" && weburl != '' && weburl != null ){

   generatedVCard = generatedVCard + "URL:" + weburl + "\r\n";

   }

   generatedVCard = generatedVCard + endVCard;


   return generatedVCard;

   },



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
