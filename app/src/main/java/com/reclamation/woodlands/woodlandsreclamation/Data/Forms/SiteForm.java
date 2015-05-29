package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import java.util.ArrayList;

/**
 * Created by Jimmy on 5/8/2015.
 */
public abstract class SiteForm extends Form{

    public int ID;
    public String SiteID;
    public String Date;
    public String TimeStamp;
    public String FacilityType;

    public String Status;
    public String Message;
    public ArrayList<String> Messages;

}
