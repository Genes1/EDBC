package bcProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
/**
 * This program was created for private purposes by Eugene Domrachev of Mrs.Wilson's 2017/18 P.3
 * Function parsing and evaluation was done using exp4j. No modifications to the original project were made.
 * 
 */

public class GraphFrameFinal extends JFrame
						implements ActionListener{
	LinkedHashMap<Integer, Integer> intCoords = new LinkedHashMap<Integer, Integer>();
	LinkedHashMap<Float, Float> floatCoords; //= new LinkedHashMap<Float, Float>();
	JFrame frame;
	JPanel contentPane, graphPane;
	JButton graphButton;
	boolean dvRequested = false;
	boolean intgRequested = false;
	static JTextField xMin;
	static JTextField xMax;
	static JTextField yMin;
	static JTextField yMax;
	JTextField equationText;
	Expression exp;
	private JLabel lblFx;
	private static JTextField dvText;
	private JLabel dvAnswer;
	private JLabel lblfxdx;
	private JLabel lblNewLabel;
	public JTextField btmIntg;
	public JTextField topIntg;
	private JLabel intgAnswer;
	private JButton button;
	double[] c = {0.171324492, 0.360761573, 0.467913935, 0.467913935, 0.360761573, 0.171324492};
	double[] x = {-0.932469514, -0.661209386, -0.238619186, 0.238619186, 0.661209386, 0.932469514};
	private JRadioButton radioTrapezoidal;
	private JRadioButton radioLRAM;
	private JRadioButton radioMRAM;
	private JRadioButton radioRRAM;
	public JRadioButton[] radioArr;
	private JTextField steps;
	private JLabel lblNewLabel_1;
	int radioIndex;
	private JLabel eqLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblbcProject;
	private JLabel lblNewLabel_3;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel stepslabel;
	public GraphFrameFinal(GraphPanel graphPane) {
		//System.out.println(getClass().getResource("../images/EDBC.jpg").getPath());
		//System.out.println(getClass().getClassLoader().getResourceAsStream("images\\EDBC.jpg"));
		
		
		ImageIcon EDBC = new ImageIcon(getClass().getResource("../images/EDBC.jpg") ); 
		this.setIconImage(EDBC.getImage());
		
		
		
		/*
		 * ImageIcon iconQuit = new ImageIcon(getClass().getResource("/images/quit.png") ); 

			public JMenuItem mnuItemQuit = new JMenuItem("Quit", iconQuit ); 
		 */
		
		
		
		/*//
		try{
			Image EDBC = ImageIO.read(new File("images\\EDBC.jpg"));
			this.setIconImage(EDBC);
			} catch(IOException e){}
		/**/
		
		
		floatCoords = new LinkedHashMap<Float, Float>();
		this.graphPane = graphPane;
		//frame and panel init
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		getContentPane().add(contentPane);
		getContentPane().add(graphPane);
		graphPane.setBounds(10, 10, 500, 500);
		
		
		xMin = new JTextField("-10");
		xMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		xMin.setBounds(68, 520, 45, 20);
		getContentPane().add(xMin);
		xMin.setColumns(10);		
		xMax = new JTextField("10");
		xMax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		xMax.setColumns(10);
		xMax.setBounds(192, 520, 45, 20);
		getContentPane().add(xMax);		
		yMin = new JTextField("-10");
		yMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		yMin.setColumns(10);
		yMin.setBounds(314, 520, 45, 20);
		getContentPane().add(yMin);	
		yMax = new JTextField("10");
		yMax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		yMax.setColumns(10);
		yMax.setBounds(435, 520, 45, 20);
		getContentPane().add(yMax);
		JLabel lblXMin = new JLabel("X min:");
		lblXMin.setFont(new Font("Arial", Font.PLAIN, 15));
		lblXMin.setBounds(25, 523, 45, 14);
		getContentPane().add(lblXMin);		
		JLabel lblXMax = new JLabel("X max:");
		lblXMax.setFont(new Font("Arial", Font.PLAIN, 15));
		lblXMax.setBounds(143, 522, 45, 14);
		getContentPane().add(lblXMax);		
		JLabel lblYMin = new JLabel("Y min:");
		lblYMin.setFont(new Font("Arial", Font.PLAIN, 15));
		lblYMin.setBounds(270, 523, 45, 14);
		getContentPane().add(lblYMin);		
		JLabel lblYMax = new JLabel("Y max:");
		lblYMax.setFont(new Font("Arial", Font.PLAIN, 15));
		lblYMax.setBounds(387, 522, 45, 14);
		getContentPane().add(lblYMax);
		
		
		//textfield
		equationText = new JTextField();
		equationText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		equationText.setBounds(618, 118, 164, 25);
		getContentPane().add(equationText);
		equationText.setColumns(10);
		
		//button
		graphButton = new JButton("Graph");
		graphButton.addActionListener(this);
		graphButton.setActionCommand("graph");
		graphButton.setToolTipText("Graph the equation.");
		graphButton.setBounds(609, 168, 109, 23);
		getContentPane().add(graphButton);
		
		JLabel fxlabel = new JLabel("f(x) =");
		fxlabel.setFont(new Font("Arial", Font.PLAIN, 30));
		fxlabel.setBounds(541, 102, 68, 55);
		getContentPane().add(fxlabel);
		
		lblFx = new JLabel("f'(       ) = ");
		lblFx.setFont(new Font("Arial", Font.PLAIN, 30));
		lblFx.setBounds(541, 226, 155, 25);
		getContentPane().add(lblFx);
		
		dvText = new JTextField();
		dvText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dvText.setBounds(565, 226, 58, 25);
		getContentPane().add(dvText);
		dvText.setColumns(10);
		
		dvAnswer = new JLabel("");
		dvAnswer.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		dvAnswer.setFont(new Font("Arial", Font.PLAIN, 30));
		dvAnswer.setBounds(664, 226, 148, 25);
		getContentPane().add(dvAnswer);
		
		JButton dxCalcButton = new JButton("Calculate");
		dxCalcButton.addActionListener(this);
		dxCalcButton.setToolTipText("Calculate the derivative at the specified point.");
		dxCalcButton.setActionCommand("dv");
		dxCalcButton.setBounds(609, 277, 109, 23);
		getContentPane().add(dxCalcButton);
		
		lblfxdx = new JLabel("f(x)dx ");
		lblfxdx.setFont(new Font("Arial", Font.PLAIN, 30));
		lblfxdx.setBounds(566, 348, 94, 25);
		getContentPane().add(lblfxdx);
		
		lblNewLabel = new JLabel("\u222B");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(543, 323, 36, 65);
		getContentPane().add(lblNewLabel);
		
		btmIntg = new JTextField();
		btmIntg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btmIntg.setBounds(518, 385, 31, 20);
		getContentPane().add(btmIntg);
		btmIntg.setColumns(10);
		
		topIntg = new JTextField();
		topIntg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		topIntg.setColumns(10);
		topIntg.setBounds(560, 317, 31, 20);
		getContentPane().add(topIntg);
		
		intgAnswer = new JLabel("");
		intgAnswer.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		intgAnswer.setFont(new Font("Arial", Font.PLAIN, 30));
		intgAnswer.setBounds(670, 348, 137, 25);
		getContentPane().add(intgAnswer);
		
		button = new JButton("Calculate");

		button.addActionListener(this);
		button.setToolTipText("Calculate the integral over the specified interval.");
		button.setActionCommand("intg");
		button.setBounds(609, 384, 109, 23);
		getContentPane().add(button);
		
		JRadioButton radioActual = new JRadioButton("Actual");
		radioActual.setOpaque(false);
		radioActual.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		radioActual.setSelected(true);
		radioActual.setFont(new Font("Arial", Font.PLAIN, 11));
		radioActual.setBounds(520, 453, 102, 23);
		radioActual.setActionCommand("check");
		radioActual.addActionListener(this);
		getContentPane().add(radioActual);
		
		radioTrapezoidal = new JRadioButton("Trapezoidal");
		radioTrapezoidal.setOpaque(false);
		radioTrapezoidal.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		radioTrapezoidal.setFont(new Font("Arial", Font.PLAIN, 11));
		radioTrapezoidal.setBounds(520, 495, 102, 23);
		radioTrapezoidal.setActionCommand("check");
		radioTrapezoidal.addActionListener(this);
		getContentPane().add(radioTrapezoidal);
		
		radioLRAM = new JRadioButton("LRAM");
		radioLRAM.setOpaque(false);
		radioLRAM.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		radioLRAM.setFont(new Font("Arial", Font.PLAIN, 11));
		radioLRAM.setBounds(645, 436, 102, 23);
		radioLRAM.setActionCommand("check");
		radioLRAM.addActionListener(this);
		getContentPane().add(radioLRAM);
		
		radioMRAM = new JRadioButton("MRAM");
		radioMRAM.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		radioMRAM.setFont(new Font("Arial", Font.PLAIN, 11));
		radioMRAM.setBounds(645, 475, 102, 23);
		radioMRAM.setActionCommand("check");
		radioMRAM.addActionListener(this);
		getContentPane().add(radioMRAM);
		radioMRAM.setOpaque(false);
		
		radioRRAM = new JRadioButton("RRAM");
		radioRRAM.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		radioRRAM.setFont(new Font("Arial", Font.PLAIN, 11));
		radioRRAM.setBounds(645, 515, 102, 23);
		radioRRAM.setActionCommand("check");
		radioRRAM.addActionListener(this);
		radioRRAM.setOpaque(false);
		getContentPane().add(radioRRAM);
		
		steps = new JTextField();
		steps.setFont(new Font("Tahoma", Font.PLAIN, 12));
		steps.setBounds(740, 474, 31, 25);
		getContentPane().add(steps);
		steps.setColumns(10);
		steps.setVisible(false);
		
		lblNewLabel_1 = new JLabel("steps");
		lblNewLabel_1.setBounds(739, 499, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		eqLabel = new JLabel("=");
		eqLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		eqLabel.setBounds(644, 348, 67, 25);
		getContentPane().add(eqLabel);
		
		lblNewLabel_2 = new JLabel("<html><font color='4171BB'>Eugene Domrachev </font></html>");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(562, 33, 293, 65);
		getContentPane().add(lblNewLabel_2);
		
		lblbcProject = new JLabel("<html><font color='4171BB'>BC Project</font></html>");
		lblbcProject.setFont(new Font("Arial", Font.PLAIN, 30));
		lblbcProject.setBounds(598, -31, 293, 132);
		getContentPane().add(lblbcProject);
		
		lblNewLabel_3 = new JLabel("<html><font color='b9d1ea'>\t\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588</font></html>");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 76));
		lblNewLabel_3.setBounds(543, 11, 264, 80);
		getContentPane().add(lblNewLabel_3);
		
		label = new JLabel("<html><font color='b9d1ea'>\t\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588</font></html>");
		label.setFont(new Font("Arial Black", Font.PLAIN, 76));
		label.setBounds(664, 220, 137, 37);
		getContentPane().add(label);
		
		label_1 = new JLabel("<html><font color='b9d1ea'>\t\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588</font></html>");
		label_1.setFont(new Font("Arial Black", Font.PLAIN, 76));
		label_1.setBounds(665, 340, 137, 37);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("<html><font color='b9d1ea'>\t\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588</font></html>");
		label_2.setFont(new Font("Arial Black", Font.PLAIN, 94));
		label_2.setBounds(520, 410, 198, 130);
		getContentPane().add(label_2);
		
		stepslabel = new JLabel("<html><font color='b9d1ea'>\t\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588</font></html>");
		stepslabel.setFont(new Font("Arial Black", Font.PLAIN, 94));
		stepslabel.setBounds(711, 453, 85, 65);
		getContentPane().add(stepslabel);
		stepslabel.setVisible(false);
		

		lblNewLabel_1.setVisible(false);
		
		radioArr = new JRadioButton[] {radioActual, radioTrapezoidal, radioLRAM, radioMRAM, radioRRAM};
		
		this.graphPane.repaint();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String eventName = e.getActionCommand();
		floatCoords.clear();

		String userEq = equationText.getText();
		//System.out.println(eventName);
		
		if (eventName.equals("graph") && !userEq.equals("") ){
			
			floatCoords.clear();
			for (float i = (float) (Double.parseDouble(xMin.getText())); i <= (float) (Double.parseDouble(xMax.getText())); i += ( Double.parseDouble(xMax.getText()) - Double.parseDouble(xMin.getText()) )/500){
				Expression exp = new ExpressionBuilder(userEq)
				        .variables("x")
				        .build()
				        .setVariable("x", i);
				floatCoords.put(i, (float) exp.evaluate());
			}		
		    graphPane.repaint();
			dvAnswer.setText("");
			dvText.setText("");
			intgAnswer.setText("");
			btmIntg.setText("");
			topIntg.setText("");
			eqLabel.setText("=");
			steps.setText("");

		}
		//TODO catch error
		if (eventName.equals("dv") /*&& (!userEq.equals("") && !dvText.getText().equals(""))*/){
			double dvDouble = Double.parseDouble(dvText.getText());
			Expression exp1 = new ExpressionBuilder(userEq)
			        .variables("x")
			        .build()
			        .setVariable("x", (dvDouble + 0.0000000001));
			Expression exp2 = new ExpressionBuilder(userEq)
				    .variables("x")
				    .build()
				    .setVariable("x", (dvDouble  - 0.0000000001));
			//System.out.println( (exp1.evaluate() + " " + exp2.evaluate()));
			dvAnswer.setText( Double.toString((exp1.evaluate() - exp2.evaluate()) / ((dvDouble + 0.0000000001) - (dvDouble  - 0.0000000001))));
			//dvRequested = true;
		}
		
		//TODO create green areas 
		if (eventName.equals("intg")){
			
			if( ( !btmIntg.getText().equals("") && !btmIntg.getText().equals("") ) /*&& (Double.parseDouble(btmIntg.getText()) < Double.parseDouble(topIntg.getText()))*/){
				double top = Double.parseDouble(topIntg.getText());
				double bot = Double.parseDouble(btmIntg.getText());
				int count = 0;
				double intgSum = 0;
				if(radioIndex == 0){    //actual
					double coeff = (Double.parseDouble(topIntg.getText()) - Double.parseDouble(btmIntg.getText()))/2;
					double coeff2 = (Double.parseDouble(topIntg.getText()) + Double.parseDouble(btmIntg.getText()))/2;
					int counter = 0;
					for (double i : x){
						Expression exp = new ExpressionBuilder(userEq)
						        .variables("x")
						        .build()
						        .setVariable("x", coeff*x[counter] + coeff2);
						intgSum += c[counter]*(exp.evaluate());
						counter ++;		
					}
					intgSum *= coeff;
					eqLabel.setText("=");
				}else if(radioIndex == 1){		//trapezoidal
					double interval = (top-bot)/Double.parseDouble(steps.getText());
					double sum = 0;
					for (double i = bot; i <= top; i +=interval ){
						Expression exp = new ExpressionBuilder(userEq)
						        .variables("x")
						        .build()
						        .setVariable("x", i);
						if((count!=0) && (count!=Integer.parseInt(steps.getText()))){
							sum += 2*exp.evaluate();
						} else {
							sum += exp.evaluate();
						}
						count++;		
					}
					intgSum = sum *(interval/2);
					eqLabel.setText("\u2248");
				}else if(radioIndex == 2){		//LRAM	
					double interval = (top-bot)/Double.parseDouble(steps.getText()); //interval is delta x
					double sum = 0;
					for (double i = bot; i < top; i +=interval ){
						Expression exp = new ExpressionBuilder(userEq)
						        .variables("x")
						        .build()
						        .setVariable("x", i);
						sum += exp.evaluate();
						count++;		
					}
					intgSum = sum *interval;
					eqLabel.setText("\u2248");
				}else if(radioIndex == 3){		//MRAM
					double interval = (top-bot)/Double.parseDouble(steps.getText()); //interval is delta x
					double sum = 0;
					double temp = bot; 
					for (double i = bot +interval ; i <= top; i +=interval ){
						Expression exp = new ExpressionBuilder(userEq)
						        .variables("x")
						        .build()
						        .setVariable("x", (i + temp)/2);
						sum += exp.evaluate();
						temp = i;
						count++;		
					}
					intgSum = sum *interval;
					eqLabel.setText("\u2248");
				}else if(radioIndex == 4){		//RRAM
					double interval = (top-bot)/Double.parseDouble(steps.getText()); //interval is delta x
					double sum = 0;
					for (double i = bot + interval; i <= top; i +=interval ){
						Expression exp = new ExpressionBuilder(userEq)
						        .variables("x")
						        .build()
						        .setVariable("x", i);
						sum += exp.evaluate();
						count++;		
					}
					intgSum = sum *interval;
					eqLabel.setText("\u2248");
				}
				intgAnswer.setText(Double.toString(intgSum));
			}
		}
		
		if (eventName.equals("check")){
			steps.setVisible(false);
			stepslabel.setVisible(false);
			lblNewLabel_1.setVisible(false);
			int counter = 0;
			JRadioButton temp = (JRadioButton) e.getSource();
			//System.out.println(e.getSource());
			for(JRadioButton i : radioArr){
				if(!i.equals(temp)){
					i.setSelected(false);
				} else if (counter > 0 ) {
					radioIndex = counter;
					steps.setVisible(true);
					stepslabel.setVisible(true);
					lblNewLabel_1.setVisible(true);
				} else if (counter ==0){
					radioIndex = 0;
				}
				counter++;
			}
		}
		
	}
	 

	  
	static class GraphPanel extends JPanel {
	    Color green = new Color(102,255,102);
	    Color red = new Color(255, 102,102);
		LinkedHashMap<Integer, Integer> intCoords = new LinkedHashMap<Integer, Integer>();		
		LinkedHashMap<Float, Float> roundedCoords = new LinkedHashMap<Float, Float>();
		LinkedHashMap<Float, Float> floatCoords = new LinkedHashMap<Float, Float>();
		//Collection<Float> elements = floatCoords.values();
		//Collection<Float> keys = floatCoords.keySet();
		GraphFrameFinal graphFrame;
		static int numAct = 0;
		
		  
		  public LinkedHashMap<Integer, Integer> getIntCoords(LinkedHashMap<Float, Float> floatCoords){
			  	 intCoords.clear();
				for (int i =  Integer.parseInt(xMin.getText()); i <= Integer.parseInt(xMax.getText()); i += 1){
					
					double x = 0;
					try {
						Expression exp = new ExpressionBuilder(graphFrame.equationText.getText())
						        .variables("x")
						        .build()
						        .setVariable("x", i);
						x = exp.evaluate();
					} catch (ArithmeticException e) {
						
					}
					
		
							intCoords.put(i, (int) x);
						 /*else {if(x < 0){intCoords.put(i, Integer.parseInt(yMin.getText()) - 15);} else {intCoords.put(i, Integer.parseInt(yMax.getText()) + 15);}}*/ 
					}
				return intCoords;
		  }
		  
		  public void getFloatCoords(GraphFrameFinal graphFrame){
			  this.graphFrame = graphFrame;
			  this.floatCoords = graphFrame.floatCoords;
			  //System.out.println(graphFrame.floatCoords.get(1f) + " <- frame floatcoords panel floatcoords ->" + floatCoords.get(1f));
		  }
		  
		  public void paintComponent(Graphics g2) {
			    numAct++;
			    
			    double xMinText = Double.parseDouble(xMin.getText());
			    double xMaxText = Double.parseDouble(xMax.getText());
			    double yMinText = Double.parseDouble(yMin.getText());
			    double yMaxText = Double.parseDouble(yMax.getText());
			    int rangeX = (int) (Double.parseDouble(xMax.getText()) - Double.parseDouble(xMin.getText()));
			    int rangeY = (int) (Double.parseDouble(yMax.getText()) - Double.parseDouble(yMin.getText()));
			    super.paintComponent(g2);
			    Graphics2D g = (Graphics2D) g2;
			    g.setColor(Color.GRAY);
		    	g.drawLine(0, 0, 0, 499); //left side
		    	g.drawLine(0, 0, 499, 0); //top side
		    	g.drawLine(0, 499, 499, 499); //bot side
		    	g.drawLine(499, 0, 499, 499); //right side
			    g.setColor(Color.black);
			    if (numAct > 1){
				    if ( ( ((int) xMinText == 0) || ( (int) xMaxText  == 0)) || ((xMinText < 0 && xMaxText > 0)) ){
				    	//VERTICAL
				    	if ((int) xMinText == 0){
				    		g.drawLine(0, 0, 0, 500);
				    	} else if ((int) xMaxText == 0){
				    		g.drawLine(499, 0, 499, 499);
				    	} else {
				    			g.drawLine( (int) (500*(Math.abs(xMinText)/rangeX)), 0, (int) (500*(Math.abs(xMinText)/rangeX)) , 500); 
				    	}

					 if ( ( ((int) yMinText == 0) || ( (int) yMaxText  == 0)) || ((yMinText < 0 && yMaxText > 0)) ){
						 //HORIZONTAL
					    if ((int) yMinText == 0){
					    	g.drawLine(0, 499, 500, 499);
					    } else if ((int) yMaxText == 0){
					   		g.drawLine(0, 0, 500, 0);
					   	} else{
					   		g.drawLine( 0, 500 - (int) (500*(Math.abs(yMinText)/rangeY)), 500, 500 - (int) (500*(Math.abs(yMinText)/rangeY))); 
					    	}
					    }
					 //GRAPHING
					 //TODO logx broken
					 this.floatCoords = graphFrame.floatCoords;
					 g.setStroke(new BasicStroke(1f));
					 g.setColor(Color.blue);
					 getFloatCoords(graphFrame);
					  Collection<Float> keys = floatCoords.keySet();
					  int counter = 0;
					  int oldY = 0;
					  for(float i : keys){
					  		
					  		//DRAW POINTS if( intCoords.get(i) == (int) (intCoords.get(i)) ){g.fillOval(i + graphSize/2, (graphSize/2 - intCoords.get(i)), 2, 2 );}
					  		if (counter > 0){
					  			
					  			g.drawLine( counter, oldY, counter+1, ( (int) (500 - 500*( (floatCoords.get(i) + Math.abs(yMinText) ) / (rangeY))))); 
					  			
					  		}
					  		
					  		/*integral graphing
							if(!graphFrame.btmIntg.getText().equals("") && !graphFrame.topIntg.getText().equals("")){	
								System.out.println("here");
								double btmIntgText = Double.parseDouble(graphFrame.btmIntg.getText());
							    double topIntgText = Double.parseDouble(graphFrame.topIntg.getText());
								g.setStroke(new BasicStroke(2f));
								if ( btmIntgText < topIntgText){
									if ( (i > btmIntgText) && (i < topIntgText) ){
										g.setColor(green);
										g.drawLine(counter, 250, counter, ( (int) (500 - 500*( (floatCoords.get(i) + Math.abs(yMinText) ) / (rangeY)))));
									} 
								} else{
									if ( (i < btmIntgText) && (i > topIntgText) ){
										g.setColor(red);
										g.drawLine(counter, 250, counter, ( (int) (500 - 500*( (floatCoords.get(i) + Math.abs(yMinText) ) / (rangeY)))));
									}
								}
								g.setStroke(new BasicStroke(1f));
								g.setColor(Color.blue);
							}
							*/
					  		counter++;
					  		oldY = (int) (500 - 500*( (floatCoords.get(i) + Math.abs(yMinText) ) / (rangeY )));
					  	    
					  };

					  /* TODO fix derivative slope grapher
					  System.out.println(graphFrame.getdvReq() + " testing for dv in paint comp");
				  	  if (graphFrame.getdvReq()){
				  		double dvNum = Double.parseDouble(GraphFrame.dvText.getText());
				  		Expression exp1 = new ExpressionBuilder(graphFrame.equationText.getText())
						        .variables("x")
						        .build()
						        .setVariable("x", (double) (dvNum - rangeX/16) );
				  		Expression exp2 = new ExpressionBuilder(graphFrame.equationText.getText())
						        .variables("x")
						        .build()
						        .setVariable("x", (double) (dvNum + rangeX/16) );
				  		  g.setColor(Color.red);
				  		  //(int) (500 - 500*( (exp1.evaluate() + Math.abs(yMinText) ) / (rangeY)))
				  		System.out.println( (int) ( 500 - 500*( (dvNum - 20 * (dvNum/rangeX) + Math.abs(xMinText) )/rangeX) ) + " " + (int) (500 - 500*( (exp1.evaluate() + Math.abs(yMinText) ) / (rangeY)))+ " " + (int) (500*( (dvNum + 20 * (dvNum/rangeX) + Math.abs(xMinText) )/rangeX) ) + " " + (int) (500 - 500*( (exp2.evaluate() + Math.abs(yMinText) ) / (rangeY))));
				  		  g.drawLine((int) (500*( (dvNum - 20 * (dvNum/rangeX) + Math.abs(xMinText) )/rangeX) ), 
				  				     (int) (500 - 500*( (exp1.evaluate() + Math.abs(yMinText) ) / (rangeY))), 
				  				     (int) (500*( (dvNum + 20 * (dvNum/rangeX) + Math.abs(xMinText) )/rangeX) ), 
				  				     (int) (500 - 500*( (exp2.evaluate() + Math.abs(yMinText) ) / (rangeY))));
				  	  }
				  	  */
					  
				    }
			    } else {
			    	//FIRST TIME STARTUP
			    	g.drawLine(250, 0, 250, 500);
			    	g.drawLine(0, 250, 500, 250);
			    	g.setColor(Color.GRAY);
			    	g.drawLine(0, 0, 0, 499); //left side
			    	g.drawLine(0, 0, 499, 0); //top side
			    	g.drawLine(0, 499, 499, 499); //bot side
			    	g.drawLine(499, 0, 499, 499); //right side
			    	
			    }
			  
			   
		  }
		  
	}
	  
	  
	public static void main(String[] args) {
		GraphPanel graphPane = new GraphPanel();
		GraphFrameFinal frame = new GraphFrameFinal(graphPane);
		graphPane.getFloatCoords(frame);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(820, 585);	
		frame.setVisible(true);

	}
}
