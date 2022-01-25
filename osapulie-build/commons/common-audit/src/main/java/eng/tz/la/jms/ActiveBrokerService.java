package eng.tz.la.jms;

import org.apache.activemq.broker.BrokerService;


public class ActiveBrokerService {
	private BrokerService broker;
 
	private String connector;
	public ActiveBrokerService() {
		//"tcp://localhost:61617" 
	}
	
	 
	public void avviaBroker(){
		if(broker==null){
//			broker=new BrokerService();
//			try {
//				broker.addConnector("tcp://localhost:61617");
//				broker.start();
//			} catch (Exception e) {
//		
//			}

		 
		}	
		
	}
	
	
}
