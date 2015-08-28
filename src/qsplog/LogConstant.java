package qsplog;

import java.util.HashMap;
import java.util.Map;

public class LogConstant {
	
	private static int no_use = -1;
	
	static int i=1;
	public static int order_client=i++;
	public static int order_client_prc=i++;
	public static int order_sort=i++;
	
	//-1ÎÞÐ§µÄ
	public static int order_sort_call_thread=no_use;
	public static int order_sort_thread_run=no_use;
	public static int order_sort_search_client=no_use;
	
	public static int order_sort_get_subindexs=i++;
	public static int order_sort_split_task=i++;
	
	public static int order_sort_parallel_search=no_use;
	
	public static int order_sort_rpc_search=i++;
	
	public static int order_search_prepare=no_use;
	public static int order_search=i++;
	
	public static int order_search_get_lucene_query=i++;
	public static int order_search_extendsWord=no_use;
	
	
	public static int order_search_lucene=i++;
	public static int order_search_getValue=i++;
	
	public static int order_sort_result_sort=i++;
	
	public static int order_sort_createSearchResults=i++;
	public static int order_sort_prepare_doc=no_use;
	
	public static int order_sort_get_docsvr=no_use;
	public static int order_sort_remote_doc=no_use;
	public static int order_sort_prepare_get_doc=no_use;
	
	public static int order_sort_rpc_docsvr=i++;
	
	public static int order_test_test=no_use;
	public static int order_docsvr=i++;

	public static int order_sort_get_local=no_use;
	public static int order_sort_highLighter=no_use;
	
}
