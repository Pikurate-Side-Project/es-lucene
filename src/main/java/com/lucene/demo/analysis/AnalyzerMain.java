package com.lucene.demo.analysis;

import java.io.IOException;
import java.io.StringReader;

import com.lucene.demo.MyAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

public class AnalyzerMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Test");
        String term = "조재혁재구 조재구";

        MyAnalyzer analyzer = new MyAnalyzer();
        TokenStream ts = analyzer.tokenStream("test", new StringReader(term));
        OffsetAttribute offsetAttr = ts.addAttribute(OffsetAttribute.class);
        CharTermAttribute attr = ts.addAttribute(CharTermAttribute.class);

        try {
            ts.reset();
            while (ts.incrementToken()) {
                // System.out.println("token: " + ts.reflectAsString(true));
                System.out.println("token: " + attr.toString());
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
