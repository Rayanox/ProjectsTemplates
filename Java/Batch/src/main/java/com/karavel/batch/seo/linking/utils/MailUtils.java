package com.karavel.batch.seo.linking.utils;

import com.karavel.batch.seo.linking.common.beans.Status;
import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.configuration.startup.GlobalConfiguration;
import com.karavel.commons.mail.MailSender;
import com.karavel.commons.mail.SendMessageIn;
import com.karavel.commons.mail.address.MailAddress;
import com.karavel.commons.mail.content.Content;
import com.karavel.commons.mail.content.FreemarkerTemplateContentStrategyImpl;
import com.karavel.commons.mail.content.StaticContentStrategyImpl;
import com.karavel.commons.mail.header.DefaultSmtpHeaderStrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Classe utilitaire d'envoi d'emails
 * @author Walid MELLOULI
 *
 */
@Service
public class MailUtils {
	
	private final static String EMAIL_ADRESSES_SEPARATORS_REGEX = "[;:,]+";

	private GlobalConfiguration globalConfiguration;

	/**
	 * Envoi d'email de notification
	 * @param freemarkerConfigurer
	 * @param mailSender
	 * @param ftlTemplate
	 * @param templateModel
	 * @param destinationMailAdressList
	 */
	public void sendNotificationMail(FreeMarkerConfigurer freemarkerConfigurer, MailSender mailSender, String ftlTemplate, Map<String, Object> templateModel, List<String> destinationMailAdressList, GenerationType generationType, Status status) {
		MailAddress[] destinationMailAdressTab = null;
		if (destinationMailAdressList != null) {
			destinationMailAdressTab = new MailAddress[destinationMailAdressList.size()];
			int cpt = 0;
			for (String mailAdress : destinationMailAdressList) {
				destinationMailAdressTab[cpt] = new MailAddress(mailAdress);
				cpt++;
			}
		}
		
		String subject = MessageFormat.format("[Batch] Info batch Linking {0} pour {1} : {2}", generationType.name(), globalConfiguration.getSiteName(), status.name());
		
		mailSender.sendMessage(
				new SendMessageIn(
						new MailAddress("auto.alerts@karavel.com", "Auto Alert"), 
						destinationMailAdressTab, 
						null, 
						null, 
						new Content(
							new StaticContentStrategyImpl(subject),
							new FreemarkerTemplateContentStrategyImpl(
							ftlTemplate, 
							templateModel, 
							freemarkerConfigurer.getConfiguration()),	
							null),
						null,
						null,
						"FR",
						"UTF8",
						new DefaultSmtpHeaderStrategyImpl("Seo Batch")
					)
				);
	}
	
	/**
	 * Récupérer la liste des adresses email destination
	 * @param emails
	 * @return
	 */
	public List<String> getEmailAdressesList(String emails) {
		List<String> emailAdressesList = new ArrayList<String>();
		String emailsAdresses = emails;
		String[] emailAdressesTab = emailsAdresses.split(EMAIL_ADRESSES_SEPARATORS_REGEX);
		emailAdressesList = Arrays.asList(emailAdressesTab);
		return emailAdressesList;
	}

	@Autowired
	public void setGlobalConfiguration(GlobalConfiguration globalConfiguration) {
		this.globalConfiguration = globalConfiguration;
	}
}
