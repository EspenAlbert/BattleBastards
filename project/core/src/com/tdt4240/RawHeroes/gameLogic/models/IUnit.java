package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IUnit extends Serializable{
    UnitName getIdentifier();
    ArrayList<Position> getInflictionZone(Position myPos, Position target);
    ArrayList<Position> getMovementZone(IBoard board, Position myPos, int movesLeft);
    ArrayList<Position> getMovementPath(IBoard board, Position myPos, Position targetPos);
    int inflictDamage(Position myPos, Position enemies);
    int attacked(int damage);
    int getWeight();
    void setAttackLogic(IUnitCombatController controller);
    void setMovementLogic(IUnitMovementController controller);
    void setHasAttacked(boolean value);
    boolean isPlayer1Unit();
    void setRemainingMoves(int moves);
    int getRemainingMoves();
    void resetMoves();

    ArrayList<Position> getAttackablePositions(Position pos, int movesLeft, IBoard board);

    int[] getAttackDmg();
    int getArmor();
    int getHealth();
    int getMaxHealth();

    boolean hasAttacked();
    IUnit getCopy();

    //TextureRegion getActiveFrame(Texture texture);
}
