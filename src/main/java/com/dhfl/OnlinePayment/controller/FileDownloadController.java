package com.dhfl.OnlinePayment.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhfl.OnlinePayment.config.ApplicationConfig;

@Controller	
@RequestMapping("/download")	
public class FileDownloadController 
{
	Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private ApplicationConfig applicationConfig;
	
    @RequestMapping("/pdf/selfcare")
    public void downloadPDFResource( HttpServletRequest request, 
                                     HttpServletResponse response) 
    {
        String dataDirectory = request.getServletContext().getRealPath(applicationConfig.getPdfDocLocation());
        Path file = Paths.get(dataDirectory, applicationConfig.getPdfDocName());
        if (Files.exists(file)) 
        {
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
}
