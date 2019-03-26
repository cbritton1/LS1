import java.util.ArrayList;

public class LemonadeStandApp {

	public static void main(String[] args) {
		
		Stand myStand = new Stand("Corys", "Strawberry Lemonade");
		
		
		
		ArrayList <Servable> servables = new ArrayList<>();
		servables.add(myStand);
		
		for(Servable el : servables) {
			System.out.println(el);
			
			el.serve();
		}

	}

}
