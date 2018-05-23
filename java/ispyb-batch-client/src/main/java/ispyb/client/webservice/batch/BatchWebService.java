package ispyb.client.webservice.batch;


import generated.ws.common.batch.IspybWS;
import generated.ws.common.batch.SessionWS3VO;
import ispyb.client.webservice.UtilsDoNotCommit;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

/**
 * 
 * batch launched by the cron job
 */
public class BatchWebService {
	public static void main(String[] args) throws Exception {

		// Get the service

		System.out.println("Launching batch job for data confidentiality");

		IspybWS iws= new IspybWS();	
		generated.ws.common.batch.BatchWebService ws = iws.getBatchWebServicePort();

			System.out.println("-----------------------------------------------------------");

			BindingProvider bindingProvider = (BindingProvider)ws;
			Map requestContext = bindingProvider.getRequestContext();
			
			requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);
			
		System.out.println("WS for update is created: now calling getDataProtected WS");

		List<SessionWS3VO> listSessionToBeProtected = ws.findSessionsToBeProtected();
		System.out.println("nbSession to be protected: "
				+ (listSessionToBeProtected == null ? 0 : listSessionToBeProtected.size()));
		
		if (listSessionToBeProtected != null) {
			for (Iterator iterator = listSessionToBeProtected.iterator(); iterator
					.hasNext();) {
				SessionWS3VO s = (SessionWS3VO) iterator.next();
				System.out.println("session to be protected = " + s.getSessionId());
				ws.protectSession(s.getSessionId());
				System.out.println("end of session protection");
			}
		}

		System.out.println("batch successfull");

		System.out.println();

		System.out.println("------");

	}
}