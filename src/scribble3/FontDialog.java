package scribble3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
  
public class FontDialog implements ActionListener
{
    JComboBox fontCombo;
    JLabel label;
    Font font;
    JButton okay;
    String resp=null,response=null;
    Scribble s1;
  
    public FontDialog() {
		// TODO Auto-generated constructor stub
    	
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        fontCombo = new JComboBox(fontNames);
        fontCombo.addActionListener(this);
        okay = new JButton("OK");
        
        resp = response;
        System.out.println("RESP "+resp+" response: "+response);
        okay.addActionListener(this);
	}

	public FontDialog(String response) {
		// TODO Auto-generated constructor stub
		this.response = response;
	}

	public void actionPerformed(ActionEvent e)
    {
        String resp = (String)fontCombo.getSelectedItem();
        font = Font.decode(resp).deriveFont(24f);
        label.setFont(font);
        label.setText(resp);
        System.out.println("FONT"+font);
        getLabel();
    }
  
    JPanel getPanel()
    {
        JPanel panel = new JPanel();
        panel.add(fontCombo);
        panel.add(okay);
        return panel;
    }
  
    Font getLabel()
    {
        String resp = (String)fontCombo.getItemAt(0);
        font = new Font(resp, Font.PLAIN, 24);
        label = new JLabel(resp, JLabel.CENTER)
        {
            protected void paintComponent(Graphics g)
            {
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                                 RenderingHints.VALUE_ANTIALIAS_ON);
                super.paintComponent(g);
            }
        };
        label.setFont(font);
//        return label;
        System.out.println("FONT"+font);
        return font;
    }
  
    public static void main(String[] args)
    {
        FontDialog app = new FontDialog();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(app.getPanel(), "North");
//        f.getContentPane().add(app.getLabel());
        app.getLabel();
        f.setSize(300,180);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}