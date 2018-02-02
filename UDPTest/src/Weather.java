import java.net.*;
public class Weather extends Thread    //�����࣬����Ϊ���߳�ִ�г���
{          
	String weather = "����Ԥ�����˵��д�����ᣬ������";
	int port = 9898;             //����˿�
	InetAddress iaddress = null;  //����InetAddress����
	MulticastSocket socket = null;   //�������㲥�׽���
	public Weather()                    //���췽��
	{
		try
		{
			//ʵ����InetAddress,ָ����ַ
			iaddress = InetAddress.getByName("224.225.10.0");
			socket = new MulticastSocket(port);   //ʵ�������㲥�׽���
			socket.setTimeToLive(1);    //ָ�����ͷ�Χ�Ǳ�������
			socket.joinGroup(iaddress); //����㲥��
		}
		catch(Exception e)
		{
			e.printStackTrace();        //����쳣��Ϣ
		}
	}
	public void run()   //run()����
	{
		while(true) 
		{
			DatagramPacket packet = null;   //����DatagramPacket����
			byte data[] = weather.getBytes();   //�����ֽ�����
			//�����ݴ��
			packet = new DatagramPacket(data,data.length,iaddress,port);
			System.out.println(new String(data));   //���㲥��Ϣ���
			try
			{
				socket.send(packet);     //��������
				sleep(3000);             //�߳�����
			}
			catch(Exception e)
			{
				e.printStackTrace();      //����쳣��Ϣ
			}
		}
	}
	public static void main(String[] args) {   //������
		// TODO Auto-generated method stub
		Weather w = new Weather();             //�����������
		w.start();                             //�����߳�
	}

}
