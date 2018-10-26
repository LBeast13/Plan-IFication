package Vue;

import Model.Metier.Plan.Trancon;
import javafx.scene.shape.Line;

public class Ligne extends Line {
	
	private Trancon trancon;
	
	public Ligne (double startX, double startY, double endX, double endY, Trancon trancon)
	{
		super(startX,startY,endX,endY);
		this.trancon=trancon;
	}
	
	public String getRue(){
		return trancon.getNomDeLaRue();
	}

}
