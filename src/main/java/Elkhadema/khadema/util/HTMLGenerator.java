package Elkhadema.khadema.util;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Elkhadema.khadema.domain.Competance;
import Elkhadema.khadema.domain.Experience;
import Elkhadema.khadema.domain.Person;

public class HTMLGenerator {
    public static String firstModelCV(Person person ,String path,List<Competance> comp, List<Experience> experience){
        Document doc=new Document("");
        Element html=doc.appendElement("hmtl");
        Element head=html.appendElement("head");
        head.appendElement("title").text("test");
        Element body=html.appendElement("body");
        body.appendElement("h1").text("hello world");
        





        return doc.outerHtml();

    }
    public static void main(String[] args) {
        firstModelCV(null, "./aaa.html", null, null);
    }
}
