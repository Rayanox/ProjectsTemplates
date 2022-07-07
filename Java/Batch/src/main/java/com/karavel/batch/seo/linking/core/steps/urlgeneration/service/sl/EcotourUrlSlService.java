package com.karavel.batch.seo.linking.core.steps.urlgeneration.service.sl;

import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.configuration.annotations.UrlSejlistService;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.service.AbstractUrlGenerationService;
import com.karavel.batch.seo.linking.utils.ZoneTouristiqueTreeUtils;
import com.karavel.catalogue.sejour.exception.ArgumentsInvalidesException;
import com.karavel.catalogue.sejour.in.CriteresGeographiqueRechercheVO;
import com.karavel.catalogue.sejour.in.CriteresRechercheSejourVOIn;
import com.karavel.catalogue.sejour.in.origine.OrigineAppelEnum;
import com.karavel.catalogue.sejour.jax_ws.IRechercheSejourServiceJaxWsFacade;
import com.karavel.catalogue.sejour.out.ObtenirUrlRechercheVOOut;
import org.springframework.beans.factory.annotation.Autowired;

@UrlSejlistService(applicableSites = {SitesEnum.ECOTOUR})
public class EcotourUrlSlService extends AbstractUrlGenerationService<CriteresRechercheSejourVOIn, ObtenirUrlRechercheVOOut> {

    private IRechercheSejourServiceJaxWsFacade rechercheSejourService;
    private ZoneTouristiqueTreeUtils zoneTouristiqueTreeUtils;

    public EcotourUrlSlService() {
        super(EcotourUrlSlService.class);
    }

    @Override
    protected ObtenirUrlRechercheVOOut serviceCall(CriteresRechercheSejourVOIn in) throws ArgumentsInvalidesException {
        return rechercheSejourService.obtenirUrlRecherche(in);
    }

    @Override
    protected String mapUrlFromResponseData(ObtenirUrlRechercheVOOut responseOut) {
        return responseOut.getUrl();
    }

    @Override
    protected CriteresRechercheSejourVOIn createInputService(ZoneNode node) {
        CriteresRechercheSejourVOIn in = new CriteresRechercheSejourVOIn(globalConfiguration.getLvSite(), globalConfiguration.getLangueId(), null);
        in.setEspaceThematique(node.getTheme().getThemespace());
        in.setGenerateUrl(true);
        in.setOrigineAppel(OrigineAppelEnum.DEFAULT);
        in.setCriteresGeographiques(getCritereGeographique(node.getZone()));
        return in;
    }

    private CriteresGeographiqueRechercheVO getCritereGeographique(ZoneTouristiqueNode node) {
        CriteresGeographiqueRechercheVO criteres = new CriteresGeographiqueRechercheVO();

        if(ZoneTouristiqueTreeUtils.LEVEL_VL == node.getNiveau().intValue())
            criteres.setVillesDestinationIds(new Long [] { node.getId() });
        else
            criteres.setZonesTouristiquesDestinationsIds(new Long [] { node.getId() });
        return criteres;
    }


    @Autowired
    public void setRechercheSejourService(IRechercheSejourServiceJaxWsFacade rechercheSejourService) {
        this.rechercheSejourService = rechercheSejourService;
    }

    @Autowired
    public void setZoneTouristiqueTreeUtils(ZoneTouristiqueTreeUtils zoneTouristiqueTreeUtils) {
        this.zoneTouristiqueTreeUtils = zoneTouristiqueTreeUtils;
    }
}
