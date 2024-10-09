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

    
    public void EmailService(SesClient sesClient) {
        this.sesClient = sesClient;
    }

    public void enviarNotificacion(String remitente, String destinatario, String asunto, String textoPlano, String cuerpoHtml) {
        // Crear la solicitud de envío de correo electrónico
        SendEmailRequest solicitud = SendEmailRequest.builder()
                .source(remitente)
                .destination(Destination.builder().toAddresses(destinatario).build())
                .message(Message.builder()
                        .subject(Content.builder().data(asunto).build())
                        .body(Body.builder()
                                .text(Content.builder().data(textoPlano).build())
                                .html(Content.builder().data(cuerpoHtml).build())
                                .build())
                        .build())
                .build();

        // Enviar el correo electrónico utilizando SES
        SendEmailResponse respuesta = sesClient.sendEmail(solicitud);

        // Registrar el ID del mensaje enviado
        System.out.println("Correo enviado exitosamente. ID del mensaje: " + respuesta.messageId());
    }
	
	
}
