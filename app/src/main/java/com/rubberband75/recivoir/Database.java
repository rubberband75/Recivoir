package com.rubberband75.recivoir;

import java.lang.reflect.Array;
import java.util.ArrayList;
import com.rubberband75.recivoir.TestDocument;

public class Database {

    static public String test() {
        return "database test";
    }

    static public String getName(String id) {
        return "First Last Name";
    }

    static public ArrayList<TestDocument> getDocuments(){
        ArrayList<TestDocument> docs = new ArrayList<TestDocument>();

        TestDocument doc1 = new TestDocument();
        TestDocument doc2 = new TestDocument();
        TestDocument doc3 = new TestDocument();

        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);

        return docs;
    }

    static public ArrayList<User> getUsers() {
        ArrayList<User> people = new ArrayList<>();

        User p1 = new User();
        User p2 = new User();
        User p3 = new User();

        people.add(p1);
        people.add(p2);
        people.add(p3);

        return people;
    }

    static public TestDocument getRecipe() {
        return new TestDocument();
    }

    static public User getUser() {
        return new User();
    }

}
