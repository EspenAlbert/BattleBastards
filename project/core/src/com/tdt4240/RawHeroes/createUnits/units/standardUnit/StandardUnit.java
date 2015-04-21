package com.tdt4240.RawHeroes.createUnits.units.standardUnit;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.ISpritesheet;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardUnit implements IUnit {

    private final int MAX_HEALTH = 20;

    private final int MIN_DMG = 5;
    private final int MAX_DMG = 10;
    private final int MAX_MOVES = 3;

    private boolean turnedRight;

    private int health;
    private boolean player1Unit;
    private boolean hasAttacked;
    private IUnitCombatController unitCombatController;
    private IUnitMovementController unitMoveController;
    private IUnitAnimationController unitAnimationController;

    private int remainingMoves;
    private int weight;

    public StandardUnit(boolean player1Unit) {
        health = MAX_HEALTH;
        this.hasAttacked = false;
        this.player1Unit = player1Unit;

        this.remainingMoves = MAX_MOVES;
        this.weight = 10;
        this.unitCombatController = new SimpleUnitCombatController(this, MIN_DMG, MAX_DMG, 1, MAX_HEALTH, MAX_MOVES);
        this.turnedRight = player1Unit;
        this.unitMoveController = new WalkingUnitMovementController();
        this.unitAnimationController = new SimpleUnitAnimationController();
        System.out.println("Created a standard unit");
    }
    private StandardUnit(boolean player1Unit, int health, boolean hasAttacked, IUnitCombatController unitCombatController, IUnitMovementController unitMoveController, int remainingMoves, int weight, IUnitAnimationController unitAnimationController) {
        this.player1Unit = player1Unit;
        this.health = health;
        this.hasAttacked = hasAttacked;
        this.unitCombatController = unitCombatController;
        this.unitMoveController = unitMoveController;
        this.remainingMoves = remainingMoves;
        this.weight = weight;
        this.unitAnimationController = unitAnimationController;
    }

    @Override
    public UnitName getIdentifier() {
        return UnitName.STANDARD_UNIT;
    }

    @Override
    public ArrayList<Position> getInflictionZone(Position myPos, Position target) {
        return unitCombatController.getInflictionZone(myPos, target);
    }

    @Override
    public ArrayList<Position> getMovementZone(IBoard board, Position myPos, int movesLeft) {
        return this.unitMoveController.getMovementZone(board, myPos, movesLeft, this.getMovesLeft());
    }

    @Override
    public ArrayList<Position> getMovementPath(IBoard board, Position myPos, Position targetPos){
        return this.unitMoveController.getMovementPath(board, myPos, targetPos);
    }

    @Override
    public int inflictDamage(Position myPos, Position targetPos) {
        return unitCombatController.inflictDamage(myPos, targetPos);
    }

    @Override
    public int attacked(int damage) {
        return this.unitCombatController.attacked(damage);
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void setAttackLogic(IUnitCombatController controller) {
        unitCombatController = controller;
    }

    @Override
    public void setMovementLogic(IUnitMovementController controller) {
        unitMoveController = controller;
    }

    @Override
    public void setHasAttacked(boolean value) {
        hasAttacked = value;
    }

    @Override
    public boolean isPlayer1Unit() {
        return player1Unit;
    }

    @Override
    public boolean isTurnedRight() {
        return turnedRight;
    }

    @Override
    public void turnDirection() {
        turnedRight = !turnedRight;
    }

    @Override
    public ArrayList<Position> getAttackablePositions(Position pos, int movesLeft, IBoard board) {
        return this.unitCombatController.getAttackablePositions(pos, movesLeft, board);
    }

    @Override
    public int getHealth() {
        return unitCombatController.getHealth();
    }

    @Override
    public int getMaxHealth() {
        return this.unitCombatController.getMaxHealth();
    }

    @Override
    public int getMovesLeft() {
        return unitCombatController.getMovesLeft();
    }

    @Override
    public void move(int movesSpent) {
        unitCombatController.move(movesSpent);
    }

    @Override
    public int[] getAttackDmg() {
        int[] a = new int[2];
        a[0] = this.unitCombatController.getMinAttackDmg();
        a[1] = this.unitCombatController.getMaxAttackDmg();
        return a;
    }

    @Override
    public int getArmor() {
        return this.unitCombatController.getArmor();
    }

    @Override
    public boolean hasAttacked() {
        return hasAttacked;
    }

    @Override
    public IUnit getCopy() {
        return new StandardUnit(player1Unit, health, hasAttacked, unitCombatController, unitMoveController,remainingMoves, weight, unitAnimationController);
    }

    @Override
    public TextureRegion getActiveFrame(Texture texture){
       return this.unitAnimationController.getActiveFrame(texture);
    }

    @Override
    public void nextFrame(){
        this.unitAnimationController.nextFrame();
    }

    @Override
    public void setActiveAnimation(RenderMode renderMode){
        switch (renderMode){

            case STATIC:
                if (this.unitAnimationController.getActiveAnimation() == AnimationConstants.MOVE_RIGHT)this.unitAnimationController.setActiveAnimation(AnimationConstants.IDLE_RIGHT);
                if (this.unitAnimationController.getActiveAnimation() == AnimationConstants.MOVE_LEFT)this.unitAnimationController.setActiveAnimation(AnimationConstants.IDLE_LEFT);
                break;
            case MOVING:
                if(this.turnedRight)this.unitAnimationController.setActiveAnimation(AnimationConstants.MOVE_RIGHT);
                else this.unitAnimationController.setActiveAnimation(AnimationConstants.MOVE_LEFT);
                break;
            case ATTACKING:
                if(this.turnedRight)this.unitAnimationController.setActiveAnimation(AnimationConstants.ATK_RIGHT);
                else this.unitAnimationController.setActiveAnimation(AnimationConstants.ATK_LEFT);
                break;
            case HURT:
                if (this.unitAnimationController.getActiveAnimation() == AnimationConstants.IDLE_RIGHT)this.unitAnimationController.setActiveAnimation(AnimationConstants.HURT_RIGHT);
                else this.unitAnimationController.setActiveAnimation(AnimationConstants.HURT_LEFT);
                break;
            case KILLED:
                this.unitAnimationController.setActiveAnimation(AnimationConstants.DEAD);
                break;
        }
    }

    @Override
    public void resetMoves() {
        unitCombatController.resetMoves();
    }

    @Override
    public void addAnimationListener(IAnimationListener animationListener){
        this.unitAnimationController.addAnimationListener(animationListener);
    }
}
