package my.lucene;


import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;



/**
 * @author 鏇惧垰
 *
 */
public class TestIndexSearch
{

	/**
	 * 
	 */
	public TestIndexSearch()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws LockObtainFailedException
	 * @throws CorruptIndexException
	 */
	public static void main(String[] args) throws  IOException
	{
		// 1 open directory

		//String path = "c:/test";
		//File dir = new File(path);
		
		RAMDirectory ram = new RAMDirectory();
		// dir = FSDirectory.open(File);

		// 2 instance writer
		IndexWriter wr1 = new IndexWriter(ram, new StandardAnalyzer(), 
				true);

		
		// 3 add document
		/*for (int i = 0; i < 2; i++)
		{
			Document doc1 = new Document();
			Field field1 = new Field("namea", "10002353", Field.Store.YES,
					Field.Index.TOKENIZED);
			Field field2 = new Field("nameb", "world dda" + i, Field.Store.YES,
					Field.Index.TOKENIZED);

			doc1.add(field1);
			doc1.add(field2);

			wr1.addDocument(doc1);
		}*/
		
		Document doc1 = new Document();
		Field field1 = new Field("namea", "周杰论", Field.Store.YES,
				Field.Index.UN_TOKENIZED);
		Field field2 = new Field("nameb", "world" , Field.Store.YES,
				Field.Index.TOKENIZED);

		doc1.add(field1);
		doc1.add(field2);

		wr1.addDocument(doc1);
	
		// 4 close
		wr1.optimize();
		wr1.close();
		wr1 = null;

		
		// 1 get a query
		Query q1 = new TermQuery(new Term("namea", "10002353"));
		q1.setBoost(111);
		Query q2 = new TermQuery(new Term("nameb", "zhang san"));
		
		Query q3 = new TermQuery(new Term("namea", "10002354"));
		//
		
		BooleanQuery bq = new BooleanQuery();
		bq.add(q1, Occur.SHOULD);
		bq.add(q2, Occur.SHOULD);
		
		bq.add(q3, Occur.SHOULD);
		
		Query query = new FuzzyQuery(new Term("namea", "周杰伦"));
		
		/*PhraseQuery
		PhraseQuery query = new PhraseQuery();
		query.add(new Term("nameb", "中"));

		query.add(new Term("nameb", "兴"));
		*/
		


		// q1 = new PrefixQuery();

		// 2 instance a searcher and search
		IndexSearcher sear = new IndexSearcher(ram);
		/*Sort sort = new Sort();  
        SortField sortField = new SortField("namea", SortField.STRING, true);  
        sort.setSort(sortField);  */
        
		Hits topDocs = sear.search(query);

		// 3 get result
		int totleHits = topDocs.length();
		for (int i=0;i<totleHits;i++)
		{
			
			Document document = topDocs.doc(i);;
			System.out.println(topDocs.score(i));
			System.out.println(document);
		}

		// 4 close searcher
		sear.close();
		sear = null;

	}


}
