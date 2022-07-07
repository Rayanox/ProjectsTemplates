package com.karavel.batch.seo.linking;

import com.karavel.batch.seo.linking.core.JobData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Le batch a été migré vers spring boot (et spring batch en particulier), mais il faudrait faire 2 choses afin que le code sot plus clean.

/** TODO
 *   [OK] 1) Changer le type de la Step 1 de chunk vers Tasklet ?
 *   2) Rajouter la nouvelle marque Pmvc BE
 *   3) Rajouter les confs de PMVC thématiques
 *   4) Coder les ajouts en DB
 *   5) Coder la fin du batch (envoi de mail)
 *   6) Retirer le CommandLineRunner ?
 *   7) Tester toutes les marques (et voir avec Christophe Cure les marques qui ne marchent pas en PROD et fix si besoin)
 *   8) Rajouter l'externalisation des configs dans DARTS et voir avec Christophe pour automatiser toute la chaine
 *   [Optionnel] 9) Paralléliser la step d'url generation
 *   [Optionnel] 10) Rajouter Lombok pour les builders de regles de linking
 */


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class LinkingBatchMain implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(LinkingBatchMain.class, args);
    }


	private JobData jobData;

	public LinkingBatchMain() {
	}

	@Override
	public void run(String... args) throws Exception {
		//TODO rajouter l'envoi de mail + System.exit(0);

		int i = 0;
		i++;
		System.exit(0);
	}

	@Autowired
	public void setJobData(JobData jobData) {
		this.jobData = jobData;
	}
}
