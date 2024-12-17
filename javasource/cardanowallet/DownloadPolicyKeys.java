package cardanowallet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.bloxbean.cardano.client.api.util.PolicyUtil;
import com.bloxbean.cardano.client.crypto.SecretKey;
import com.bloxbean.cardano.client.exception.CborSerializationException;
import com.bloxbean.cardano.client.transaction.spec.Policy;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class DownloadPolicyKeys {
	public void DownloadKeys () throws CborSerializationException {
		Policy policy = PolicyUtil.createMultiSigScriptAllPolicy("Landano Policy", 1);
		
		 /*try {
			 
			 	String pathToTempFolder = Core.getConfiguration().getTempPath().getAbsolutePath() + "/";

			    //Assuming this file doesn't exist
			    File tempFile2 = new java.io.File( pathToTempFolder + "SomeFileName.xls" );
			    //if a file with the same name could exists you should check that and rename the file   (a good workaround for that problem is using the GUID in the filename) 

			    FileWriter writer2 = new java.io.FileWriter(tempFile2);
			    writer2.append("Some tekst");
			    writer2.close();
//			    CreateFileDocument
			    
			    // fileinputstream
			    FileInputStream tempStream2 = new java.io.FileInputStream(tempFile2);
//			    ArrayList<MendixObject> mxObject =  
			    Core.storeFileDocumentContent(this.getContext(), this.InputFileDocument2, tempStream2);
			    tempStream2.close();
			    
	            // Create output directory if it doesn't exist
//	            Path dirPath = Core.getConfiguration().getTempPath().getAbsolutePath();
//	            Files.createDirectories(dirPath);

	            // Get policy keys
			 // create object
			    List<SecretKey> policyKeys = policy.getPolicyKeys().get(0).getBytes();
			    List<IMendixObject> policyKeyDocuments = null;
			    for (int i = 0; i < policyKeys.size(); i++) {
					IMendixObject newObject = Core.instantiate(getContext(), "System.FileDocument");
					system.proxies.FileDocument fileDocument = system.proxies.FileDocument.initialize(this.getContext(),newObject);
				    Core.storeFileDocumentContent(this.getContext(), (IMendixObject) fileDocument, tempStream2);

	            // Create individual key files
	            
	                SecretKey key = policyKeys.get(i);
	                String keyFileName = String.format("policy_key_%d.skey", i);
	                Path keyPath = dirPath.resolve(keyFileName);
	                
	                // Write key to file
	                try (FileOutputStream fos = new FileOutputStream(keyPath.toFile())) {
	                    fos.write(key.getBytes());
	                }
	            }
			    return policyKeyDocuments;
		 } catch (IOException e) {
           throw new RuntimeException("Error exporting policy keys", e);
		 }*/
	}

}
