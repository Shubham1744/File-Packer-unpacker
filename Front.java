import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

class Front
{
	public static void main(String arg[])
	{
		Window obj = new Window();
	}
}

class Window //implements ActionListener
{
	public Window()
	{
		JFrame f = new JFrame("Marvellous Login");
		
		JButton bobj = new JButton("Submit");
		bobj.setBounds(100,200,140,40);
		
		JLabel lobj1 = new JLabel("Enter File Name");
		lobj1.setBounds(10,10,100,100);
		
		JTextField tf1 = new JTextField();
		tf1.setBounds(110,60,130,30);
		
		JLabel lobj2 = new JLabel("Enter File name");
        lobj2.setBounds(10,100,100,100);
        JTextField tf2 = new JTextField();
        // (x cordinate, y cordinate, widtyh, height)
        tf2.setBounds(100,120,130,30);
        
		f.add(lobj1);
        f.add(bobj);
        f.add(tf1);
        f.add(lobj2);
        f.add(tf2);
		f.setSize(600,600);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bobj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eobj){
				System.out.println("Data Entered :"+tf1.getText());
				System.out.println("Data Entered :"+tf2.getText());
				Packer pobj = new Packer(tf1.getText(),tf2.getText());
				NewWindow o = new NewWindow();
			}
		});
	}
}
class NewWindow
{
	public NewWindow()
	{
		JFrame fobj = new JFrame("New Frame");
		fobj.setSize(600,600);
		fobj.setVisible(true);
		fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
class Packer
{
	// Object for file writing
	public FileOutputStream outstream = null;

	// parametrised constructor
	public Packer(String FolderName, String FileName)
	{
		try
		{
			System.out.println("Inside Packer Constructor");
			// Create new file for packing
			File outfile = new File(FileName);
			outstream = new FileOutputStream(FileName);

			// Set the current working directory for folder traversal
			// System.setProperty("user.dir",FolderName);
			
			TravelDirectory(FolderName);
		}
		catch(Exception obj)
		{
			System.out.println(obj);
		}
	}

	public void TravelDirectory(String path)
	{
		File directoryPath = new File(path);

		// Get all file names from directory
		File arr[] = directoryPath.listFiles();

		for(File filename : arr)
		{
			//System.out.println(filename.getAbsolutePath());
			
			if(filename.getName().endsWith(".txt"))
			{
				PackFile(filename.getAbsolutePath());	
			}		
		}
	}

	public void PackFile(String FilePath)
	{
//		System.out.println("File name received "+ FilePath);
		byte Header[] = new byte[100];
		byte Buffer[] = new byte[1024];
		int length = 0;

		FileInputStream istream = null;

		File fobj = new File(FilePath);

		String temp = FilePath+" "+fobj.length();
		
		// Create header of 100 bytes
		for(int i = temp.length(); i< 100; i++)
		{
			temp = temp + " ";
		}		

		Header = temp.getBytes();
		try
		{
			// open the file for reading
			istream = new FileInputStream(FilePath);

			outstream.write(Header,0,Header.length);
			while((length = istream.read(Buffer)) > 0)
			{
				outstream.write(Buffer,0,length);
			}

			istream.close();
		}
		catch(Exception obj)
		{}
		// System.out.println("Header : "+temp.length());

		// Packing logic
	}
}
