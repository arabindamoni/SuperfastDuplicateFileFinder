/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package duplicatefilefinder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author arabindamoni
 */
public class DuplicateFIleFinder {

    /**
     * @param args the command line arguments
     */
    
    private HashMap<String, ArrayList<String>> map ;
    
    public int getMapSize(){
        if(map == null){ 
            return 0;
        }
        else{
            return map.size();
        }
    }
    
    public void scan(File file){ 
                
     //   System.out.println(map.size() +"   "+ file.getAbsolutePath());
        
        //if file is a directry then recursively traverse it
        if(file.isDirectory()){
            File[] flist = file.listFiles();
            for( int i = 0 ; i < flist.length; i++){
                scan(flist[i]);
            }
        }
        else{
            //compares using filename and length and puts the matched files in a HashMap 'map'     
            
            String filedata = file.getName() + ":" +file.length();
            
            if(!map.containsKey(filedata)){
                ArrayList<String> arr = new ArrayList<>();
                arr.add(file.getAbsolutePath());
                map.put(filedata, arr);
            }
            else{                
                map.get(filedata).add(file.getAbsolutePath());
            }
        }                
    }       
    
    public HashMap<String, ArrayList<String>> findDuplicate(File file){
        map = new HashMap<>();
        scan(file);
        return map;
    }
    
    
    
    // returns size in more readable format
    private static String convertSize(int size){
        
        double KB = size/1024;
        if(KB < 1) return size+"B";
        double MB = KB/1024;
        if(MB < 1) return KB + "KB";
        double GB = MB/1024;
        if(GB < 1) return MB+"MB";
        return GB+"GB";                         
    }       
    
}
