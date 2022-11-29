package com.melihcelenk.dcmproje.alandegistir;

import com.melihcelenk.dcmproje.Attrs;
import com.melihcelenk.dcmproje.DateUtils;
import com.melihcelenk.dcmproje.FileUtils;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class NewAttrs {
    public static void study(String sourcePathStr) {
        String todayDate = DateUtils.getTodayDate();
        String todayTime = DateUtils.getTodayTime();

        sourcePathStr += "\\";


        // String sourcePathStr = "D:\\Users\\melih\\Downloads\\BT KANAMA demo örnekler\\BT KANAMA\\Patient"+i+"\\";


        File dicomPath = new File(sourcePathStr);

        System.out.println("*********************************" + sourcePathStr + "****************************************");
        int length = new File(sourcePathStr).list().length;
        System.out.println("Dosya sayısı:" + length);
        for (String s : dicomPath.list()) {
            System.out.println(s);
        }

        for (String s : dicomPath.list()) {

            //File file = new File(dicomPath, "IM-" + String.format("%04d", j) + ".dcm");

            File file = new File(dicomPath, s);

            //LOG.info(String.valueOf(reader.getMetaData().getInt(Tag.DiffusionBValue, 0)));
            DicomInputStream dis = null;
            try {
                dis = new DicomInputStream(file);
                Attributes fmi = dis.readFileMetaInformation();
                Attributes attrs = dis.readDataset(-1, -1);

                System.out.println(":::" + s);

                attrs.setSpecificCharacterSet("ISO_IR 148");
                attrs.setString(Tag.StudyDescription, VR.LO, "BT, Beyin (Kontrastsız)"); // -EDH, easy

                // NEW FILE
                //String destinationPathStr = "D:\\Users\\melih\\Documents\\HEVI\\Yeni Veriler\\Patient" + number + "\\";
                //String destinationPathStr = "D:\\HEVI\\VERİ\\yeni-mri\\Patient" + number + "\\";
                String destinationPathStr = "D:\\HEVI\\DOSYALAR\\BUGLAR\\yeni-281";

                FileUtils.createFolder(destinationPathStr);
                File newFile = new File(destinationPathStr, s);
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
