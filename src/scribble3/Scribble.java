package scribble3; 

import java.awt.*;
import java.awt.event.*;
import java.awt.font.GlyphVector;
import java.io.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager; 

public class Scribble extends JFrame{

	protected Box theBox,box_horiz;
	int inc=1,text=0,fill_toggle=0;
	JButton btnText,clear,line,ellipse,rect,fillBut,strokeBtn,bgc,image,select;
	Color strokeColor=Color.BLACK, fillColor=Color.BLACK;
	DrawingBoard d1;
	int toggle=0,selection=0,rect_toggle=0;
    JMenu menu; 
    JMenuItem mi;
    protected JMenuBar menuBar;
    List shapes = new ArrayList();
    JSlider slider;
    String response=null;
    JCheckBox fill;
  public Scribble(String title) {
    super(title);
    // calling factory method 
    canvas = makeCanvas(); 
    getContentPane().setLayout(new BorderLayout()); 
    menuBar = createMenuBar(); 
    theBox = createButton();
    box_horiz = createButton();
    d1=new DrawingBoard();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(theBox, BorderLayout.WEST);
    getContentPane().add(canvas, BorderLayout.CENTER);
    getContentPane().add(d1, BorderLayout.CENTER);
    
    addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent e) {
	  if (exitAction != null) { 
	    exitAction.actionPerformed(new ActionEvent(Scribble.this, 0, null)); 
	  }
	}
      }); 
  }
  
  
  
  protected Box createButton(){
	  theBox= Box.createVerticalBox();
	  box_horiz = Box.createHorizontalBox();
	  btnText = makeJButton("./src/text.png",1);
	  fill = makeJCheckBox();
	  line = makeJButton("./src/Line.png",2);
	  ellipse = makeJButton("./src/Ellipse.png",3);
	  rect = makeJButton("./src/Rectangle.png",4);
	  strokeBtn = makeJButton("./src/stroke.jpg", 1);
	  fillBut = makeColorJButton("./src/fill.png", 6, false);
	  clear = makeJButton("./src/Eraser.png",1);
	  bgc = makeColorbgcJButton("./src/Color.jpg",8);
	  image = makeColorimgJButton("./src/camera.png",9);
	  select = makeJButton("./src/selection.png",4);
	  slider = new JSlider(JSlider.HORIZONTAL, 0, 3, 2);
	  slider.setBorder(null);
	  slider.setAlignmentX(LEFT_ALIGNMENT);
	  slider.setMinorTickSpacing(10);
	  slider.setMajorTickSpacing(3);
	  slider.setPaintTicks(true);
	  slider.setPaintLabels(true);
	  slider.setPreferredSize(new Dimension(1,1));
	  
	  theBox.add(btnText);
	  theBox.add(clear);
	  theBox.add(line);
	  theBox.add(ellipse);
	  theBox.add(rect);
	  theBox.add(strokeBtn);
	  theBox.add(fillBut);
	  theBox.add(bgc);
	  theBox.add(image);
	  theBox.add(select);
	  theBox.add(slider);
	  theBox.add(fill);

	  
	return theBox;
	  
  }
  
  public JButton makeColorJButton(String icon,final int actionNum,final boolean stroke){
	  JButton Colorbtn = new JButton();
	  Icon butIcon = new ImageIcon(icon);
	  Colorbtn.setIcon(butIcon);
	  
	  Colorbtn.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
			                     if(stroke){
                                     
			                         strokeColor = JColorChooser.showDialog(null,  "Pick a Stroke", Color.BLACK);
			                  } else {
			                          fillColor = JColorChooser.showDialog(null,  "Pick a Fill", Color.BLACK);
			                  }
							}
  
			  
	  });
	  
	  return Colorbtn;
	  
  }
  public JCheckBox makeJCheckBox(){
	  JCheckBox b1 = new JCheckBox("Fill");
	  
	  b1.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(b1.isSelected()==true){
				fill_toggle=1;
			}
			else
				fill_toggle=0;
		}
		  
	  });
	  
	return b1;
	  
  }
  
  public JButton makeColorimgJButton(String icon,final int actionNum){
	  JButton Colorbtn1 = new JButton();
	  Icon butIcon1 = new ImageIcon(icon);
	  Colorbtn1.setIcon(butIcon1);
	  
	  Colorbtn1.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								JLabel label = new JLabel();
								Image img2 = null;
								int img_get = chooser.showDialog(null, "image");
								if (img_get == JFileChooser.APPROVE_OPTION){
								File img = chooser.getSelectedFile();
								if (img != null) {
								  if (img.isFile()) {
								    String filename1 = chooser.getSelectedFile().getAbsolutePath();
								    ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename1).getImage());
									Dimension size = new Dimension(getContentPane().getWidth(), getContentPane().getHeight());
									label.setPreferredSize(size);
									label.setIcon(imageIcon);
								    getContentPane().add(label);
								    
								  }
								}
								else
								   	 JOptionPane.showMessageDialog(getContentPane(),"Select the Image!","Error",JOptionPane.ERROR_MESSAGE);
								}
			                
			                          
			                          
								
							}
  
			  
	  });
	  
	  return Colorbtn1;
	  
  }
  public JButton makeColorbgcJButton(String icon,final int actionNum){
	  JButton Colorbtn1 = new JButton();
	  Icon butIcon1 = new ImageIcon(icon);
	  Colorbtn1.setIcon(butIcon1);
	  
	  Colorbtn1.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
			                     if(fillColor!=Color.black){
			                    	 getContentPane().setBackground(fillColor);
			                    	 
			                     }else
			                    	 JOptionPane.showMessageDialog(getContentPane(),"Select Color before changing Background Color!","Error",JOptionPane.ERROR_MESSAGE);
							}
  
			  
	  });
	  
	  return Colorbtn1;
	  
  }
  
  public JButton makeJButton(String iconFile, final int actionNum){
	  	            JButton Colorbtn = new JButton();
	  	            Icon butIcon = new ImageIcon(iconFile);
	  	          Colorbtn.setIcon(butIcon);
	  	             
	  	        Colorbtn.addActionListener(new ActionListener() {
	  	 
	  	                public void actionPerformed(ActionEvent e) {
	  	                    inc = actionNum;
	  	                    System.out.println("actionNum: " + inc);
	  	                    if(e.getSource().equals(clear)){
	  	                    	toggle=1;
	  	                    	System.out.println(toggle);
	  	                    	if(slider.getValue() == 3)
	  	                    		selection=25;
	  	                    	if(slider.getValue() == 2)
	  	                    		selection=15;
	  	                    	if(slider.getValue() == 1)
	  	                    		selection=5;
	  	                    	
	  	                    	System.out.println("Slider: "+slider.getValue());
	  	                    }
	  	                    if(e.getSource().equals(select)){
	  	                    	rect_toggle=1;
	  	                    }
	  	                    if(e.getSource().equals(btnText)){
	  	                    	d1.printString();
	  	                    }
	  	                     
	  	                }
	  	            });
	  	             
	  	            return Colorbtn; 
	  
  }
	  

  protected JMenuBar createMenuBar() {
  menuBar = new JMenuBar();

    // File menu 
    menu = new JMenu("File"); 
    menuBar.add(menu); 

    mi = new JMenuItem("New");
    menu.add(mi);
    mi.addActionListener(new NewFileListener()); 

    mi = new JMenuItem("Open");
    menu.add(mi);
    mi.addActionListener(new OpenFileListener()); 

    mi = new JMenuItem("Save");
    menu.add(mi);
    mi.addActionListener(new SaveFileListener()); 

    mi = new JMenuItem("Save As");
    menu.add(mi);
    mi.addActionListener(new SaveAsFileListener()); 

    menu.add(new JSeparator()); 

    exitAction = new ExitListener(); 
    mi = new JMenuItem("Exit");
    menu.add(mi);
    mi.addActionListener(exitAction); 

    // option menu
    menu = new JMenu("Option"); 
    menuBar.add(menu); 

    mi = new JMenuItem("Undo");
    menu.add(mi);
    mi.addActionListener(new UndoListener());
  
    
    mi = new JMenuItem("Color");
    menu.add(mi);
    mi.addActionListener(new ColorListener()); 
    
    mi = new JMenuItem("Font");
    menu.add(mi);
    mi.addActionListener(new FontListener()); 

    // horizontal space 
    menuBar.add(Box.createHorizontalGlue());

    // Help menu 
    menu = new JMenu("Help"); 
    menuBar.add(menu); 

    mi = new JMenuItem("About");
    menu.add(mi);
    mi.addActionListener(new AboutListener()); 
    return menuBar; 
  }

  // factory method 
  protected ScribbleCanvas makeCanvas() {
    return new ScribbleCanvas(); 
  }

  protected void newFile() { 
    currentFilename = null; 
    d1.newFile(); 
    setTitle("Scribble Pad");
  }

  protected void openFile(String filename) { 
    currentFilename = filename; 
    d1.openFile(filename);
    try{
    setTitle("Scribble Pad [" + currentFilename + "]"); 
    }
    catch(NoSuchElementException e){
    	JOptionPane.showMessageDialog(null, "No File Found!");
    }
  }

  protected void saveFile() { 
    if (currentFilename == null) {
      currentFilename = "Untitled"; 
    }
    d1.saveFile(currentFilename); 
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  protected void saveFileAs(String filename) { 
    currentFilename = filename; 
    d1.saveFile(filename); 
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  class UndoListener implements ActionListener { 
	    
	    public void actionPerformed(ActionEvent e) {
	    	d1.undo();
	    }

	  }

  class NewFileListener implements ActionListener { 
	    
	    public void actionPerformed(ActionEvent e) {
	      newFile(); 
	    }

	  }
  
  
  class OpenFileListener implements ActionListener { 

    public void actionPerformed(ActionEvent e) {
      int retval = chooser.showDialog(null, "Open");
      if (retval == JFileChooser.APPROVE_OPTION) {
	File theFile = chooser.getSelectedFile();
	if (theFile != null) {
	  if (theFile.isFile()) {
	    String filename = chooser.getSelectedFile().getAbsolutePath();
	    openFile(filename); 
	  }
	}
      }
    }

  }

  class SaveFileListener implements ActionListener { 
    
    public void actionPerformed(ActionEvent e) {
      saveFile(); 
    }

  }

  class SaveAsFileListener implements ActionListener { 

    public void actionPerformed(ActionEvent e) {
      int retval = chooser.showDialog(null, "Save As");
      if (retval == JFileChooser.APPROVE_OPTION) {
	File theFile = chooser.getSelectedFile();
	if (theFile != null) {
	  if (!theFile.isDirectory()) {
	    String filename = chooser.getSelectedFile().getAbsolutePath();
	    saveFileAs(filename); 
	  }
	}
      }
    }

  }

  class ExitListener implements ActionListener { 
    
    public void actionPerformed(ActionEvent e) {
      int result = JOptionPane.showConfirmDialog(null,
						 "Do you want to exit Scribble Pad?", 
						 "Exit Scribble Pad?",
						 JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
	saveFile(); 
	System.exit(0); 
      }
    }

  }

  class ColorListener implements ActionListener { 
    
    public void actionPerformed(ActionEvent e) {
      Color result = dialog.showDialog();
      if (result != null) { 
	canvas.setCurColor(result);
      }
    }

    protected ColorDialog dialog = 
      new ColorDialog(Scribble.this, "Choose color", canvas.getCurColor());

  }
  
  class FontListener implements ActionListener { 
	    
	    public void actionPerformed(ActionEvent e) {
		  	
		      FontDialog.main(null);

	    }

	  }

  class AboutListener implements ActionListener { 
    
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(null, 
				    "DrawingPad version 1.0\nCopyright (c) Sneha Priya Ale 2015", "About", 
				    JOptionPane.INFORMATION_MESSAGE); 
    }

  }

  protected ScribbleCanvas canvas; 
//  protected JMenuBar menuBar; 

  protected String currentFilename = null; 
  protected ActionListener exitAction; 
  protected JFileChooser chooser = new JFileChooser(".");

  public static void main(String[] args) {
    JFrame frame = new Scribble("Scribble Pad");
    frame.setSize(width, height);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width/2 - width/2,
		      screenSize.height/2 - height/2);
    frame.show();
  }

  protected static int width = 600; 
  protected static int height = 400; 

	            
	            public class DrawingBoard extends JComponent{
	            	
	  				ArrayList<Shape> shapes = new ArrayList<Shape>();
	                ArrayList<Color> shapeFill = new ArrayList<Color>();
		            ArrayList<Color> shapeStroke = new ArrayList<Color>();
		            Point drawStart, drawEnd;
					String response;
					int text;
		            Font font = new Font("Lucida Sans Regular", Font.PLAIN, 50);
		            FontDialog FD = new FontDialog();
					
		            public DrawingBoard(){
		            	this.addMouseListener(new MouseAdapter(){
		            		public void mousePressed(MouseEvent e){
		            			if(inc!=1){
		            			drawStart = new Point(e.getX(), e.getY());
		            			drawEnd = drawStart;
		            			 repaint();
		            			}
		            			
		            		}
		            		public void mouseReleased(MouseEvent e){
		            			  if(inc != 1){
			  	                                  // Create a shape using the starting x & y
			  	                                  // and finishing x & y positions
			  	                                 
			  	                                Shape aShape = null;
			  	                                 
			  	                                if (inc == 2){
			  	                                    aShape = drawLine(drawStart.x, drawStart.y,
			  	                                            e.getX(), e.getY());
			  	                                  shapes.add(aShape);
			  	                                  if(fill.isSelected()==false)
			  	                                	  fillColor= getContentPane().getBackground();
			  	                                  shapeFill.add(fillColor);
			  	                                  shapeStroke.add(strokeColor);
			  	                                } else
			  	                                 
			  	                                if (inc == 3){
			  	                                    aShape = drawEllipse(drawStart.x, drawStart.y,
			  	                                            e.getX(), e.getY());
			  	                                  shapes.add(aShape);

			  	                                if(fill.isSelected()==false)
			  	                                	  fillColor= getContentPane().getBackground();
			  	                                  shapeFill.add(fillColor);
			  	                                  shapeStroke.add(strokeColor);
			  	                                } else
			  	                                 
			  	                                if (inc == 4) {
			  	                                     
			  	                                    // Create a new rectangle using x & y coordinates
			  	                                    aShape = drawRectangle(drawStart.x, drawStart.y,
			  	                                            e.getX(), e.getY());
			  	                                     if(rect_toggle==1){
			  	                                  fillColor= getContentPane().getBackground();
			  	                                strokeColor= getContentPane().getBackground();
			  	                                     }
			  	                                    	 
					  	                                if(fill.isSelected()==false)
					  	                                	  fillColor= getContentPane().getBackground();
					  	                                  shapeFill.add(fillColor);
				  	                                  shapeStroke.add(strokeColor);
					  	                                  shapes.add(aShape);
			  	                                }
			  	                                 
			  	                                 
			  	                                   
			  	                                  drawStart = null;
			  	                                  drawEnd = null;
		            				  
		            			 
		            			 repaint();
		            			  }
		            		}
		            	});
		            	this.addMouseMotionListener(new MouseMotionAdapter(){
		            		public void mouseDragged(MouseEvent e){
	                            if(inc == 1){
            		                                int x = e.getX();
            		                                int y = e.getY();
            		                                 
            		                                Shape aShape = null;
            		                                 
            		                                // Make stroke and fill equal to eliminate the fact that this is an ellipse
            		                                if((toggle==1)) 
            		                                {
            		                                	strokeColor= getContentPane().getBackground();
            		                                	fillColor= getContentPane().getBackground();
            		                                	aShape = drawBrush(x,y,selection,selection);
            		                                	shapes.add(aShape);
            		                                	shapeFill.add(fillColor);
            		                                	shapeStroke.add(strokeColor);
            		                                	
            		                                }
            		                                else{
            		                                strokeColor = fillColor;
            		                                 
            		                                aShape = drawBrush(x,y,selection,selection);
            		                                 
            		                                shapes.add(aShape);
            		                                  shapeFill.add(fillColor);
            		                                  shapeStroke.add(strokeColor);
            		                                }
            		                            }  
            		                            drawEnd = new Point(e.getX(), e.getY());
            	
		            			repaint();
		            		}
		            	});
		            	
		            }
		            
					public void printString() {
						// TODO Auto-generated method stub
						ActionEvent e = null;
	                    response = JOptionPane.showInputDialog("Enter the Text you want to Print.");
	                    text=1;
						repaint();
						
					}
					public void saveFile(String filename) {
						// TODO Auto-generated method stub
					    try {
					        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)); 
					        out.writeObject(shapes); 
					        out.close(); 
					        System.out.println("Save drawing to " + filename);
					      } catch (IOException e) {
					        System.out.println("Unable to write file: " + filename); 
					      } 
						
					}
					public void newFile() {
						// TODO Auto-generated method stub
					    shapes.clear();
					    repaint();
						
					}
					public void openFile(String filename) {
						// TODO Auto-generated method stub
							    try {
							      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)); 
							      shapes = (ArrayList<Shape>) in.readObject(); 
							      in.close(); 
							      repaint();
							    } catch (IOException e1) {
							      System.out.println("Unable to open file: " + filename); 
							    } catch (ClassNotFoundException e2) {
							      System.out.println(e2); 
							    }
							  
						
					}
					public void undo() {
						// TODO Auto-generated method stub
						if(shapes.size()==0)
							JOptionPane.showMessageDialog(null,"You didn't start drawing!!", "ERROR", JOptionPane.ERROR_MESSAGE);
						else{
						shapes.remove(shapes.size() - 1);
						repaint();
						}
						
						
					}
					public void paint(Graphics g)
	                {
						Graphics2D graphSettings;   
	                        graphSettings = (Graphics2D)g;
	                         
	                        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                                RenderingHints.VALUE_ANTIALIAS_ON);
	                         
	                        graphSettings.setStroke(new BasicStroke(4));
	 
	                        Iterator<Color> strokeCounter = shapeStroke.iterator();
	                        Iterator<Color> fillCounter = shapeFill.iterator();
	                         
	                         
	                        graphSettings.setComposite(AlphaComposite.getInstance(
	                                AlphaComposite.SRC_OVER, 1.0f));
	                         
	                        
	            			if(text==1){
	            				Shape ashape=null;
	            				graphSettings.setColor(fillColor);
	    	                    GlyphVector vect = font.createGlyphVector(graphSettings.getFontRenderContext(), response);
	    	                    ashape = vect.getOutline(0f, (float) -vect.getVisualBounds().getY());
	            				graphSettings.draw(ashape);
	            				
	            			}
	                        
	                        for (Shape s : shapes)
	                        {
	                            // Grabs the next stroke from the color arraylist
	                            graphSettings.setPaint(strokeCounter.next());
	                             
	                            graphSettings.draw(s);
	                            
	                            
	                             
	                            // Grabs the next fill from the color arraylist
	                            graphSettings.setPaint(fillCounter.next());
	                             
	                            graphSettings.fill(s);
	                            
	                        }
	 
	                        // Guide shape used for drawing
	                        if (drawStart != null && drawEnd != null)
	                        {
	                            // Makes the guide shape transparent
	                             
	                            graphSettings.setComposite(AlphaComposite.getInstance(
	                                    AlphaComposite.SRC_OVER, 0.40f));
	                             
	                            // Make guide shape gray for professional look                            
	                            graphSettings.setPaint(Color.LIGHT_GRAY);
	                            Shape ashape=null;
	                            if (inc == 2){
	                            	ashape = drawLine(drawStart.x, drawStart.y,drawEnd.x, drawEnd.y);
	                            }else if (inc == 3){
	                            	ashape = drawEllipse(drawStart.x, drawStart.y,drawEnd.x, drawEnd.y);
	                            }else if(inc == 4){
	                            	ashape = drawRectangle(drawStart.x, drawStart.y,drawEnd.x, drawEnd.y);
	                            }
	                             
	                            // Create a new rectangle using x & y coordinates
	                             
	                                graphSettings.draw(ashape);
	                        }
	                }

					private Rectangle2D.Float drawRectangle(
	                        int x1, int y1, int x2, int y2)
	                {
	                    // Get the top left hand corner for the shape
	                    // Math.min returns the points closest to 0
	                     
	                        int x = Math.min(x1, x2);
	                        int y = Math.min(y1, y2);
	                         
	                        // Gets the difference between the coordinates and
	                         
	                        int width = Math.abs(x1 - x2);
	                        int height = Math.abs(y1 - y2);
	 
	                        return new Rectangle2D.Float(
	                                x, y, width, height);
	                }
	                
	                 
	                // The other shapes will work similarly
	                // More on this in the next tutorial
	                 
	                private Ellipse2D.Float drawEllipse(
	                        int x1, int y1, int x2, int y2)
	                {
	                        int x = Math.min(x1, x2);
	                        int y = Math.min(y1, y2);
                        int width = Math.abs(x1 - x2);
	                        int height = Math.abs(y1 - y2);
	 
	                        return new Ellipse2D.Float(
	                                x, y, width, height);
	                }
	                
	                private Line2D.Float drawLine(int x1, int y1, int x2, int y2){
	                	return new Line2D.Float(x1, y1, x2, y2);
	                }
	                private Ellipse2D.Float drawBrush(int x1, int y1, int brushStrokeWidth, int brushStrokeHeight){
	                	return new Ellipse2D.Float(x1, y1, brushStrokeWidth, brushStrokeHeight);
	                }
	                

	            



		            }
        }


