package com.lucene.demo;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexMain {

    public static void main(String[] args) throws Exception {
        
        File indexDirectory = new File("index");
        Directory directory = FSDirectory.open(indexDirectory.toPath());
        Map<String, Analyzer> analyzerPerField = new HashMap<>();
        analyzerPerField.put("name", new MyAnalyzer());
        analyzerPerField.put("description", new MyAnalyzer());
        PerFieldAnalyzerWrapper wrapper = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), analyzerPerField);
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(wrapper));

        List<Person> personList = Arrays.asList(
            new Person("id0", "jaegoo cho", "나는 안녕", 23),
            new Person("id1", "jaegoo", "나는 안녕", 25),
            new Person("id3", "goo jho", "나는 안녕", 28),
            new Person("id4", "jaehyuk jho", "나는 안녕", 32),
            new Person("id5", "hyein woo", "나는 안녕", 58),
            new Person("id6", "cho", "나는 안녕", 23)
        );
        
        for (Person person : personList) {
            Term term = new Term("id", person.getId());
            Document document = new Document();
            document.add(new StringField("id", person.getId(), Field.Store.YES));
            document.add(new TextField("name", person.getName(), Field.Store.YES));
            document.add(new TextField("description", person.getDescription(), Field.Store.YES));
            document.add(new LongPoint("age", person.getAge()));
            indexWriter.updateDocument(term, document);
        }

        indexWriter.commit();
        indexWriter.close();
    }
}