package com.lucene.demo.analysis;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

public class AnalyzerMain {
    public static void main(String[] args) throws IOException {

        String term = "Hello World";

        StandardAnalyzer analyzer = new StandardAnalyzer();
        TokenStream ts = analyzer.tokenStream("test", new StringReader(term));
        OffsetAttribute offsetAttr = ts.addAttribute(OffsetAttribute.class);

        try {
            ts.reset();
            while (ts.incrementToken()) {
                System.out.println("token: " + ts.reflectAsString(true));

                System.out.println("token start offset: " + offsetAttr.startOffset());
                System.out.println("  token end offset: " + offsetAttr.endOffset());
            }
            ts.end();
        } finally {
            ts.close();
            analyzer.close();
        }
    }
}
