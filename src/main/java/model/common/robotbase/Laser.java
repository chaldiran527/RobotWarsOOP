package common.robotbase;

public class Laser extends Weapon{


    protected Laser(int pSpeed) {
        super(pSpeed);
    }

    @Override
    public void triggerWeapon(int pPosX, int pPosY, ORIENTATION pDirection) {
        //define specific triggerweapon method of this class
    }

    public void move(){
        //defines move
    }
}
