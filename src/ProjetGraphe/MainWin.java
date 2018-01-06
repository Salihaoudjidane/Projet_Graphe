    package ProjetGraphe;
	import javax.imageio.ImageIO;
	import javax.swing.*;
	import javax.swing.border.EmptyBorder;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.IOException;
	import java.awt.GridLayout; 


public class MainWin extends JPanel {

	    private Graphe graph;
	    private GraphPanel graphPanel;

public MainWin(){
	        super.setLayout(new BorderLayout());
	        setGraphPanel();
	    }
//------------------------------------------------------------------------------------------------------------------------------
//
	    private void setGraphPanel(){
	        graph = new Graphe();
	        graphPanel = new GraphPanel(graph);
	        graphPanel.setPreferredSize(new Dimension(9000, 4096));
	        setButtons();
	        JScrollPane scroll = new JScrollPane();  
	        scroll.setViewportView(graphPanel);
	        scroll.setPreferredSize(new Dimension(750, 500));
	        scroll.getViewport().setViewPosition(new Point(4100, 0));
	        add(scroll, BorderLayout.CENTER);   
	        
	        
	    }
//---------------------------------------------------------------------------------------------------------------
// a completer 
	    private void setButtons(){
	        JButton run = new JButton("Run");      
	        JButton reset = new JButton("Reset");
	        final JButton about = new JButton("About");
	        JPanel buttonPanel = new JPanel();
	        JPanel buttonPanel1 = new JPanel();
	        JPanel panel = new JPanel();
	       	       
	        this.setVisible(true);
	        buttonPanel.setBackground(DessinObjets.parseColor("#f7f2f2"));
	        buttonPanel.add(reset);
	        buttonPanel.add(run);
	        //buttonPanel.add(about);
	        JLabel label1 = new JLabel("Informations :");
		 	buttonPanel.add(label1);
	        
	        reset.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                graphPanel.reset();
	            }
	        });

	      /*  about.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                JOptionPane.showMessageDialog(null,
	                        "Cliquez n'importe ou pour creer un sommet\n" +
	                        "Cliquez sur un sommet en allant vers un autre pour creer l'arc\n" +
	                        "Cliquez sur un arc pour donner une valeur\n\n" +
	                        "Racourcis:\n" +
	                        "Shift + BtnGauche            :    Sommet source\n" +
	                        "Shift + btnDroit             :    Sommet distination\n" +
	                        "Ctrl + Clic  		  :    supprimer un sommet ou un arc\n");
	            }
	        });*/

	        run.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	                AlgoDijkstra algodijkstra = new AlgoDijkstra(graph);
	                try{
	                	algodijkstra.run();
	                    graphPanel.setPath(algodijkstra.getDestinationPath());
	                } catch (IllegalStateException ise){
	                    JOptionPane.showMessageDialog(null, ise.getMessage());
	                }
	            }
	        });

	        //add(buttonPanel, BorderLayout.SOUTH); // position des boutons
	        add(buttonPanel,BorderLayout.EAST);
	    }

	}
