package java_io_test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * Java IO 和 NIO 测试类，最后测试 Okio
 * 知识点：
 * 1. Java IO 是基于流的概念，设计上可以进行插管嵌套。
 * 2. Java NIO 是基于通道的概念，面向 Buffer , 强制使用 Buffer, 可以同时实现读取和输入，
 * https://www.zhihu.com/question/29005375
 */
public class JavaIoTest {
    public static void main(String[] args) {
//        io_1();
//        io_2();
        io_3();
    }

    /**
     * NIO 的使用测试, NIo 的核心知识点是什么？
     */
    public static void io_3() {
        File file = new File("io_3.text");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel channel = randomAccessFile.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.putChar('a');
            byteBuffer.putChar('b');
            byteBuffer.putChar('c');
            byteBuffer.flip();
            channel.write(byteBuffer);
            byteBuffer.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用 Reader 和 Writer 输出和读取字符串
     * Reader 和 Writer 是面向字符的输出
     */
    public static void io_2() {
        File file = new File("io_2.text");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("这是一个字符输出");
            // flush 表示将缓冲区内的字符一次性输出到文件中
            bufferedWriter.flush();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            // 面向字符？转成 char 就这就输出了汉字？
            System.out.println((char) bufferedReader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进行 IO 流向文件输出和输入操作
     */
    public static void io_1() {
        File file = new File("io_1.text");
        try (
                OutputStream outputStream = new FileOutputStream(file);
        ) {
            outputStream.write('a');
            outputStream.write('b');
            outputStream.write('c');
            outputStream.write('d');

            InputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) != -1) {
                System.out.println(new String(buffer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
