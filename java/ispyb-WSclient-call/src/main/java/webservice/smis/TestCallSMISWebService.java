package webservice.smis;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.google.gson.Gson;

import generated.ws.smis.ExpSessionInfoLightVO;
import generated.ws.smis.ProposalParticipantInfoLightVO;
import generated.ws.smis.SMISWebService;
import generated.ws.smis.SMISWebService_Service;
import webservice.UtilsDoNotCommit;

public class TestCallSMISWebService {

	protected static SMISWebService ws;
	protected static SMISWebService_Service service;
	
	public TestCallSMISWebService() throws Exception {
		super();
		initWebService();
	}

	
	private static void initWebService() throws Exception {
		// Get the services for ISPyB
		service = new SMISWebService_Service();	
		ws=service.getSMISWebServiceBeanPort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.SMISU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.SMISP);
		// To be sure it has enough time to achieve (service launched once a day at 4:12 am)
		// ws.setTimeout(120*60*1000);

	}

	public static void main(String args[]) {
		try {

			System.out.println("*************** testCallSMISWebServices ***************");
			initWebService();
			//testCallSMIS();
			//testFindMainProposersForProposal();
			//testFindSessionsByBeamlineAndDates();
			testFindModifiedProposals();
			//testFindScientistsForProposal();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static void testCallSMIS() throws Exception {
		System.out.println("*************** testfindRecentSessionsInfoLightForProposalPk ***************");
		//Long proposalPk =new Long(1170); //MX415
		Long proposalPk =new Long(57256); //MX1752
		List<ExpSessionInfoLightVO>  vos = ws.findRecentSessionsInfoLightForProposalPk(proposalPk);

		if (vos != null) {
			System.out.println("Sessions length = " + vos.size() + "\n");
			int i=0;
			for (Iterator iterator = vos.iterator(); iterator.hasNext();) {
				ExpSessionInfoLightVO sesVO = (ExpSessionInfoLightVO) iterator.next();
				System.out.println("Session[" + i + "] = " + sesVO.getBeamlineName() + sesVO.toString() + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.toString() + vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		
		
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindMainProposersForProposal() throws Exception {
		System.out.println("*************** testfindMainProposersForProposalForProposalPk ***************");
		//Long pk =new Long(1170); //MX415
		Long pk =new Long(31529);//mx415
		//Long pk =new Long(57256); //MX1752
		List<ProposalParticipantInfoLightVO> vos = ws.findMainProposersForProposal(pk);

		if (vos != null) {
			System.out.println("Proposal participants length = " + vos.size() + "\n");
			int i=0;
			for (Iterator<ProposalParticipantInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
				ProposalParticipantInfoLightVO sesVO = (ProposalParticipantInfoLightVO) iterator.next();
				System.out.println("ProposalParticipantInfoLightVO[" + i + "] = " + sesVO.getScientistName() + "   " + sesVO.getScientistFirstName()+  "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.toString() + vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		
		
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindModifiedProposals() throws Exception {
		System.out.println("*************** testfindSessionsByBeamlineAndDates ***************");
		String date1="20/11/2017";
		String date2="22/01/2018";
		
		List<Long>  pks = ws.findNewMXProposalPKs(date1, date2);
		if (pks != null) {
			System.out.println("nb of proposals = " + pks.size() + "\n");
			int i=0;
			for (Iterator<Long> iterator = pks.iterator(); iterator.hasNext();) {
				Long pk = (Long) iterator.next();
				System.out.println("proposalPk[" + i + "] = " + pk.toString() + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + pks.toString() );
		} else
			System.out.println("This is what I got as a response : NULL \n");
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindSessionsByBeamlineAndDates() throws Exception {
		System.out.println("*************** testfindSessionsByBeamlineAndDates ***************");
		//Long proposalPk =new Long(1170); //MX415
		Long proposalPk =new Long(57256); //MX1752
		String bl = "BM30A" ;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		Calendar cal2 = cal1;
		cal1.roll(Calendar.YEAR, -1);
		
		List<ExpSessionInfoLightVO>  vos = ws.findSessionsByBeamlineAndDates(bl , cal1, cal2);
		if (vos != null) {
			System.out.println("Sessions length = " + vos.size() + "\n");
			int i=0;
			for (Iterator<ExpSessionInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
				ExpSessionInfoLightVO sesVO = (ExpSessionInfoLightVO) iterator.next();
				System.out.println("Session[" + i + "] = " + sesVO.toString() + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.toString() + vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindScientistsForProposal() throws Exception {
		System.out.println("*************** testfindScientistsForProposal ***************");
		Long proposalPk =new Long(1170); //MX415
		String name="DELAGENIERE";
		String firstName = "Solange";
		int maxResults = 2;
		
		//Long pk =new Long(57256); //MX1752
	
		List<ProposalParticipantInfoLightVO> vos = ws.findScientistsByNameAndFirstName(name, firstName, maxResults);
		int i=0;
		for (Iterator<ProposalParticipantInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
			ProposalParticipantInfoLightVO vo = (ProposalParticipantInfoLightVO) iterator.next();
			System.out.println("Scientist[" + i + "] = " + vo.getScientistName() + vo.getScientistFirstName() + vo.getLabName() + "\n");
			i=i+1;
		}
		
		 System.out.println("Json scientists: ");
		 System.out.println(new Gson().toJson(vos));

		System.out.println();
		System.out.println("------");
		
		 vos = ws.findScientistsForProposalByNameAndFirstName(proposalPk, name, firstName);
		 
		i=0;
		for (Iterator<ProposalParticipantInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
				ProposalParticipantInfoLightVO vo = (ProposalParticipantInfoLightVO) iterator.next();
				System.out.println("Scientist[" + i + "] = " + vo.getScientistName() + vo.getScientistFirstName() + vo.getLabName() + "\n");
				i=i+1;
		}
		 
		 System.out.println("Json scientists: ");
		 System.out.println(new Gson().toJson(vos));

		System.out.println();
		System.out.println("------");
	}


}