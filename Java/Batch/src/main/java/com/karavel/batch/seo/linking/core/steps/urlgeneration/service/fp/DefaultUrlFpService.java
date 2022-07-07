package com.karavel.batch.seo.linking.core.steps.urlgeneration.service.fp;

import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.service.AbstractUrlGenerationService;
import com.karavel.url.referentiel.precalcul.service.UrlReferentielPrecalculGenerationService;
import com.karavel.url.referentiel.precalcul.service.bean.GetUrlFPIn;
import com.karavel.url.referentiel.precalcul.service.bean.GetUrlFPOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUrlFpService extends AbstractUrlGenerationService<GetUrlFPIn, GetUrlFPOut> {

    private UrlReferentielPrecalculGenerationService urlReferentielPrecalculGenerationService;

    public DefaultUrlFpService() {
        super(DefaultUrlFpService.class);
    }


    @Override
    protected GetUrlFPOut serviceCall(GetUrlFPIn in) {
        return urlReferentielPrecalculGenerationService.getUrlFP(in);
    }

    @Override
    protected String mapUrlFromResponseData(GetUrlFPOut responseOut) {
        return responseOut.getPublicUrl();
    }

    @Override
    protected GetUrlFPIn createInputService(ZoneNode node) {
        GetUrlFPIn in = new GetUrlFPIn();
        in.setSiteId(globalConfiguration.getLvSite());
        in.setLogementId(node.getZone().getId());
        return in;
    }


    @Autowired
    public void setUrlReferentielPrecalculGenerationService(UrlReferentielPrecalculGenerationService urlReferentielPrecalculGenerationService) {
        this.urlReferentielPrecalculGenerationService = urlReferentielPrecalculGenerationService;
    }
}
