package z7z8.encrypt;

import java.security.Security;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.ws.Provider;

public class encryptType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] name=getCryptoImpls("MessageDigest");
		for(String s:name){
			System.out.println(s+"\n");
		}

	}
	
	public static String[] getCryptoImpls(String serviceType) {
        Set result = new HashSet();
    
        // All all providers
        java.security.Provider[] providers = Security.getProviders();
        for (int i=0; i<providers.length; i++) {
            // Get services provided by each provider
            Set keys = providers[i].keySet();
            for (Iterator it=keys.iterator(); it.hasNext(); ) {
                String key = (String)it.next();
                key = key.split(" ")[0];
    
                if (key.startsWith(serviceType+".")) {
                    result.add(key.substring(serviceType.length()+1));
                } else if (key.startsWith("Alg.Alias."+serviceType+".")) {
                    // This is an alias
                    result.add(key.substring(serviceType.length()+11));
                }
            }
        }
        return (String[])result.toArray(new String[result.size()]);
    }

}
