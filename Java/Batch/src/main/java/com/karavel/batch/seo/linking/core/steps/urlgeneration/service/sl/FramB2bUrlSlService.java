package com.karavel.batch.seo.linking.core.steps.urlgeneration.service.sl;

import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.configuration.annotations.UrlSejlistService;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.service.AbstractUrlGenerationService;
import com.karavel.front.url.watchdog.WatchDogException;
import com.karavel.url.referentiel.generator.fram.IUrlGeneratorFram;
import com.karavel.url.referentiel.generator.pmvc.GenerateSLUrlIn;
import com.karavel.url.referentiel.generator.pmvc.GenerateUrlOut;
import org.springframework.beans.factory.annotation.Autowired;

@UrlSejlistService(applicableSites = {SitesEnum.FRAM})
public class FramB2bUrlSlService extends AbstractUrlGenerationService<GenerateSLUrlIn, GenerateUrlOut> {

    private IUrlGeneratorFram urlGeneratorFram;

    public FramB2bUrlSlService() {
        super(FramB2bUrlSlService.class);
    }


    @Override
    protected GenerateUrlOut serviceCall(GenerateSLUrlIn in) throws WatchDogException {
        return urlGeneratorFram.generateUrlSL(in);
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
    public void setUrlGeneratorFram(IUrlGeneratorFram urlGeneratorFram) {
        this.urlGeneratorFram = urlGeneratorFram;
    }
}
