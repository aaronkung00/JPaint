package model.persistence;

public class CopyCommand {

    private static CopyCommand instance = new CopyCommand();
    private ShapeList shapeList;

    private CopyCommand() {

    }

    public static CopyCommand getCopyInstance(){
        return instance;
    }

    public void copy(ShapeList shapeList)
    {
        this.shapeList = shapeList;
        for(Shape s : this.shapeList.getSelectedShapes())
            shapeList.addToCopy(s);
        // debug ==  System.out.println(shapeList.getCopiedShapesList().size());
    }

}
