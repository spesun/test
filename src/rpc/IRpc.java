package rpc;

import com.zte.qsp.searcher.ipc.VersionedProtocol;


public interface IRpc extends VersionedProtocol
{

	/**version id*/
	public static final long versionID = 0;
	
	public String ping(String in);
	
}
