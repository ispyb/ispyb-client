package webservice.ispyb.screening;

import generated.ws.mx.edna.IspybWS;
import generated.ws.mx.edna.ScreeningRankSet3VO;
import generated.ws.mx.edna.ScreeningRankWS3VO;
import generated.ws.mx.edna.ToolsForScreeningEDNAWebService;

import java.util.Calendar;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import webservice.UtilsDoNotCommit;

public class TestScreeningEDNAToolsWebService {

	protected static generated.ws.mx.edna.IspybWS iws;
	protected static ToolsForScreeningEDNAWebService ws;

	private static void initWebService() throws Exception {
		iws = new IspybWS();	

		System.out.println("-----------------------------------------------------------");

		ws=iws.getToolsForScreeningEDNAWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);
	}

	public static void main(String args[]) {
		try {
			System.out.println("*************** testScreeningWebServices ***************");
			initWebService();

			 testStoreOrUpdateScreening();
			 testStoreOrUpdateScreeningRank();
			 testStoreOrUpdateScreeningRankValue();
			 testStoreOrUpdateScreeningRankSet();
			 testStoreOrUpdateScreeningRankSetValue();
			 testStoreOrUpdateScreeningOutput();
			 testStoreOrUpdateScreeningOutputLattice();
			 testStoreOrUpdateScreeningStrategy();
			 testStoreOrUpdateScreeningStrategyWedge();
			 testStoreOrUpdateScreeningStrategySubWedge();
			 testStoreOrUpdateScreeningInput();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static void testStoreOrUpdateScreening() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreening ***************");
		java.lang.Integer ret = -1;

		Integer screeningId = null;
		Integer dataCollectionId = 1;//19570;
		Integer diffractionPlanId = 1;//127806;
		Calendar recordTimeStamp = Calendar.getInstance();
		recordTimeStamp.set(2012, 10, 9, 14, 52, 55);
		String programVersion = "programVersion";
		String comments = "some comments";
		String shortComments = "shortComments";
		String xmlSampleInformation = "<xmlSampleInformation>";

		ret = ws.storeOrUpdateScreening(screeningId, dataCollectionId, diffractionPlanId, recordTimeStamp, programVersion, comments,
				shortComments, xmlSampleInformation);
		System.out.println("C This is what I got as a response : screeningId  = " + ret + "  \n");

		// foreign key null
		Integer ret2 = ws.storeOrUpdateScreening(screeningId, null, diffractionPlanId, recordTimeStamp, programVersion, comments,
				shortComments, xmlSampleInformation);
		System.out.println("F This is what I got as a response : screeningId  = " + ret2 + "  \n");

		// update
		screeningId = ret;
		comments = "some comments modif";

		ret = ws.storeOrUpdateScreening(ret, dataCollectionId, diffractionPlanId, recordTimeStamp, programVersion, comments,
				shortComments, xmlSampleInformation);
		System.out.println("U Update done  : screeningId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateScreeningRank() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningRank ***************");
		Integer ret = -1;

		Integer screeningRankId = null;
		Integer screeningRankSetId = 1;
		Integer screeningId = 1;//80752;
		Double rankValue = 0.2;
		String rankInformation = "rankInformation";

		ret = ws.storeOrUpdateScreeningRank(screeningRankId, screeningRankSetId, screeningId, rankValue, rankInformation);
		System.out.println("C This is what I got as a response : screeningRankId  = " + ret + "  \n");

		// foreign key null
		Integer ret2 = ws.storeOrUpdateScreeningRank(screeningRankId, null, screeningId, rankValue, rankInformation);
		System.out.println("F This is what I got as a response : screeningRankId  = " + ret2 + "  \n");

		ret2 = ws.storeOrUpdateScreeningRank(screeningRankId, screeningRankSetId, null, rankValue, rankInformation);
		System.out.println("F This is what I got as a response : screeningRankId  = " + ret2 + "  \n");

		// update
		screeningRankId = ret;
		rankInformation = "rankInformation modif";
		ret = ws.storeOrUpdateScreeningRank(screeningRankId, screeningRankSetId, screeningId, rankValue, rankInformation);

		System.out.println("U This is what I got as a response : screeningRankId  = " + ret + "  \n");

	}

	private static void testStoreOrUpdateScreeningRankValue() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningRankValue ***************");
		Integer ret = -1;

		Integer screeningRankId = 1;//6240;
		Integer screeningRankSetId = 1;
		Integer screeningId = 1;//80752;
		Double rankValue = 0.3;
		String rankInformation = "rankInfo";
		ScreeningRankWS3VO value = new ScreeningRankWS3VO();
		value.setScreeningRankId(screeningRankId);
		value.setScreeningRankSetId(screeningRankSetId);
		value.setScreeningId(screeningId);
		value.setRankValue(rankValue);
		value.setRankInformation(rankInformation);
		ret = ws.storeOrUpdateScreeningRankValue(value);
		System.out.println("This is what I got as a response : screeningRankId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateScreeningRankSet() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningRankSet ***************");
		Integer ret = -1;

		Integer screeningRankSetId = 1;
		String rankEngine = "RankEngine";
		String rankingProjectFileName = "RankingProjectFileName";
		String rankingSummaryFileName = "RankingSummaryFileName";

		ret = ws.storeOrUpdateScreeningRankSet(screeningRankSetId, rankEngine, rankingProjectFileName, rankingSummaryFileName);
		System.out.println("This is what I got as a response : screeningRankSetId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateScreeningRankSetValue() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningRankSetValue ***************");
		Integer ret = -1;

		Integer screeningRankSetId = null;
		String rankEngine = "RankEngine";
		String rankingProjectFileName = "RankingProjectFileName";
		String rankingSummaryFileName = "RankingSummaryFileName";

		ScreeningRankSet3VO value = new ScreeningRankSet3VO();
		value.setScreeningRankSetId(screeningRankSetId);
		value.setRankEngine(rankEngine);
		value.setRankingProjectFileName(rankingProjectFileName);
		value.setRankingSummaryFileName(rankingSummaryFileName);

		ret = ws.storeOrUpdateScreeningRankSetValue(value);
		System.out.println("This is what I got as a response : screeningRankSetId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateScreeningOutput() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningOutput ***************");
		Integer ret = -1;

		Integer screeningOutputId = null;
		Integer screeningId = 1;//80752;
		String statusDescription = "statusDescription";
		Integer rejectedReflections = 2;
		Double resolutionObtained = 0.1;
		Double spotDeviationR = 0.2;
		Double spotDeviationTheta = 0.3;
		Double beamShiftX = 0.4;
		Double beamShiftY = 0.5;
		Integer numSpotsFound = 1;
		Integer numSpotsUsed = 2;
		Integer numSpotsRejected = 3;
		Double mosaicity = 0.6;
		Double ioverSigma = 0.7;
		Byte diffractionRings = 1;
		Byte strategySuccess = 0;
		Byte indexingSuccess = 1;
		Byte mosaicityEstimated = 1;
		Double rankingResolution = 1.1;
		String program = "program";
		Double doseTotal = 1.3;
		Double totalExposureTime = 1.4;
		Double totalRotationRange = 1.5;
		Integer totalNumberOfImages = 16;
		Double rFriedel = 1.7;

		ret = ws.storeOrUpdateScreeningOutput(screeningOutputId, screeningId, statusDescription, rejectedReflections,
				resolutionObtained, spotDeviationR, spotDeviationTheta, beamShiftX, beamShiftY, numSpotsFound, numSpotsUsed,
				numSpotsRejected, mosaicity, ioverSigma, diffractionRings, strategySuccess, indexingSuccess, mosaicityEstimated,
				rankingResolution, program, doseTotal, totalExposureTime, totalRotationRange, totalNumberOfImages, rFriedel);
		System.out.println("C This is what I got as a response : screeningOutputId  = " + ret + "  \n");

		// foreign key null
		Integer ret2 = ws.storeOrUpdateScreeningOutput(screeningOutputId, null, statusDescription, rejectedReflections,
				resolutionObtained, spotDeviationR, spotDeviationTheta, beamShiftX, beamShiftY, numSpotsFound, numSpotsUsed,
				numSpotsRejected, mosaicity, ioverSigma, diffractionRings, strategySuccess, indexingSuccess, mosaicityEstimated,
				rankingResolution, program, doseTotal, totalExposureTime, totalRotationRange, totalNumberOfImages, rFriedel);
		System.out.println("This is what I got as a response : screeningOutputId  = " + ret2 + "  \n");

		// update
		screeningOutputId = ret;
		statusDescription = "statusDescription modif";

		ret = ws.storeOrUpdateScreeningOutput(screeningOutputId, screeningId, statusDescription, rejectedReflections,
				resolutionObtained, spotDeviationR, spotDeviationTheta, beamShiftX, beamShiftY, numSpotsFound, numSpotsUsed,
				numSpotsRejected, mosaicity, ioverSigma, diffractionRings, strategySuccess, indexingSuccess, mosaicityEstimated,
				rankingResolution, program, doseTotal, totalExposureTime, totalRotationRange, totalNumberOfImages, rFriedel);
		System.out.println("U this is what I got as a response : screeningOutputId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateScreeningOutputLattice() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningOutputLattice ***************");
		Integer ret = -1;

		Integer screeningOutputLatticeId = null;
		Integer screeningOutputId = 1;//80738;
		String spaceGroup = "spaceGroup";
		String pointGroup = "pointGroup";
		String bravaisLattice = "BravaisLattice";
		Double rawOrientationMatrix_a_x = 0.01;
		Double rawOrientationMatrix_a_y = 0.02;
		Double rawOrientationMatrix_a_z = 0.03;
		Double rawOrientationMatrix_b_x = 0.04;
		Double rawOrientationMatrix_b_y = 0.05;
		Double rawOrientationMatrix_b_z = 0.06;
		Double rawOrientationMatrix_c_x = 0.07;
		Double rawOrientationMatrix_c_y = 0.08;
		Double rawOrientationMatrix_c_z = 0.09;
		Double unitCell_a = 0.01;
		Double unitCell_b = 0.02;
		Double unitCell_c = 0.03;
		Double unitCell_alpha = 0.04;
		Double unitCell_beta = 0.05;
		Double unitCell_gamma = 0.06;
		Calendar recordTimeStamp = Calendar.getInstance();
		recordTimeStamp.set(2012, 10, 9, 14, 52, 55);
		Boolean labelitIndexing = false;

		ret = ws.storeOrUpdateScreeningOutputLattice(screeningOutputLatticeId, screeningOutputId, spaceGroup, pointGroup,
				bravaisLattice, rawOrientationMatrix_a_x, rawOrientationMatrix_a_y, rawOrientationMatrix_a_z,
				rawOrientationMatrix_b_x, rawOrientationMatrix_b_y, rawOrientationMatrix_b_z, rawOrientationMatrix_c_x,
				rawOrientationMatrix_c_y, rawOrientationMatrix_c_z, unitCell_a, unitCell_b, unitCell_c, unitCell_alpha, unitCell_beta,
				unitCell_gamma, recordTimeStamp, labelitIndexing);
		System.out.println("C This is what I got as a response : screeningOutputLatticeId  = " + ret + "  \n");
		screeningOutputLatticeId = ret;

		// foreign key null
		ret = ws.storeOrUpdateScreeningOutputLattice(screeningOutputLatticeId, null, spaceGroup, pointGroup, bravaisLattice,
				rawOrientationMatrix_a_x, rawOrientationMatrix_a_y, rawOrientationMatrix_a_z, rawOrientationMatrix_b_x,
				rawOrientationMatrix_b_y, rawOrientationMatrix_b_z, rawOrientationMatrix_c_x, rawOrientationMatrix_c_y,
				rawOrientationMatrix_c_z, unitCell_a, unitCell_b, unitCell_c, unitCell_alpha, unitCell_beta, unitCell_gamma,
				recordTimeStamp, labelitIndexing);
		System.out.println("F This is what I got as a response : screeningOutputLatticeId  = " + ret + "  \n");

		// update

		// screeningOutputLatticeId = 80723;
		spaceGroup = "spaceGroup modif";
		ret = ws.storeOrUpdateScreeningOutputLattice(screeningOutputLatticeId, screeningOutputId, spaceGroup, pointGroup,
				bravaisLattice, rawOrientationMatrix_a_x, rawOrientationMatrix_a_y, rawOrientationMatrix_a_z,
				rawOrientationMatrix_b_x, rawOrientationMatrix_b_y, rawOrientationMatrix_b_z, rawOrientationMatrix_c_x,
				rawOrientationMatrix_c_y, rawOrientationMatrix_c_z, unitCell_a, unitCell_b, unitCell_c, unitCell_alpha, unitCell_beta,
				unitCell_gamma, recordTimeStamp, labelitIndexing);
		System.out.println("U This is what I got as a response : screeningOutputLatticeId  = " + ret + "  \n");

	}

	private static void testStoreOrUpdateScreeningStrategy() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningStrategy ***************");
		Integer ret = -1;

		Integer screeningStrategyId = null;
		Integer screeningOutputId = 1;//80738;
		Double phiStart = 0.1;
		Double phiEnd = 0.2;
		Double rotation = 0.3;
		Double exposureTime = 0.4;
		Double resolution = 0.5;
		Double completeness = 0.6;
		Double multiplicity = 0.7;
		Byte anomalous = 1;
		String program = "Program";
		Double rankingResolution = 0.8;
		Double transmission = 0.9;

		ret = ws.storeOrUpdateScreeningStrategy(screeningStrategyId, screeningOutputId, phiStart, phiEnd, rotation, exposureTime,
				resolution, completeness, multiplicity, anomalous, program, rankingResolution, transmission);
		System.out.println("C This is what I got as a response : screeningStrategyId  = " + ret + "  \n");

		// update
		screeningStrategyId = ret;
		program = "Program modif";
		ret = ws.storeOrUpdateScreeningStrategy(screeningStrategyId, screeningOutputId, phiStart, phiEnd, rotation, exposureTime,
				resolution, completeness, multiplicity, anomalous, program, rankingResolution, transmission);
		System.out.println("U This is what I got as a response : screeningStrategyId  = " + ret + "  \n");

		// foreign key null
		ret = ws.storeOrUpdateScreeningStrategy(screeningStrategyId, null, phiStart, phiEnd, rotation, exposureTime, resolution,
				completeness, multiplicity, anomalous, program, rankingResolution, transmission);
		System.out.println("This is what I got as a response : screeningStrategyId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateScreeningStrategyWedge() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningStrategyWedge ***************");
		Integer ret = 1;

		Integer screeningStrategyWedgeId = null;
		Integer screeningStrategyId = 1;//13837;
		Integer wedgeNumber = 1;
		Double resolution = 0.1;
		Double completeness = 0.2;
		Double multiplicity = 0.3;
		Double doseTotal = 0.4;
		Integer numberOfImages = 2;
		Double phi = new Double(14);
		Double kappa = new Double(15);
		String comments = "comments";
		Double wavelength = 1.1;
		Double chi = 0.1;

		ret = ws.storeOrUpdateScreeningStrategyWedge(screeningStrategyWedgeId, screeningStrategyId, wedgeNumber, resolution,
				completeness, multiplicity, doseTotal, numberOfImages, phi, kappa, chi, comments, wavelength);

		System.out.println("C This is what I got as a response : screeningStrategyWedgeId = " + ret + "  \n");
		screeningStrategyWedgeId = ret;

		// update
		ret = ws.storeOrUpdateScreeningStrategyWedge(screeningStrategyWedgeId, screeningStrategyId, wedgeNumber, resolution,
				completeness, multiplicity, doseTotal, numberOfImages, phi, kappa, chi, comments, wavelength);

		System.out.println("U This is what I got as a response : screeningStrategyWedgeId = " + ret + "  \n");

		// foreign key null
		ret = ws.storeOrUpdateScreeningStrategyWedge(screeningStrategyWedgeId, null, wedgeNumber, resolution, completeness,
				multiplicity, doseTotal, numberOfImages, phi, kappa, chi, comments, wavelength);

		System.out.println("F This is what I got as a response : screeningStrategyWedgeId = " + ret + "  \n");
	}

	private static void testStoreOrUpdateScreeningStrategySubWedge() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningStrategySubWedge ***************");
		Integer ret = 1;

		Integer screeningStrategySubWedgeId = null;
		Integer screeningStrategyWedgeId = 1;
		Integer subWedgeNumber = 2;
		String rotationAxis = "rotationAxis";
		Double axisStart = new Double(0.1);
		Double axisEnd = new Double(0.12);
		Double exposureTime = new Double(2);
		Double transmission = new Double(10);
		Double oscillationRange = new Double(3);
		Double completeness = new Double(11);
		Double multiplicity = new Double(12);
		Double doseTotal = new Double(13);
		Integer numberOfImages = 3;
		String comments = "comments";

		ret = ws.storeOrUpdateScreeningStrategySubWedge(screeningStrategySubWedgeId, screeningStrategyWedgeId, subWedgeNumber,
				rotationAxis, axisStart, axisEnd, exposureTime, transmission, oscillationRange, completeness, multiplicity, doseTotal,
				numberOfImages, comments);

		System.out.println("C This is what I got as a response : screeningStrategySubWedgeId = " + ret + "  \n");

		// update
		screeningStrategySubWedgeId = ret;
		comments = "comments modif";
		ret = ws.storeOrUpdateScreeningStrategySubWedge(screeningStrategySubWedgeId, screeningStrategyWedgeId, subWedgeNumber,
				rotationAxis, axisStart, axisEnd, exposureTime, transmission, oscillationRange, completeness, multiplicity, doseTotal,
				numberOfImages, comments);

		System.out.println("U This is what I got as a response : screeningStrategySubWedgeId = " + ret + "  \n");

		// foreign key null
		ret = ws.storeOrUpdateScreeningStrategySubWedge(screeningStrategySubWedgeId, null, subWedgeNumber, rotationAxis, axisStart,
				axisEnd, exposureTime, transmission, oscillationRange, completeness, multiplicity, doseTotal, numberOfImages, comments);

		System.out.println("F This is what I got as a response : screeningStrategySubWedgeId = " + ret + "  \n");
	}

	private static void testStoreOrUpdateScreeningInput() throws Exception {
		System.out.println("*************** testStoreOrUpdateScreeningInput ***************");
		Integer ret = -1;

		Integer screeningInputId = null;
		Integer screeningId = 1;//80752;
		Integer diffractionPlanId = null;
		Double beamX = 2.1;
		Double beamY = 3.2;
		Double rmsErrorLimits = 4.2;
		Double minimumFractionIndexed = 5.3;
		Double maximumFractionRejected = 6.4;
		Double minimumSignalToNoise = 7.5;
		String xmlSampleInformation = "<xmlSampleInformation>";

		ret = ws.storeOrUpdateScreeningInput(screeningInputId, screeningId, diffractionPlanId, beamX, beamY, rmsErrorLimits,
				minimumFractionIndexed, maximumFractionRejected, minimumSignalToNoise, xmlSampleInformation);
		System.out.println("C This is what I got as a response : screeningInputId  = " + ret + "  \n");

		// update
		screeningInputId = ret;
		xmlSampleInformation = "<xmlSampleInformation modif>";
		ret = ws.storeOrUpdateScreeningInput(screeningInputId, screeningId, diffractionPlanId, beamX, beamY, rmsErrorLimits,
				minimumFractionIndexed, maximumFractionRejected, minimumSignalToNoise, xmlSampleInformation);
		System.out.println("U This is what I got as a response : screeningInputId  = " + ret + "  \n");

		// foreign key null
		ret = ws.storeOrUpdateScreeningInput(screeningInputId, null, diffractionPlanId, beamX, beamY, rmsErrorLimits,
				minimumFractionIndexed, maximumFractionRejected, minimumSignalToNoise, xmlSampleInformation);
		System.out.println("F This is what I got as a response : screeningInputId  = " + ret + "  \n");
	}
}
