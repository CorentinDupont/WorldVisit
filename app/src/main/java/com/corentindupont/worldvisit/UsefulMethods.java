package com.corentindupont.worldvisit;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by cdupont on 09/06/2017.
 */

public class UsefulMethods {
    public static String dateToString(Date date, String format){
        String dateString = null;
        if(date!=null){
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.FRANCE);
            dateString=dateFormat.format(date);
        }

        return dateString;
    }

    public static Date stringToDate(String dateString, String format){
        Date date=null;
        if(dateString!=null){
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.FRANCE);
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        return date;
    }

    public static File[] getSavedFiles(){
        File exportDir = new File(Environment.getExternalStorageDirectory().getPath()+"/visite/export/");
        return exportDir.listFiles();
    }

    public static List<Integer> getSavedFilesIDs(){
        List<Integer> listSavedFilesIDs = new ArrayList<>();
        File exportDir = new File(Environment.getExternalStorageDirectory().getPath()+"/visite/export/");
        File[] listSavedFiles = exportDir.listFiles();
        if(listSavedFiles != null){
            for(File savedFile : listSavedFiles){
                Log.e("getIDS","name : "+savedFile.getName());
                String[] namePart = savedFile.getName().split("\\.");
                listSavedFilesIDs.add(Integer.parseInt(namePart[0]));
            }
            Collections.sort(listSavedFilesIDs);
        }
        return listSavedFilesIDs;
    }

    public static void deleteAllSavedFiles() throws IOException {
        File exportDir = new File(Environment.getExternalStorageDirectory().getPath()+"/visite/export/");

        for(File savedFile : exportDir.listFiles()){
            Log.e("deleteExportFile", "file : " + savedFile.getName());
            savedFile.delete();
            if (savedFile.exists()) {
                savedFile.getCanonicalFile().delete();

            }
        }
    }
    public static void deleteSavedFileByID(int id_visite) throws IOException {
        File saveFile = new File(Environment.getExternalStorageDirectory().getPath()+"/visite/export/"+id_visite+".xml");
        saveFile.delete();
    }
}
