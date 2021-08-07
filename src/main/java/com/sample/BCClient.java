package com.sample;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieServiceResponse.ResponseType;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

public class BCClient {
	//http://34.230.82.179:8081/kie-server/services/rest/server/containers/instances/DemoRule_1.0.0-SNAPSHOT
	
	private static final String URL = "http://34.230.82.179:8081/kie-server/services/rest/server";
	  private static final String USER = "admin";
	  private static final String PASSWORD = "admin";

	  private static final MarshallingFormat FORMAT = MarshallingFormat.XSTREAM;

	  private static KieServicesConfiguration conf;
	  private static KieServicesClient kieServicesClient;

	
	public static void main(String[] args) {
		conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);

	    //If you use custom classes, such as Obj.class, add them to the configuration.
	    Set<Class<?>> extraClassList = new HashSet<Class<?>>();
	    extraClassList.add(Object.class);
	    conf.addExtraClasses(extraClassList);

	    conf.setMarshallingFormat(FORMAT);
	    kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
		
		// Retrieve list of KIE containers
	      List<KieContainerResource> kieContainers = kieServicesClient.listContainers().getResult().getContainers();
	      if (kieContainers.size() == 0) {
	          System.out.println("No containers available...");
	          return;
	      }
	      else {
	    	  
	    	  System.out.println("containers available...");
	    	  KieContainerResource container = kieContainers.get(0);
	          String containerId = container.getContainerId();
	          System.out.println("containerId..."+containerId);
	      }
	      
	      String containerId = "com.myspace:DemoRule:1.0.0";
	      System.out.println("== Sending commands to the server ==");
	      RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
	      KieCommands commandsFactory = KieServices.Factory.get().getCommands();

	      Command<?> insert = commandsFactory.newInsert("Samrat");
	      Command<?> fireAllRules = commandsFactory.newFireAllRules();
	      Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insert, fireAllRules));

	      ServiceResponse<String> executeResponse = rulesClient.executeCommands(containerId, batchCommand);

	      if(executeResponse.getType() == ResponseType.SUCCESS) {
	        System.out.println("Commands executed with success! Response: ");
	        System.out.println(executeResponse.getResult());
	      } else {
	        System.out.println("Error executing rules. Message: ");
	        System.out.println(executeResponse.getMsg());
	      }
	      
	      
		
	}

}
