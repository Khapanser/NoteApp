package main.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class QCard2 extends QCard {
    private int level;
    private Date createDate;
    private boolean done;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd - hh:mm");


    public QCard2 (String title,  String description)
    {
        super();
        this.done = false;
        this.createDate = new Date();
        System.out.println("createDate:" + sdf.format(createDate));
        this.level = 2;
        super.setTitle("["+sdf.format(createDate)+"] - "+title);
        super.setDescription(description);
    }

    public QCard2 () {
        super();
        this.done = false;
        this.createDate = new Date();
        System.out.println("QCard2 create date:" + sdf.format(createDate));
        this.level = 2;
        super.setTitle("["+sdf.format(createDate)+"] -  Title");
    }


    public void setLevel(int lvl){ this.level = lvl; }

    public int getLevel (){ return this.level; }

    public Date getCreateDate(){ return this.createDate; }

    public String getFormatedCreateDate(){ return sdf.format(this.createDate); }

    public void setCreateDate(Date date){ this.createDate=date; }

    public boolean isDone () { return this.done; }

    public void setDone() {this.done = true; }

    public void setUndone() {this.done = false; }

}
