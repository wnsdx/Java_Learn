import java.net.*;
public class Address {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress ip;           //����InetAddress����
		try                       //ʹ��try���鲶׽���ܳ��ֵ��쳣
		{
			ip = InetAddress.getLocalHost();   //ʵ��������
			String localname = ip.getHostName(); //��ȡ������
			String locallip  = ip.getHostAddress();//��ȡ����IP��ַ
			System.out.println("��������"+localname);      //�����������
			System.out.println("����IP��ַ��"+locallip);  //������IP��ַ���
		}catch(UnknownHostException e) 
		{
			e.printStackTrace();          //����쳣��Ϣ
		}
	}

}
