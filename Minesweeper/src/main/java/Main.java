import java.io.IOException;
import java.util.*;

class Cell {
    private boolean isMine = false;
    private final int[] coords;
    private int label = 0;
    private boolean isMarked = false;
    private boolean isFreed = false;

    public Cell(int x, int y) {
        this.coords = new int[]{x, y};
    }

    public String show() {
        if (!isMine) {
            if (isMarked && !isFreed) return "*";
            if (!isFreed) return ".";
            if (label == 0) return "/";
            else return String.valueOf(label);
        }
        else {
            if (isMarked) return "*";
            else return ".";
        }
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMark() {
        isMarked = true;
    }

    public boolean isFreed() {
        return isFreed;
    }

    public void unMark() {
        isMarked = false;

    }

    public int getLabel() {
        return label;
    }

    public void incLabel() {
        this.label++;
    }

    public void decLabel() {
        this.label--;
    }

    public void setFreed(boolean freed) {
        isFreed = freed;
    }

    public int[] getCoords() {
        return coords;
    }

    public boolean isMine() {
        return isMine;
    }

    public void deMine() {
        isMine = false;
    }

    public void setMine() {
        isMine = true;
    }
}

class Field {
    private final Cell[][] field;
    private int counter;
    private final Random r = new Random();

    public Cell[][] getField() {
        return field;
    }

