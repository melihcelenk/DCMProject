package com.melihcelenk.dcmproje;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/*
 * @Author : Melih Çelenk
 * @Description: Dicom projesi
 * To get the data types (VR.DA etc) https://www.dcm4che.org/docs/dcm4che-2.0.17-apidocs/org/dcm4che2/data/Tag.html
 */
public class Main {
    public static void main(String[] args) throws IOException {

        //String sourcePathStr = "D:\\Users\\melih\\Downloads\\BT KANAMA demo örnekler\\BT KANAMA\\";
        String sourcePathStr = "D:\\HEVI\\VERİ\\mri\\";
        List<String> fileNames = new ArrayList<String>();


        try (Stream<Path> walk = Files.walk(Paths.get(sourcePathStr))) {
            AtomicInteger patientID = new AtomicInteger(18);
            walk.filter(Files::isDirectory)
                    .forEach(path -> {
                        fileNames.add(path.toString());
                        //System.out.println( path.toString());
                        String pathStr = path.toString();
                        String separator = "\\";
                        String folderName[] = pathStr.split("mri\\\\");



                        try{
                            System.out.println(folderName[1]);
                            String baseFolder[] = folderName[1].split(("\\\\"));
                            System.out.println(baseFolder.length);


                            // şu an hedef klasör yapısı bu şekilde:
                            // patientID-patientName/studyInstanceUID-studyDescription/seriesInstanceUID-seriesDescription/instanceUID.dcm
                            // Bir sonraki veri için kaynak klasörler yukarıdaki gibi düzenlenirse baseFolder[1] yerine baseFolder[2] kullanılması gerekir

                            if(baseFolder.length>1){
                                Attrs.study(pathStr, patientID.get(), baseFolder[1]); // Her bir ana klasörde 2 serie vardı. SeriesDescription bu klasörlerin isimlerinden alındı. Standart bir yapı olması için kaynak klasörler de aşağıdaki şekilde düzenlenebilir
                            }
                            else if(baseFolder.length==1){
                                patientID.getAndIncrement();
                            }

                        }catch(Exception e){

                        }


                    });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
