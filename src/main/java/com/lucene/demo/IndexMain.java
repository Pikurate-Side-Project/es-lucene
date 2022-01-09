package com.lucene.demo;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexMain {

    public static void main(String[] args) throws Exception {
        
        File indexDirectory = new File("index");
        Directory directory = FSDirectory.open(indexDirectory.toPath());
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));

        List<Person> personList = Arrays.asList(
            new Person("id0", "조재구", 25),
            new Person("id1", "우혜인", 23),
            new Person("id2", "조재혁", 28)
        );
        
        for (Person person : personList) {
            Term term = new Term("ID", person.getId());
            Document document = new Document();
            document.add(new StringField("id", person.getId(), Field.Store.YES));
            document.add(new StringField("name", person.getName(), Field.Store.YES));
            document.add(new LongPoint("age", person.getAge()));
            indexWriter.updateDocument(term, document);
        }

        indexWriter.commit();
        indexWriter.close();
    }
}