    Field(int rows, int cols, int counter) {
        this.counter = counter;
        this.field = new Cell[rows][cols];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                field[i][j] = new Cell(i, j);
            }
        }
    }

    public void setMines() {
        while (counter != 0) {
            int rX = r.nextInt(field.length-1);
            int rY = r.nextInt(field.length-1);
            if (!field[rX][rY].isMine()) {
                field[rX][rY].setMine();
                setLabels(rX, rY,"set");
                counter--;
            }
        }
    }

    public void setLabels(int i, int j, String action) {
        List<Cell> cells=new ArrayList<>();
        if (field[i][j].isMine()) {
            while (field[i][j].getLabel()!=0)field[i][j].decLabel();
            if (i == 0 && j == 0) {
                if (!field[i][j + 1].isMine()) cells.add(field[i][j + 1]);
                if (!field[i + 1][j].isMine()) cells.add(field[i + 1][j]);
                if (!field[i + 1][j + 1].isMine()) cells.add(field[i + 1][j + 1]);
            }
            if (i == field.length - 1 && j == 0) {
                if (!field[i][j + 1].isMine()) cells.add(field[i][j + 1]);
                if (!field[i - 1][j].isMine()) cells.add(field[i - 1][j]);
                if (!field[i - 1][j + 1].isMine()) cells.add(field[i - 1][j + 1]);
            }
            if (j == field.length - 1 && i == 0) {
                if (!field[i][j - 1].isMine()) cells.add(field[i][j - 1]);
                if (!field[i + 1][j].isMine()) cells.add(field[i + 1][j]);
                if (!field[i + 1][j - 1].isMine()) cells.add(field[i + 1][j - 1]);
            }
            if (i == field.length - 1 && j == field.length - 1) {
                if (!field[i - 1][j - 1].isMine()) cells.add(field[i - 1][j - 1]);
                if (!field[i - 1][j].isMine()) cells.add(field[i - 1][j]);
                if (!field[i][j - 1].isMine()) cells.add(field[i][j - 1]);
            }
            if (i == 0 && j < field.length - 1 && j != 0) {//top
                if (!field[i][j - 1].isMine()) cells.add(field[i][j - 1]);
                if (!field[i][j + 1].isMine()) cells.add(field[i][j + 1]);
                if (!field[i + 1][j].isMine()) cells.add(field[i + 1][j]);
                if (!field[i + 1][j - 1].isMine()) cells.add(field[i + 1][j - 1]);
                if (!field[i + 1][j + 1].isMine()) cells.add(field[i + 1][j + 1]);
            }
            if (i < field.length - 1 && i != 0 && j == 0) {//left
                if (!field[i][j + 1].isMine()) cells.add(field[i][j + 1]);
                if (!field[i - 1][j].isMine()) cells.add(field[i - 1][j]);
                if (!field[i + 1][j].isMine()) cells.add(field[i + 1][j]);
                if (!field[i - 1][j + 1].isMine()) cells.add(field[i - 1][j + 1]);
                if (!field[i + 1][j + 1].isMine()) cells.add(field[i + 1][j + 1]);
            }
            if (j == field.length - 1 && i != 0 && i != field.length - 1) {//right
                if (!field[i][j - 1].isMine()) cells.add(field[i][j - 1]);
                if (!field[i - 1][j].isMine()) cells.add(field[i - 1][j]);
                if (!field[i + 1][j].isMine()) cells.add(field[i + 1][j]);
                if (!field[i - 1][j - 1].isMine())cells.add( field[i - 1][j - 1]);
                if (!field[i + 1][j - 1].isMine()) cells.add(field[i + 1][j - 1]);
            }
            if (j != 0 && j != field.length - 1 && i == field.length - 1) {//bottom
                if (!field[i][j +1 ].isMine())  cells.add(  field[i][j + 1]);
                if (!field[i][j - 1].isMine()) cells.add(field[i][j - 1]);
                if (!field[i-1][j +1].isMine())cells.add(field[i - 1][j + 1]);
                if (!field[i-1][j - 1].isMine()) cells.add(field[i - 1][j - 1]);
                if (!field[i-1][j].isMine())cells.add( field[i - 1][j]);
            }
            if (j != 0 && j != field.length - 1 && i != 0 && i != field.length - 1) {
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k == 0 && l == 0) continue;
                        if (!field[i + k][j + l].isMine()) {
                            cells.add(field[i + k][j + l]);
                        }
                    }
                }
            }
        }
        if (action.equals("set")) cells.forEach(Cell::incLabel);

        else if (action.equals("dec")) cells.forEach(Cell::decLabel);

        cells.clear();
    }

    public void check(Queue<Cell> q) {
        while (!q.isEmpty()) {
            int[] coords = q.poll().getCoords();
            int i = coords[0];
            int j = coords[1];
            if (field[i][j].getLabel() == 0&&!field[i][j].isFreed()&&!field[i][j].isMine()) {
                if (i == 0 && j == 0) {
                    q.add(field[i][j + 1]);
                    q.add(field[i + 1][j]);
                    q.add(field[i + 1][j + 1]);
                }
                if (i == field.length - 1 && j == 0) {
                    q.add(field[i][j + 1]);
                    q.add(field[i - 1][j]);
                    q.add(field[i - 1][j + 1]);
                }
                if (j == field.length - 1 && i == 0) {
                    q.add(field[i][j - 1]);
                    q.add(field[i + 1][j]);
                    q.add(field[i + 1][j - 1]);
                }
                if (i == field.length - 1 && j == field.length - 1) {
                    q.add(field[i - 1][j - 1]);
                    q.add(field[i - 1][j]);
                    q.add(field[i][j - 1]);
                }
                if (i == 0 && j < field.length - 1 && j != 0) {
                    q.add(field[i][j - 1]);
                    q.add(field[i][j + 1]);
                    q.add(field[i + 1][j]);
                    q.add(field[i + 1][j - 1]);
                    q.add(field[i + 1][j + 1]);
                }
                if (i < field.length - 1 && i != 0 && j == 0) {
                    q.add(field[i][j + 1]);
                    q.add(field[i - 1][j]);
                    q.add(field[i + 1][j]);
                    q.add(field[i - 1][j + 1]);
                    q.add(field[i + 1][j + 1]);
                }
                if (j == field.length - 1 && i != 0 && i != field.length - 1) {
                    q.add(field[i][j - 1]);
                    q.add(field[i - 1][j]);
                    q.add(field[i + 1][j]);
                    q.add(field[i - 1][j - 1]);
                    q.add(field[i + 1][j - 1]);
                }
                if (j != 0 && j != field.length - 1 && i == field.length - 1) {
                    q.add(field[i][j + 1]);
                    q.add(field[i][j - 1]);
                    q.add(field[i - 1][j + 1]);
                    q.add(field[i - 1][j - 1]);
                    q.add(field[i - 1][j]);
                }
                if (j != 0 && j != field.length - 1 && i != 0 && i != field.length - 1) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (k == 0 && l == 0) continue;
                            q.add(field[i + k][j + l]);
                        }

                    }
                }
            }
            field[i][j].setFreed(true);
        }
    }

    public void draw() {
        for (int i = 0; i < 2; i++) {
            for (int j = -2; j < field.length + 1; j++) {
                if (j == -1 || j == field.length) System.out.print("|");
                if (i == 1) {
                    if (j != 1 && j < field.length) System.out.print("—");
                } else {
                    if (j == -2) System.out.print(" ");
                    if (j >= 0 && j != field.length) System.out.print(j + 1);
                }
            }
            System.out.println();
        }
        for (int i = 0; i < field.length + 1; i++) {
            for (int j = -2; j < field.length + 1; j++) {
                if (j == -1 || j == field.length) System.out.print("|");
                if (i != field.length) {
                    if (j == -2) System.out.print(i + 1);
                    if (j >= 0 && j != field.length) {
                        System.out.print(field[i][j].show());
                    }
                } else if (j != 1 && j < field.length) System.out.print("—");
            }
            System.out.println();
        }
    }
}

