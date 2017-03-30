package webservice.ispyb.batchUpdate;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import generated.ws.common.update.IspybWS;
import generated.ws.common.update.UpdateFromSMISWebService;
import webservice.UtilsDoNotCommit;

public class TestUpdateFromSMISWebService {

	protected static UpdateFromSMISWebService ws;
	
	private static void initWebService() throws Exception {
		// Get the services for ISPyB
		IspybWS service = new IspybWS();	

		ws=service.getUpdateFromSMISWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);

		// To be sure it has enough time to achieve (service launched once a day at 4:12 am)
		// ws.setTimeout(120*60*1000);

	}

	public static void main(String args[]) {
		try {

			System.out.println("*************** testUpdateFromSMISWebServices ***************");
			initWebService();

			testUpdateFromSMIS();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static void testUpdateFromSMIS() throws Exception {
		System.out.println("*************** testUpdateFromSMIS ***************");
		ws.updateFromSMIS();
		System.out.println("update successfull");
		System.out.println();
		System.out.println("------");
	}

}
