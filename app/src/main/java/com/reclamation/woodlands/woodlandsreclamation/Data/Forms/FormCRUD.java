package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormAdapter;

import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public interface FormCRUD {
    List<Form> getAllForms();
    void createForm(Form form);
    void deleteForm(Form form);
    void updateForm(Form form);

    FormAdapter getAdapter();

}
