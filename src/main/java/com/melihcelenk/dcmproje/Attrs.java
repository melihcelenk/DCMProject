package com.melihcelenk.dcmproje;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Attrs {
    public static void areValuesEmpty(Attributes attrs) {
        if (attrs.getString(Tag.MediaStorageSOPClassUID) == null || attrs.getString(Tag.MediaStorageSOPClassUID) == "") {
            System.out.println("MediaStorageSOPClassUID boş");
        }
        if (attrs.getString(Tag.MediaStorageSOPInstanceUID) == null || attrs.getString(Tag.MediaStorageSOPInstanceUID) == "") {
            System.out.println("MediaStorageSOPInstanceUID boş");
        }
        if (attrs.getString(Tag.SOPClassUID) == null || attrs.getString(Tag.SOPClassUID) == "") {
            System.out.println("SOPClassUID boş");
        }
        if (attrs.getString(Tag.SOPInstanceUID) == null || attrs.getString(Tag.SOPInstanceUID) == "") {
            System.out.println("SOPInstanceUID boş");
        }
        if (attrs.getString(Tag.StudyDate) == null || attrs.getString(Tag.StudyDate) == "") {
            System.out.println("StudyDate boş");
        }
        if (attrs.getString(Tag.SeriesDate) == null || attrs.getString(Tag.SeriesDate) == "") {
            System.out.println("SeriesDate boş");
        }
        if (attrs.getString(Tag.AcquisitionDate) == null || attrs.getString(Tag.AcquisitionDate) == "") {
            System.out.println("AcquisitionDate boş");
        }
        if (attrs.getString(Tag.StudyTime) == null || attrs.getString(Tag.StudyTime) == "") {
            System.out.println("StudyTime boş");
        }
        if (attrs.getString(Tag.SeriesDate) == null || attrs.getString(Tag.SeriesDate) == "") {
            System.out.println("SeriesDate boş");
        }

        if (attrs.getString(Tag.AcquisitionTime) == null || attrs.getString(Tag.AcquisitionTime) == "") {
            System.out.println("AcquisitionTime boş");
        }
        if (attrs.getString(Tag.AccessionNumber) == null || attrs.getString(Tag.AccessionNumber) == "") {
            System.out.println("AccessionNumber boş");
        }
        if (attrs.getString(Tag.Modality) == null || attrs.getString(Tag.Modality) == "") {
            System.out.println("Modality boş");
        }
        if (attrs.getString(Tag.Manufacturer) == null || attrs.getString(Tag.Manufacturer) == "") {
            System.out.println("Manufacturer boş");
        }
        if (attrs.getString(Tag.StudyDescription) == null || attrs.getString(Tag.StudyDescription) == "") {
            System.out.println("StudyDescription boş");
        }
        if (attrs.getString(Tag.SeriesDescription) == null || attrs.getString(Tag.SeriesDescription) == "") {
            System.out.println("SeriesDescription boş");
        }
        if (attrs.getString(Tag.PatientName) == null || attrs.getString(Tag.PatientName) == "") {
            System.out.println("PatientName boş");
        }
        if (attrs.getString(Tag.PatientID) == null || attrs.getString(Tag.PatientID) == "") {
            System.out.println("PatientID boş");
        }
        if (attrs.getString(Tag.PatientBirthDate) == null || attrs.getString(Tag.PatientBirthDate) == "") {
            System.out.println("PatientBirthDate boş");
        }
        if (attrs.getString(Tag.PatientSex) == null || attrs.getString(Tag.PatientSex) == "") {
            System.out.println("PatientSex boş");
        }
        if (attrs.getString(Tag.PatientAge) == null || attrs.getString(Tag.PatientAge) == "") {
            System.out.println("PatientAge boş");
        }

        if (attrs.getString(Tag.StudyInstanceUID) == null || attrs.getString(Tag.StudyInstanceUID) == "") {
            System.out.println("StudyInstanceUID boş");
        }
        if (attrs.getString(Tag.SeriesInstanceUID) == null || attrs.getString(Tag.SeriesInstanceUID) == "") {
            System.out.println("SeriesInstanceUID boş");
        }
        if (attrs.getString(Tag.StudyID) == null || attrs.getString(Tag.StudyID) == "") {
            System.out.println("StudyID boş");
        }
        if (attrs.getString(Tag.SeriesNumber) == null || attrs.getString(Tag.SeriesNumber) == "") {
            System.out.println("SeriesNumber boş");
        }
        if (attrs.getString(Tag.AcquisitionNumber) == null || attrs.getString(Tag.AcquisitionNumber) == "") {
            System.out.println("AcquisitionNumber boş");
        }
        if (attrs.getString(Tag.InstanceNumber) == null || attrs.getString(Tag.InstanceNumber) == "") {
            System.out.println("InstanceNumber boş");
        }
    }

    public static void changeValues(Attributes attrs, String dateToday, String timeToday, int patientID, String pathologyName) {
        attrs.setString(Tag.StudyDescription, VR.LO, "Hemorrhage-Test-" + pathologyName); // -EDH, easy
        //attrs.setString(Tag.SeriesDescription, VR.LO, "Non-Contrast CT");

        attrs.setString(Tag.MediaStorageSOPClassUID, VR.UI, attrs.getString(Tag.SOPClassUID));
        attrs.setString(Tag.MediaStorageSOPInstanceUID, VR.UI, attrs.getString(Tag.SOPInstanceUID));

        attrs.setString(Tag.AcquisitionDate, VR.DA, dateToday);
        attrs.setString(Tag.AcquisitionTime, VR.TM, timeToday);

        attrs.setString(Tag.StudyDate, VR.DA, dateToday);
        attrs.setString(Tag.StudyTime, VR.TM, timeToday + "000"); // Format farklı StudyTime: 181011.629388

        attrs.setString(Tag.PatientID, VR.LO, patientID + "");
        attrs.setString(Tag.PatientName, VR.PN, "Patient-" + patientID + "");
        attrs.setString(Tag.AccessionNumber, VR.LO, patientID + "");

        attrs.setString(Tag.Manufacturer, VR.LO, "GE");

        attrs.setString(Tag.PatientBirthDate, VR.DA, "19670501");
        attrs.setString(Tag.PatientSex, VR.CS, "M");
        attrs.setString(Tag.PatientAge, VR.AS, "055Y");

        // attrs.setString(Tag.SeriesNumber, VR.IS, "1");

        attrs.setString(Tag.AcquisitionNumber, VR.IS, "1");

        attrs.setString(Tag.SliceThickness, VR.DS, "5");

        System.out.println("-->" + attrs.getString(Tag.StudyDescription));
    }

    public static void study(String pathologyName, String sourcePathStr, int number) {
        String todayDate = DateUtils.getTodayDate();
        String todayTime = DateUtils.getTodayTime();

        sourcePathStr+="\\";


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

                System.out.println(s);
                //Attrs.areValuesEmpty(attrs);
                Attrs.changeValues(attrs, todayDate, todayTime, number, pathologyName);

                // NEW FILE
                //String destinationPathStr = "D:\\Users\\melih\\Documents\\HEVI\\Yeni Veriler\\Patient" + number + "\\";
                String destinationPathStr = "D:\\Users\\melih\\Documents\\HEVI\\Yeni Veriler-ge-demo\\Patient" + number + "\\";
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

