package com.melihcelenk.dcmproje.alandegistir;

public class Degistir {
    public static void main(String[] args) {

        String sourcePathStr = "D:\\HEVI\\DOSYALAR\\BUGLAR\\281";

        // Dicom dosyasındaki bazı alanları değiştirmek için (null olup olmadığına bakılmıyor)
        NewAttrs.study(sourcePathStr);

    }
}
