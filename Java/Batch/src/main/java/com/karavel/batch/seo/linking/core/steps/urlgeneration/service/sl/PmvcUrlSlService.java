package com.karavel.batch.seo.linking.core.steps.urlgeneration.service.sl;

import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.configuration.annotations.UrlSejlistService;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.service.AbstractUrlGenerationService;
import com.karavel.front.url.watchdog.WatchDogException;
import com.karavel.url.referentiel.generator.pmvc.GenerateSLUrlIn;
import com.karavel.url.referentiel.generator.pmvc.GenerateUrlOut;
import com.karavel.url.referentiel.generator.pmvc.IUrlGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@UrlSejlistService(applicableSites = {SitesEnum.PROMOVACANCES, SitesEnum.PROMOVACANCES_BELGIQUE})
public class PmvcUrlSlService extends AbstractUrlGenerationService<GenerateSLUrlIn, GenerateUrlOut> {

    private IUrlGenerator urlGenerator;

    public PmvcUrlSlService() {
        super(PmvcUrlSlService.class);
    }

    @Override
    protected GenerateUrlOut serviceCall(GenerateSLUrlIn in) throws WatchDogException {
        return urlGenerator.generateUrlSL(in);
    }

    @Override
    protected String mapUrlFromResponseData(GenerateUrlOut responseOut) {
        return responseOut.getUri();
    }

    @Override
    protected GenerateSLUrlIn createInputService(ZoneNode node) {
        GenerateSLUrlIn in = new GenerateSLUrlIn(node.getZone().getId(), node.getZone().getNiveau());
        in.setLvSite(globalConfiguration.getLvSite());
        in.setThematiqueId(node.getTheme().getThemespace());
        return in;
    }


    @Autowired
    public void setUrlGenerator(IUrlGenerator urlGenerator) {
        this.urlGenerator = urlGenerator;
    }
}
