package main.java;


public class QCard {
    private String title;
    private String description;

    public QCard(String t, String d)
    {
        this.title = t;
        this.description = d;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public void setDescription(String d){
        this.description = d;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String toString()
    {
        return title;
    }
}
