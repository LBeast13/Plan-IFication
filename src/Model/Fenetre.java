package Vue;
import java.awt.*;
import javax.swing.*;

import Model.Metier.DemandeLivraisons;
import Model.Metier.Noeud;
import Model.Metier.Plan;

import java.awt.event.*;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
//import PlaCo;
public class Fenetre extends JFrame{
	public Fenetre(List<String> idNoeuds,Plan plan,DemandeLivraisons demandeLivraisons) {//parametre:liste de noeud et de troncons
		this.setTitle("Plan");
		this.setSize(1900,1000);
		this.setLocation(200,50);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//AfficherNoeuds(noeuds);
		Graphics g=null;
			this.setContentPane(new DessineTroncons(plan,g));
			this.setContentPane(new DessineNoeuds(idNoeuds,g));
			this.setContentPane(new Dessine(plan,g,demandeLivraisons));
	}
}