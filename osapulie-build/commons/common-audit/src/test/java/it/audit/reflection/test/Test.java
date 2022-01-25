package it.audit.reflection.test;
 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;
import eng.tz.la.model.OperationExportFile;
import eng.tz.la.model.custom.CustomSettyng;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
		

		ClassLogTest clReflec=new ClassLogTest();
		clReflec.setCognome("Liberto");
		clReflec.setNome("Luca");
		clReflec.setEta(10.0);
		clReflec.setData(new Date());
		
		 MetaLine metaLine=new MetaLine(new MetaField("NOME","NICOLA"));
		 metaLine
		.addField("VIA","NAZIONE 354",true)
		.addField("CAP", "80056")
		.addField(new MetaField("1",2,Integer.class));
		
		
		AuditManager.audit().laSettyng(CustomSettyng.class).addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").log(clReflec).log(metaLine).setActor("LUCA")
		 .log(metaLine).build(true);
//		.exportUser(false, new Export() {	
//			@Override
//			public void call(OperationExportFile export) throws IOException {
//			// export.saveToSystemFolderCons();
//				//System.out.println(export.getFileNameWithoutExt());	
//			}
//		}); 
		
		// Result:f1ff3264a30d7e4bcc6005d8479a805b 1cd22afc34e68d122c68392b108e9adf 1436c70a027c81312c7740cff11eaf35  2e8e7243b0340c1b52decfe9f7c0121b
		// 11-07-2018 19:59:41.341 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[nome==>salvatore ],
		

		
	} 
	
	
}
