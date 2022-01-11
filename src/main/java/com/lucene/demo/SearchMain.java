package com.lucene.demo;

import java.io.File;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SearchMain {
    
    public static void main(String[] args) throws Exception {
        // QueryParser queryParser = new QueryParser("name", new WhitespaceAnalyzer());
        // Query query = queryParser.parse("name:조재");

        File indexDirectory = new File("index");
        Directory directory = FSDirectory.open(indexDirectory.toPath());
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        Query query = new PrefixQuery(new Term("name", "jae"));
        // Query query = queryParser.parse("jaegoo jho");
        // Query ageQuery = LongPoint.newSetQuery("age", 25);
        // BooleanQuery.Builder booleanQueryBuilder = new BooleanQuery.Builder();
        // booleanQueryBuilder.add(nameQuery, BooleanClause.Occur.SHOULD);
        // booleanQueryBuilder.add(ageQuery, BooleanClause.Occur.SHOULD);
        // TopDocs topDocs = indexSearcher.search(booleanQueryBuilder.build(), 10);
        TopDocs topDocs = indexSearcher.search(query, 10);

        long searchCount = topDocs.totalHits.value;
        System.out.println("cout: " + searchCount);

        for (ScoreDoc scoreDoc: topDocs.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(" - name: " + document.get("name") + "(" + document.get("id") + ")");
        }
    }
}
