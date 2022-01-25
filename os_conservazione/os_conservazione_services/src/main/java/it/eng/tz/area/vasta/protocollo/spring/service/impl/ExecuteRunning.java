package it.eng.tz.area.vasta.protocollo.spring.service.impl;

import it.eng.tz.area.vasta.protocollo.spring.service.IExecuteRunning;


public class ExecuteRunning extends Thread{
	private Thread thread;
	private IExecuteRunning execute;
 
	
	public ExecuteRunning(IExecuteRunning execute) {
		this.execute=execute;
		this.start();
	}
	
	@Override
	public void run() {
		
		try {
			getExecute().running();
		}catch(Exception e) {}
		
	}

	
	
public void start() {
	if(thread!=null){ thread.interrupt(); thread=null; }
	thread= new Thread(this);
	thread.start();
}

public void setExecute(IExecuteRunning execute) {
	this.execute = execute;
}

public IExecuteRunning getExecute() {
	if(execute==null) {
		return new IExecuteRunning() {
			
			@Override
			public void running() throws Exception {
				 
				
			}
		};
	}
	return execute;
}

	
}
