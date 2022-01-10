package com.lucene.demo.analysis;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class FilterMain {
    public static void main(String[] args) throws IOException {
        String term = "Hello Everyone";

        StandardTokenizer tokenizer = new StandardTokenizer();
        tokenizer.setReader(new StringReader(term));
        LowerCaseFilter filter = new LowerCaseFilter(tokenizer);
        CharTermAttribute attr = filter.addAttribute(CharTermAttribute.class);
        
        try {
            filter.reset();
            while (filter.incrementToken()) {
                System.out.println(attr.toString());
            }
        } finally {
            filter.end();
        }
        filter.close();
    }  
}
