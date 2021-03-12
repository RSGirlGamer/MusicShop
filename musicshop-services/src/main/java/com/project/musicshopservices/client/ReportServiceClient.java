package com.project.musicshopservices.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceClient {
	@Value("${spring.base.url.ws.reports}")
	String urlReportsWS;
	
	public Response generateReport(String orderID, String recipient, String client) {
		ClientConfig clientConfig = new ClientConfig();
		Client clientWS = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = clientWS.target(this.urlReportsWS).path("generateReport");
		Form form = new Form();
		form.param("orderID", orderID);
		form.param("client", client);
		form.param("recipient", recipient);
		Response response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Response.class);
		return response;
	}
}
