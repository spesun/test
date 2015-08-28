package dfs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JsoupTestZky {

	private static Map<String, String> filter = new HashMap<String, String>();
	private static StringBuilder bf = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		 
		/* // 直接从字符串中输�?HTML 文档
		 String html = "<html><head><title> �?��中国社区 </title></head>"
		  + "<body><p> 这里�?jsoup 项目的相关文�?</p></body></html>"; 
		 Document doc = Jsoup.parse(html); 

		 // �?URL 直接加载 HTML 文档
		 Document doc = Jsoup.connect("http://www.oschina.net/").get(); 
		 String title = doc.title(); 

		 Document doc = Jsoup.connect("http://www.oschina.net/") 
		  .data("query", "Java")   // 请求参数
		  .userAgent("I �?m jsoup") // 设置 User-Agent 
		  .cookie("auth", "token") // 设置 cookie 
		  .timeout(3000)           // 设置连接超时时间
		  .post();                 // 使用 POST 方法访问 URL 
*/
		 // 从文件中加载 HTML 文档
		 /*File input = new File("d:/plan_result.xml"); 
		 Document doc = Jsoup.parse(input,"UTF-8","http://www.oschina.net/"); 
		 Elements masthead = doc.select("att[key=setupMR]");
		 
		 for (int i = 0; i < masthead.size(); i++) {
			System.out.println(i);
			System.out.println(masthead.get(i));
		}*/
		 
		 //System.out.println(masthead.first().outerHtml());
		 Document doc = Jsoup.connect("http://e.zte.com.cn/pub/ztercn/news/media/201204/t20120405_299739.html").get(); 
		Element body = doc.body();
		 
		 filter.put("div", "div[id~=(content|Content|$content)]");
			filter.put("table", "td[class~=($content|$Content|t_msgfont)]");
			filter.put("title", "title|TITLE");
			
			findKeywords(doc);
			
		findContent(body,"p");
		System.out.println(bf);
		findContent(body,"div");
		//System.out.println(bf);
		findContent(body,"table");
		
		//System.out.println(bf);
	}
	
	/**
	 * @param doc
	 * @throws IOException
	 */
	private static void findKeywords(Document doc) throws IOException{
		//Document doc = Jsoup.connect(url).get();
		Elements metas = doc.getElementsByTag("meta");
		String keywords = null;
		for(Element meta:metas){
			if(meta.attr("name").equals("keywords")){
			    keywords = meta.attr("content");
				//System.out.println(keywords);
			    bf.append(keywords+" ");
			}	
		}
		if(keywords==null){
			//outputstream.write(("keywords->>>>>"+"\n\n").getBytes("UTF-8"));
		}
	}
	
	/**
	 * @param body
	 * @param tag
	 * @throws IOException
	 */
	private static void findContent(Element body, String tag) throws IOException{
		if(tag.equalsIgnoreCase("p")){
			Elements paragraphs = body.getElementsByTag("p");
			if(paragraphs.isEmpty()){
				//System.out.println("no such kind of content!<p>\n");
			}else{
				for (Element para : paragraphs) {
					String outtext = para.text();
					bf.append(outtext + " ");
				}
			}
		}else if(filter.containsKey(tag)){
			Elements contents = body.select(filter.get(tag));
			if(contents.isEmpty()){
			//	System.out.println("no such kind of content!<div id =content>\n");
			}else{
				for (Element content : contents){
					String outtext = content.text();
					bf.append(outtext + " ");
				}
			}
		}
	}
}
