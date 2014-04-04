
package br.edu.ifsp.util;

import java.util.List;

public class Util {

    public static int returnIndex(String toIndex, String[] args) {  
        for (int i=0; i<args.length; i++) {  
            if (toIndex.equals(args[i] ) )   
                return i;  
        }  
        return -1;  
    } 
    
}
