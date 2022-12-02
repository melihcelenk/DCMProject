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

        String sourcePathStr = "D:\\HEVI\\VERİ\\CTA data\\";
        List<String> fileNames = new ArrayList<String>();


        try (Stream<Path> walk = Files.walk(Paths.get(sourcePathStr))) {
            AtomicInteger patientID = new AtomicInteger(25);
            walk.filter(Files::isDirectory)
                    .forEach(path -> {
                        fileNames.add(path.toString());

                        String pathStr = path.toString();
                        System.out.println("->" + pathStr);

                        String folderName[] = pathStr.split("CTA data\\\\");



                        try{
                            System.out.println(folderName[1]);
                            String baseFolder[] = folderName[1].split(("\\\\"));
                            System.out.println(baseFolder.length);


                            // şu an hedef klasör yapısı bu şekilde:
                            // patientID-patientName/studyInstanceUID-studyDescription/seriesInstanceUID-seriesDescription/instanceUID.dcm
                            // Bir sonraki veri için kaynak klasörler yukarıdaki gibi düzenlenirse baseFolder[1] yerine baseFolder[2] kullanılması gerekir

                            if(baseFolder.length>1){

                            }
                            else if(baseFolder.length==1){
                                patientID.getAndIncrement();
                                Attrs.study(pathStr, patientID.get(), "CTA-LVO-Test-Series"); // Her bir ana klasörde 2 serie vardı. SeriesDescription bu klasörlerin isimlerinden alındı. Standart bir yapı olması için kaynak klasörler de aşağıdaki şekilde düzenlenebilir
                            }

                        }catch(Exception e){

                        }


                    });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
