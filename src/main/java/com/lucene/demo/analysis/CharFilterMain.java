package com.lucene.demo.analysis;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.charfilter.HTMLStripCharFilter;

public class CharFilterMain {
    public static void main(String[] args) throws IOException {
        StringReader term = new StringReader("<b>Hello</b> Everyone");

        char[] content = new char[1024 * 8];
        HTMLStripCharFilter charFilter = new HTMLStripCharFilter(term);
        try {
            int len = charFilter.read(content);
            System.out.println(new String(content, 0, len));
        } finally {
            charFilter.close();
        }
    }
}
