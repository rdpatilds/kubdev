package com.drools.model;

public class Account {
	private String contactRecordStatus;
	private String contactEmail;
	private String recentInteraction;
	private String leaseStatus;
	private String recentPayment;
	private String bankruptcy;
	private int leaseTerm;
	private String renewal;
	private String sendSurvey;
	
	public Account() {
	}

	public String getContactRecordStatus() {
		return contactRecordStatus;
	}

	public void setContactRecordStatus(String contactRecordStatus) {
		this.contactRecordStatus = contactRecordStatus;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	public String getRecentInteraction() {
		return recentInteraction;
	}

	public void setRecentInteraction(String recentInteraction) {
		this.recentInteraction = recentInteraction;
	}


	public String getLeaseStatus() {
		return leaseStatus;
	}

	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}

	public String getRecentPayment() {
		return recentPayment;
	}

	public void setRecentPayment(String recentPayment) {
		this.recentPayment = recentPayment;
	}

	public String getBankruptcy() {
		return bankruptcy;
	}

	public void setBankruptcy(String bankruptcy) {
		this.bankruptcy = bankruptcy;
	}

	public int getLeaseTerm() {
		return leaseTerm;
	}

	public void setLeaseTerm(int leaseTerm) {
		this.leaseTerm = leaseTerm;
	}

	public String getRenewal() {
		return renewal;
	}

	public void setRenewal(String renewal) {
		this.renewal = renewal;
	}
	
	public String getSendSurvey() {
		return sendSurvey;
	}

	public void setSendSurvey(String sendSurvey) {
		this.sendSurvey = sendSurvey;
	}
	
	
	@Override
	public String toString() {
		return "NPS complaince: [contactRecordStatus=" + contactRecordStatus + ", contactEmail=" + contactEmail + ", recentInteraction=" + recentInteraction
				+ ", leaseStatus=" + leaseStatus + ", recentPayment=" + recentPayment + ", bankruptcy=" + bankruptcy + ", leaseTerm=" + leaseTerm + ", renewal=" + renewal
				+ "]";
	}

	
}
