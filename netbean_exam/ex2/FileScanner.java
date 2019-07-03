/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2;

import java.io.File;

/**
 *
 * @author bit
 */
public class FileScanner {
    private String path;
    private File[] fList;
    public FileScanner() {
        path = "";
        fList = null;
    }
    public String FileScan(String path){
        StringBuffer sb = new StringBuffer();
        this.path = path;
        
        try {
            File f = new File(this.path);
            fList = f.listFiles();
            for(File e: fList){
                if (e.isDirectory()) {
                    sb.append("[Directory]=>:" + e.getAbsolutePath() + "\n");
                } else {
                    sb.append("[File]=>:" + e.getAbsolutePath() + "\n");
                }
            }
        } catch (Exception e) {
            return "잘못된 경로";
        }
        return sb.toString();
    }
    
}
