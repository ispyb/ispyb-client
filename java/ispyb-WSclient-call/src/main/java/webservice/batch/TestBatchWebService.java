package webservice.batch;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import generated.ws.common.update.IspybWS;
import generated.ws.mx.collection.SessionWS3VO;
import webservice.UtilsDoNotCommit;



public class TestBatchWebService {
	
	private static BatchWebService wsPort;

	private static void initWebService() throws Exception {
		// Get the services for ISPyB
		IspybWS service = new IspybWS();	

		wsPort=service.getBatchWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)wsPort;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);


	}

	public static void main(String args[]) {
		try {

			System.out.println("*************** testBatchWebServices ***************");

			initWebService();

			testFindSessionsToProtect();
			// testFindSessionsNotProtectedToProtect();
			// testProtectSession();
			// testProtectNotProtectedSessions();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static String sessionToString(SessionWS3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("sessionId=" + vo.getSessionId() + " " + "beamLineSetupId=" + vo.getBeamLineSetupId() + " "
				+ "proposalId=" + vo.getProposalId() + " " + "projectCode=" + vo.getProjectCode() + " " + "startDate="
				+ vo.getStartDate() + " " + "endDate=" + vo.getEndDate() + " ");
		str.append('}');

		return (str.toString());
	}

	private static void testFindSessionsToProtect() throws Exception {
		System.out.println("*************** testFindSessionsToBeProtected ***************");
		List<SessionWS3VO> listSessionToBeProtected = wsPort.findSessionsToBeProtected();
		System.out.println("nbSession to be protected: "
				+ (listSessionToBeProtected == null ? 0 : listSessionToBeProtected.size()));
		if (listSessionToBeProtected != null) {
			System.out.println("Session[] length = " + listSessionToBeProtected.size() + "\n");
			for (Iterator iterator = listSessionToBeProtected.iterator(); iterator
					.hasNext();) {
				SessionWS3VO s = (SessionWS3VO) iterator.next();
				System.out.println("Session = " + sessionToString((s)) + "\n");
			}
			System.out.println("This is what I got as a response :\n" + listSessionToBeProtected.toString() + listSessionToBeProtected);
		} else
			System.out.println("This is what I got as a response : NULL \n");
	}

	private static void testFindSessionsNotProtectedToProtect() throws Exception {
		System.out.println("*************** testFindSessionsToBeProtected ***************");
		List<SessionWS3VO> vos = wsPort.findSessionsNotProtectedToBeProtected();
		if (vos != null) {
			System.out.println("Session[] length = " + vos.size() + "\n");
			for (Iterator iterator = vos.iterator(); iterator
					.hasNext();) {
				SessionWS3VO s = (SessionWS3VO) iterator.next();
				Date lastUpdate = s.getLastUpdate().getTime();
				System.out.println("Session = " + s.getSessionId() + "  " + s.getBeamlineName()
						+ "  " + lastUpdate + "\n");

			}
			System.out.println("This is what I got as a response :\n" + vos.toString() + vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
	}

	private static void testProtectSession() throws Exception {
		System.out.println("*************** testProtectSession ***************");
		Integer sessionId = 37598;
		wsPort.protectSession(sessionId);
		System.out.println("data protection successfull");
		System.out.println();
		System.out.println("------");
	}

	private static void testProtectNotProtectedSessions() throws Exception {
		System.out.println("*************** testProtectNotProtectedSessions ***************");

		List<SessionWS3VO> vos = wsPort.findSessionsNotProtectedToBeProtected();
		if (vos != null) {
			for (Iterator iterator = vos.iterator(); iterator
					.hasNext();) {
				SessionWS3VO s = (SessionWS3VO) iterator.next();
				wsPort.protectSession(s.getSessionId());
				System.out.println("data protection for session : " + s.getSessionId() + " \n");
			}

		}

		System.out.println();
		System.out.println("------");
	}

}
