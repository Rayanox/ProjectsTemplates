package com.karavel.batch.seo.linking.core.steps.fetching;

import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueListes;
import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.catalogue.sejour.out.referentielgeographique.ZoneTouristiqueVO;

import java.util.List;

public class FetchingWrapper {

    private ZoneTouristiqueVO[] zonesTouristiquesTab;
    private ZoneTouristiqueNode rootNode;
    private ZoneTouristiqueListes zoneTouristiqueListes;
    private List<ZoneNode> finalListOfAllRemainingNodes;

    private GenerationType generationType;

    public ZoneTouristiqueVO[] getZonesTouristiquesTab() {
        return zonesTouristiquesTab;
    }

    public void setZonesTouristiquesTab(ZoneTouristiqueVO[] zonesTouristiquesTab) {
        this.zonesTouristiquesTab = zonesTouristiquesTab;
    }

    public ZoneTouristiqueNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(ZoneTouristiqueNode rootNode) {
        this.rootNode = rootNode;
    }

    public ZoneTouristiqueListes getZoneTouristiqueListes() {
        return zoneTouristiqueListes;
    }

    public void setZoneTouristiqueListes(ZoneTouristiqueListes zoneTouristiqueListes) {
        this.zoneTouristiqueListes = zoneTouristiqueListes;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }

    public List<ZoneNode> getFinalListOfAllRemainingNodes() {
        return finalListOfAllRemainingNodes;
    }

    public void setFinalListOfAllRemainingNodes(List<ZoneNode> finalListOfAllRemainingNodes) {
        this.finalListOfAllRemainingNodes = finalListOfAllRemainingNodes;
    }
}
