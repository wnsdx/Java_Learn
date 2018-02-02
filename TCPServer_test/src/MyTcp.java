//TCP服务器端程序，在getserver()方法中建立服务器套接字，调用
//getClientMessage()方法获取客户端信息
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;    //导入java.io包
import java.net.*;   //导入java.net包

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
public class MyTcp extends JFrame{
	private BufferedReader reader;     //创建BufferedReader对象
	private ServerSocket server;       //创建ServerSocket对象
	private PrintWriter writer;      //声明PrintWriter对象
	private Socket socket;             //创建Socket对象socket
	private JTextArea ta = new JTextArea();   //创建JTextArea对象
	private JTextField tf = new JTextField(); //创建JTextField对象
	Container cc;                             //声明Container对象
	public MyTcp(String title)              //构造方法
	{
		super(title);                         //调用父类的构造方法
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane();    //实例化对象
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf,"South");    //将文本框放在窗体的下部
		tf.addActionListener(new ActionListener()
				{
					//绑定事件
					public void actionPerformed(ActionEvent e)
					{
						//将文本框中的信息写入流
						writer.println(tf.getText());
						//将文本框中的信息显示在文本域中
						ta.append(tf.getText());
						ta.setSelectionEnd(ta.getText().length());
						tf.setText("");         //将文本框清空
					}
				});
	}
	void getserver() 
	{
		try 
		{
			server = new ServerSocket(8998);      //实例化Socket对象
//			System.out.println("服务器套接字已经创建成功");//输出信息
			ta.append("服务器套接字已经创建成功\n");
			while(true)                //如果套接字是连接状态
			{
//				System.out.println("等待客户机的连接");  //输出信息
				ta.append("等待客户机的连接\n");
				socket = server.accept();    //实例化Socket对象
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //实例化BufferedReader对象
				writer = new PrintWriter(socket.getOutputStream(),true);
				getClientMessage();          //调用getClientMessage()方法
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();      //输出异常信息
		}
	}
	private void getClientMessage()
	{
		try
		{
			while(true)  //如果套接字是连接状态
			{
				//获取客户端信息
//				System.out.println("客户机:"+reader.readLine());
				
				ta.append("客户机:"+reader.readLine());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();    //输出异常信息
		}
		try
		{
			if(reader!=null)
			{
				reader.close();   //关闭流
			}
			if(socket!=null)
			{
				socket.close();   //关闭套接字
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTcp tcp = new MyTcp("监听端口");    //创建本类对象
		tcp.setSize(200,200);     //设置窗体大小
		tcp.setVisible(true);     //将窗体显示
		tcp.getserver();            //调用方法
	}

}
