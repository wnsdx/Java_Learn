//TCP�������˳�����getserver()�����н����������׽��֣�����
//getClientMessage()������ȡ�ͻ�����Ϣ
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;    //����java.io��
import java.net.*;   //����java.net��

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
public class MyTcp extends JFrame{
	private BufferedReader reader;     //����BufferedReader����
	private ServerSocket server;       //����ServerSocket����
	private PrintWriter writer;      //����PrintWriter����
	private Socket socket;             //����Socket����socket
	private JTextArea ta = new JTextArea();   //����JTextArea����
	private JTextField tf = new JTextField(); //����JTextField����
	Container cc;                             //����Container����
	public MyTcp(String title)              //���췽��
	{
		super(title);                         //���ø���Ĺ��췽��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane();    //ʵ��������
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf,"South");    //���ı�����ڴ�����²�
		tf.addActionListener(new ActionListener()
				{
					//���¼�
					public void actionPerformed(ActionEvent e)
					{
						//���ı����е���Ϣд����
						writer.println(tf.getText());
						//���ı����е���Ϣ��ʾ���ı�����
						ta.append(tf.getText());
						ta.setSelectionEnd(ta.getText().length());
						tf.setText("");         //���ı������
					}
				});
	}
	void getserver() 
	{
		try 
		{
			server = new ServerSocket(8998);      //ʵ����Socket����
//			System.out.println("�������׽����Ѿ������ɹ�");//�����Ϣ
			ta.append("�������׽����Ѿ������ɹ�\n");
			while(true)                //����׽���������״̬
			{
//				System.out.println("�ȴ��ͻ���������");  //�����Ϣ
				ta.append("�ȴ��ͻ���������\n");
				socket = server.accept();    //ʵ����Socket����
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //ʵ����BufferedReader����
				writer = new PrintWriter(socket.getOutputStream(),true);
				getClientMessage();          //����getClientMessage()����
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();      //����쳣��Ϣ
		}
	}
	private void getClientMessage()
	{
		try
		{
			while(true)  //����׽���������״̬
			{
				//��ȡ�ͻ�����Ϣ
//				System.out.println("�ͻ���:"+reader.readLine());
				
				ta.append("�ͻ���:"+reader.readLine());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();    //����쳣��Ϣ
		}
		try
		{
			if(reader!=null)
			{
				reader.close();   //�ر���
			}
			if(socket!=null)
			{
				socket.close();   //�ر��׽���
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTcp tcp = new MyTcp("�����˿�");    //�����������
		tcp.setSize(200,200);     //���ô����С
		tcp.setVisible(true);     //��������ʾ
		tcp.getserver();            //���÷���
	}

}
