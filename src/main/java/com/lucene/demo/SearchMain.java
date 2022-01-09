package com.lucene.demo;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SearchMain {
    
    public static void main(String[] args) throws Exception {
        File indexDirectory = new File("index");
        Directory directory = FSDirectory.open(indexDirectory.toPath());
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

        TermQuery nameQuery = new TermQuery(new Term("name", "우혜인"));
        Query ageQuery = LongPoint.newSetQuery("age", 25);

        BooleanQuery.Builder booleanQueryBuilder = new BooleanQuery.Builder();
        booleanQueryBuilder.add(nameQuery, BooleanClause.Occur.MUST);
        booleanQueryBuilder.add(ageQuery, BooleanClause.Occur.MUST);

        TopDocs topDocs = indexSearcher.search(booleanQueryBuilder.build(), 10);
        System.out.println("cout: " + topDocs.totalHits.value);
        long searchCount = topDocs.totalHits.value;
        for (int index = 0; index < searchCount; index++) {
            Document documnet = indexSearcher.doc(topDocs.scoreDocs[index].doc);
            System.out.println("\t - id: " + documnet.get("id"));
        }
    }
}
