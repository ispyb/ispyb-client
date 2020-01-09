package webservice.batch;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import generated.ws.common.batch.IspybWS;
import generated.ws.common.batch.SessionWS3VO;
import webservice.UtilsDoNotCommit;



public class TestBatchWebService {
	
	private static generated.ws.common.batch.BatchWebService wsPort;

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

			//testFindSessionsToProtect();
			testFindSessionsNotProtectedToProtect();
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

				//String proposalAccount = s.getProposalVO().getCode() + s.getProposalVO().getNumber();
				// beamline
				ispyb.common.util.beamlines.ESRFBeamlineEnum abl = ESRFBeamlineEnum.retrieveBeamlineWithName(s.getBeamlineName());
				String beamline = abl == null ? "" : abl.getDirectoryName();
				String proposalAccount = s.getProposalName();
				// directory
				Date folderDate = s.getStartDate().getTime();
				SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
				String directory = "";
				if (folderDate != null)
					directory = dt.format(folderDate);

				System.out.println("Session = " + s.getSessionId() + "proposal: " + proposalAccount
						+ "  bl: " + beamline + "  dir: " +  directory + "\n");

				
				//System.out.println("Session = " + sessionToString((s)) + "\n");
			}
			System.out.println("This is what I got as a response :\n" + listSessionToBeProtected.toString() + listSessionToBeProtected);
		} else
			System.out.println("This is what I got as a response : NULL \n");
	}

	private static void testFindSessionsNotProtectedToProtect() throws Exception {
		System.out.println("*************** testFindSessionsToBeProtected ***************");
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		String date1Str = "20180101";
		String date2Str = "20180228";
		
		Date date1 = dt.parse(date1Str);
		Date date2 = dt.parse(date2Str);
		
		//int nbdays= 10;
		//Date date1 = new Date (date2.getTime() - 1000*60*60*24*nbdays);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		List<SessionWS3VO> vos = wsPort.findSessionsNotProtectedToBeProtectedByDates(cal1,cal2);
		if (vos != null) {
			System.out.println("Session[] length = " + vos.size() + "\n");
			for (Iterator iterator = vos.iterator(); iterator
					.hasNext();) {
				SessionWS3VO s = (SessionWS3VO) iterator.next();
				Date lastUpdate = s.getLastUpdate().getTime();
							
				String proposalAccount = s.getProposalName();
				// beamline
				ESRFBeamlineEnum abl = ESRFBeamlineEnum.retrieveBeamlineWithName(s.getBeamlineName());
				String beamline = abl == null ? "" : abl.getDirectoryName();
				// directory
				Date folderDate = s.getStartDate().getTime();
				String directory = "";
				if (folderDate != null)
					directory = dt.format(folderDate);

				System.out.println("Session = " + s.getSessionId() + "  proposal: " + proposalAccount
				+ "  bl: " + beamline + "  dir: " +  directory + "  lastUpdate: "+ lastUpdate + "\n");

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
