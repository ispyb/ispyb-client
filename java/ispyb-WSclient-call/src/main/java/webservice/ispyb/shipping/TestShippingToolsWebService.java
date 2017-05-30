package webservice.ispyb.shipping;

import generated.ws.mx.shipping.IspybWS;
import generated.ws.mx.shipping.Laboratory3VO;
import generated.ws.mx.shipping.PersonWS3VO;
import generated.ws.mx.shipping.ProposalWS3VO;

import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import webservice.UtilsDoNotCommit;
import webservice.ispyb.TestWebServiceUtils;

public class TestShippingToolsWebService {

	protected static generated.ws.mx.shipping.IspybWS iws;
	protected static generated.ws.mx.shipping.ToolsForShippingWebService ws;

	public TestShippingToolsWebService() throws Exception {
		super();
		initWebService();
	}

	private static void initWebService() throws Exception {
		
		iws = new IspybWS();	

		System.out.println("-----------------------------------------------------------");

		ws=iws.getToolsForShippingWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);

		//ws.setTimeout(3 * 1000);// 15

	}

	public static void main(String args[]) {
		try {
			System.out.println("*************** testShippingWebServices ***************");
			initWebService();
			
			 testFindProposal();

			 testFindPersonBySessionId();
			 testFindPersonBySessionIdLocalContact();
			 testFindProposalByCodeAndNumber();
			 testFindPersonByCodeAndNumber();
			 testFindLaboratoryByCodeAndNumber();
			 testFindPersonByProposal();
			 testFindLaboratoryByProposal();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	public static String personToString(PersonWS3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("personId=" + vo.getPersonId() + " " + "laboratoryId=" + vo.getLaboratoryId() + " " + "familyName="
				+ vo.getFamilyName() + " " + "givenName=" + vo.getGivenName() + " " + "login=" + vo.getLogin() + " ");
		str.append('}');

		return (str.toString());
	}

	public static String proposalToString(ProposalWS3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("proposalId=" + vo.getProposalId() + " " + "personId=" + vo.getPersonId() + " " + "code=" + vo.getCode() + " "
				+ "number=" + vo.getNumber() + " ");
		str.append('}');

		return (str.toString());
	}

	public static String laboratoryToString(Laboratory3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("laboratoryId=" + vo.getLaboratoryId() + " " + "name=" + vo.getName() + " " + "city=" + vo.getCity() + " ");
		str.append('}');

		return (str.toString());
	}

	private static void testFindPersonBySessionId() throws Exception {
		System.out.println("*************** testFindPersonBySessionId ***************");
		PersonWS3VO pv = null;
		Integer sessionId = 31362;

		pv = ws.findPersonBySessionId(sessionId);
		System.out.println("This is what I got as a response : personValue = " + personToString(pv) + "  \n");
	}

	private static void testFindPersonBySessionIdLocalContact() throws Exception {
		System.out.println("*************** testFindPersonBySessionIdLocalContact ***************");
		PersonWS3VO pv = null;
		Integer sessionId = 32833;

		pv = ws.findPersonBySessionIdLocalContact(sessionId);
		System.out.println("This is what I got as a response : personValue = " + personToString(pv) + "  \n");
	}

	public static void testFindProposalByCodeAndNumber() throws Exception {
		System.out.println("*************** testFindProposalByCodeAndNumber ***************");
		ProposalWS3VO pv = null;
		String code = "FX";
		Integer number = 1;

		pv = ws.findProposalByCodeAndNumber(code, number);
		System.out.println("This is what I got as a response : proposalValue = " + proposalToString(pv) + "  \n");
	}

	private static void testFindPersonByCodeAndNumber() throws Exception {
		System.out.println("*************** testFindPersonByCodeAndNumber ***************");
		PersonWS3VO pv = null;
		String code = "fx";
		Integer number = 1;

		pv = ws.findPersonByCodeAndNumber(code, number);
		System.out.println("This is what I got as a response : personValue = " + personToString(pv) + "  \n");
	}

	private static void testFindLaboratoryByCodeAndNumber() throws Exception {
		System.out.println("*************** testFindLaboratoryByCodeAndNumber ***************");
		Laboratory3VO lv = null;
		String code = "fx";
		Integer number = 1;

		lv = ws.findLaboratoryByCodeAndNumber(code, number);
		System.out.println("This is what I got as a response : laboratoryValue = " + laboratoryToString(lv) + "  \n");
	}

	public static void testFindProposal() throws Exception {
		System.out.println("*************** testFindProposal ***************");
		ProposalWS3VO pv = null;
		String code = "opid";
		String number = "30B";

		pv = ws.findProposal(code, number);
		System.out.println("This is what I got as a response : proposalValue = " + proposalToString(pv) + "  \n");

		code = "mx";
		number = "415";

		pv = ws.findProposal(code, number);
		System.out.println("This is what I got as a response : proposalValue = " + proposalToString(pv) + "  \n");

	}

	private static void testFindPersonByProposal() throws Exception {
		System.out.println("*************** testFindPersonByProposal ***************");
		PersonWS3VO pv = null;
		String code = "OPID";
		String number = "30b";

		pv = ws.findPersonByProposal(code, number);
		System.out.println("This is what I got as a response : personValue = " + personToString(pv) + "  \n");
	}

	private static void testFindLaboratoryByProposal() throws Exception {
		System.out.println("*************** testFindLaboratoryByProposal ***************");
		Laboratory3VO lv = null;
		String code = "ifx";
		String number = "1";
		lv = ws.findLaboratoryByProposal(code, number);
		System.out.println("This is what I got as a response : laboratoryValue = " + laboratoryToString(lv) + "  \n");
		
		code = "mx";
		number = "415";
		lv = ws.findLaboratoryByProposal(code, number);
		System.out.println("This is what I got as a response : laboratoryValue = " + laboratoryToString(lv) + "  \n");
	}

	public PersonWS3VO findPersonByCodeAndNumber(String code, String number) throws Exception {
		System.out.println("*************** findPersonByCodeAndNumber ***************");
		PersonWS3VO pv = null;

		pv = ws.findPersonByProposal(code, number);
		return pv;
	}

	public Laboratory3VO findLaboratoryByCodeAndNumber(String code, String number) throws Exception {
		System.out.println("*************** findLaboratoryByCodeAndNumber ***************");
		Laboratory3VO lv = null;

		lv = ws.findLaboratoryByProposal(code, number);
		return lv;
	}

}
