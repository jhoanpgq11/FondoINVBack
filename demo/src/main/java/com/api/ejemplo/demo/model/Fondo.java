package com.api.ejemplo.demo.model;


import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Fondos")
public class Fondo {

	 @DynamoDBAutoGeneratedKey
	    @DynamoDBHashKey(attributeName = "fondoId")
	    private String fondoId;

	    @DynamoDBAttribute(attributeName = "nombreFondo")
	    private String nombreFondo;

	    @DynamoDBAttribute(attributeName = "descripcion")
	    private String descripcion;

	    @DynamoDBAttribute(attributeName = "montoMinimo")
	    private double montoMinimo; 

	    public Fondo() {
	        this.fondoId = UUID.randomUUID().toString();
	    }

	   
	    public String getFondoId() {
	        return fondoId;
	    }

	    public void setFondoId(String fondoId) {
	        this.fondoId = fondoId;
	    }

	   
	    public String getNombreFondo() {
	        return nombreFondo;
	    }

	    public void setNombreFondo(String nombreFondo) {
	        this.nombreFondo = nombreFondo;
	    }

	    
	    public String getDescripcion() {
	        return descripcion;
	    }

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }

	    
	    public double getMontoMinimo() {
	        return montoMinimo;
	    }

	    public void setMontoMinimo(double montoMinimo) {
	        this.montoMinimo = montoMinimo;
	    }
	}

	

