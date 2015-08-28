package my.lucene3;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
  
public class LucencSortTest {  
  
    public static void main(String []args)throws Exception {  
        String [] ids = {"1","2","3","4","5"};  
        String [] bookName = {"java1 start","java2 begin","java3 in action","java4 web","ssh"};  
        String [] page = {"300","300","300","302","302"};  
        String [] price = {"89","99","70","60","120"};  
        Directory directory = new RAMDirectory();  
          
        StandardAnalyzer s = new StandardAnalyzer(Version.LUCENE_30);
        IndexWriter indexWriter = new IndexWriter(directory, s, MaxFieldLength.LIMITED);  
        for(int i = 0;i < ids.length;i++){  
            Document doc = new Document();  
            doc.add(new Field("id",ids[i],Field.Store.YES,Field.Index.NOT_ANALYZED));  
            doc.add(new Field("bookName",bookName[i],Field.Store.YES,Field.Index.NOT_ANALYZED));  
            doc.add(new Field("page",page[i],Field.Store.YES,Field.Index.NOT_ANALYZED));  
            doc.add(new Field("price",price[i],Field.Store.YES,Field.Index.NOT_ANALYZED));  
            indexWriter.addDocument(doc);  
        }  
		System.out.println("total:" + indexWriter.numDocs());
		indexWriter.close();
        
        Term term = new Term("bookName","java");  
        TermQuery q1 = new TermQuery(term);
        q1.setBoost(100000000);
        
        Term pageTerm = new Term("page","302");
        TermQuery q2 = new TermQuery(pageTerm);
        
        
        BooleanQuery query = new BooleanQuery();  
        query.add(q1, Occur.SHOULD);
        query.add(q2, Occur.SHOULD);

        IndexSearcher indexSearcher = new IndexSearcher(directory);  
        Sort sort = new Sort();  
        SortField sortField = new SortField("bookName", SortField.STRING, true);  
        sort.setSort(sortField);  
        
		TopDocs topDocs = indexSearcher.search(query, null, 10, sort);
        ScoreDoc [] scoreDoc = topDocs.scoreDocs;  
        for(int i=0;i<scoreDoc.length;i++){  
        	System.out.println(scoreDoc[i].score);
            Document d = indexSearcher.doc(scoreDoc[i].doc);  
            System.out.println(d);
            //System.out.println(d.get("bookName")+"\t\t"+d.get("page")+"\t\t"+d.get("price"));  
        }  
    }  
}  
