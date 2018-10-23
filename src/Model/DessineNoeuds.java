package Vue;
	import java.awt.Graphics; 
	import java.math.*;
	import java.util.List;

import java.util.Map;
import java.awt.Color;
	import java.awt.*;
	import javax.swing.*;

import Model.Metier.Noeud;
import Model.Metier.NoeudFactory;

import java.awt.event.*;
	import java.awt.Dimension;
	public class DessineNoeuds extends JPanel{
		
		double x1;
		double y1;
		int x;
		int y;
		double xmin;
		double xmax;
		double ymin;
		double ymax;
		double a;
		double b;
		List<String> idNoeuds;
		public DessineNoeuds(List<String> idNoeuds,Graphics g) {
			this.idNoeuds=idNoeuds;
			
			//taille d'ecran
			Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			int height = (int)dimension.getHeight();
			int width  = (int)dimension.getWidth();
			System.out.println("height="+height);
			System.out.println("width="+width);
			//Noeud n1=noeuds.entrySet().iterator().next().getValue();
			xmin=Double.POSITIVE_INFINITY;
			xmax=Double.NEGATIVE_INFINITY;
			ymin=Double.POSITIVE_INFINITY;
			ymax=Double.NEGATIVE_INFINITY;
			for(String idNoeud:idNoeuds) {
				Noeud n=NoeudFactory.getNoeudParId(idNoeud);
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
			//paintComponent(g);
		}	
		public void paintComponent(Graphics g) {
			for(String idNoeud:idNoeuds) {
				Noeud n=NoeudFactory.getNoeudParId(idNoeud);
				x1=n.getLongitude();
				y1=n.getLatitude();
				int abscisse=(int)((x1-xmin)*a);
				int ordonnee=(int)((ymax-y1)*b);
				//System.out.println("abscisse="+abscisse);
				//repaint();
				g.setColor(Color.black);
				g.fillOval(abscisse, ordonnee, 5, 5);
			}
			
			
			//System.out.println(n1.toString());
			//tranfert des donnees
			/*for(Map.Entry<String, Noeud> entryNoeud:noeuds.entrySet()) {
				Noeud n=entryNoeud.getValue();
				x1=n.getLongitude();
				y1=n.getLatitude();
				//x=(int)(x1);
				//y=(int)(y1);
				x=(int)((x1-4.8)*Math.pow(10,7));
				y=(int)((y1-45.7)*Math.pow(10,6));
				System.out.println("x="+x);
				System.out.println("y="+y);
				
			}		
			repaint();*/
		}
		/*public void paint(Graphics g,int x,int y) {
			System.out.println("dessiner!");
			g.setColor(Color.black);
			g.fillOval(x, y, 5, 5);
		}*/
	}