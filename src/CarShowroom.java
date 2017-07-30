import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.*;

import java.sql.*;

import java.util.*;

class CarShowroomFrame extends JFrame implements ActionListener{


      
      JMenuItem home, add, modify, search,search1, print, exit;
      JMenuBar mb=new JMenuBar();
      JInternalFrame addInternalFrame,searchCustomerFrame1 ,modifyInternalFrame, searchInternalFrame1,addInternalFrame1, modifyInternalFrame1, searchInternalFrame;
      JLayeredPane jlayeredpane = new JLayeredPane();
      JLabel label,HomeLabel, carNameLabel, carNameSearchLabel, modelNoLabel, modelNoSearchLabel, carTypeLabel, colorLabel,Label1,HomeLabel1, carNameLabel1, carNameSearchLabel1, modelNoLabel1, modelNoSearchLabel1, carTypeLabel1, colorLabel1;
      JTextField carNameTextField1, carNameSearchTextField1, modelNoTextField1, modelNoSearchTextField1, carTypeTextField1, colorTextField1,carNameTextField, carNameSearchTextField, modelNoTextField, modelNoSearchTextField, carTypeTextField, colorTextField;
      JTextField mcarNameTextField, mcarNameSearchTextField, mmodelNoTextField, mmodelNoSearchTextField, mcarTypeTextField, mcolorTextField,mcarNameTextField1, mcarNameSearchTextField1, mmodelNoTextField1, mmodelNoSearchTextField1, mcarTypeTextField1, mcolorTextField1;
      JPanel addPanel, modifyPanel,modifySearchPanel, searchPanel,searchBycarNamePanel,searchBymodelNoPanel,searchByAircraftRegNoPanel,searchAllPanel, searchTablePanel,  printPanel,addPanel1,addPanel11, modifyPanel1,modifySearchPanel1, searchPanel1,searchBycarNamePanel1,searchBymodelNoPanel1,searchAllPanel1, searchTablePanel1,  printPanel1;
      JButton addButton,addButton1,modifyButton1,deleteButton1, modifySearchButton1, searchBycarNamePanel1Button1, searchBymodelNoButton1, searchByAircraftRegNoButton1,searchAllButton1,modifyButton,deleteButton, modifySearchButton, searchBycarNamePanelButton, searchBymodelNoButton,searchAllButton;
      GridBagLayout grid = new GridBagLayout();
      GridBagLayout grid1 = new GridBagLayout();
      GridBagLayout grid11 = new GridBagLayout();
      
      GridBagConstraints gbc1 = new GridBagConstraints();
      GridBagConstraints gbc11 = new GridBagConstraints();
      GridBagConstraints gbc111 = new GridBagConstraints();
      JTable searchReportTable;
      JTable searchReportTable1;
      int v= ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
      int h= ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
      JScrollPane jsp;
      JScrollPane jsp1;
      JScrollPane jsp11;
      
      String query1=" ";
      String text1=" ";
      String text2=" ";
      String text3=" ";
      String text4=" ";
      String text5=" ";
      String text6=" ";
      String text7=" ";
      int idno=0;
      final String[] colHeaders = { "Car Name", "Model No.","Car Type", "Color"};
      final String[] colHeaders1 = { "Customer Name", "Age.","Address", "Car"};
      Object[][] data;
      Object[][] dataa;
      Object[][] data1={ };
      Object[][] data2={};
      Vector Rows_Data = new  Vector();
      String[] row_data = new String[4];
      String connstr="jdbc:mysql://127.0.0.1:3306/showroom";
      String jdbcDriver="com.mysql.jdbc.Driver";
	
