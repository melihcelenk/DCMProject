package com.melihcelenk.dcmproje;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.util.UIDUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/*
 * @Author : Melih Çelenk
 * @Description: Dicom projesi
 * To get the data types (VR.DA etc) https://www.dcm4che.org/docs/dcm4che-2.0.17-apidocs/org/dcm4che2/data/Tag.html
 */
public class Main {
    public static void main(String[] args) throws IOException {

        for(int i=1; i<=15; i++){
            String sourcePathStr = "D:\\Users\\melih\\Downloads\\BT KANAMA demo örnekler\\BT KANAMA\\Patient"+i+"\\";
            File dicomPath = new File(sourcePathStr);

            System.out.println("*********************************"+sourcePathStr+"****************************************");
            int length = new File(sourcePathStr).list().length;
            System.out.println("Dosya sayısı:" + length);

            for(int j=1; j<=length; j++){
                File file = new File(dicomPath, "IM-"+String.format("%04d", j)+".dcm");

                //LOG.info(String.valueOf(reader.getMetaData().getInt(Tag.DiffusionBValue, 0)));
                DicomInputStream dis = null;
                try {
                    dis = new DicomInputStream(file);
                    Attributes fmi = dis.readFileMetaInformation();
                    Attributes attrs = dis.readDataset(-1, -1);

                    System.out.println("--------IM-"+String.format("%04d", j)+".dcm-------------------------------------");
                    //Attrs.areValuesEmpty(attrs);
                    Attrs.changeValues(attrs, DateUtils.getTodayDate(), DateUtils.getTodayTime(),i);

                    // NEW FILE
                    String destinationPathStr = "D:\\Users\\melih\\Documents\\HEVI\\Yeni Veriler\\Patient"+i+"\\";
                    FileUtils.createFolder(destinationPathStr);
                    File newFile = new File(destinationPathStr, "IM-"+String.format("%04d", j)+".dcm");
                    DicomOutputStream dos = new DicomOutputStream(newFile);

                    dos.writeDataset(fmi, attrs);

                    dos.flush();
                    dos.close();
                    if (Objects.nonNull(dis)) dis.close();

                } catch (IOException e) {
                    //throw new RuntimeException(e);
                }


            }
        }






    }
}
