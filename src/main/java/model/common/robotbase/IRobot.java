package model.common.robotbase;

import java.awt.Graphics;
import java.time.LocalTime;

import model.common.IConstants;
import model.common.IConstantsV2;

public abstract class IRobot implements IConstants {
    protected int energy;
    protected int posX;
    protected int posY;
    protected int strikeIndex;
    protected int weaponIndex;
    protected int speed;
    protected Weapon weapons[];//difference between OBject [][] instance & Object instance[]
    protected Weapon strikes[];//array of strikes for the robot, they are set based on the Iconstant in constructor of this class
    protected DamageLevel directionsdamage[];//
    protected MOVEMENT currentMovement;
    protected ORIENTATION currentOrientation;

    public IRobot(){
        this(ORIENTATION.EAST,ROBOT_SPEED_DEFAULT);
    }

    public IRobot(ORIENTATION pOrientation, int pSpeed){//when instantiated the
        directionsdamage = new DamageLevel[MOVEMENT.values().length];//length of directions and attacks
        weapons = new Weapon[WEAPONS_PER_ROBOT];
        strikes = new Weapon[STRIKES_PER_ROBOT];

        this.currentOrientation = pOrientation;

        this.strikeIndex = 0;
        this.weaponIndex = 0;
        this.speed = pSpeed;
    }

    /*
    * el move es la direccion que el jugador esta presionano, con eso y la hora de accion
    * versus la hora actual se sabe cuanto tiempo ha transcurrido por ende
    * dada la velocidadd del robot, se puede saber cuanto hay que desplazarlo,
    * a que velocidad lo desplazo, actualizo el X, Y
    * reduzco la energia
    * refresco la pantalla con el graphics
    */
    public void move(MOVEMENT pMove, LocalTime pActionTime, Graphics g){
        if(pMove != null) {
            switch (pMove) {//En cada CASE se debe reducir la vida del robot debido a que ha consumido energia moviendose
                case LEFT:
                    if(posX-speed > 0) {
                        posX -= speed;
                        break;
                    }
                case RIGHT:
                    if(posX+speed < ARENA_WIDTH) {
                        posX += speed;
                        break;
                    }
                case UP:
                    if(posY-speed > 0) {
                        posY -= speed;
                        break;
                    }
                case DOWN:
                    if(posY+speed < IConstantsV2.Y_AXIS_BORDER) {
                        posY += speed;
                        break;
                    }
            }
        }
    }

    protected abstract void refreshMove(MOVEMENT pMove, LocalTime pActionTime, Graphics g);

    public void hit(int pStrikeId, LocalTime pActionTime, Graphics g){
        //this.strikes[pStrikeId].fire(this.posX, this.posY, this.currentOrientation);
    }

    public void fire(int pWeaponId, LocalTime pActionTime, Graphics g){
        this.weapons[pWeaponId].fire(this.posX, this.posY, this.currentOrientation);
    }

    /*
    * @plevel, numero del nivel del arma que acerto o logro colisionar con el robot
    * este es el nivel del arma o golpe que le acerte a este robot
    * entonces ahora hay que dado el nivel escoger la regla que aplica
    * acualizar el damage respectivo
    */
    public void damage(int pLevel){
        // logic for damage in case of collision
    }

    public void addStrike(Weapon pStrike){
        strikes[strikeIndex] = pStrike;
        strikeIndex=++strikeIndex%STRIKES_PER_ROBOT;
    }

    public void addWeapon(Weapon pStrike){
        weapons[weaponIndex] = pStrike;
        weaponIndex=++weaponIndex%WEAPONS_PER_ROBOT;
    }
}
