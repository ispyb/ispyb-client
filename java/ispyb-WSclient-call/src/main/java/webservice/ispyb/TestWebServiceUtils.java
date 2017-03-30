package webservice.ispyb;

public class TestWebServiceUtils {

	public TestWebServiceUtils() throws Exception {
		super();
	}

	public static void main(String args[]) {

	}

	public static String initServerAddress() {
		String serverAddress = "http://localhost:8080/ispyb-ws/ispybWS/"; // dev

		// String serverAddress = "http://pyproserv.esrf.fr:8080/ispyb-ejb3/ispybWS/"; // new prod

		// String serverAddress = "http://160.103.210.127:8080/ispyb-ejb3/ispybWS/"; // test

		return serverAddress;
	}

	public static class ProposalBean {

		private String proposalCode;

		private String proposalNumber;

		private String beamline;

		public ProposalBean(String proposalCode, String proposalNumber, String beamline) {
			super();
			this.proposalCode = proposalCode;
			this.proposalNumber = proposalNumber;
			this.beamline = beamline;
		}

		public String getProposalCode() {
			return proposalCode;
		}

		public void setProposalCode(String proposalCode) {
			this.proposalCode = proposalCode;
		}

		public String getProposalNumber() {
			return proposalNumber;
		}

		public void setProposalNumber(String proposalNumber) {
			this.proposalNumber = proposalNumber;
		}

		public String getBeamline() {
			return beamline;
		}

		public void setBeamline(String beamline) {
			this.beamline = beamline;
		}

	}

}
