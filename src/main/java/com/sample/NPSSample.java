package com.sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.core.definitions.InternalKnowledgePackage;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import com.drools.model.Account;
//import com.drools.service.notification.NotificationService;
//import com.drools.service.notification.NotificationService;
//import com.drools.service.notification.impl.NotificationServiceImpl;
//import com.drools.service.notification.impl.NotificationServiceImpl;

/**
 * This is a sample class to launch a rule.
 */
@SuppressWarnings({ "restriction", "unused" })
public class NPSSample {
	private static StatelessKieSession session;

	public static final void main(String[] args) {
		try {
			if (args.length < 8) {
				System.out.println(
						"Usage: DemoRule <contactRecordStatus> <contactEmail> <recentInteraction> <leaseStatus> <recentPayment> <bankruptcy> <leaseTerm> <renewal>\n\n"
								+ "    <contactRecordStatus> is Contact Record Status\n"
								+ "    <contactEmail> is Contact Email Verification Status\n"
								+ "    <leaseStatus> Lease Status\n"
								+ "    <recentPayment> Most Recent Payment\n"
								+ "    <bankruptcy> is Bankruptcy? Yes or No\n"
								+ "    <leaseTerm> Lease Term\n"
								+ "    <renewal> Renewal Opportunity\n"
								);
				System.exit(1);
			}
			
			final String contactRecordStatus = args[0];
			final String contactEmail = args[1];
			final String recentInteraction = args[2];
			final String leaseStatus = args[3];
			final String recentPayment = args[4];
			final String bankruptcy = args[5];
			final String leaseTerm = args[6];
			final String renewal = args[7];
			

			System.out.println("NPS account details: " + contactRecordStatus + " " + contactEmail + " "+recentInteraction+" "+leaseStatus+" "+recentPayment+" "+bankruptcy+" "+leaseTerm+" "+renewal);
            int leaseTermVal = Integer.parseInt(leaseTerm);
			
			InternalKnowledgeBase knowledgeBase = createKnowledgeBaseFromSpreadsheet();
			session = knowledgeBase.newStatelessKieSession();
			//NotificationService notificationService = new NotificationServiceImpl();

			// Working Memory (Facts)
			Account account = new Account();
			account.setContactRecordStatus(contactRecordStatus);
			account.setContactEmail(contactEmail);
			account.setRecentInteraction(recentInteraction);
			account.setLeaseStatus(leaseStatus);
			account.setRecentPayment(recentPayment);
			account.setBankruptcy(bankruptcy);
			account.setLeaseTerm(leaseTermVal);
			account.setRenewal(renewal);
			
			
			//Pattern Matching
			session.execute(account);
			
            
			
			if(account.getSendSurvey()=="yes") {
				//notificationService.sendEmail(account);
				System.out.println("\n Action : ");
				System.out.println("NPS Rollout Email Notification Sent to Customer ");
				System.out.println("-----------------------------");
				System.out.println("customer1 " + account);
				System.out.println("-----------------------------");
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private static InternalKnowledgeBase createKnowledgeBaseFromSpreadsheet() throws Exception {
		final DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		dtconf.setInputType(DecisionTableInputType.XLS);

		final KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("NPS_DecisionTable301.xls"), ResourceType.DTABLE, dtconf);

		if (knowledgeBuilder.hasErrors()) {
			System.out.println(knowledgeBuilder.getErrors().toString());
			throw new RuntimeException(knowledgeBuilder.getErrors().toString());
		}

		final InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addPackages(knowledgeBuilder.getKnowledgePackages());

		return knowledgeBase;
	}
}
