package com.lucene.demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;

public class MyAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        NGramTokenizer src = new NGramTokenizer();
        TokenStream result = new LowerCaseFilter(src);
        return new TokenStreamComponents(src, result);
    }
}
