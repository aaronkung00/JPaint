package controller;

import model.interfaces.IApplicationState;
import model.persistence.*;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

import java.awt.*;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private ShapeList shapeList;
    private PaintCanvas paintCanvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState ) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    public JPaintController(IUiModule uiModule, IApplicationState applicationState , ShapeList shapeList , PaintCanvas paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.paintCanvas = paintCanvas;
    }


    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());



        uiModule.addEvent(EventName.COPY, () ->  CopyCommand.getCopyInstance().copy(shapeList));
        uiModule.addEvent(EventName.PASTE, () -> PasteCommand.getPastInstance().paste(shapeList,paintCanvas));
        uiModule.addEvent(EventName.DELETE, () -> DeleteCommand.getDeleteInstance().delete(shapeList,paintCanvas));
        uiModule.addEvent(EventName.REDO, () -> RedoCommand.getRedoCommand().run());
        uiModule.addEvent(EventName.UNDO, () -> UndoCommand.getUndoCommand().run());



    }
}
