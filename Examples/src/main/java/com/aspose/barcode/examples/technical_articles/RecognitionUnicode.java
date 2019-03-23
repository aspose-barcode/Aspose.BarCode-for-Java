package com.aspose.barcode.examples.technical_articles;
//ExStart: RecognitionUnicode
import com.aspose.barcode.*;
import com.aspose.barcode.generation.BarCodeGenerator;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
 
public class RecognitionUnicode {
    public static void main(String[] args) throws IOException {
        try
        {
            License lic = new License();
            lic.setLicense("D:\\ aspose.barcode.lic");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        String file = "pdf417_un.png";
        String scodeText = "منحة";
        System.out.println("codetext: " + scodeText);
        String codeText = getCodeTextFromUnicode(scodeText);
        BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.PDF_417, codeText);
        generator.save(file);
 
        BarCodeReader r = new BarCodeReader(file,DecodeType.PDF_417);
        boolean rb = r.read();
        String rc = r.getCodeText();
        try {
            String s = getUnicodeFromCodeText(rc);
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        r.close();
    }
 
    private static String getCodeTextFromUnicode(String s) throws UnsupportedEncodingException {
        byte[] bs = s.getBytes("UTF-8");
       StringBuffer buf = new StringBuffer();
        for (int i = 0; i < bs.length; i++) {
            byte b = bs[i];
            if(b >=0)
            {
                buf.append((char)b);
            }
            else
            {
                buf.append((char)(127 - b));
            }
        }
        return buf.toString();
    }
    private static String getUnicodeFromCodeText(String cs) throws UnsupportedEncodingException {
        byte[] bs = new byte[cs.length()];
        for(int i=0; i< cs.length();i++)
        {
            char c = cs.charAt(i);
            if(c < 128)
            {
                bs[i] = (byte) c;
            }
            else
            {
                bs[i] = (byte) (127 - c);
            }
        }
        return new String(bs," UTF-8");
    }
}
//ExEnd: RecognitionUnicode