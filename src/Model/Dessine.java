package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import Model.Metier.*;
/*import Model.Metier.Livraison;
import Model.Metier.Noeud;
import Model.Metier.NoeudFactory;*/

public class Dessine extends JPanel{
	double x1;
	double y1;
	double x2;
	double y2;
	String origine;
	String destination;
	int x3;
	int y3;
	int x4;
	int y4;
	double xmin=Double.POSITIVE_INFINITY;
	double xmax=Double.NEGATIVE_INFINITY;
	double ymin=Double.POSITIVE_INFINITY;
	double ymax=Double.NEGATIVE_INFINITY;
	double a;
	double b;
	Model.Metier.Plan plan;
	DemandeLivraisons demandeLivraisons;
	public Dessine(Model.Metier.Plan plan,Graphics g,DemandeLivraisons demandeLivraisons) {
			this.demandeLivraisons=demandeLivraisons;
			this.plan = plan;
			List<String> idNoeuds=plan.getNoeuds();
			List<Livraison> listeLivraison=demandeLivraisons.getPointsDeLivraisons();
			//taille d'ecran
			Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			int height = (int)dimension.getHeight();
			int width  = (int)dimension.getWidth();
			System.out.println("height="+height);
			System.out.println("width="+width);
			//Noeud n1=noeuds.entrySet().iterator().next().getValue();
			
			for(String idNoeud:idNoeuds) {
				Model.Metier.Noeud n = Model.Metier.NoeudFactory.getNoeudParId(idNoeud);
				x1=n.getLongitude();
				y1=n.getLatitude();
				if(x1<xmin) {xmin=x1;}
				if(x1>xmax) {xmax=x1;}
				if(y1<ymin) {ymin=y1;}
				if(y1>ymax) {ymax=y1;}
			}	
			System.out.println("xmax="+xmax);
			System.out.println("ymax="+ymax);
			System.out.println("xmin="+xmin);
			System.out.println("ymin="+ymin);
			
			double h=0.8*height;
			double w=0.8*width;
			double a=w/(xmax-xmin);
			double b=h/(ymax-ymin);
			System.out.println("h="+h);
			System.out.println("w="+w);
			System.out.println("a="+a);
			System.out.println("b="+b);
			this.a=a;
			this.b=b;
			repaint();
		}
		
		
		public void paintComponent(Graphics g) {
			
			for(String idNoeud : plan.getNoeuds()) {
				for(Model.Metier.Plan.Trancon trancon : plan.getSuccesseurs(idNoeud)) {
					Model.Metier.Noeud n1 = Model.Metier.NoeudFactory.getNoeudParId(idNoeud);
					Model.Metier.Noeud n2 = Model.Metier.NoeudFactory.getNoeudParId(trancon.getDestination());
					int x1 = (int) ((n1.getLongitude()-xmin)*a);
					int y1 = (int) ((ymax-n1.getLatitude())*b);
					int x2 = (int) ((n2.getLongitude()-xmin)*a);
					int y2 = (int) ((ymax-n2.getLatitude())*b);
					g.drawLine(x1, y1, x2, y2);
				}
				for(Livraison livraison:demandeLivraisons.getPointsDeLivraisons()) {
					
					Noeud n=NoeudFactory.getNoeudParId(livraison.getNoeud());
					x1=n.getLongitude();
					y1=n.getLatitude();
					int abscisse=(int)((x1-xmin)*a);
					int ordonnee=(int)((ymax-y1)*b);
					//System.out.println("abscisse="+abscisse);
					//repaint();
					g.setColor(Color.black);
					g.fillOval(abscisse, ordonnee, 15, 15);
				}
		}		
		
	
	}
	
		
	
}