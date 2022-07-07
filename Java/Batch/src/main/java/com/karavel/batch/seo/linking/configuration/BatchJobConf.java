package com.karavel.batch.seo.linking.configuration;

import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.core.steps.fetching.FetchingWrapper;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.UrlGenerationWrapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchJobConf {

    private static final String LINKING_NAME = "Batch de linking";

    @Bean
    public Job linkingJob(@Qualifier("fetchingZoneDataStep") Step fetchingZoneDataStep,
                          @Qualifier("urlGenerationStep") Step urlGenerationStep,
                          @Qualifier("linkingStep") Step linkingStep,
                          @Qualifier("mailSendingStep") Step mailSendingStep,
                          JobBuilderFactory jobBuilderFactory) {

        return jobBuilderFactory.get(LINKING_NAME)
                .incrementer(new RunIdIncrementer())
                .start(fetchingZoneDataStep)
                .next(urlGenerationStep)
                .next(linkingStep)
                .next(mailSendingStep)
                .build();
    }

    /******************
     *                *
     *      STEP 1    * - Recuperation des donnees de Zones Touristiques
     *                *
     ******************/

    @Bean
    public Step fetchingZoneDataStep(@Qualifier("ZonesTouristiquesFetchingTasklet") Tasklet tasklet,
                                     StepBuilderFactory stepBuilderFactory) {

        return stepBuilderFactory.get("FetchingZoneDataStep")
                .tasklet(tasklet)
                .build();
    }


    /******************
     *                *
     *      STEP 2    * - Generation des URLs + Ecriture en DB
     *                *
     ******************/


    @Bean
    public Step urlGenerationStep(@Qualifier("UrlGenerationReader") ItemReader<UrlGenerationWrapper> reader,
                                  @Qualifier("UrlGenerationDatabaseWriter") ItemWriter<ZoneNode> writer,
                                  @Qualifier("UrlGenerationProcessor") ItemProcessor<UrlGenerationWrapper, ZoneNode> processor,
                                  StepBuilderFactory stepBuilderFactory) {

        return stepBuilderFactory.get("UrlGenerationStep")
                .<UrlGenerationWrapper, ZoneNode>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


    /******************
     *                *
     *      STEP 3    *  - Generation LINKING + Ecriture DB
     *                *
     ******************/


    @Bean
    public Step linkingStep(@Qualifier("linkingReader") ItemReader<ZoneNode> linkingReader,
                            @Qualifier("LinkingWriter") ItemWriter<ZoneNode> linkingWriter,
                            @Qualifier("linkingProcessor") ItemProcessor<ZoneNode, ZoneNode> linkingProcessor,
                            StepBuilderFactory stepBuilderFactory,
                            TaskExecutor taskExecutor) {

        return stepBuilderFactory.get("LinkingStep")
                .<ZoneNode, ZoneNode>chunk(10)
                .reader(linkingReader)
                .processor(linkingProcessor)
                .writer(linkingWriter)
                .taskExecutor(taskExecutor)
                .throttleLimit(20)
                .build();
    }


    /******************
     *                *
     *      STEP 4    *  - Envoi de mail de résultat de linking
     *                *
     ******************/


    @Bean
    public Step mailSendingStep(@Qualifier("EmailTasklet") Tasklet emailTasklet,
                                StepBuilderFactory stepBuilderFactory) {

        return stepBuilderFactory.get("EmailSendingStep")
                .tasklet(emailTasklet)
                .build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch_async_task");
    }
}
