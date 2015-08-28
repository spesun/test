package my;

import java.io.FileInputStream;

public class JavaVersionUtil {

 private static final String str = "D:/NewQs/source/qs/build/com/zte/qs/parse/parser/ParserFactory.class";

 // �汾�Ŷ�Ӧ��
 // 5.0
 // �汾��(version):49.0
 // 6.0
 // �汾��(version):50.0
 // 1.4
 // �汾��(version):46.0
 // 1.3
 // �汾��(version):45.3

 public static void main(String args[]) {
  try {
   // ��ȡ�ļ�����,�ļ��ǵ�ǰĿ¼�µ�First.class
   FileInputStream fis = new FileInputStream(str);
   int length = fis.available();
   // �ļ�����
   byte[] data = new byte[length];
   // ��ȡ�ļ����ֽ�����
   fis.read(data);
   // �ر��ļ�
   fis.close();
   // �����ļ�����
   parseFile(data);
  } catch (Exception e) {
   System.out.println(e);
  }
 }

 private static void parseFile(byte[] data) {
  // ���ħ��
  System.out.print("ħ��(magic):0x");
  System.out.print(Integer.toHexString(data[0]).substring(6)
    .toUpperCase());
  System.out.print(Integer.toHexString(data[1]).substring(6)
    .toUpperCase());
  System.out.print(Integer.toHexString(data[2]).substring(6)
    .toUpperCase());
  System.out.println(Integer.toHexString(data[3]).substring(6)
    .toUpperCase());
  // ���汾�źʹΰ汾����
  int minor_version = (((int) data[4]) << 8) + data[5];
  int major_version = (((int) data[6]) << 8) + data[7];
  System.out.println("�汾��(version):" + major_version + "."
    + minor_version);
 }

}

