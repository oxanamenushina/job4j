package ru.job4j.tictactoe;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * GameLogic.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class GameLogic implements Logic {

    /**
     * The Display object.
     */
    private Display display;

    /**
     * The playing field.
     */
    private Field field;

    /**
     * True - the computer plays for X,
     * false - the computer plays for 0.
     */
    private boolean isCompX;

    /**
     * Sequences with the possibility of winning the user.
     */
    private Set<List<Cell>> user;

    /**
     * The counter of moves.
     */
    private int counter;

    /**
     * Field size.
     */
    private int size;

    /**
     * The length of the sequence of similar signs required to win.
     */
    private int winNum;

    /**
     * True - the user won, false - the user didn't win.
     */
    private boolean userWin;

    /**
     * True - the computer won, false - the computer didn't win.
     */
    private boolean compWin;

    /**
     * The number of identical signs in a sequence
     * at which the sequence is considered potentially winning.
     */
    private int checkDigit;

    /**
     * Free cells with possible sequences for them to move the computer.
     */
    private Set<Sequence> freeCells;

    public GameLogic(Field field, Display display) {
        this.field = field;
        this.display = display;
    }

    /**
     * Logic initialization.
     * @param size - field size.
     * @param isCompX true - the computer plays for X,
     * false - the computer plays for 0.
     */
    @Override
    public void init(int size, boolean isCompX) {
        this.user = new TreeSet<>(new SequenceComparator());
        this.freeCells = new TreeSet<>();
        this.userWin = false;
        this.compWin = false;
        this.size = size;
        this.isCompX = isCompX;
        this.field.create(size);
        this.counter = 0;
        this.winNum = size <= 5 ? size : 5;
        this.checkDigit = this.winNum < 5 ? this.winNum - 1 : this.winNum - 2;
        this.display.init(size);
        this.fillEmpty();
        if (this.isCompX) {
            int[] coord = this.makeFirstMove();
            this.setChanges(coord[0], coord[1], true);
        }
    }

    /**
     * The method checks if the field is completely filled.
     * @return true - the field is completely filled,
     * false - the field isn't completely filled.
     */
    @Override
    public boolean isFilled() {
        return this.counter >= this.size * this.size;
    }

    /**
     * The method checks if the computer has won.
     * @return true - the computer has won,
     * false - the computer hasn't won.
     */
    @Override
    public boolean isCompWin() {
        return this.compWin;
    }

    /**
     * The method checks if the user has won.
     * @return true - the user has won,
     * false - the user hasn't won.
     */
    @Override
    public boolean isUserWin() {
        return this.userWin;
    }

    /**
     * The user and the computer make a move.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @return true - the user and the computer made a move,
     * false - the user or the computer didn't make a move.
     */
    @Override
    public boolean move(int x, int y) {
        this.setChanges(x, y, !this.isCompX);
        Cell last = this.field.getCell(x, y);
        this.searchUserCombinations(last, this::getStraight, this::getDiagonal);
        this.checkAllCells(last);
        this.updateFreeCells();
        this.compMove();
        return !this.isFilled() && !this.compWin && !this.userWin;
    }

    /**
     * The computer makes a move.
     */
    private void compMove() {
        if (!this.isFilled() && !this.userWin) {
            int[] coord = this.counter < 2 ? this.makeFirstMove() : this.makeNextMove();
            this.setChanges(coord[0], coord[1], this.isCompX);
        }
    }

    /**
     * The method makes changes to the playing field
     * and the graphical representation of the playing field.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @param sign - true - x, false - 0.
     */
    private void setChanges(int x, int y, boolean sign) {
        this.field.set(x, y, sign);
        this.display.setSign(x + 1, y + 1, sign);
        this.counter++;
    }

    /**
     * The computer makes a move.
     * @return coordinates.
     */
    private int[] makeFirstMove() {
        return this.field.getCell(size / 2, size / 2).getSign().equals(" ")
                ? new int[]{size / 2, size / 2} :  new int[]{size / 2 - 1, size / 2 - 1};
    }

    /**
     * The computer makes the next move.
     * @return coordinates.
     */
    private int[] makeNextMove() {
        Iterator<List<Cell>> it = this.user.iterator();
        Iterator<Sequence> itSeq = this.freeCells.iterator();
        Cell target;
        if (this.checkCompWinningCombinations() < this.winNum - 1 && it.hasNext()) {
            target = this.findTargetCell(it.next(), false);
        } else {
            Sequence seq = itSeq.next();
            Iterator<List<Cell>> iter = seq.getSequences().iterator();
            target = iter.hasNext() ? this.findTargetCell(iter.next(), true) : seq.getCell();
        }
        this.checkCombinations(target, this.user, true);
        return target.getCoord();
    }

    /**
     * The method checks the number of signs
     * in potentially winning combinations.
     * @return the number of signs.
     */
    private int checkCompWinningCombinations() {
        int result = 0;
        Iterator<Sequence> itSeq = this.freeCells.iterator();
        if (itSeq.hasNext()) {
            Sequence seq = itSeq.next();
            result = seq.getSequences().iterator().hasNext()
                    ? this.getMaxNumberOfSignsInSequence(seq.getSequences().iterator().next()) : 0;
        }
        return result;
    }

    /**
     * The method updates free cells with their sequences.
     */
    private void updateFreeCells() {
        Set<Sequence> tempSeq = new HashSet<>(this.freeCells);
        this.freeCells.clear();
        for (Sequence seq : tempSeq) {
            if (seq.getCell().getSign().equals(" ")) {
                this.freeCells.add(seq);
            }
        }
    }

    /**
     * The method selects the optimal cell for the computer to move.
     * @param comb the combination.
     * @param isComp - true - combinations of computer moves,
     * false - combinations of user moves.
     * @return the optimal cell for the computer to move.
     */
    private Cell findTargetCell(List<Cell> comb, boolean isComp) {
        List<int[]> sequences = new ArrayList<>();
        int sum = 0;
        int start = 0;
        int max = 0;
        int[] seq = new int[2];
        for (int i = 0; i < comb.size(); i++) {
            if (!comb.get(i).getSign().equals(" ")) {
                start = sum == 0 ? i : start;
                sum++;
            }
            if ((comb.get(i).getSign().equals(" ") && sum != 0) || (i == comb.size() - 1 && start != 0 && sum != 0)) {
                int end = i == comb.size() - 1 && !comb.get(i).getSign().equals(" ") ? i : i - 1;
                int[] current = new int[]{start, end};
                sequences.add(current);
                if (max < sum) {
                    max = sum;
                    seq = current;
                }
                start = 0;
                sum = 0;
            }
        }
        int index = sequences.indexOf(seq);
        Cell result = max == 0 ? comb.stream().filter(f -> f.getSign().equals(" ")).findFirst().get()
                : index > 0 || (sequences.size() == 1 && sequences.get(0)[0] > 0)
                ? comb.get(sequences.get(index)[0] - 1) : comb.get(sequences.get(index)[1] + 1);
        this.compWin = (isComp && this.checkLengthOfContinuousSequenceOfSigns(comb, result)) || this.compWin;
        return result;
    }

    /**
     * The method fills Set with Sequence objects.
     */
    private void fillEmpty() {
        IntStream.range(0, this.size).forEach(i -> IntStream.range(0, this.size).forEach(j ->
                this.freeCells.add(new Sequence(this.field.getCell(i, j),
                        this.getSequencesForCell(this.field.getCell(i, j), this::getStraight, this::getDiagonal)))));
    }

    /**
     * The method returns possible sequences for the cell.
     * @param cell - the cell.
     * @param str - a straight direction.
     * @param diag - a diagonal direction.
     * @return sequences.
     */
    private Set<List<Cell>> getSequencesForCell(Cell cell, BiFunction<Cell, Integer, List<Cell>> str,
                                                      BiFunction<Cell, Integer, List<Cell>> diag) {
        Set<List<Cell>> current = new TreeSet<>(new SequenceComparator());
        for (int i = -1; i <= 1; i += 2) {
            List<Cell> straight = str.apply(cell, i);
            if (straight.size() >= this.winNum) {
                current.add(straight);
            }
            List<Cell> diagonal = diag.apply(cell, i);
            if (diagonal.size() >= this.winNum) {
                current.add(diagonal);
            }
        }
        return current;
    }

    /**
     * The method returns the sequence in a straight direction.
     * @param cell - the cell.
     * @param index - the index of the direction.
     * @return the sequence.
     */
    private List<Cell> getStraight(Cell cell, int index) {
        List<Cell> empty = new ArrayList<>();
        int first = index > 0 ? cell.getCoord()[0] : cell.getCoord()[1];
        int second = index > 0 ? cell.getCoord()[1] : cell.getCoord()[0];
        int currInd = first - this.winNum + 1 > 0 ? first - this.winNum + 1 : 0;
        int endInd = first + this.winNum - 1 < this.size - 1 ? first + this.winNum - 1 : this.size - 1;
        IntStream.range(0, endInd - currInd + 1).forEach(n ->
                empty.add(this.field.getCell(index > 0 ? currInd + n : second, index > 0 ? second : currInd + n)));
        return empty;
    }

    /**
     * The method returns the sequence in a diagonal direction.
     * @param cell - the cell.
     * @param index - the index of the direction.
     * @return the sequence.
     */
    private List<Cell> getDiagonal(Cell cell, int index) {
        List<Cell> empty = new ArrayList<>();
        int[] range = this.getRange(cell.getCoord()[0], cell.getCoord()[1], index);
        IntStream.range(0, range[3] - range[1] + 1)
                .forEach(n -> empty.add(this.field.getCell(range[0] + n * index, range[1] + n)));
        return empty;
    }

    /**
     * The method returns the range of the sequence.
     * @param x - x-coordinate of the cell.
     * @param y - y-coordinate of the cell.
     * @param index - the index of the direction.
     * @return the range of the sequence.
     */
    private int[] getRange(int x, int y, int index) {
        int lower = (int) IntStream.range(1, this.winNum)
                .takeWhile(n -> x - n * index >= 0 && x - n * index < this.size && y - n >= 0).count();
        int firstRow = x - lower * index;
        int firstCol = y - lower;
        int upper = (int) IntStream.range(1, this.winNum)
                .takeWhile(n -> x + n * index >= 0 && x + n * index < this.size && y + n < this.size).count();
        int endRow = x + upper * index;
        int endCol = y + upper;
        return new int[]{firstRow, firstCol, endRow, endCol};
    }

    /**
     * The method checks Set freeCells
     * for the presence of foreign signs.
     * @param cell - the cell.
     */
    private void checkAllCells(Cell cell) {
        for (Sequence seq : this.freeCells) {
            this.checkCombinations(cell, seq.getSequences(), false);
        }
    }

    /**
     * The method checks sequences
     * for the presence of foreign signs.
     * @param cell - the cell.
     * @param sequences - the sequences.
     * @param isUser - true - user's combinations,
     * false - computer's combinations.
     */
    private void checkCombinations(Cell cell, Set<List<Cell>> sequences, boolean isUser) {
        Iterator<List<Cell>> it = sequences.iterator();
        Set<List<Cell>> temp = new HashSet<>();
        while (it.hasNext()) {
            List<Cell> seq = it.next();
            if (seq.contains(cell)) {
                List<Cell> current = this.checkSequence(cell, seq, isUser);
                it.remove();
                if (!current.isEmpty()) {
                    temp.add(current);
                }
            }
        }
        sequences.addAll(temp);
    }

    /**
     * The method checks the sequence.
     * @param last - the last move.
     * @param seq - the sequence.
     * @param isUser - true - user's combination,
     * false - computer's combination.
     * @return a sequence.
     */
    private List<Cell> checkSequence(Cell last, List<Cell> seq, boolean isUser) {
        int ind = seq.indexOf(last);
        List<Cell> result = ind < this.winNum ? seq.subList(ind + 1, seq.size()) : seq.subList(0, ind);
        return (result.size() < this.winNum) || (result.size() > this.winNum && isUser
                && result.stream().filter(f -> !f.getSign().equals(" ")).count() < 3) ? new ArrayList<>() : result;
    }

    /**
     * The method searches for potentially winning
     * combinations of the user in different directions.
     * @param last - the user's last move.
     * @param str a straight direction.
     * @param diag a diagonal direction.
     */
    private void searchUserCombinations(Cell last, BiFunction<Cell, Integer, List<Cell>> str,
                                                      BiFunction<Cell, Integer, List<Cell>> diag) {
        for (int i = -1; i <= 1; i += 2) {
            List<Cell> straight = str.apply(last, i);
            if (straight.size() >= this.winNum) {
                this.addUserCombinations(last, straight);
            }
            List<Cell> diagonal = diag.apply(last, i);
            if (diagonal.size() >= this.winNum) {
                this.addUserCombinations(last, diagonal);
            }
        }
    }

    /**
     * The method adds potentially winning combinations to the Set.
     * @param last - the user's last move.
     * @param seq - the sequence.
     */
    private void addUserCombinations(Cell last, List<Cell> seq) {
        String opponent = last.getSign().equals("X") ? "0" : "X";
        for (int i = 0; seq.size() >= this.winNum && i <= seq.size() - this.winNum; i++) {
            int count = 0;
            int sum = 0;
            for (int j = i; j < i + this.winNum; j++) {
                count = seq.get(j).getSign().equals(opponent) ? ++count : count;
                sum = seq.get(j).getSign().equals(last.getSign()) ? ++sum : sum;
            }
            if (count == 0 && sum >= this.checkDigit) {
                this.user.add(seq.subList(i, i + this.winNum));
                this.userWin = sum >= this.winNum || this.userWin;
            }
        }
    }

    /**
     * The method checks the length of a continuous
     * sequence of identical signs.
     * @param seq - the sequence.
     * @param cell - the cell.
     * @return true - the length of a continuous sequence of identical
     * signs is greater than or equal to the winning length,
     * false - the length of a continuous sequence of identical signs
     * is less than the winning length.
     */
    private boolean checkLengthOfContinuousSequenceOfSigns(List<Cell> seq, Cell cell) {
        boolean result = false;
        int sum = 0;
        for (int i = 0; i < seq.size(); i++) {
            if (!seq.get(i).getSign().equals(" ") || seq.get(i).equals(cell)) {
                sum++;
            }
            if (seq.get(i).getSign().equals(" ") || i == seq.size() - 1) {
                if (sum >= this.winNum) {
                    result = true;
                    break;
                }
                sum = 0;
            }
        }
        return result;
    }

    /**
     * The method returns the maximum number of signs
     * in a sequence with a length equal to the winning length.
     * @param seq - the sequence.
     * @return the maximum number of signs.
     */
    private int getMaxNumberOfSignsInSequence(List<Cell> seq) {
        int max = 0;
        if (seq.size() == this.winNum) {
            max = (int) seq.stream().filter(f -> !f.getSign().equals(" ")).count();
        } else {
            for (int i = 0; i <= seq.size() - this.winNum; i++) {
                int sum = (int) IntStream.range(i, i + winNum).filter(n -> !seq.get(n).getSign().equals(" ")).count();
                max = max < sum ? sum : max;
            }
        }
        return max;
    }

    /**
     * The method returns the number of signs in the sequence.
     * @param seq - the sequence.
     * @return the number of signs in the sequence.
     */
    private int getSum(List<Cell> seq) {
        return (int) seq.stream().filter(f -> !f.getSign().equals(" ")).count();
    }

    /**
     * Class SequenceComparator.
     */
    private class SequenceComparator implements Comparator<List<Cell>> {

        @Override
        public int compare(List<Cell> o1, List<Cell> o2) {
            int max1 = getMaxNumberOfSignsInSequence(o1);
            int max2 = getMaxNumberOfSignsInSequence(o2);
            return max1 - max2 != 0 ? max1 - max2 : getSum(o1) - getSum(o2) != 0
                    ? getSum(o1) - getSum(o2) : o1.size() - o2.size() != 0 ? o1.size() - o2.size()
                    : Arrays.compare(o1.get(0).getCoord(), o2.get(0).getCoord()) != 0
                    ? Arrays.compare(o1.get(0).getCoord(), o2.get(0).getCoord())
                    : Arrays.compare(o1.get(1).getCoord(), o2.get(1).getCoord()) != 0
                    ? Arrays.compare(o1.get(1).getCoord(), o2.get(1).getCoord())
                    : Arrays.compare(o1.get(2).getCoord(), o2.get(2).getCoord());
        }
    }

    /**
     * Class Sequence.
     */
    private class Sequence implements Comparable<Sequence> {

        /**
         * An empty cell.
         */
        private Cell cell;

        /**
         * The sequences available to move.
         */
        private Set<List<Cell>> sequences;

        public Sequence(Cell cell, Set<List<Cell>> sequences) {
            this.cell = cell;
            this.sequences = sequences;
        }

        /**
         * The method returns the cell.
         * @return the cell.
         */
        public Cell getCell() {
            return this.cell;
        }

        /**
         * The method returns the sequences.
         * @return the sequences.
         */
        public Set<List<Cell>> getSequences() {
            return this.sequences;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sequence sequence = (Sequence) o;
            return Objects.equals(cell, sequence.cell);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cell);
        }

        @Override
        public int compareTo(Sequence o) {
            int[] max1 = this.getMax(this.sequences);
            int[] max2 = this.getMax(o.getSequences());
            return max2[0] - max1[0] != 0 ? max2[0] - max1[0] : max2[1] - max1[1] != 0 ? max2[1] - max1[1]
                    : max2[2] - max1[2] != 0 ? max2[2] - max1[2] : max2[3] - max1[3] != 0 ? max2[3] - max1[3]
                    : this.cell.getCoord()[0] - o.getCell().getCoord()[0] != 0
                    ? this.cell.getCoord()[0] - o.getCell().getCoord()[0]
                    : this.cell.getCoord()[1] - o.getCell().getCoord()[1];
        }

        private int[] getMax(Set<List<Cell>> seq) {
            int[] numbers = new int[4];
            Iterator<List<Cell>> it = seq.iterator();
            if (it.hasNext()) {
                List<Cell> current = it.next();
                numbers[0] = getMaxNumberOfSignsInSequence(current);
                numbers[1] = getSum(current);
            }
            for (List<Cell> current : seq) {
                numbers[2] += getSum(current);
            }
            numbers[3] = seq.size();
            return numbers;
        }
    }
}