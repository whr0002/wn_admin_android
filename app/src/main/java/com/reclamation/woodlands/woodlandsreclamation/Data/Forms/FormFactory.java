package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

/**
 * Created by Jimmy on 5/8/2015.
 */
public class FormFactory {
    public Form getFrom(String formType){
        if(formType == null){
            return null;
        }

        if(formType.equalsIgnoreCase("FORM1")){
            return new Form1();
        }else if(formType.equalsIgnoreCase("FORM2")){
            return new Form2();
        }

        return null;
    }
}
