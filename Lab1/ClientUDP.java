//implement talker.c
//recieve a message
//print message
//form packets


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {
   public static void main(String args[]) {
      
      int portNum = 10038;
      DatagramPacket packet;
      short id = 0;
      short length;
      String str = null;
      byte op = 0;
      byte[] buffer;
      InetAddress addr = null;
      int i = 5;
      
      if(args.length != 4)
      {
         System.out.println("Usage: ClientUDP hostname op message");
      }
      
      try {
         addr = InetAddress.getByName(args[1]);
      } catch (Exception e)
      {}
      if (args[3].length() < 1024) 
         str = args[3];
      else 
         str = args[3].substring(0, 1024); 
              
      op = Byte.parseByte(args[2]);
      length = (short)str.length();
      length += 5;
      buffer = new byte[length];
      buffer[0] = (byte)((length >> 8) & 0xff);
      buffer[1] = (byte)(length);
      buffer[2] = (byte)((id >> 8) & 0xff);
      buffer[3] = (byte)(id & 0xff);
      buffer[4] = op;
      
      for (char ch: str.toCharArray()){
         
         buffer[i] = (byte)ch;
         i++;
      }
      
      packet = new DatagramPacket(buffer, (int)length, addr, portNum);
      
      
      
   }
}
