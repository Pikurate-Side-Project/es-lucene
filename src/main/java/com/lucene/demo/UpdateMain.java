package com.lucene.demo;

import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class UpdateMain {
    public static void main(String[] args) throws Exception {
        
        File indexDirectory = new File("index");
        Directory directory = FSDirectory.open(indexDirectory.toPath());
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));

        Term term = new Term("id", "id2");
        indexWriter.deleteDocuments(term);

        indexWriter.commit();
        indexWriter.close();
    }
}
