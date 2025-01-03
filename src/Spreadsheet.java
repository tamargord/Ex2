public class Spreadsheet {
    private int width;
    private int height;
    private Cell[][] cells;

    public Spreadsheet(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell get(int x, int y) {
        validateCell(x, y);
        return cells[x][y];
    }

    public void set(int x, int y, Cell cell) {
        validateCell(x, y);
        cells[x][y] = cell;
    }

    private void validateCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Invalid cell reference");
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int xCell(String address) {
        String letters = leadingLetters(address);
        if (letters == null || letters.length() != 1) {
            return -1;
        }
        char ch = Character.toUpperCase(letters.charAt(0));
        if (ch < 'A' || ch > 'Z') {
            return -1;
        }
        return ch - 'A';
    }

    private String leadingLetters(String s) {
        int i = 0;
        while (i < s.length() && Character.isLetter(s.charAt(i))) {
            i++;
        }
        if (i == 0) {
            return null;
        }
        return s.substring(0, i);
    }

    public int yCell(String address) {
        String digits = trailingDigits(address);
        if (digits == null || digits.isEmpty()) {
            return -1;
        }
        try {
            int rowNum = Integer.parseInt(digits);
            return rowNum - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String trailingDigits(String s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isDigit(s.charAt(i))) {
            i--;
        }
        if (i == s.length() - 1) {
            return null;
        }
        return s.substring(i + 1);
    }

    public String eval(int x, int y) {
        validateCell(x, y);
        return cells[x][y].getValue();
    }

    public String[][] evalAll() {
        String[][] result = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[i][j] = cells[i][j].getValue();
            }
        }
        return result;
    }

    public static class Cell {
        private String value = "";
        private Cell dependency;

        public String getValue() {
            if (dependency == null) {
                return value;
            } else {
                return dependency.getValue();
            }
        }

        public int computeDepth() {
            if (dependency == null) {
                return 0;
            } else if (dependency == this) {
                return -1;
            } else {
                return 1 + dependency.computeDepth();
            }
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setDependency(Cell dependency) {
            this.dependency = dependency;
        }
    }

    public static void main(String[] args) {
        // Example Usage
        Spreadsheet sheet = new Spreadsheet(5, 5);
        Cell cell1 = new Cell();
        cell1.setValue("42");
        sheet.set(0, 0, cell1);

        Cell cell2 = new Cell();
        cell2.setDependency(cell1); // cell2 depends on cell1
        sheet.set(1, 0, cell2);

        System.out.println(sheet.eval(0, 0)); // Should print 42
        System.out.println(sheet.eval(1, 0)); // Should also print 42
    }
}
