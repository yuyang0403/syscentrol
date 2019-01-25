package com.yuyang.other.hdfs;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * @author yuyang
 * @create 2019/1/23 14:51
 * @desc
 **/
public class HdfsUtil {
    // 日志
    public static Logger logger = Logger.getLogger(HdfsUtil.class);

    // 集群环境配置
    public static Configuration conf = new Configuration();

    /**
     * 在hdfs上创建目录
     *
     * @param 创建目录
     * @return
     * @throws Exception
     */

    public static boolean mkdir(String src) throws Exception {
        if (StringUtils.isEmpty(src) || src.trim().length() == 0) {
            logger.error("要创建的目录不能为空!");
            return false;
        }
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(src);
        boolean isSuccess = fs.mkdirs(path);
        return isSuccess;
    }

    /**
     * 在hdfs上删除目录
     *
     * @param 要刪除的目录
     * @param 是否递归
     * @return
     * @throws Exception
     */
    public static boolean rmdir(String src, boolean recursive) throws Exception {
        if (StringUtils.isEmpty(src) || src.trim().length() == 0) {
            logger.error("要删除的目录不能为空!");
            return false;
        }
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(src);
        boolean isSuccess = fs.delete(path, recursive);
        return isSuccess;
    }

    /**
     * 在hdfs上删除文件
     *
     * @param 要刪除的文件
     * @return
     * @throws Exception
     */
    public static boolean rmFile(String src) throws Exception {
        if (StringUtils.isEmpty(src) || src.trim().length() == 0) {
            logger.error("文件名不能为空!");
            return false;
        }
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(src);
        if (fs.isDirectory(path)) {
            logger.error(src + ":是一个目录，请检查传入的参数!");
            return false;
        }
        boolean isSuccess = fs.delete(path);
        return isSuccess;
    }

    /**
     * 向hdfs上传文件
     *
     * @param 是否删除源文件
     * @param 是否覆盖hdfs文件
     * @param 源路径
     * @param 目标路径
     * @throws Exception
     */
    public static void copyFromLocal(boolean delSrc, boolean overwrite, String src, String dst) throws Exception {
        if (StringUtils.isEmpty(src) || src.trim().length() == 0 || StringUtils.isEmpty(dst)
                || dst.trim().length() == 0) {
            logger.error("源文件名或目标文件名不能为空!");
            return;
        }
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path(src);
        Path dstPath = new Path(dst);
        fs.copyFromLocalFile(delSrc, overwrite, srcPath, dstPath);
    }

    /**
     * 从hdfs下载文件
     *
     * @param 是否删除源文件
     * @param 源路径
     * @param 目标路径
     * @throws Exception
     */
    public static void copyToLocal(boolean delSrc, String src, String dst) throws Exception {
        if (StringUtils.isEmpty(src) || src.trim().length() == 0 || StringUtils.isEmpty(dst)
                || dst.trim().length() == 0) {
            logger.error("源文件名或目标文件名不能为空!");
            return;
        }
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path(src);
        Path dstPath = new Path(dst);
        fs.copyToLocalFile(delSrc, srcPath, dstPath);
    }

    /**
     * 列出该目录的文件
     *
     * @param 要查看的目录
     * @return
     * @throws Exception
     */
    public static FileStatus[] listFileStatus(String src) throws Exception {
        if (StringUtils.isEmpty(src) || src.trim().length() == 0) {
            logger.error("目录不能为空!");
            return null;
        }
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(src);
        return fs.listStatus(path);
    }

    /**
     * 读文件
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static byte[] readFile(String src) throws Exception {
        if (StringUtils.isEmpty(src) || src.trim().length() == 0) {
            logger.error("文件名不能为空!");
            return null;
        }
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(src);
        FSDataInputStream in = fs.open(path);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] b = new byte[1024];
        int len = 0;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
        return out.toByteArray();
    }

    /**
     * 写文件
     *
     * @param src
     * @param hdfs
     * @param overwrite
     * @return
     */
    public static boolean writeFile(String src, String hdfs, boolean overwrite) {
        FSDataOutputStream out = null;
        try {
            if (StringUtils.isEmpty(hdfs) || hdfs.trim().length() == 0) {
                logger.error("hdfs文件名不能为空!");
                return false;
            }
            if (StringUtils.isEmpty(src) || src.trim().length() == 0) {
                logger.error("src文件名不能为空!");
                return false;
            }
            FileSystem fs = FileSystem.get(conf);

            Path path = new Path(hdfs);
            out = fs.create(path, overwrite);

            byte[] b = readFile(src);
            out.write(b);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Test
    public void test(){
        try {
            System.out.println(mkdir("filesys"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
