import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class MacTest {

	public static void getMacs() {
        try {
            /*
             * Get NetworkInterface for the current host and then read the
             * hardware address.
             */
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
 
            if (nis != null) {
                while (nis.hasMoreElements()) {
                    NetworkInterface currentNI = nis.nextElement();
                    Enumeration<InetAddress> addresses = currentNI.getInetAddresses();
 
                    while (addresses.hasMoreElements()) {
                        InetAddress inetAddress = addresses.nextElement();
                        
                        byte[] mac = currentNI.getHardwareAddress();
 
                        if (mac != null) {
                            if (mac.length > 0) {
                                String hostName = inetAddress.getHostName();
                                String hostAddr = inetAddress.getHostAddress();
                                System.out.println("HOSTNAME = " + hostName + " HOSTADDR = " + hostAddr);
 
                                System.out.print(
                                    "Name: " + currentNI.getName() + " | " /*+
                                    "DisplayName: " + currentNI.getDisplayName()*/ + " MAC: ");
 
                                /*
                                 * Extract each array of mac address and convert it to hexa with the
                                 * following format 08-00-27-DC-4A-9E.
                                 */
                                for (int i = 0; i < mac.length; i++) {
                                    System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                                }
                                System.out.println();
                            }
                        } else {
                            System.out.println("Address doesn't exist or is not accessible.");
                        }
                    }
                }
 
            } else {
                System.out.println("Network Interface for the specified address is not found.");
            }
 
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getMacs();

	}

}
