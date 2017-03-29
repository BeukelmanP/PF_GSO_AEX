package gso_aex;

import java.util.Random;

public interface IFonds {

	String getNaam();

	double getKoers();
        
        void setRndKoers(Random rnd);
}