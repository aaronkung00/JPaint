package model.interfaces;

import model.persistence.Coordinates;

public interface IMoveCommand {
    void updateMove(Coordinates newStartPoint);
    void updateOffset();
}
