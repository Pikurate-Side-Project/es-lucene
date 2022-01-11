package com.lucene.demo;

import java.io.File;
import java.io.IOException;
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
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class PersonIndexer {
    
    private IndexWriter indexWriter;
    private IndexSearcher indexSearcher;

    public PersonIndexer(String indexDirectoryPath) throws IOException {
        File indexDirectory = new File(indexDirectoryPath);
        Directory directory = FSDirectory.open(indexDirectory.toPath());
        Map<String, Analyzer> analyzerPerField = new HashMap<>();
        analyzerPerField.put("name", new MyAnalyzer());
        analyzerPerField.put("description", new MyAnalyzer());
        PerFieldAnalyzerWrapper wrapper = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), analyzerPerField);

        indexWriter = new IndexWriter(directory, new IndexWriterConfig(wrapper));
    }

    public void close() throws CorruptIndexException, IOException {
        indexWriter.close();
    }

    public void addDocuments(List<Person> personList) throws IOException {
        for (Person person: personList) {
            Term term = new Term("id", person.getId());
            Document document = new Document();
            document.add(new StringField("id", person.getId(), Field.Store.YES));
            document.add(new TextField("name", person.getName(), Field.Store.YES));
            document.add(new StringField("description", person.getDescription(), Field.Store.YES));
            document.add(new LongPoint("age", person.getAge()));
            indexWriter.updateDocument(term, document);
        }
        indexWriter.commit();
    }

    public IndexSearcher setIndexSearcher(String indexDirectoryPath) throws IOException {
        File indexDirectory = new File(indexDirectoryPath);
        Directory directory = FSDirectory.open(indexDirectory.toPath());
        indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        return indexSearcher;
    }
}
