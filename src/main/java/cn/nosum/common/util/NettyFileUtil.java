package cn.nosum.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NettyFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(NettyFileUtil.class);

    private final static Integer BYTE_BUFFER_LENGTH=1024;
    private final static String LINE_SEPARATOR= System.getProperty("line.separator");

    /**
     * 将内存中的数据写入到磁盘，如果路径不存在则创建
     * @param data 文件内容
     * @param filePath 文件保存路劲
     * @param append 文件存在时是否追加内容
     */
    public static void dataToFile(String data, String filePath,boolean append) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file,append);
             FileChannel fileChannel = fileOutputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(getLength(data.length())+LINE_SEPARATOR.length());
            int i = 0;
            int length = data.getBytes().length;
            // 一次性读取完毕
            if (BYTE_BUFFER_LENGTH > data.getBytes().length) {
                byteBuffer.put(data.getBytes(), i, data.getBytes().length);
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
            } else {
                // 循环读取
                for (int temp = 0; temp < data.getBytes().length; temp += BYTE_BUFFER_LENGTH) {
                    byteBuffer.clear();
                    byteBuffer.put(data.getBytes(), temp, BYTE_BUFFER_LENGTH);
                    byteBuffer.flip();
                    fileChannel.write(byteBuffer);
                }
            }
            fileOutputStream.write(LINE_SEPARATOR.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件内容
     * @param filePath 目标文件路径
     * @return 文件内容
     */
    public static String dataFromFile(String filePath) {
        File file = new File(filePath);
        // 从输入流中获取 channel
        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileChannel channel = fileInputStream.getChannel()) {

            // 分配缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_BUFFER_LENGTH);
            StringBuilder result = new StringBuilder();
            while (true) {
                byteBuffer.clear();
                // 将 channel数据写到buffer中
                int read = channel.read(byteBuffer);
                // 因为byteBuffer大小原因，因此需要用一个中间字符串接受一下
                result.append(new String(byteBuffer.array()));
                if (read == -1) {
                    break;
                }
            }
            return result.toString();
        } catch (Exception e) {
            logger.error("文件读取错误，错误原因 ：{0}", e);
        }
        return null;
    }

    /**
     * 文件复制
     * @param sourceFilePath 源路径
     * @param targetFilePath 目标路劲
     */
    public static void copyFileUseChannelTransfer(String sourceFilePath, String targetFilePath) {
        File source = new File(sourceFilePath);
        File target = new File(targetFilePath);
        try (FileInputStream fileInputStream = new FileInputStream(source);
             FileOutputStream fileOutputStream = new FileOutputStream(target);
             FileChannel fileInputStreamChannel = fileInputStream.getChannel();
             FileChannel fileOutputStreamChannel = fileOutputStream.getChannel()) {
            // 直接将输入channel复制到输出channel
            fileOutputStreamChannel.transferFrom(
                    fileInputStreamChannel,
                    fileInputStreamChannel.position(),
                    fileInputStreamChannel.size()
            );

        } catch (Exception e) {
            logger.error("文件复制错误，错误原因 ：{0}", e);
        }
    }

    public static int getLength(Integer length){
        return BYTE_BUFFER_LENGTH>length?length:BYTE_BUFFER_LENGTH;
    }
}
