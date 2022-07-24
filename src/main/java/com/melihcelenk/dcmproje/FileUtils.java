package com.melihcelenk.dcmproje;

import java.io.File;
import java.util.Scanner;

public class FileUtils {
    public static void createFolder(String path){
        File theDir = new File(path);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    }
}