class Terminal {
    private Field field;
    private boolean lost=false;
    public void prepare() {

        final Random r = new Random();
        int c = getNumOfMines();
        int moves = 0;
        field = new Field(9, 9, c);
        field.setMines();
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField().length; j++) {
                System.out.print(field.getField()[i][j].getLabel());

            }
            System.out.println();
        }
        while (c != 0&&!lost) {
            field.draw();
            Result res = input();
            int y = res.getY();
            int x = res.getX();
            String action = res.getAction();
            Cell curCell = field.getField()[x - 1][y - 1];
            if (action.equals(" free")) {
                Queue<Cell> q = new LinkedList<>();
                q.add(curCell);
                if (curCell.isMine() && moves == 0) {//1st move is always safe, but sometimes it doesn't work correctly

                    field.setLabels(x-1,y-1,"dec");
                    curCell.deMine();
                    while (true) {
                        int rX = r.nextInt(field.getField().length);
                        int rY = r.nextInt(field.getField().length);
                        if (!field.getField()[rX][rY].isMine()) {//random mine placement
                            field.getField()[rX][rY].setMine();
                            field.setLabels(rX, rY,"set");
                            break;
                        }
                    }

                    field.check(q);
                    moves++;
                }
                if (!curCell.isMine()) {
                    field.check(q);
                    moves++;
                }
                if (curCell.isMine()) {
                    System.out.println("You stepped on a mine and failed!");
                    lost=true;
                    break;
                }
            } else if (action.equals(" mine")) {

                if (!field.getField()[x - 1][y - 1].isMarked()) {
                    if (field.getField()[x - 1][y - 1].isMine()) {
                        c--;
                    } else c++;
                    field.getField()[x - 1][y - 1].setMark();
                } else {
                    field.getField()[x - 1][y - 1].unMark();
                    if (field.getField()[x - 1][y - 1].isMine()) c++;
                    else c--;
                }
            }
            for (int i = 0; i < field.getField().length; i++) {
                for (int j = 0; j < field.getField().length; j++) {
                    if (field.getField()[i][j].isMine()) System.out.println(field.getField()[i][j].getLabel());
                }
            }
            System.out.println(c);
        }
        if (!lost) System.out.println("Congratulations! You found all mines!");
    }

    private Result input() {

        System.out.println("Set/unset mines marks or claim a cell as free: ");
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        String action = sc.nextLine();

        return Result.getInstance(y, x, action);
    }

    private int getNumOfMines() {

        System.out.println("How many mines do you want on the field?");
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        return c;
    }
}

class Result {
    private final int y;
    private final int x;
    private final String action;

    public Result(int y, int x, String action) {
        this.y = y;
        this.x = x;
        this.action = action;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String getAction() {
        return action;
    }

    static Result getInstance(int first, int second, String action) {

        return new Result(first, second, action);
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Terminal t = new Terminal();
        t.prepare();
    }
}