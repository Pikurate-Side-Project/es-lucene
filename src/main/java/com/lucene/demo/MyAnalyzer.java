package com.lucene.demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class MyAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        StandardTokenizer src = new StandardTokenizer();
        TokenStream result = new LowerCaseFilter(src);
        result = new NGramTokenFilter(result, 1, 10, true);
        return new TokenStreamComponents(src, result);
    }
}
