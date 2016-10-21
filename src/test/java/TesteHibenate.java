
import br.com.servicedesk.model.Chamado;

public class TesteHibenate {

	public static void main(String[] args) {
		
		Chamado chamado = new Chamado("Problem Mah",1,1);
		
		chamado.create();
		
		
	}

}
