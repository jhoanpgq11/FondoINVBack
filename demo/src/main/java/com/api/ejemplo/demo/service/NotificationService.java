package com.api.ejemplo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.internal.eventstreaming.Message;
import com.api.ejemplo.demo.model.Cliente;

import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;
import software.amazon.awssdk.services.ses.model.VerifyEmailIdentityRequest;
import software.amazon.awssdk.services.ses.model.VerifyEmailIdentityResponse;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;


@Service
public class NotificationService {

	  @Autowired
	    private SesClient sesClient; 
	   
	    @Autowired
	    private SnsClient snsClient;

	    @Value("${aws.ses.from-email}")
	    private String fromEmail;

	    public void verifyEmailIdentity(String emailAddress) {
	        VerifyEmailIdentityRequest request = VerifyEmailIdentityRequest.builder()
	                .emailAddress(emailAddress)
	                .build();

	        VerifyEmailIdentityResponse response = sesClient.verifyEmailIdentity(request);
	        System.out.println("Verification request submitted for: " + emailAddress);
	        System.out.println("Response: " + response.toString());
	    }

	    public void sendEmail(String to, String subject, String body) {
	        SendEmailRequest request = SendEmailRequest.builder()
	                .source(fromEmail) // Debe ser un email verificado en SES
	                .destination(Destination.builder().toAddresses(to).build())
	                .message(Message.builder()
	                        .subject(Content.builder().data(subject).build())
	                        .body(Body.builder().text(Content.builder().data(body).build()).build())
	                        .build())
	                .build();

	        SendEmailResponse response = sesClient.sendEmail(request);
	        System.out.println("Email sent! Message ID: " + response.messageId());
	    }

	    public void sendSms(String phoneNumber, String message) {
	        PublishRequest request = PublishRequest.builder()
	                .message(message)
	                .phoneNumber(phoneNumber)
	                .build();

	        PublishResponse result = snsClient.publish(request);
	        System.out.println("SMS sent! Message ID: " + result.messageId());
	    }
}