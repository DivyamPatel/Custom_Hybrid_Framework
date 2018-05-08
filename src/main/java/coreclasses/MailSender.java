package coreclasses;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import enums.ConfigurationFile;
import enums.ConfigurationProperties;
import managers.FileReaderManager;
import runners.TestRunner1;

public class MailSender {

	TestRunner1 testRunner1 = new TestRunner1();
	private String mailContent;
	public int passed,failed,skipped,total;

	public void sendMail() 
	{
		total = passed + failed + skipped;
		generateMailContent(passed, failed, skipped, total);
		send(mailContent);
	}

	@SuppressWarnings("deprecation")
	private void generateMailContent(int passed, int failed, int skipped, int total) {
		String dateFormat = new SimpleDateFormat("dd-MMM-yyyy h:mm a, z").format(new Date());
		try
		{
			File reportTemplate = new File(System.getProperty("user.dir") +"/"+ FileReaderManager.getInstance().getConfigReader(ConfigurationFile.DEFAULT).getConfProperty(ConfigurationProperties.REPORT_TEMPLATE));
			mailContent = FileUtils.readFileToString(reportTemplate);
			mailContent = mailContent.replace("${ReportHeader}", "Test Execution Report on "+dateFormat);
			mailContent = mailContent.replace("${TotalFeaturesExecuted}", String.valueOf(total));
			mailContent = mailContent.replace("${PassedFeatures}", String.valueOf(passed));
			mailContent = mailContent.replace("${FailedFeatures}", String.valueOf(failed));
			mailContent = mailContent.replace("${SkippedFeatures}", String.valueOf(skipped));
		} catch (Exception e) {
			System.out.println("Exception raised while creating mail content: "+e.getMessage());
		}
	}

	private void send(String mailcontent) {

		try { 
			System.out.println("=====Sending Email=====");
			HtmlEmail email = new HtmlEmail(); 
			email.setHostName("192.168.0.6"); 
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("makemeTek@gmail.com", "123456")); 
			email.setSSLOnConnect(false); 
			email.addTo(FileReaderManager.getInstance().getConfigReader(ConfigurationFile.EMAIL).getConfProperty(ConfigurationProperties.TO));
			email.setFrom(FileReaderManager.getInstance().getConfigReader(ConfigurationFile.EMAIL).getConfProperty(ConfigurationProperties.FROM)); 
			email.addCc(FileReaderManager.getInstance().getConfigReader(ConfigurationFile.EMAIL).getConfProperty(ConfigurationProperties.CC));
			email.setSubject(FileReaderManager.getInstance().getConfigReader(ConfigurationFile.EMAIL).getConfProperty(ConfigurationProperties.SUBJECT)); 
			email.attach(new File(testRunner1.getReportPath() + "/report.html"));
			email.setHtmlMsg(mailContent);
			email.send(); 
			System.out.println("=====Email Sent=====");

		} catch(Exception e) { 
			System.out.println("Exception raised while sending mail: "+e.getMessage()); 
		}
	}

}