     public CarShowroomFrame()
     {
        setTitle("Car Showroom Management System");
        setSize(new Dimension(800, 580));
        Container cpane = getContentPane();

        home = new JMenuItem("Home",new ImageIcon("image/home.png"));
        mb.add(home);
    	add = new JMenuItem("Add Car ",new ImageIcon("image/plus.png"));
    	mb.add(add);
    	modify = new JMenuItem("Sell Car ",new ImageIcon("image/modify.png"));
    	mb.add(modify);
    	search = new JMenuItem("Search Record ",new ImageIcon("image/Search.png"));
    	mb.add(search);
        search1 = new JMenuItem("Search Customer ",new ImageIcon("image/Search.png"));
    	mb.add(search1);
    	exit = new JMenuItem("EXIT",new ImageIcon("image/stop.png"));
    	mb.add(exit);
    	setJMenuBar(mb);

    	home.addActionListener(this);
    	add.addActionListener(this);
    	modify.addActionListener(this);
    	search.addActionListener(this);
        search1.addActionListener(this);
    	exit.addActionListener(this);

    	HomeLabel = new JLabel("",new ImageIcon("image/homeWallPaper.jpg"), JLabel.CENTER);
    	addInternalFrame = new JInternalFrame("Add Car to Record");
    	OrderCarToCompany();
    	modifyInternalFrame = new JInternalFrame("Customer Entry");
    	GetCustDetails();
    	searchInternalFrame = new JInternalFrame("Search Car");
    	CheckStock();
        searchInternalFrame1 = new JInternalFrame("Customer Details");
        SearchCustomer();
    	
        HomeLabel.setOpaque(true);
    	addInternalFrame.setOpaque(true);
    	modifyInternalFrame.setOpaque(true);
    	searchInternalFrame.setOpaque(true);
        searchInternalFrame1.setOpaque(true);

    	jlayeredpane.add(HomeLabel);
    	jlayeredpane.add(addInternalFrame);
    	jlayeredpane.add(modifyInternalFrame);
    	jlayeredpane.add(searchInternalFrame);
        jlayeredpane.add(searchInternalFrame1);

    	cpane.add(jlayeredpane);

    	HomeLabel.setBounds(0,0,800,515);
    	addInternalFrame.setBounds(0,0,800,515);
    	modifyInternalFrame.setBounds(0,0,800,515);
    	searchInternalFrame.setBounds(0,0,800,515);
        searchInternalFrame1.setBounds(0,0,800,515);

    	addInternalFrame.setVisible(false);
    	modifyInternalFrame.setVisible(false);
    	searchInternalFrame.setVisible(false);
        searchInternalFrame1.setVisible(false);


        
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {

                    CarShowroomFrame.this.windowClosed();
                }
            }
        );



    }
    void OrderCarToCompany()
    {
    	addPanel = new JPanel();
		addPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		addPanel.setLayout(grid);

    	carNameLabel = new JLabel("Car Name");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(carNameLabel, gbc1);
    	addPanel.add(carNameLabel);

    	carNameTextField = new JTextField();
    	gbc1 = gridlayout(200,0,1,1);
    	grid.setConstraints(carNameTextField, gbc1);
    	addPanel.add(carNameTextField);

    	modelNoLabel = new JLabel("Model No.");
    	gbc1 = gridlayout(0,0,0,2);
    	grid.setConstraints(modelNoLabel, gbc1);
    	addPanel.add(modelNoLabel);

    	modelNoTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,2);
    	grid.setConstraints(modelNoTextField, gbc1);
    	addPanel.add(modelNoTextField);

    	carTypeLabel = new JLabel("Car Type");
    	gbc1 = gridlayout(0,0,0,3);
    	grid.setConstraints(carTypeLabel, gbc1);
    	addPanel.add(carTypeLabel);

    	carTypeTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,3);
    	grid.setConstraints(carTypeTextField, gbc1);
    	addPanel.add(carTypeTextField);

    	colorLabel = new JLabel("Color");
    	gbc1 = gridlayout(0,0,0,4);
    	grid.setConstraints(colorLabel, gbc1);
    	addPanel.add(colorLabel);


    	colorTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,4);
    	grid.setConstraints(colorTextField, gbc1);
    	addPanel.add(colorTextField);

    	addButton = new JButton("Add Record");
    	gbc1 = gridlayout(100,0,2,8);
    	grid.setConstraints(addButton, gbc1);
    	addPanel.add(addButton);
    	addButton.addActionListener(this);

    	addInternalFrame.add(addPanel);


    }
    void GetCustDetails()
    {
          addPanel1 = new JPanel();
		addPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		addPanel1.setLayout(grid1);

          carNameLabel1 = new JLabel("Customer Name");
           gbc11 = gridlayout(0,0,0,1);
    	grid1.setConstraints(carNameLabel1, gbc11);
    	addPanel1.add(carNameLabel1);

           carNameTextField1 = new JTextField();
    	gbc11 = gridlayout(200,0,1,1);
    	grid1.setConstraints(carNameTextField1, gbc11);
    	addPanel1.add(carNameTextField1);

          modelNoLabel1 = new JLabel("Age");
    	gbc11 = gridlayout(0,0,0,2);
    	grid1.setConstraints(modelNoLabel1, gbc11);
    	addPanel1.add(modelNoLabel1);

          modelNoTextField1 = new JTextField();
    	gbc11 = gridlayout(100,0,1,2);
    	grid1.setConstraints(modelNoTextField1, gbc11);
    	addPanel1.add(modelNoTextField1);

           carTypeLabel1 = new JLabel("Address");
    	gbc11 = gridlayout(0,0,0,3);
    	grid1.setConstraints(carTypeLabel1, gbc11);
    	addPanel1.add(carTypeLabel1);

          carTypeTextField1 = new JTextField();
    	gbc11 = gridlayout(100,0,1,3);
    	grid1.setConstraints(carTypeTextField1, gbc11);
    	addPanel1.add(carTypeTextField1);

           colorLabel1 = new JLabel("Car");
    	gbc11 = gridlayout(0,0,0,4);
    	grid1.setConstraints(colorLabel1, gbc11);
    	addPanel1.add(colorLabel1);


           colorTextField1 = new JTextField();
    	gbc11 = gridlayout(100,0,1,4);
    	grid1.setConstraints(colorTextField1, gbc11);
    	addPanel1.add(colorTextField1);
        
         addButton1 = new JButton("Add Customer");
    	gbc11 = gridlayout(100,0,2,8);
    	grid1.setConstraints(addButton1, gbc11);
    	addPanel1.add(addButton1);
    	addButton1.addActionListener(this);

    	modifyInternalFrame.add(addPanel1); 
    	addPanel1.setBounds(0, 100, 800, 415); 

    	
}
    void CheckStock()
    {


    	searchBycarNamePanel = new JPanel();
    	searchBycarNamePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchBycarNamePanel.setLayout(grid);

    	carNameSearchLabel = new JLabel("Car Name");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(carNameSearchLabel, gbc1);
    	searchBycarNamePanel.add(carNameSearchLabel);

    	carNameSearchTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,1);
    	grid.setConstraints(carNameSearchTextField, gbc1);
    	searchBycarNamePanel.add(carNameSearchTextField);

    	searchBycarNamePanelButton = new JButton("Search");
    	gbc1 = gridlayout(0,0,1,2);
    	grid.setConstraints(searchBycarNamePanelButton, gbc1);
    	searchBycarNamePanel.add(searchBycarNamePanelButton);
    	searchBycarNamePanelButton.addActionListener(this);

    	searchInternalFrame.add(searchBycarNamePanel);
    	searchBycarNamePanel.setBounds(0, 400, 200, 75);

    	searchBymodelNoPanel = new JPanel();
    	searchBymodelNoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchBymodelNoPanel.setLayout(grid);

    	modelNoSearchLabel = new JLabel("Model No.");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(modelNoSearchLabel, gbc1);
    	searchBymodelNoPanel.add(modelNoSearchLabel);

    	modelNoSearchTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,1);
    	grid.setConstraints(modelNoSearchTextField, gbc1);
    	searchBymodelNoPanel.add(modelNoSearchTextField);

    	searchBymodelNoButton = new JButton("Search");
    	gbc1 = gridlayout(0,0,1,2);
    	grid.setConstraints(searchBymodelNoButton, gbc1);
    	searchBymodelNoPanel.add(searchBymodelNoButton);
    	searchBymodelNoButton.addActionListener(this);

    	searchInternalFrame.add(searchBymodelNoPanel);
    	searchBymodelNoPanel.setBounds(200, 400, 200, 75);

    	searchByAircraftRegNoPanel = new JPanel();
    	searchByAircraftRegNoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchByAircraftRegNoPanel.setLayout(grid);


    	searchAllPanel = new JPanel();
    	searchAllPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchAllPanel.setLayout(grid);



    	searchAllButton = new JButton("Search All");
    	gbc1 = gridlayout(0,0,100,1);
    	grid.setConstraints(searchAllButton, gbc1);
    	searchAllPanel.add(searchAllButton);
    	searchAllButton.addActionListener(this);



		searchInternalFrame.add(searchAllPanel);
    	searchAllPanel.setBounds(630,400, 150, 75);



    	searchTablePanel = new JPanel();
    	searchTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchTablePanel.setLayout(grid);

    	searchReportTable = new JTable(data1 ,colHeaders);
    	gbc1 = gridlayout(0, 0, 0, 0);
    	grid.setConstraints(searchReportTable, gbc1);
    	jsp = new JScrollPane(searchReportTable,v,h);
		gbc1 = gridlayout(750, 320, 0, 0);
    	grid.setConstraints(jsp, gbc1);
		searchTablePanel.add(jsp);
		jsp.setBounds(0, 0, 700, 400);
		jsp.setVisible(true);


    	searchInternalFrame.add(searchTablePanel);

    	searchTablePanel.setBounds(0, 0, 800, 350);
    	searchTablePanel.setVisible(true);


    }
    
    void SearchCustomer()
    {
       

    	searchBycarNamePanel1 = new JPanel();
    	searchBycarNamePanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchBycarNamePanel1.setLayout(grid11);

    	carNameSearchLabel1 = new JLabel("Customer Name");
    	gbc111 = gridlayout(0,0,0,1);
    	grid11.setConstraints(carNameSearchLabel1, gbc111);
    	searchBycarNamePanel1.add(carNameSearchLabel1);

    	carNameSearchTextField1 = new JTextField();
    	gbc111 = gridlayout(100,0,1,1);
    	grid11.setConstraints(carNameSearchTextField1, gbc111);
    	searchBycarNamePanel1.add(carNameSearchTextField1);

    	searchBycarNamePanel1Button1 = new JButton("Search");
    	gbc111 = gridlayout(0,0,1,2);
    	grid11.setConstraints(searchBycarNamePanel1Button1, gbc111);
    	searchBycarNamePanel1.add(searchBycarNamePanel1Button1);
    	searchBycarNamePanel1Button1.addActionListener(this);

   	searchInternalFrame1.add(searchBycarNamePanel1);
        searchBycarNamePanel1.setBounds(0, 400, 200, 75);

    	searchBymodelNoPanel1 = new JPanel();
    	searchBymodelNoPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchBymodelNoPanel1.setLayout(grid11);

    	modelNoSearchLabel1= new JLabel("Car Name");
    	gbc111 = gridlayout11(0,0,0,1);
    	grid11.setConstraints(modelNoSearchLabel1, gbc111);
    	searchBymodelNoPanel1.add(modelNoSearchLabel1);

    	modelNoSearchTextField1 = new JTextField();
    	gbc111 = gridlayout11(100,0,1,1);
    	grid11.setConstraints(modelNoSearchTextField1, gbc111);
    	searchBymodelNoPanel1.add(modelNoSearchTextField1);

    	searchBymodelNoButton1 = new JButton("Search");
    	gbc111 = gridlayout11(0,0,1,2);
    	grid11.setConstraints(searchBymodelNoButton1, gbc111);
    	searchBymodelNoPanel1.add(searchBymodelNoButton1);
    	searchBymodelNoButton1.addActionListener(this);

    	searchInternalFrame1.add(searchBymodelNoPanel1);
    	searchBymodelNoPanel1.setBounds(200, 400, 200, 75);

    	

    	searchAllPanel1= new JPanel();
    	searchAllPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchAllPanel1.setLayout(grid11);



    	searchAllButton1 = new JButton("Search All");
    	gbc111 = gridlayout11(0,0,100,1);
    	grid11.setConstraints(searchAllButton1, gbc111);
    	searchAllPanel1.add(searchAllButton1);
    	searchAllButton1.addActionListener(this);




		searchInternalFrame1.add(searchAllPanel1);
    	searchAllPanel1.setBounds(630,400, 150, 75);



    	searchTablePanel1 = new JPanel();
    	searchTablePanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchTablePanel1.setLayout(grid11);

    	searchReportTable1 = new JTable(data1 ,colHeaders1);
    	gbc111 = gridlayout11(0, 0, 0, 0);
    	grid11.setConstraints(searchReportTable1, gbc111);
    	jsp1 = new JScrollPane(searchReportTable1,v,h);
		gbc111= gridlayout11(750, 320, 0, 0);
    	grid11.setConstraints(jsp1, gbc111);
		searchTablePanel1.add(jsp1);
		jsp1.setBounds(0, 0, 700, 400);
		jsp1.setVisible(true);


    	searchInternalFrame1.add(searchTablePanel1);

    	searchTablePanel1.setBounds(0, 0, 800, 350);
    	searchTablePanel1.setVisible(true);


    
    }
    void addRecordCar()
    {
    	if(text1.length()!=0)
    	{
    		if(text2.length()!=0)
    		{
    			try
    			{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/showroom","root","naman");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery("select * from car where car_name= '"+ text1 +"' and model_no= '"+ text2 +"'");
					if(result.next())
					{
						JOptionPane.showMessageDialog(null, "Car.already exist.","Warring ",JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						st.executeUpdate(query1);
						carNameTextField.setText("");
    					modelNoTextField.setText("");
    					carTypeTextField.setText("");
    					colorTextField.setText("");
    					
    					JOptionPane.showMessageDialog(null, "New Record Add Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);
					}
					con.close();
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);

	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    	}
	    	else
	    	{
	    		JOptionPane.showMessageDialog(null, "Model No. Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    		}
     	}
     	else
     	{
    		JOptionPane.showMessageDialog(null, "Car Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    
    
    
    
    
    
    
    
    
    
     void addRecordCarCustomer()
    {
    	if(text1.length()!=0)
    	{
    		if(text2.length()!=0)
    		{
    			try
    			{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/showroom","root","naman");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery("select * from customer where name= '"+ text1 +"' and age= '"+ text2 +"'");
					if(result.next())
					{
						JOptionPane.showMessageDialog(null, "Customer. already exist.","Warring ",JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						st.executeUpdate(query1);
						carNameTextField1.setText("");
    					modelNoTextField1.setText("");
    					carTypeTextField1.setText("");
    					colorTextField1.setText("");
    					
    					JOptionPane.showMessageDialog(null, "New Record Add Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);
					}
					con.close();
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);

	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    	}
	    	else
	    	{
	    		JOptionPane.showMessageDialog(null, "Age Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    		}
     	}
     	else
     	{
    		JOptionPane.showMessageDialog(null, "Customer Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    void searchRecordForModify()
    {
    			try
    			{
					Class.forName(jdbcDriver);
					Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/showroom","root","naman");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery(query1);
					if(result.next())
					{
						idno=result.getInt(1);
						text1=result.getString(2);
						text2=result.getString(3);
						text3=result.getString(4);

						text4=result.getString(5);
						

						mcarNameTextField.setText(text1);
    					mmodelNoTextField.setText(text2);
    					mcarTypeTextField.setText(text3);
    					mcolorTextField.setText(text4);
    				
    					JOptionPane.showMessageDialog(null, "Record Found Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						JOptionPane.showMessageDialog(null, "There are no such Record exist","Massage ",JOptionPane.INFORMATION_MESSAGE);
					}
					con.close();
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);

	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}


    }
    void modifyRecord()
    {
    	if(text1.length() !=0)
    	{
    		if(text2.length() !=0)
    		{
    			try
    			{
					Class.forName(jdbcDriver);
					Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/showroom","root","naman");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery("select * from car where car_name= '"+ text1 +"' and model_no= '"+ text2 +"'");
					if(result.next())
					{
						JOptionPane.showMessageDialog(null, "Car already exist.","Warring  ",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						st.executeUpdate(query1);
						mcarNameTextField1.setText("");
    					mmodelNoTextField1.setText("");
    					mcarTypeTextField1.setText("");
    					mcolorTextField1.setText("");
    					
    					JOptionPane.showMessageDialog(null, "Record Update Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);
    				}
					con.close();
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);

	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    	}
	    	else
	    	{
	    		JOptionPane.showMessageDialog(null, "Model No Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    		}
     	}
     	else
     	{
    		JOptionPane.showMessageDialog(null, "Car Name Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    

    void searchRecord()
    {
    			try
    			{
					Class.forName(jdbcDriver);
					Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/showroom","root","naman");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery(query1);
					Rows_Data.clear();
					int row=0;
					while(result.next())
					{
						row_data=new String[4];
						row=row+1;
						for(int i=0; i < 4; i++)
						{
							row_data[i]=result.getString(i+1);
						}
						Rows_Data.addElement(row_data);
					}
					if(row != 0)
					{
					JOptionPane.showMessageDialog(null, "Record Found Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						JOptionPane.showMessageDialog(null, "There are no such Record exist","Massage ",JOptionPane.INFORMATION_MESSAGE);
					}
					con.close();
					data= new Object[row][4];
					Rows_Data.copyInto(data);
					jsp.setVisible(false);
					remove(searchReportTable);
					searchReportTable = new JTable(data ,colHeaders);
					jsp = new JScrollPane(searchReportTable,v,h);
					gbc1 = gridlayout(0, 0, 0, 0);
    				grid.setConstraints(searchReportTable, gbc1);
    				jsp = new JScrollPane(searchReportTable,v,h);
					gbc1 = gridlayout(750, 320, 0, 0);
    				grid.setConstraints(jsp, gbc1);
					searchTablePanel.add(jsp);
					jsp.setBounds(0, 0, 780, 400);
					jsp.setVisible(true);

	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);

	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
    }


    
    protected void windowClosed() {

    	
        System.exit(0);
    }
    
    
     void SearchCustomerData()
    {
    			try
    			{
					Class.forName(jdbcDriver);
					Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/showroom","root","naman");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery(query1);
					Rows_Data.clear();
					int row=0;
					while(result.next())
					{
						row_data=new String[4];
						row=row+1;
						for(int i=0; i < 4; i++)
						{
							row_data[i]=result.getString(i+1);
						}
						Rows_Data.addElement(row_data);
					}
					if(row != 0)
					{
					JOptionPane.showMessageDialog(null, "Record Found Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						JOptionPane.showMessageDialog(null, "There are no such Record exist","Massage ",JOptionPane.INFORMATION_MESSAGE);
					}
					con.close();
					dataa= new Object[row][4];
					Rows_Data.copyInto(dataa);
					jsp1.setVisible(false);
					remove(searchReportTable1);
					searchReportTable1 = new JTable(dataa ,colHeaders1);
					jsp1 = new JScrollPane(searchReportTable1,v,h);
					gbc111 = gridlayout11(0, 0, 0, 0);
    				grid11.setConstraints(searchReportTable, gbc11);
    				jsp1 = new JScrollPane(searchReportTable1,v,h);
					gbc111 = gridlayout11(750, 320, 0, 0);
    				grid11.setConstraints(jsp1, gbc111);
					searchTablePanel1.add(jsp1);
					jsp1.setBounds(0, 0, 780, 400);
					jsp1.setVisible(true);

	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);

	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
    }


 
 
    
    GridBagConstraints gridlayout(int ix, int iy, int gx, int gy)
    {
    		gbc1.fill= GridBagConstraints.BOTH;
    		gbc1.ipadx = ix;
			gbc1.ipady = iy;
			gbc1.gridx = gx;
			gbc1.gridy = gy;
			return gbc1;
    }
    GridBagConstraints gridlayout1(int ix, int iy, int gx, int gy)
    {
    		gbc11.fill= GridBagConstraints.BOTH;
    		gbc11.ipadx = ix;
			gbc11.ipady = iy;
			gbc11.gridx = gx;
			gbc11.gridy = gy;
			return gbc11;
    }
     GridBagConstraints gridlayout11(int ix, int iy, int gx, int gy)
    {
    		gbc111.fill= GridBagConstraints.BOTH;
    		gbc111.ipadx = ix;
			gbc111.ipady = iy;
			gbc111.gridx = gx;
			gbc111.gridy = gy;
			return gbc111;
    }
    public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==home)
		{
		 	HomeLabel.setVisible(true);
		 	addInternalFrame.setVisible(false);
		 	modifyInternalFrame.setVisible(false);
		 	searchInternalFrame.setVisible(false);
                        searchInternalFrame1.setVisible(false);
		}
		if (ae.getSource()==add)
		{
		 	HomeLabel.setVisible(false);
		 	addInternalFrame.setVisible(true);
		 	modifyInternalFrame.setVisible(false);
		 	searchInternalFrame.setVisible(false);
                        searchInternalFrame1.setVisible(false);
		}
		if (ae.getSource()==modify)
		{
		 	HomeLabel.setVisible(false);
		 	addInternalFrame.setVisible(false);
		 	modifyInternalFrame.setVisible(true);
		 	searchInternalFrame.setVisible(false);
                        searchInternalFrame1.setVisible(false);
		}
                if (ae.getSource()==search)
		{
		 	HomeLabel.setVisible(false);
		 	addInternalFrame.setVisible(false);
		 	modifyInternalFrame.setVisible(false);
		 	searchInternalFrame.setVisible(true);
                        searchInternalFrame1.setVisible(false);
		}
                if (ae.getSource()==search1)
		{
		 	HomeLabel.setVisible(false);
		 	addInternalFrame.setVisible(false);
		 	modifyInternalFrame.setVisible(false);
		 	searchInternalFrame.setVisible(false);
                        searchInternalFrame1.setVisible(true);
		}
		
		
		if (ae.getSource()==addButton)
		{
		 	text1=carNameTextField.getText();
    		text1=text1.trim();
    		text2=modelNoTextField.getText();
    		text2=text2.trim();
    		text3=carTypeTextField.getText();
    		text3=text3.trim();
    		text4=colorTextField.getText();
    		text4=text4.trim();

    		query1 ="insert into car values( '"+text1+"' , '"+text2+"' , '"+text3+"' , '"+text4+"')";
		 	addRecordCar();
		}
                if (ae.getSource()==addButton1)
		{
		 	text1=carNameTextField1.getText();
    		text1=text1.trim();
    		text2=modelNoTextField1.getText();
    		text2=text2.trim();
    		text3=carTypeTextField1.getText();
    		text3=text3.trim();
    		text4=colorTextField1.getText();
    		text4=text4.trim();

    		query1 ="insert into customer values( '"+text1+"' , '"+text2+"' , '"+text3+"' , '"+text4+"')";
		 	addRecordCarCustomer();
		}
		if (ae.getSource()==modifyButton)
		{

			text1=mcarNameTextField.getText();
    		text1=text1.trim();
    		text2=mmodelNoTextField.getText();
    		text2=text2.trim();
    		text3=mcarTypeTextField.getText();
    		text3=text3.trim();
    		text4=mcolorTextField.getText();
    		text4=text4.trim();
    		
    		query1 ="update car set  car_name= '"+ text1 +"', model_no= '"+ text2 +"', cartype= '"+ text3 +"', color= '"+text4+"' where car_name="+idno;
    		modifyRecord();

		}
		
		if (ae.getSource()==modifySearchButton)
		{
			text1=mcarNameSearchTextField.getText();
    		text1=text1.trim();
    		text2=mmodelNoSearchTextField.getText();
    		text2=text2.trim();
    		query1 = "Select * from car where car_name= '"+ text1 +"' and model_no= '"+ text2 +"'";
		 	searchRecordForModify();
		}
		if (ae.getSource()==searchBycarNamePanelButton)
		{
		 	text1=carNameSearchTextField.getText();
    		text1=text1.trim();
    		query1 = "Select * from car where car_name= '"+ text1 +"'";
    		searchRecord();
		}
		if (ae.getSource()==searchBymodelNoButton)
		{
		 	text2=modelNoSearchTextField.getText();
    	 	text2=text2.trim();
    	 	query1 = "Select * from car where  model_no = '"+ text2 +"'";
    		searchRecord();
		}
		
		if (ae.getSource()==searchAllButton)
		{
			query1 = "select * from car";
		 	searchRecord();
		}
                
                if (ae.getSource()==searchBycarNamePanel1Button1)
		{
		 	text1=carNameSearchTextField1.getText();
    		text1=text1.trim();
    		query1 = "Select * from customer where name= '"+ text1 +"'";
    		SearchCustomerData();
		}
		if (ae.getSource()==searchBymodelNoButton1)
		{
		 	text2=modelNoSearchTextField1.getText();
    	 	text2=text2.trim();
    	 	query1 = "Select * from customer where age= '"+ text2 +"'";
    		SearchCustomerData();
		}
                
                if (ae.getSource()==searchAllButton1)
		{
			query1 = "select * from customer";
		 	SearchCustomerData();
		}
                
		if (ae.getSource()==exit)
		{
		 	System.exit(0);
		}


	}

}
public class CarShowroom {

    public static void main(String[] args) {


      	// Create application frame.
        CarShowroomFrame frame = new CarShowroomFrame();

        // Show frame.
        frame.show();


    }
}
