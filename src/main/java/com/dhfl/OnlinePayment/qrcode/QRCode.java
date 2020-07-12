package com.dhfl.OnlinePayment.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.util.Base64Utils;

public class QRCode {
	public static String generateQRCodeImage(String text){
		String encodedText = "";
		try {
			int width = 350;
			int height = 350;
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
			stream.flush();

			byte[] data = stream.toByteArray();
			encodedText = "data:image/png;base64," + Base64Utils.encodeToString(data);
			stream.close();
		} catch (Exception e) {
			System.out.println("Exception@QRCode : " + e);
		}
		return encodedText;
	}
}
