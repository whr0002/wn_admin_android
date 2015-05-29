package com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisit;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.reclamation.woodlands.woodlandsreclamation.Activity.FormActivity;
import com.reclamation.woodlands.woodlandsreclamation.DB.DAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitProperties;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Uploader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Jimmy on 5/27/2015.
 */
public class SVUploader extends Uploader {


    public SVUploader(FormActivity a, int total, DAO dao, ProgressDialog progressDialog) {
        super(a, total, dao, progressDialog);
    }

    @Override
    public String getUrl() {
        return BASE_WEBSITE_URL + "SiteVisitSubmit";
    }

    @Override
    public RequestParams getParams(Form form) {
        SiteVisitForm f = (SiteVisitForm) form;
        RequestParams p = new RequestParams();

        photoDAO.open();
        List<Photo> photos = photoDAO.findPhotos(SiteVisitProperties.FORM_TYPE, f.ID, null);
        photoDAO.close();

        p.put("Username", userInfo.username);
        p.put("Password", userInfo.password);
        p.put("Group", userInfo.role);

        p.put("ReviewSiteID", f.SiteID);
        p.put("FacilityTypeName", f.FacilityType);
        p.put("Date", f.Date);

        p.put("RefusePF", getPassOrFail(f.RefusePF));
        p.put("RefuseComment", f.RefuseComment);
        p.put("DrainagePF", getPassOrFail(f.DrainagePF));
        p.put("DrainageComment", f.DrainageComment);
        p.put("RockGravelPF", getPassOrFail(f.RockGravelPF));
        p.put("RockGravelComment", f.RockGravelComment);
        p.put("BareGroundPF", getPassOrFail(f.BareGroundPF));
        p.put("BareGroundComment", f.BareGroundComment);
        p.put("SoilStabilityPF", getPassOrFail(f.SoilStabilityPF));
        p.put("SoilStabilityComment", f.SoilStabilityComment);
        p.put("ContoursPF", getPassOrFail(f.ContoursPF));
        p.put("ContoursComment", f.ContoursComment);
        p.put("CWDPF", getPassOrFail(f.CWDPF));
        p.put("CWDComment", f.CWDComment);
        p.put("ErosionPF", getPassOrFail(f.ErosionPF));
        p.put("ErosionComment", f.ErosionComment);
        p.put("SoilCharPF", getPassOrFail(f.SoilCharPF));
        p.put("SoilCharComment", f.SoilCharComment);
        p.put("TopsoilDepthPF", getPassOrFail(f.TopsoilDepthPF));
        p.put("TopsoilDepthComment", f.TopsoilDepthComment);
        p.put("RootingPF", getPassOrFail(f.RootingPF));
        p.put("RootingComment", f.RootingComment);
        p.put("WSDPF", getPassOrFail(f.WSDPF));
        p.put("WSDComment", f.WSDComment);
        p.put("TreeHealthPF", getPassOrFail(f.TreeHealthPF));
        p.put("TreeHealthComment", f.TreeHealthComment);
        p.put("WeedsInvasivesPF", getPassOrFail(f.WeedsInvasivesPF));
        p.put("WeedsInvasivesComment", f.WeedsInvasivesComment);
        p.put("NSCPF", getPassOrFail(f.NSCPF));
        p.put("NSCComment", f.NSCComment);
        p.put("LitterPF", getPassOrFail(f.LitterPF));
        p.put("LitterComment", f.LitterComment);

        p.put("Recommendation", f.Recommendation);


        // Attach photos
        if(photos != null){

            p.put("NumberOfImages", photos.size());
            for(int i=0;i<photos.size();i++){

                Photo photo = photos.get(i);
                String fileParamName = "Image"+i;
                String pathParamName = "Path"+i;
                String formTypeParamName = "FormType"+i;
                String descParamName = "Desc"+i;
                String classificationName = "Classification"+i;


                String localPath = photos.get(i).path;
                File file = new File(localPath);
                String realPath = getRealPath(file, userInfo.role);

                if(realPath != null){
                    p.put(pathParamName, realPath);
                    try {
                        p.put(fileParamName, file);
                    }catch (FileNotFoundException e){
                        Toast.makeText(mActivity,"Missing photos", Toast.LENGTH_LONG).show();
                        return null;
                    }

                    p.put(formTypeParamName, photo.formType);
                    p.put(descParamName, photo.description);
                    p.put(classificationName, photo.classification);
                }else{
                    Toast.makeText(mActivity,"Missing photos", Toast.LENGTH_LONG).show();
                    return null;
                }

            }





        }else{
            p.put("NumberOfImages", 0);
        }

        return p;
    }

    private String getRealPath(File file, String role){
        if(file.exists()){
            String filename = file.getName();
//            String fullPath = "";
//            if(role == null){
//                fullPath = BASE_STORAGE_URL+"unknown/"+filename;
//            }else if(role.toLowerCase().equals("super admin")){
//                fullPath = BASE_STORAGE_URL+"superadmin/"+filename;
//            }else{
//                fullPath = BASE_STORAGE_URL+role.toLowerCase()+"/"+filename;
//            }

            return filename;
        }


        return null;
    }

    private String getPassOrFail(int input){

        if(input == 1){
            return "Pass";
        }else if(input == 2){
            return "Fail";
        }else{
            return "";
        }

    }
}
