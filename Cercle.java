package Vue;



import Model.Metier.Noeud;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Cercle extends Circle {
	

	private Noeud noeud;
	
	public Cercle (double centerX, double centerY, double radius, Paint fill, Noeud noeud){
		
		super(centerX,centerY,radius,fill);
		this.noeud=noeud;		
	}
	
	public String getIntersection(){
		return noeud.getId();
	}


}
