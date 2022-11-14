package common.robotbase;

public abstract class Weapon extends DamageLevel {
    private int posX;//Attributes for the drawing of the weapon attack
    private int posY;
    private int speed;

    protected Weapon(int pSpeed)
    {
        this.speed = pSpeed;
    }

    public void fire(int pPosX, int pPosY, ORIENTATION pDirection){//there is an instance from a value of orientation
        if(this.isEnabled()){
            triggerWeapon(pPosX, pPosY, pDirection);
        }
    }
    protected abstract void triggerWeapon(int pPosX, int pPosY, ORIENTATION pDirection);

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
