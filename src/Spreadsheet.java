public class Spreadsheet {
    private Cell[][] cells;

    public Spreadsheet(int x, int y) {
        cells = new Cell[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell get(int x, int y) {
        return cells[x][y];
    }

    public void set(int x, int y, Cell c) {
        cells[x][y] = c;
    }

    public int width() {
        return cells.length;
    }

    public int height() {
        return cells[0].length;
    }

    public int xCell(String c) {
        if (c == null || c.isEmpty()) return -1;

        int column = 0;
        for (int i = 0; i < c.length(); i++) {
            char ch = c.charAt(i);
            if (Character.isDigit(ch)) break;

            if (ch < 'A' || ch > 'Z') return -1;
            column = column * 26 + (ch - 'A');
        }
        return column;
    }

    public int yCell(String c) {
        StringBuilder rowPart = new StringBuilder();
        for (char ch : c.toCharArray()) {
            if (Character.isDigit(ch)) {
                rowPart.append(ch);
            }
        }
        try {
            return Integer.parseInt(rowPart.toString()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String eval(int x, int y) {
        Cell cell = cells[x][y];
        if (cell == null || cell.value == null) {
            return "[empty]";
        }
        String value = cell.value;
        if (Cell.isNumber(value)) {
            return value;
        } else if (Cell.isForm(value)) {
            return String.valueOf(Cell.computeForm(value));
        } else {
            return value;
        }
    }


    public String[][] evalAll() {
        int width = width();
        int height = height();
        String[][] result = new String[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[i][j] = eval(i, j);
            }
        }

        return result;
    }

    public int[][] depth() {
        int width = width();
        int height = height();
        int[][] result = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = cells[i][j];
                if (cell == null || cell.value == null) {
                    result[i][j] = -1;
                } else {
                    String value = cell.value;
                    if (Cell.isNumber(value) || Cell.isText(value)) {
                        result[i][j] = 0;
                    } else if (Cell.isForm(value)) {
                        result[i][j] = 1;
                    } else {
                        result[i][j] = -1;
                    }
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        Spreadsheet sheet = new Spreadsheet(5, 5);

        sheet.set(0, 0, new Cell());
        sheet.get(0, 0).setValue("123");

        sheet.set(1, 1, new Cell());
        sheet.get(1, 1).setValue("=4+2*3");

        System.out.println(sheet.eval(0, 0));
        System.out.println(sheet.eval(1, 1));

        String[][] allEvaluations = sheet.evalAll();
        for (String[] row : allEvaluations) {
            for (String cellValue : row) {
                System.out.print((cellValue != null ? cellValue : "[empty]") + " ");
            }
            System.out.println();
        }

        int[][] depths = sheet.depth();
        for (int[] row : depths) {
            for (int depth : row) {
                System.out.print(depth + " ");
            }
            System.out.println();
        }
    }
}
