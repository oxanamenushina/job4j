package ru.job4j.algorithms;

import java.util.*;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Robot {

    /**
     * Пройденные ячейки массива.
     */
    private Set<Cell> passed = new HashSet<>();

    /**
     * Метод находит минимальный путь до конечной точки.
     * @param board заданный двумерный массив.
     * @param sx координата x начальной ячейки пути.
     * @param sy координата y начальной ячейки пути.
     * @param fx координата x конечной ячейки пути.
     * @param fy координата y конечной ячейки пути.
     * @return длину минимального пути до конечной точки.
     */
    public int minWay(int[][] board, int sx, int sy, int fx, int fy) {
        int result = 0;
        Cell start = new Cell(sx, sy);
        Cell finish = new Cell(fx, fy);
        this.passed.add(start);
        Deque<Cell> path = new LinkedList<>(List.of(start));
        Queue<Deque<Cell>> ways = new LinkedList<>(List.of(path));
        while (!ways.isEmpty() && result == 0) {
            Deque<Cell> way = ways.poll();
            List<Cell> nearby = findNearby(way.peekLast(), board, finish);
            for (Cell cell : nearby) {
                if (finish.equals(cell)) {
                    result = way.size() + 1;
                    break;
                }
                Deque<Cell> current = new LinkedList<>(way);
                current.addLast(cell);
                ways.offer(current);
            }
        }
        if (result == 0) {
            throw new NoWayException("There is no way.");
        }
        return result;
    }

    /**
     * Метод возвращает для заданной ячейки
     * список всех соседних (по горизонтали и вертикали)
     * непройденных ячеек со значением 1.
     * @param cell заданная ячейка.
     * @param board массив.
     * @return список всех соседних (по горизонтали и вертикали)
     * непройденных ячеек со значением 1.
     */
    private List<Cell> findNearby(Cell cell, int[][] board, Cell finish) {
        List<Cell> cells = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            Cell cur = new Cell(cell.x + i, cell.y);
            if (isInRange(cur.x, board[0].length) && board[cur.y][cur.x] == 1 && !passed.contains(cur)) {
                cells.add(cur);
                if (!finish.equals(cur)) {
                    this.passed.add(cur);
                }
            }
            cur = new Cell(cell.x, cell.y + i);
            if (isInRange(cur.y, board.length) && board[cur.y][cur.x] == 1 && !passed.contains(cur)) {
                cells.add(cur);
                if (!finish.equals(cur)) {
                    this.passed.add(cur);
                }
            }
        }
        return cells;
    }

    /**
     * Метод проверяет находится ли данная координата в пределах массива.
     * @param coord координата.
     * @param size размер массива.
     * @return true - координата находится в пределах массива, иначе - false.
     */
    private boolean isInRange(int coord, int size) {
        return coord >= 0 && coord < size;
    }

    /**
     * Класс Cell.
     */
    private class Cell {
        private final int x;
        private final int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Cell coords = (Cell) o;
            return x == coords.x && y == coords.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}