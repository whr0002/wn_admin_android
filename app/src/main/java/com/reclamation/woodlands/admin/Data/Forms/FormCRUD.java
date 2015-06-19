package com.reclamation.woodlands.admin.Data.Forms;

import com.reclamation.woodlands.admin.Adapter.FormAdapter;

import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public interface FormCRUD {
    List<SiteForm> getAllForms();
    void createForm(SiteForm siteForm);
    void deleteForm(SiteForm siteForm);
    void updateForm(SiteForm siteForm);

    FormAdapter getAdapter();

}
