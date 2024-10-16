package com.api.ejemplo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.internal.eventstreaming.Message;

import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;


public class EmailService {
	
		@Autowired
	    private SesClient sesClient;

	    public void sendEmail(String from, String to, String subject, String textBody, String htmlBody) {
	        SendEmailRequest request = SendEmailRequest.builder()
	                .source(from)
	                .destination(Destination.builder().toAddresses(to).build())
	                .message(Message.builder()
	                        .subject(Content.builder().data(subject).build())
	                        .body(Body.builder()
	                                .text(Content.builder().data(textBody).build())
	                                .html(Content.builder().data(htmlBody).build())
	                                .build())
	                        .build())
	                .build();

	        SendEmailResponse response = sesClient.sendEmail(request);
	        System.out.println("Email sent! Message ID: " + response.messageId());
	
	
}
}
