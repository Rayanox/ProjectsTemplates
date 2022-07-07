package com.karavel.batch.seo.linking.common.beans;


public enum Status {
    SUCCESS("OK"), ERROR("KO");
    
    private String shortLabel;
    
    Status(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    public String getShortLabel() {
        return shortLabel;
    }
    
}
