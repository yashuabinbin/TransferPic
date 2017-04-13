package com.pic.main;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by lbb on 2017/4/12.
 */
public class DoTransfer {

    private static String rootPath = "F:/bak/pic";
    private static String targetPath = "F:/pic";

    public static void main(String[] args) {
        recursionFiles(new File(rootPath));
    }


    /**
     * 遍历所有文件
     *
     * @param file
     */
    public static void recursionFiles(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                recursionFiles(f);
            }
        } else {
            if (file.getName().endsWith(".JPG")
                    || file.getName().endsWith(".jpg")
                    || file.getName().endsWith(".PNG")
                    || file.getName().endsWith(".png"))
                System.out.println("start transfering file ==== " + file.getName());
                transfer(file);
        }
    }

    /**
     * 转移文件操作
     *
     * @param sourceFile
     */
    private static void transfer(File sourceFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel in = null;
        FileChannel out = null;

        try {
            File file = new File(targetPath + "/" + sourceFile.getName());
            if(!file.exists()) {
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
