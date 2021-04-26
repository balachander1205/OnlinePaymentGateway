package com.dhfl.OnlinePayment.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhfl.OnlinePayment.config.ApplicationConfig;

@Controller	
@RequestMapping("/download")	
public class FileDownloadController 
{
	Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private ApplicationConfig applicationConfig;
	
    @RequestMapping(value = "/pdf/selfcare", method = RequestMethod.GET)
    @ResponseBody
    public void downloadPDFResource( HttpServletRequest request, 
                                     HttpServletResponse response) 
    {
        String dataDirectory = request.getServletContext().getRealPath(applicationConfig.getPdfDocLocation());
        dataDirectory = applicationConfig.getPdfDocLocation();
        System.out.println("PDF Location="+dataDirectory);
        Path file = Paths.get(dataDirectory, applicationConfig.getPdfDocName());
        if (Files.exists(file)) 
        {
        	System.out.println("File Name="+applicationConfig.getPdfDocName()+"| Doc Type="+applicationConfig.getPdfDocName());
            response.setContentType(applicationConfig.getPdfDocType());
            response.addHeader("Content-Disposition", "attachment; filename="+applicationConfig.getPdfDocName());
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } 
            catch (Exception ex) {
                ex.printStackTrace();
                logger.debug("Exception@/download/pdf/selecare="+ex);
            }
        }
    }
    
    @RequestMapping(value = "/pdf/tc", method = RequestMethod.GET)
    @ResponseBody
    public void downloadTCResource( HttpServletRequest request, 
                                     HttpServletResponse response) 
    {
        String dataDirectory = request.getServletContext().getRealPath(applicationConfig.gettNCpdfDocLocation());
        dataDirectory = applicationConfig.getPdfDocLocation();
        System.out.println("T&C PDF Location="+dataDirectory);
        Path file = Paths.get(dataDirectory, applicationConfig.gettNCpdfDocName());
        if (Files.exists(file)) 
        {
        	System.out.println("T&C File Name="+applicationConfig.gettNCpdfDocName()+"| Doc Type="+applicationConfig.gettNCpdfDocName());
            response.setContentType(applicationConfig.getPdfDocType());
            response.addHeader("Content-Disposition", "attachment; filename="+applicationConfig.gettNCpdfDocName());
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } 
            catch (Exception ex) {
                ex.printStackTrace();
                logger.debug("Exception@/download/pdf/tc="+ex);
            }
        }
    }
}
