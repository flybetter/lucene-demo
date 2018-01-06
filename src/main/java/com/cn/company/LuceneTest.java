package com.cn.company;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;

/**
 * luceneTest class
 *
 * @author bingyu wu
 * @Date: 2018/1/5
 * @Time: 下午5:25
 */
public class LuceneTest {

    public static void main(String[] args)throws Exception {
        Article article = new Article(1, "Lucene全文检索框架",
                "Lucene如果信息检索系统在用户出了检索请求后再去网上找答案", "田守枝");

        Document doc = new Document();
        doc.add(new LongField("id", article.getId(), Field.Store.YES));
        doc.add(new StringField("author", article.getAuthor(), Field.Store.YES));
        doc.add(new TextField("title", article.getTitle(), Field.Store.YES));
        doc.add(new TextField("content", article.getContent(), Field.Store.YES));

        Directory directory= FSDirectory.open(new File("./indexDir/"));

        Analyzer analyzer=new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LATEST,analyzer);
        IndexWriter indexWriter=new IndexWriter(directory,indexWriterConfig);
        indexWriter.addDocument(doc);
        indexWriter.close();
    }
}
