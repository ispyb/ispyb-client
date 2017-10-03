package webservice.ispyb.sample;

import generated.ws.mx.blsample.BlSampleWS3VO;
import generated.ws.mx.blsample.BlSubSampleWS3VO;
import generated.ws.mx.blsample.Container3VO;
import generated.ws.mx.blsample.Crystal3VO;
import generated.ws.mx.blsample.DiffractionPlanWS3VO;
import generated.ws.mx.blsample.IspybWS;
import generated.ws.mx.blsample.Protein3VO;
import generated.ws.mx.blsample.SampleInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.xml.ws.BindingProvider;

import webservice.UtilsDoNotCommit;

public class TestBLSampleToolsWebService {

	protected static generated.ws.mx.blsample.IspybWS iws;
	protected static generated.ws.mx.blsample.ToolsForBLSampleWebService ws;

	public TestBLSampleToolsWebService() throws Exception {
		super();
		initWebService();
	}

	private static void initWebService() throws Exception {
		iws = new IspybWS();	

		System.out.println("-----------------------------------------------------------");

		ws=iws.getToolsForBLSampleWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);
	}

	public static void main(String args[]) {
		try {
			System.out.println("*************** testBLSampleWebServices ***************");
			initWebService();

			 testStoreOrUpdateDiffractionPlan();
			 testStoreOrUpdateDiffractionPlanNew();
			 testFindBLSample();
			 testStoreOrUpdateBLSample();
			 testFindSampleInfoLightForProposal();
			 testFindSampleInfoLightForProposalCodeAndNumber();
			 testGetPdbFilePath();
			 testFindProteinAcronymsForProposal();
			 testStoreOrUpdateBLSubSample();
			 testGetSampleInfo();
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static void testStoreOrUpdateDiffractionPlan() throws Exception {
		System.out.println("*************** testStoreOrUpdateDiffractionPlan ***************");
		Integer ret = -1;

		Integer diffractionPlanId = null;
		String experimentKind = "Default";
		Double observedResolution = 0.01;
		Double minimalResolution = 0.02;
		Double exposureTime = 0.03;
		Double oscillationRange = 0.04;
		Double maximalResolution = 0.05;
		Double screeningResolution = 0.06;
		Double radiationSensitivity = 0.07;
		String anomalousScatterer = "anomalousScatterer";
		Double preferredBeamSizeX = 0.001;
		Double preferredBeamSizeY = 0.002;
		Double preferredBeamDiameter = 0.003;
		String comments = "someComments ws";
		Double aimedCompleteness = 0.003;
		Double aimedIOverSigmaAtHighestRes = 0.004;
		Double aimedMultiplicity = 0.005;
		Double aimedResolution = 0.006;
		Boolean anomalousData = false;
		String complexity = "complexity";
		Boolean estimateRadiationDamage = true;
		String forcedSpaceGroup = "forcedSpaceGroup";
		Double requiredCompleteness = 0.007;
		Double requiredMultiplicity = 0.008;
		Double requiredResolution = 0.009;
		String strategyOption = "strategyoption";
		String kappaStrategyOption = "kappaStrategyOption";
		Integer numberOfPositions = 3;
		
		Double minDimAccrossSpindleAxis = 0.0001;
		Double maxDimAccrossSpindleAxis = 0.0002;
		Double radiationSensitivityBeta = 0.0003;
		Double radiationSensitivityGamma = 0.0004;
		Double minOscWidth = 0.5;
	
		ret = ws.storeOrUpdateDiffractionPlan(diffractionPlanId, experimentKind, observedResolution, minimalResolution,
				exposureTime, oscillationRange, maximalResolution, screeningResolution, radiationSensitivity, anomalousScatterer,
				preferredBeamSizeX, preferredBeamSizeY, preferredBeamDiameter,comments, aimedCompleteness, aimedIOverSigmaAtHighestRes, aimedMultiplicity,
				aimedResolution, anomalousData, complexity, estimateRadiationDamage, forcedSpaceGroup, requiredCompleteness,
				requiredMultiplicity, requiredResolution, strategyOption, kappaStrategyOption, numberOfPositions, minDimAccrossSpindleAxis, 
				maxDimAccrossSpindleAxis, radiationSensitivityBeta, radiationSensitivityGamma, minOscWidth);
		System.out.println("This is what I got as a response : diffractionPlanId = " + ret + "  \n");
	}

	private static void testStoreOrUpdateDiffractionPlanNew() throws Exception {
		System.out.println("*************** testStoreOrUpdateDiffractionPlanNew ***************");
		Integer ret = -1;

		Integer diffractionPlanId = null;
		String experimentKind = "Default";
		Double observedResolution = 0.01;
		Double minimalResolution = 0.02;
		Double exposureTime = 0.03;
		Double oscillationRange = 0.04;
		Double maximalResolution = 0.05;
		Double screeningResolution = 0.06;
		Double radiationSensitivity = 0.07;
		String anomalousScatterer = "anomalousScatterer";
		Double preferredBeamSizeX = 0.001;
		Double preferredBeamSizeY = 0.002;
		Double preferredBeamDiameter= 0.0033;
		String comments = "someComments ws";
		Double aimedCompleteness = 0.003;
		Double aimedIOverSigmaAtHighestRes = 0.004;
		Double aimedMultiplicity = 0.005;
		Double aimedResolution = 0.006;
		Boolean anomalousData = false;
		String complexity = "complexity";
		Boolean estimateRadiationDamage = true;
		String forcedSpaceGroup = "forcedSpaceGroup";
		Double requiredCompleteness = 0.007;
		Double requiredMultiplicity = 0.008;
		Double requiredResolution = 0.009;
		String strategyOption = "strategyoption";
		String kappaStrategyOption = "kappaStrategyOption";
		Integer numberOfPositions = 3;
		Double minDimAccrossSpindleAxis = 0.0001;
		Double maxDimAccrossSpindleAxis = 0.0002;
		Double radiationSensitivityBeta = 0.0003;
		Double radiationSensitivityGamma = 0.0004;
		Double minOscWidth = 0.5;

		ret = ws.storeOrUpdateDiffractionPlan(diffractionPlanId, experimentKind, observedResolution,
				minimalResolution, exposureTime, oscillationRange, maximalResolution, screeningResolution, radiationSensitivity,
				anomalousScatterer, preferredBeamSizeX, preferredBeamSizeY, preferredBeamDiameter, comments, aimedCompleteness, aimedIOverSigmaAtHighestRes,
				aimedMultiplicity, aimedResolution, anomalousData, complexity, estimateRadiationDamage, forcedSpaceGroup,
				requiredCompleteness, requiredMultiplicity, requiredResolution, strategyOption, kappaStrategyOption,
				numberOfPositions, minDimAccrossSpindleAxis, maxDimAccrossSpindleAxis, radiationSensitivityBeta,
				radiationSensitivityGamma, minOscWidth);
		System.out.println("This is what I got as a response : diffractionPlanId = " + ret + "  \n");
	}

	private static void testFindBLSample() throws Exception {
		System.out.println("*************** testFindBLSample ***************");
		BlSampleWS3VO blSample = null;
		Integer blSampleId = 1;//398649;

		blSample = ws.findBLSample(blSampleId);
		System.out.println("This is what I got as a response : BLSampleValue = " + (blSampleToString(blSample)) + "  \n");
	}

	public static String blSampleToString(BlSampleWS3VO blSample) {
		if (blSample == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("blSampleId=" + blSample.getBlSampleId() + " " + "crystalId=" + blSample.getCrystalId() + " "
				+ "diffractionPlanId=" + blSample.getDiffractionPlanId() + " " + "containerId=" + blSample.getContainerId() + " "
				+ "name=" + blSample.getName() + " " + "location=" + blSample.getLocation() + " ");
		str.append('}');

		return (str.toString());
	}

	public static String sampleInfoLightToString(SampleInfo info) {
		if (info == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("blSample=" + info.getSampleId() + ", name= " + info.getSampleName() + ", location= " + info.getSampleLocation()
				+ ", smiles =  " + info.getSmiles() + "\n" + "crystal= " + info.getCrystalSpaceGroup() + ", cellA = "
				+ info.getCellA() + ", cellGamma = " + info.getCellGamma() + "\n" + "protein= " + info.getProteinAcronym() + "\n"
				+ "diffractionPlan= " + diffractionPlanToString(info.getDiffractionPlan()) + "\n" + "experimentType= "
				+ info.getExperimentType() + "\n" + "container= " + info.getContainerSampleChangerLocation());
		str.append('}');

		return (str.toString());
	}

	public static String crystalToString(Crystal3VO crystal) {
		if (crystal == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("crystalId=" + crystal.getCrystalId() + " " + "name=" + crystal.getName() + " " + "spaceGroup="
				+ crystal.getSpaceGroup() + " ");
		str.append('}');

		return (str.toString());
	}

	public static String proteinToString(Protein3VO protein) {
		if (protein == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("proteinId=" + protein.getProteinId() + " " + "acronym=" + protein.getAcronym() + " ");
		str.append('}');

		return (str.toString());
	}

	public static String diffractionPlanToString(DiffractionPlanWS3VO diffractionPlan) {
		if (diffractionPlan == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("diffractionPlanId=" + diffractionPlan.getDiffractionPlanId() + " " + "minimalResolution = "
				+ diffractionPlan.getMinimalResolution() + ", experimentKind = " + diffractionPlan.getExperimentKind());
		str.append('}');

		return (str.toString());
	}

	public static String containerToString(Container3VO container) {
		if (container == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("containerId=" + container.getContainerId() + " " + "beamlineLocation=" + container.getBeamlineLocation() + " "
				+ "sampleChangerLocation=" + container.getSampleChangerLocation() + " ");
		str.append('}');

		return (str.toString());
	}

	private static void testStoreOrUpdateBLSample() throws Exception {
		System.out.println("*************** testStoreOrUpdateBLSample ***************");
		Integer ret = -1;

		Integer blSampleId = null;
		Integer diffractionPlanId = 1;//126818;
		Integer crystalId = 1;//336887;
		Integer containerId = 1;//311715;
		String name = "mysamplename";
		String code = "code";
		String location = "2";
		Double holderLength = new Double(22);
		Double loopLength = 0.5;
		String loopType = "Nylon";
		Double wireWidth = 0.01;
		String comments = "test for mxcube";
		String completionStage = "completionStage";
		String structureStage = "structureStage";
		String publicationStage = "publicationStage";
		String publicationComments = "publicationComments";
		String blSampleStatus = "blSampleStatus";
		Byte isInSampleChanger = 0;
		String lastKnownCenteringPosition = "lastKnownCentringPosition";

		BlSampleWS3VO blSample = new BlSampleWS3VO();
		blSample.setBlSampleId(blSampleId);
		blSample.setDiffractionPlanId(diffractionPlanId);
		blSample.setCrystalId(crystalId);
		blSample.setContainerId(containerId);
		blSample.setName(name);
		blSample.setCode(code);
		blSample.setLocation(location);
		blSample.setHolderLength(holderLength);
		blSample.setLoopLength(loopLength);
		blSample.setLoopType(loopType);
		blSample.setWireWidth(wireWidth);
		blSample.setComments(comments);
		blSample.setCompletionStage(completionStage);
		blSample.setStructureStage(structureStage);
		blSample.setPublicationStage(publicationStage);
		blSample.setPublicationComments(publicationComments);
		blSample.setBlSampleStatus(blSampleStatus);
		blSample.setIsInSampleChanger(isInSampleChanger);
		blSample.setLastKnownCenteringPosition(lastKnownCenteringPosition);

		ret = ws.storeOrUpdateBLSample(blSample);
		System.out.println("C This is what I got as a response : blSampleId = " + ret + "  \n");

		// foreign keys null
		blSample.setCrystalId(null);
		ret = ws.storeOrUpdateBLSample(blSample);
		System.out.println("F This is what I got as a response : blSampleId = " + ret + "  \n");

		blSample.setCrystalId(1);
		blSample.setContainerId(null);
		ret = ws.storeOrUpdateBLSample(blSample);
		System.out.println("F This is what I got as a response : blSampleId = " + ret + "  \n");

	}

	public static void testFindSampleInfoLightForProposal() throws Exception {
		System.out.println("*************** testFindSampleInfoLightForProposal ***************");
		List<SampleInfo> sampleInfos = null;
		Integer proposalId = 1;
		String beamlineLocation = "ID29";
		long start = System.currentTimeMillis();
		sampleInfos = ws.findSampleInfoLightForProposal(proposalId, beamlineLocation);
		if (sampleInfos != null) {
			System.out.println("SampleInfo[] length = " + sampleInfos.size() + "\n");
			for (Iterator iterator = sampleInfos.iterator(); iterator.hasNext();) {
				SampleInfo sampleInfo = (SampleInfo) iterator.next();
				System.out.println("SampleInfo = " + sampleInfoLightToString(sampleInfo) + "\n");
			}
			System.out.println("This is what I got as a response :\n" + sampleInfos.toString() + sampleInfos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		long end = System.currentTimeMillis();
		System.out.println("testFindSampleInfoLightForProposal in " + (end - start) + " ms.");
	}

	public static void testFindSampleInfoLightForProposalCodeAndNumber() throws Exception {
		System.out.println("*************** testFindSampleInfoLightForProposalCodeAndNumber ***************");
		List<SampleInfo> sampleInfos = null;
		String proposalCode = "mx";
		String proposalNumber = "415";
		String beamlineLocation = "ID29";
		long start = System.currentTimeMillis();
		sampleInfos = ws.findSampleInfoLightForProposalCodeAndNumber(proposalCode, proposalNumber, beamlineLocation);
		if (sampleInfos != null) {
			System.out.println("SampleInfo[] length = " + sampleInfos.size() + "\n");
			for (Iterator iterator = sampleInfos.iterator(); iterator.hasNext();) {
				SampleInfo sampleInfo = (SampleInfo) iterator.next();
				System.out.println("SampleInfo = " + sampleInfoLightToString(sampleInfo) + "\n");
			}
			System.out.println("This is what I got as a response :\n" + sampleInfos.toString() + sampleInfos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		long end = System.currentTimeMillis();
		System.out.println("testFindSampleInfoLightForProposal in " + (end - start) + " ms.");
	}

	public static void testGetPdbFilePath() throws Exception {
		System.out.println("*************** testGetPdbFilePath ***************");

		String proteinAcronym = "yF1";
		String spaceGroup = "P212121";
		String pdbFilePath = ws.getPdbFilePath(proteinAcronym, spaceGroup);
		System.out.println("This is what I got as a response : pdb filePath : " + pdbFilePath + " \n");

		proteinAcronym = "FBP";
		spaceGroup = "Undefined";
		pdbFilePath = ws.getPdbFilePath(proteinAcronym, spaceGroup);
		System.out.println("This is what I got as a response : pdb filePath : " + pdbFilePath + " \n");

	}

	public static void testFindProteinAcronymsForProposal() throws Exception {
		System.out.println("*************** testFindProteinAcronymsForProposal ***************");

		String proposalCode = "mx";
		String proposalNumber = "415";
		List<String> listProteinAcronyms = ws.findProteinAcronymsForProposal(proposalCode, proposalNumber);
		int length = 0;
		String s = "";
		if (listProteinAcronyms != null) {
			length = listProteinAcronyms.size();
			for (Iterator iterator = listProteinAcronyms.iterator(); iterator
					.hasNext();) {
				String string = (String) iterator.next();
				s += string + ", ";
			}
		}

		System.out.println("This is what I got as a response : listProteinAcronyms : " + length + ": " + s + " \n");

	}

	private static void testStoreOrUpdateBLSubSample() throws Exception {
		System.out.println("*************** testStoreOrUpdateBLSubSample ***************");
		Integer ret = -1;

		Integer blSubSampleId = null;
		Integer diffractionPlanId = 126818;
		Integer sampleId = 336887;
		Integer positionId = 1;
		Integer motorPositionId = null;
		String blSubSampleUUID = "blSubSampleUUID";
		String imgFileName = "imgFileName";
		String imgFilePath = "imgFilePath";
		String comments = "comments";

		BlSubSampleWS3VO blSubSample = new BlSubSampleWS3VO();
		blSubSample.setBlSubSampleId(blSubSampleId);
		blSubSample.setDiffractionPlanId(diffractionPlanId);
		blSubSample.setBlSampleId(sampleId);
		blSubSample.setPositionId(positionId);
		blSubSample.setBlSubSampleUUID(blSubSampleUUID);
		blSubSample.setImgFileName(imgFileName);
		blSubSample.setImgFilePath(imgFilePath);
		blSubSample.setComments(comments);

		ret = ws.storeOrUpdateBLSubSample(blSubSample);
		System.out.println("C This is what I got as a response : blSubSample = " + ret + "  \n");

		// foreign keys null
		blSubSample.setBlSampleId(null);
		ret = ws.storeOrUpdateBLSubSample(blSubSample);
		System.out.println("F This is what I got as a response : blSubSample = " + ret + "  \n");

	}

	public static void testGetSampleInfo() throws Exception {
		System.out.println("*************** testGetSampleInfo ***************");
		SampleInfo sampleInfo = null;
		Integer sampleId = 445137;
		long start = System.currentTimeMillis();
		sampleInfo = ws.getSampleInformation(sampleId);
		if (sampleInfo != null) {
			System.out.println("SampleInfo = " + sampleInfoLightToString((sampleInfo)) + "\n");
		} else
			System.out.println("This is what I got as a response : NULL \n");
		long end = System.currentTimeMillis();
		System.out.println("testGetSampleInfo in " + (end - start) + " ms.");
	}
	
	public static void testFindBySpaceGroupShortName() throws Exception {
		System.out.println("*************** testfindBySpaceGroupShortName ***************");
		SampleInfo sampleInfo = null;
		Integer sampleId = 445137;
		long start = System.currentTimeMillis();
		sampleInfo = ws.getSampleInformation(sampleId);
		if (sampleInfo != null) {
			System.out.println("SampleInfo = " + sampleInfoLightToString((sampleInfo)) + "\n");
		} else
			System.out.println("This is what I got as a response : NULL \n");
		long end = System.currentTimeMillis();
		System.out.println("testGetSampleInfo in " + (end - start) + " ms.");
	}

}
