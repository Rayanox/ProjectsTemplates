package com.karavel.batch.seo.linking.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.catalogue.sejour.in.linking.ZonesTouristiquesUriType;


/**
 * Generic step out
 * @author Walid MELLOULI
 *
 */
public class BatchOuts {

    private ZoneTouristiqueNode rootNode;
    private List<ZoneNode> zoneNodes;
    private Date insertionDate;
    private int linkingCount;
    private ArrayList<ZonesTouristiquesUriType> modesProcessed;

    public BatchOuts() {
        super();
    }

    public BatchOuts(ZoneTouristiqueNode rootNode) {
        super();
        this.rootNode = rootNode;
    }

    /**
     * @return the rootNode
     */
    public ZoneTouristiqueNode getRootNode() {
        return rootNode;
    }

    /**
     * @param rootNode the rootNode to set
     */
    public void setRootNode(ZoneTouristiqueNode rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * @return the insertionDate
     */
    public Date getInsertionDate() {
        return insertionDate;
    }

    /**
     * @param insertionDate the insertionDate to set
     */
    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }


    public int getLinkingCount() {
        return linkingCount;
    }


    public void setLinkingCount(int linkingCount) {
        this.linkingCount = linkingCount;
    }

    public void incrementLinkingCount() {
        linkingCount++;
    }

    public ArrayList<ZonesTouristiquesUriType> getModesProcessed() {
        return modesProcessed;
    }

    public void setModesProcessed(ArrayList<ZonesTouristiquesUriType> modesProcessed) {
        this.modesProcessed = modesProcessed;
    }

    public List<ZoneNode> getZoneNodes() {
        return zoneNodes;
    }

    public void setZoneNodes(List<ZoneNode> zoneNodes) {
        this.zoneNodes = zoneNodes;
    }
}

