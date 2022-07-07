package com.karavel.batch.seo.linking.common.beans;

public enum LinkZTType { 
    SAME_ZTn("meme"), 
    DIFFERENT_ZTn("differente");
    
    private String naturalLanguage;
    
    private LinkZTType(String naturalLanguage) {
        this.naturalLanguage = naturalLanguage;
    }
    
    public static LinkZTType getLinkZTType(String naturalLanguage) {
        LinkZTType linkZTType = null;
        for(LinkZTType currLinkZTType : LinkZTType.values()) {
            if(currLinkZTType != null && currLinkZTType.naturalLanguage.equals(naturalLanguage)) {
                linkZTType = currLinkZTType;
                break;
            }
        }
        return linkZTType;
    }
    
    public String naturalLanguage() {
        return naturalLanguage;
    }
    
}