package com.app.oxostayadmin.model;

public class ApprovedModel {

    String aadhaarCard,address,fullName,gstCert,panCard,phNumber;
    boolean approvedOrNot;

    public ApprovedModel(String aadhaarCard, String address, boolean approvedOrNot, String fullName, String gstCert, String panCard, String phNumber) {
        this.aadhaarCard = aadhaarCard;
        this.address = address;
        this.approvedOrNot = approvedOrNot;
        this.fullName = fullName;
        this.gstCert = gstCert;
        this.panCard = panCard;
        this.phNumber = phNumber;
    }

    public ApprovedModel(){

    }

    public String getAadhaarCard() {
        return aadhaarCard;
    }

    public void setAadhaarCard(String aadhaarCard) {
        this.aadhaarCard = aadhaarCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getApprovedOrNot() {
        return approvedOrNot;
    }

    public void setApprovedOrNot(boolean approvedOrNot) {
        this.approvedOrNot = approvedOrNot;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGstCert() {
        return gstCert;
    }

    public void setGstCert(String gstCert) {
        this.gstCert = gstCert;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
