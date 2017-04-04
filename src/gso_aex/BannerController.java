package gso_aex;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BannerController {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private ArrayList<IFonds> koersen = new ArrayList<>();

    public BannerController(AEXBanner banner)  {
        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();
        koersen = effectenbeurs.getKoersen();
        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        TimerTask TT = new TimerTask(){
            @Override 
            public void run(){
                koersen = effectenbeurs.getKoersen();
                
            }
        };
        
        pollingTimer.schedule(TT, 2000);

    }
public List<IFonds> getKoersen(){
    return koersen;
}
    
    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }
}
