interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() {
        System.out.println("Guitar is playing: Strum strum");
    }
}

class Piano implements Playable {
    public void play() {
        System.out.println("Piano is playing: Plink plonk");
    }
}

public class InterfaceImplementationDemo {
    public static void main(String[] args) {
        Playable g = new Guitar();
        Playable p = new Piano();
        g.play();
        p.play();
    }
}
