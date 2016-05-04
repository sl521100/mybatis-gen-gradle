package com.sl.tools;

import org.apache.tools.ant.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shilin on 2016/5/3.
 */
public class MymybatisTool {
    private static final String s1 = "/*<AUTOGEN--BEGIN>*/";
    private static final String s2 = "/*<AUTOGEN--END>*/";

    private static final String s3 = "<!--AUTOGEN-BEGIN-->";
    private static final String s4 = "<!--AUTOGEN-END-->";

    public static void main(String[] args) {
        String path = "";
        String targetPath = "";
        String flag = "";
        if (args.length > 2) {
            path = args[0];
            targetPath = args[1];
            flag = args[2];
        }
//test
//        path = "D:\\Programe\\java\\maven\\mybatis-generator-1.3.2-source\\mybatis-gen-gradle\\src\\main\\java\\com\\sl\\dao";
//        targetPath = "D:\\Programe\\java\\maven\\mybatis-generator-1.3.2-source\\mybatis-gen-gradle\\test\\lgha;" +
//                "D:\\Programe\\java\\maven\\mybatis-generator-1.3.2-source\\mybatis-gen-gradle\\test\\mybatis";
//        flag = "UPDATE";

        if (flag.equals("ADD")) {
            invokeAddComment(path);
        } else if (flag.equals("UPDATE")){
            invokeUpdateComment(path, targetPath);
        }

    }

    private static void invokeAddComment(String path) {
        List<File> listJava = new ArrayList<File>();
        List<File> listXml = new ArrayList<File>();

        String[] pathArr = path.split(";");
        for (String item : pathArr) {
            File dir = new File(item);
            getFileList(dir, ".java", listJava);
            getFileList(dir, ".xml", listXml);
        }


        String b1 = "(public[\\S\\s]*?\\{)";


        for (File item : listJava) {
            String str = readFileContent(item);
            boolean isWrite = false;
            if (0 < str.indexOf(s1)) continue;

            Matcher m = Pattern.compile(b1).matcher(str);
            if (m.find()) {
                str = m.replaceFirst(m.group() + "\n" + s1 + "\n");
                isWrite = true;
            }


            int last = str.lastIndexOf("}");
            if (0 <= last) {
                str = str.substring(0, last) + "\n" + s2 + "\n" + str.substring(last);
                isWrite = true;
            }
            if (isWrite) {
                writeFileContent(str, item);
                System.out.println("java文件加标识："+item.getAbsolutePath());
            }
        }

        for (File item : listXml) {
            String str = readFileContent(item);

            if (0 < str.indexOf(s3)) continue;

            boolean isWrite = false;
            Matcher m = Pattern.compile("(<mapper[\\s\\S]*?>)").matcher(str);
            if (m.find()) {
                str = m.replaceFirst(m.group() + "\n" + s3 + "\n");
                isWrite = true;
            }
            m = Pattern.compile("(</mapper[\\s\\S]*?>)").matcher(str);
            if (m.find()) {
                str = m.replaceFirst("\n" + s4 + "\n" + m.group());
                isWrite = true;
            }
            if (isWrite) {
                writeFileContent(str, item);
                System.out.println("xml文件加标识："+item.getAbsolutePath());
            }

        }
    }

    private static String getMatchString(String regex, String content) {
        Matcher m = Pattern.compile(regex).matcher(content);
        if (m.find()) {
            return m.group();
        }
        return "";
    }

    private static String getReplaceString(String regex, String content, String replaceContent) {
        Matcher m = Pattern.compile(regex).matcher(content);
        if (m.find()) {
            return m.replaceFirst(replaceContent);
        }
        return "";
    }

    private static void invokeUpdateComment(String path, String targetPath) {
        invokeAddComment(path);//先添加注释

        List<File> listTargetJava = new ArrayList<File>();
        List<File> listTargetXml = new ArrayList<File>();
        String[] pathArr = targetPath.split(";");
        for (String item : pathArr) {
            File dir = new File(item);
            getFileList(dir, ".java", listTargetJava);
            getFileList(dir, ".xml", listTargetXml);
        }


        List<File> listJava = new ArrayList<File>();
        List<File> listXml = new ArrayList<File>();
        File dir = new File(path);
        getFileList(dir, ".java", listJava);
        getFileList(dir, ".xml", listXml);


        String b1 = "(/\\*<AUTOGEN--BEGIN>\\*/[\\s\\S]*/\\*<AUTOGEN--END>\\*/)";
        String b2 = "(<!--AUTOGEN-BEGIN-->[\\s\\S]*<!--AUTOGEN-END-->)";

        for (File item : listJava) {
            String str = readFileContent(item);
            boolean isWrite = false;
            if (str.indexOf(s1) < 0) continue;

            for (File f : listTargetJava) {
                if (f.getName().equals(item.getName())) {

                    String findStr = getMatchString(b1, str);
                    if (findStr != null && !findStr.equals("")) {
                        String tarStr = readFileContent(f);
                        if (tarStr != null && !tarStr.equals("")) {
                            tarStr = getReplaceString(b1, tarStr, findStr);
                            writeFileContent(tarStr, f);

                            System.out.println("java文件更新：" + f.getAbsolutePath());
                        }
                    }

                    break;
                }
            }
        }

        for (File item : listXml) {
            String str = readFileContent(item);
            boolean isWrite = false;
            if (str.indexOf(s3) < 0) continue;

            for (File f : listTargetXml) {
                if (f.getName().equals(item.getName())) {

                    String findStr = getMatchString(b2, str);
                    if (findStr != null && !findStr.equals("")) {
                        String tarStr = readFileContent(f);
                        if (tarStr != null && !tarStr.equals("")) {
                            tarStr = getReplaceString(b2, tarStr, findStr);
                            writeFileContent(tarStr, f);

                            System.out.println("xml文件更新："+f.getAbsolutePath());

                        }
                    }

                    break;
                }
            }
        }
    }

    private static String readFileContent(File item) {
        String content = "";
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(item)));
            String str = "";
            while ((str = br.readLine()) != null) {
                sb.append(str + "\n");
            }
            content = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return content;
    }

    private static String writeFileContent(String content, File item) {

        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(item)));
            br.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return content;
    }

    private static void getFileList(File file, final String suffix, List<File> listFiles) {
        List<File> resultList = new ArrayList<File>();
        File dir = file;
        if (dir.isDirectory()) {
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) {
                    getFileList(item, suffix, listFiles);
                } else {
                    if (item.getName().endsWith(suffix)) {
                        listFiles.add(item);
                    }
                }
            }
        }
    }

}
