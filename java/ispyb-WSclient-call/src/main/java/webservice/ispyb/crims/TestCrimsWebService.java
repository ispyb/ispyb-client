package webservice.ispyb.crims;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generated.ws.mx.crims.CrimsWebService;
import generated.ws.mx.crims.IspybWS;
import generated.ws.mx.shipping.BlSubSample3VO;
import generated.ws.mx.shipping.Position3VO;
import webservice.UtilsDoNotCommit;

public class TestCrimsWebService {
	
	protected static generated.ws.mx.crims.IspybWS iws;
	protected static generated.ws.mx.crims.CrimsWebService ws;

	public TestCrimsWebService() throws Exception {
		super();
		initWebService();
	}

	private static void initWebService() throws Exception {
		
		IspybWS iws = new IspybWS();	

		System.out.println("-----------------------------------------------------------");

		CrimsWebService ws=iws.getCrimsWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);

		//ws.setTimeout(3 * 1000);// 15

	}

	public static void main(String args[]) {
		try {
			System.out.println("*************** testCrimsWebServices ***************");
			initWebService();
			//testGetDCFromShippingId();
			//testStoreShipping();
			testFindProteinAcronymsForProposal();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static void testStoreShipping() throws Exception {
		System.out.println("*************** testStoreShipping for CRIMS ***************");
		
		String propcode = "mx";
		String propnumber = "415";
		String shippingFull = "{'shippingId':310127,'shippingName':'testSD','deliveryAgentAgentName':null,'deliveryAgentShippingDate':null,'deliveryAgentDeliveryDate':null,'deliveryAgentAgentCode':null,'deliveryAgentFlightCode':null,'shippingStatus':'opened','timeStamp':'May 15, 2018 3:10:32 PM','laboratoryId':null,'isStorageShipping':null,'creationDate':'May 15, 2018 3:10:32 PM','comments':'','sendingLabContactVO':{'labContactId':248308,'personVO':{'personId':396486,'laboratoryVO':{'laboratoryId':311197,'laboratoryUUID':null,'name':'ESRF','address':'71 avenue des Martyrs\nCS 40220\n38043\n','city':'GRENOBLE','country':'ESRF','url':null,'organization':null,'laboratoryExtPk':200450},'siteId':'190200','personUUID':null,'familyName':'GUILLOT','givenName':'Sarah','title':'Mme','emailAddress':'sarah.guillot@esrf.fr','phoneNumber':null,'login':null,'faxNumber':null,'externalId':null},'cardName':'GUILLOT-ESRF-GRENOBLE','defaultCourrierCompany':'-1','courierAccount':'','billingReference':'','dewarAvgCustomsValue':0,'dewarAvgTransportValue':0},'returnLabContactVO':{'labContactId':248308,'personVO':{'personId':396486,'laboratoryVO':{'laboratoryId':311197,'laboratoryUUID':null,'name':'ESRF','address':'71 avenue des Martyrs\nCS 40220\n38043\n','city':'GRENOBLE','country':'ESRF','url':null,'organization':null,'laboratoryExtPk':200450},'siteId':'190200','personUUID':null,'familyName':'GUILLOT','givenName':'Sarah','title':'Mme','emailAddress':'sarah.guillot@esrf.fr','phoneNumber':null,'login':null,'faxNumber':null,'externalId':null},'cardName':'GUILLOT-ESRF-GRENOBLE','defaultCourrierCompany':'-1','courierAccount':'','billingReference':'','dewarAvgCustomsValue':0,'dewarAvgTransportValue':0},'returnCourier':null,'dateOfShippingToUser':null,'shippingType':'DewarTracking','dewarVOs':[{'dewarId':315068,'code':'Dewar1','comments':null,'storageLocation':null,'dewarStatus':'opened','timeStamp':'May 15, 2018 3:10:32 PM','isStorageDewar':null,'barCode':'ESRF0315068','customsValue':0,'transportValue':0,'trackingNumberToSynchrotron':null,'trackingNumberFromSynchrotron':null,'facilityCode':null,'type':'Dewar','isReimbursed':null,'containerVOs':[{'containerId':350117,'code':'puck1','containerType':'Puck','capacity':16,'beamlineLocation':null,'sampleChangerLocation':null,'containerStatus':null,'timeStamp':'May 15, 2018 3:10:46 PM','barcode':null,"
				+ "'sampleVOs':[{'blSampleId':665187,'diffractionPlanVO':{'diffractionPlanId':404348,'experimentKind':'Default','observedResolution':0.0,'minimalResolution':null,'exposureTime':0.0,'oscillationRange':0.0,'maximalResolution':null,'screeningResolution':null,'radiationSensitivity':null,'anomalousScatterer':null,'preferredBeamSizeX':null,'preferredBeamSizeY':null,'preferredBeamDiameter':null,'comments':null,'aimedCompleteness':null,'aimedIOverSigmaAtHighestRes':null,'aimedMultiplicity':null,'aimedResolution':null,'anomalousData':null,'complexity':null,'estimateRadiationDamage':null,'forcedSpaceGroup':null,'requiredCompleteness':null,'requiredMultiplicity':null,'requiredResolution':0.0,'strategyOption':null,'kappaStrategyOption':null,'numberOfPositions':null,'minDimAccrossSpindleAxis':null,'maxDimAccrossSpindleAxis':null,'radiationSensitivityBeta':null,'radiationSensitivityGamma':null,'minOscWidth':null,'axisRange':null},'crystalVO':{'crystalId':371134,'diffractionPlanVO':{'diffractionPlanId':404348,'experimentKind':'Default','observedResolution':0.0,'minimalResolution':null,'exposureTime':0.0,'oscillationRange':0.0,'maximalResolution':null,'screeningResolution':null,'radiationSensitivity':null,'anomalousScatterer':null,'preferredBeamSizeX':null,'preferredBeamSizeY':null,'preferredBeamDiameter':null,'comments':null,'aimedCompleteness':null,'aimedIOverSigmaAtHighestRes':null,'aimedMultiplicity':null,'aimedResolution':null,'anomalousData':null,'complexity':null,'estimateRadiationDamage':null,'forcedSpaceGroup':null,'requiredCompleteness':null,'requiredMultiplicity':null,'requiredResolution':0.0,'strategyOption':null,'kappaStrategyOption':null,'numberOfPositions':null,'minDimAccrossSpindleAxis':null,'maxDimAccrossSpindleAxis':null,'radiationSensitivityBeta':null,'radiationSensitivityGamma':null,'minOscWidth':null,'axisRange':null},'proteinVO':{'proteinId':303627,'name':'Triosephosphate isomerase','acronym':'A-TIM','safetyLevel':null,'molecularMass':null,'proteinType':null,'sequence':null,'personId':null,'timeStamp':'Jul 24, 2007 2:18:34 PM','isCreatedBySampleSheet':0,'externalId':null},'name':'62c6d662-1f44-4c57-bad1-ae0c5cc0ac73','spaceGroup':'C3','morphology':null,'color':null,'sizeX':null,'sizeY':null,'sizeZ':null,'cellA':1.0,'cellB':1.0,'cellC':10.0,'cellAlpha':10.0,'cellBeta':10.0,'cellGamma':10.0,'comments':null,'pdbFileName':null,'pdbFilePath':null},'name':'sp1','code':'','location':'','holderLength':22.0,'loopLength':null,'loopType':null,'wireWidth':null,'comments':'','completionStage':null,'structureStage':null,'publicationStage':null,'publicationComments':null,'blSampleStatus':null,'isInSampleChanger':null,'lastKnownCenteringPosition':null,'smiles':null,'lastImageURL':null,"
				+ "'blsampleImageVOs':[]"
				+ "}]}],"
				+ "'sessionVO':{'sessionId':61843,'expSessionPk':85657,'proposalVO':{'proposalId':1170,'title':'TEST','code':'MX','number':'415','type':'MX','externalId':null,'timeStamp':'Jan 1, 2001 12:00:00 AM','participants':null},'beamLineSetupVO':{'beamLineSetupId':1532357,'synchrotronMode':null,'undulatorType1':null,'undulatorType2':null,'undulatorType3':null,'focalSpotSizeAtSample':null,'focusingOptic':null,'beamDivergenceHorizontal':null,'beamDivergenceVertical':null,'polarisation':null,'monochromatorType':null,'setupDate':null,'synchrotronName':null,'maxExpTimePerDataCollection':null,'minExposureTimePerImage':null,'goniostatMaxOscillationSpeed':null,'goniostatMinOscillationWidth':null,'minTransmission':null,'CS':null},'projectCode':null,'startDate':'Jul 4, 2018 9:30:00 AM','endDate':'Jul 5, 2018 1:00:00 AM','beamlineName':'ID30A-1','scheduled':1,'nbShifts':2,'comments':null,'beamlineOperator':'MALBET-MONACO  S','usedFlag':null,'sessionTitle':null,'structureDeterminations':null,'dewarTransport':null,'databackupFrance':null,'databackupEurope':null,'visit_number':null,'operatorSiteNumber':'17074','timeStamp':'Mar 19, 2018 1:24:24 PM','lastUpdate':'Jul 5, 2018 1:00:00 AM','protectedData':null,'externalId':null,'nbReimbDewars':1}}],'sessions':[{'sessionId':61843,'expSessionPk':85657,'proposalVO':{'proposalId':1170,'title':'TEST','code':'MX','number':'415','type':'MX','externalId':null,'timeStamp':'Jan 1, 2001 12:00:00 AM','participants':null},'beamLineSetupVO':{'beamLineSetupId':1532357,'synchrotronMode':null,'undulatorType1':null,'undulatorType2':null,'undulatorType3':null,'focalSpotSizeAtSample':null,'focusingOptic':null,'beamDivergenceHorizontal':null,'beamDivergenceVertical':null,'polarisation':null,'monochromatorType':null,'setupDate':null,'synchrotronName':null,'maxExpTimePerDataCollection':null,'minExposureTimePerImage':null,'goniostatMaxOscillationSpeed':null,'goniostatMinOscillationWidth':null,'minTransmission':null,'CS':null},'projectCode':null,'startDate':'Jul 4, 2018 9:30:00 AM','endDate':'Jul 5, 2018 1:00:00 AM','beamlineName':'ID30A-1','scheduled':1,'nbShifts':2,'comments':null,'beamlineOperator':'MALBET-MONACO  S','usedFlag':null,'sessionTitle':null,'structureDeterminations':null,'dewarTransport':null,'databackupFrance':null,'databackupEurope':null,'visit_number':null,'operatorSiteNumber':'17074','timeStamp':'Mar 19, 2018 1:24:24 PM','lastUpdate':'Jul 5, 2018 1:00:00 AM','protectedData':null,'externalId':null,'nbReimbDewars':1}]}" ;
			
		Integer blSubSampleId = null;
		String blSubSampleUUID = "blSubSampleUUID";
		String imgFileName = "imgFileName";
		String imgFilePath = "imgFilePath";

		BlSubSample3VO blSubSample = new BlSubSample3VO();
		blSubSample.setBlSubSampleId(blSubSampleId);
		blSubSample.setBlSubSampleUUID(blSubSampleUUID);
		blSubSample.setImgFileName(imgFileName);
		blSubSample.setImgFilePath(imgFilePath);

		Integer positionId = null;
		Integer relativePositionId = null;
		Double posX = 0.1;
		Double posY = 0.2;
		Double posZ = 0.3;
		Double scale = 0.4;

		Position3VO value = new Position3VO();
		value.setPosX(posX);
		value.setPosY(posY);
		value.setPosZ(posZ);
		value.setScale(scale);
		
		
		blSubSample.setPositionVO(value);
		
		
		String ret = ws.storeShippingFull(propcode, propnumber, shippingFull);//storeShipping(shipping);
		System.out.println("This is what I got as a response : shippingFull = " + ret + "  \n");

	}
	
	private static void testGetDCFromShippingId() throws Exception {
		System.out.println("*************** testGetDCFromShippingId for CRIMS ***************");
		
		String shippingId= "310127";		
		
		String ret = ws.getDataCollectionFromShippingId(shippingId);//storeShipping(shipping);
		System.out.println("This is what I got as a response : datacollections = " + ret + "  \n");

	}
	
	private static void testFindProteinAcronymsForProposal() throws Exception {
		System.out.println("*************** testFindProteinAcronymsForProposal for CRIMS ***************");
		
		String ret = ws.findProteinAcronymsForProposal("mx", "415");
		System.out.println("This is what I got as a response : proteins = " + ret + "  \n");
	}
	
	private static Gson getGson(){
		return new GsonBuilder().serializeNulls().create();
	}


}
