package sra1.jee.projet.drive.mock;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import sra1.jee.projet.drive.model.Reclamation;
import sra1.jee.projet.drive.model.ReclamationStatus;

@Component
@Qualifier("mock")
public class ReclamationMock {
	public HashMap<Integer, Reclamation> recls = new HashMap<Integer, Reclamation>();
	int id = 1;
	
	public ReclamationMock() {
		{
			Reclamation r1 = new Reclamation();
			r1.setId(id);
			r1.setIdClient((long) id);
			r1.setTitle("Ma perchonne est chacrée");
			r1.setDescription("Vous ne me touchez pas Monchieur");
			r1.setDateTime(new Date(2022, 04, 21));
			r1.setStatus(ReclamationStatus.WAITING);
			recls.put(id, r1);
			id++;
		}
		{
			Reclamation r2 = new Reclamation();
			r2.setId(id);
			r2.setIdClient((long) id);
			r2.setTitle("La poudre de perlimpinpin ne fonctionne pas");
			r2.setDescription("Venez me chercher !");
			r2.setDateTime(new Date(2018, 11, 23));
			r2.setStatus(ReclamationStatus.WAITING);
			recls.put(id, r2);
			id++;
		}
		{
			Reclamation r3 = new Reclamation();
			r3.setId(id);
			r3.setIdClient((long) id);
			r3.setTitle("Je suis endettée de 5 millions");
			r3.setDescription("J'ai besoin de thunes !");
			r3.setDateTime(new Date(2022, 04, 23));
			r3.setStatus(ReclamationStatus.CLOSED);
			recls.put(id, r3);
			id++;
		}
	}

	/**
	 * @return the recls
	 */
	public HashMap<Integer, Reclamation> getRecls() {
		return recls;
	}
}