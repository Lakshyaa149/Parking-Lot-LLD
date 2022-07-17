public class Slot {

    SlotType type;
    int row;
    int col;
    boolean isFree;

    Slot(int row,int col,SlotType type){
        this.row=row;
        this.col=col;
        this.type=type;
    }
    public SlotType getType() {
        return type;
    }

    public void setType(SlotType type) {
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }


}
