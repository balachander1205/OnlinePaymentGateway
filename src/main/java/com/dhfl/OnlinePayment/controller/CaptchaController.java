package com.dhfl.OnlinePayment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sun.media.jfxmedia.logging.Logger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("captcha")
public class CaptchaController {

	@RequestMapping(method = RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpg");
		int iTotalChars = 6;
		int iHeight = 40;
		int iWidth = 150;
		Font fntStyle1 = new Font("Arial", Font.BOLD, 30);
		Random randChars = new Random();
		//String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, iTotalChars);
		String sImageCode = getCaptcha();
		System.out.println("Image COde="+sImageCode);
		BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();
		int iCircle = 15;
		//g2dImage.setColor(new Color(255, 0, 0));
		/*for (int i = 0; i < iCircle; i++) {
			g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
		}*/
		g2dImage.setFont(fntStyle1);
		//g2dImage.setColor(new Color(0, 255, 0));
		for (int i = 0; i < iTotalChars; i++) {
			//g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
			g2dImage.setColor(new Color(255, 255, 255));
			if (i % 2 == 0) {
				g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
			} else {
				g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
			}
		}
		OutputStream osImage = response.getOutputStream();
		ImageIO.write(biImage, "jpeg", osImage);
		g2dImage.dispose();
		HttpSession session = request.getSession();
		session.setAttribute("captcha_security", sImageCode);
	}
	
	public static String getCaptcha() {
		StringBuilder sb = null;
		try {
			int n = 6;
			// chose a Character random from this String
			String AlphaNumericString = "0123456789";
			// create StringBuffer size of AlphaNumericString
			sb = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
				// generate a random number between
				// 0 to AlphaNumericString variable length
				int index = (int) (AlphaNumericString.length() * Math.random());
				// add Character one by one in end of sb
				sb.append(AlphaNumericString.charAt(index));
			}
		}catch (Exception e) {
			System.out.println("Exception@getCaptcha="+e);
		}
		return sb.toString();
	} 
}