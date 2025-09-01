package org.example;

public class Tags {
    private int id;
    private String name;

    public Tags(){}
    public Tags(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){return this.id;}
    public int setId(int id){return this.id = id;}
    public String getName(){return this.name;}
    public String setName(String name){return this.name = name;}

}
