package com.pic.main;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lbb on 2017/4/12.
 */
public class QuChong {

    private static final String filePath = "F:/pic";
    private static Map<String, FileInfo> map = new HashMap<>();
    private static List<File> delFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File picFile = new File(filePath);

        File[] files = picFile.listFiles();
        for (File file : files) {
            String picMd5 = Md5Utils.getFileMD5String(file);

            FileInfo fileInfo = map.get(picMd5);
            if (fileInfo == null) {
                fileInfo = new FileInfo();
                fileInfo.setNum(1);
                fileInfo.setPicLen(file.length());
                fileInfo.setPics(new ArrayList<File>() {{add(file);}});
                fileInfo.setPicMd5(Md5Utils.getFileMD5String(file));
                map.put(picMd5, fileInfo);
            } else {
                fileInfo.getPics().add(file);
                fileInfo.setNum(fileInfo.getNum() + 1);
            }
        }

        System.out.println("=================================");
        System.out.println("=================================");
        System.out.println("=================================");
        System.out.println("=================================");

        for (Map.Entry<String, FileInfo> entry : map.entrySet()) {
            FileInfo fileInfo = entry.getValue();
//            if (fileInfo.getNum() > 1) {
//                for (int i = 0; i < fileInfo.getPics().size(); i++) {
//                    File file = fileInfo.getPics().get(i);
//                    if (i != 0) {
//                        delFiles.add(file);
//                    }
//                }
//            } else if (fileInfo.getNum() == 1) {
//                System.out.println(fileInfo.getPics().get(0).getName());
//                transfer(fileInfo.getPics().get(0));
//            }

            System.out.println(fileInfo.getPics().get(0).getName());
            transfer(fileInfo.getPics().get(0));
        }

//        map.clear();
//
//        for (File file : delFiles) {
//            System.out.println(file.getName());
//            System.out.println(file.delete());
//        }
    }


    public static void transfer(File sourceFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel in = null;
        FileChannel out = null;

        try {
            File file = new File("F:/pic_bak/" + sourceFile.getName());
            if (!file.exists()) {
                file.createNewFile();
            } else {
                System.out.println("existed ==== " + file.getName());
            }

            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(file);

            in = fis.getChannel();
            out = fos.getChannel();

            in.transferTo(0, in.size(), out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
